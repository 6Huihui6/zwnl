package com.zwnl.ports.service.impl;

import java.time.LocalDateTime;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwnl.common.constants.ports.PortsConstants;
import com.zwnl.common.domain.dto.PageResponseResult;
import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.common.enums.AppHttpCodeEnum;
import com.zwnl.common.redis.CacheService;
import com.zwnl.model.ports.dtos.*;
import com.zwnl.model.ports.po.Ports;
import com.zwnl.model.user.pos.Users;
import com.zwnl.utils.thread.AppThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zwnl.ports.mapper.portsMapper;
import com.zwnl.ports.service.IportsService;

/**
 * 帖子Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-09
 */
@Service
@Slf4j
public class portsServiceImpl extends ServiceImpl<portsMapper, Ports> implements IportsService
{
    @Autowired
    private portsMapper zwnlPortsMapper;
    @Autowired
    private CacheService cacheService;

    /**
     * 查询帖子
     *
     * @param portsId 帖子主键
     * @return 帖子
     */
    @Override
    public ResponseResult selectPortsByPortsId(Long portsId) {
        if (portsId == null) {
            return ResponseResult.errorResult(400,"参数错误");
        }
        Ports ports = getById(portsId);
        if (ports == null) {
            return ResponseResult.errorResult(400,"数据不存在");
        }
        return ResponseResult.okResult(ports);
    }

    /**
     * 点击帖子
     *
     * @param dto 帖子主键
     * @return 帖子
     */
    @Override
    public ResponseResult read(ReadBehaviorDto dto) {
        ResponseResult responseResult = selectPortsByPortsId(dto.getPortsId());
        if (responseResult.getCode() == 200) {
            Ports ports = (Ports) responseResult.getData();
            ports.setViews(ports.getViews() + 1);
            boolean update = updateById(ports);
            if (!update) {
                return ResponseResult.errorResult(400,"更新失败");
            }
            //1.检查参数
            if (dto == null || dto.getPortsId() == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
            }

            //2.是否登录
            Users user = AppThreadLocalUtil.getUser();
            if (user == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            }

            //3.更新阅读次数
            String readBehaviorJson = (String) cacheService.hGet(PortsConstants.PORTS_READ_BEHAVIOR + dto.getPortsId().toString(), user.getUserId().toString());
            if (StringUtils.isNotBlank(readBehaviorJson)) {
                ReadBehaviorDto readBehaviorDto = JSON.parseObject(readBehaviorJson, ReadBehaviorDto.class);
                dto.setCount((short) (readBehaviorDto.getCount() + dto.getCount()));
            }
            // 保存当前key
            log.info("保存当前key:{} {} {}", dto.getPortsId(), user.getUserId(), dto);
            cacheService.hPut(PortsConstants.PORTS_READ_BEHAVIOR + dto.getPortsId().toString(), user.getUserId().toString(), JSON.toJSONString(dto));

            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return responseResult;

    }

    /**
     * 查询帖子列表
     *
     * @param pageDto 帖子
     * @return 帖子集合
     */
    @Override
    public ResponseResult selectPortsList(PageDto pageDto) {
        //校验参数
        pageDto.checkParam();

        //分页查询
        IPage page = new Page(pageDto.getPage(), pageDto.getSize());
        LambdaQueryWrapper<Ports> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(pageDto.getChannel() != null, Ports::getChannel, pageDto.getChannel())
                .eq(Ports::getStatus, Ports.Status.PUBLISHED)
                .eq(Ports::getIsDeleted, Ports.Status.NORMAL)
                .orderByDesc(pageDto.getOrder() == 2,Ports::getHeat)
                .orderByAsc(pageDto.getOrder() == 1,Ports::getCreatedAt);

        page = page(page, lambdaQueryWrapper);

        ResponseResult pageResponseResult = new PageResponseResult((int) page.getCurrent(), (int) page.getSize(), (int) page.getTotal());
        pageResponseResult.setData(page.getRecords());

        return pageResponseResult;

    }

    /**
     * 新增或修改帖子
     *
     * @param portsDto 帖子
     * @return 结果
     */
    @Override
    public ResponseResult addOrUpdatePorts(PortsDto portsDto) {
        if (portsDto == null) {
            return ResponseResult.errorResult(400,"参数错误");
        }

        if (portsDto.getPortsId() == null) {
            Ports ports = new Ports();
            BeanUtil.copyProperties(portsDto, ports);

            ports.setUserId(AppThreadLocalUtil.getUser().getUserId());
            ports.setStatus(Ports.Status.PUBLISHED.getCode());
            ports.setCreatedAt(LocalDateTime.now());
            ports.setUpdatedAt(LocalDateTime.now());
            ports.setIsDeleted(0);

            boolean save = save(ports);

            if (!save) {
                return ResponseResult.errorResult(400,"帖子发布失败");
            }
        } else {
            Ports ports = new Ports();
            BeanUtil.copyProperties(portsDto, ports);
            boolean update = updateById(ports);

            if (!update) {
                return ResponseResult.errorResult(400,"帖子修改失败");
            }
        }

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);

    }

    /**
     * 批量删除帖子
     *
     * @param portsIds 需要删除的帖子主键集合
     * @return 结果
     */
    @Override
    public ResponseResult deletePortsByPostIds(Long[] portsIds) {
        for (Long portsId : portsIds) {
            Ports ports = getById(portsId);
            if (ports == null) {
                return ResponseResult.errorResult(400,"数据不存在");
            }
            ports.setIsDeleted(1);
            boolean update = updateById(ports);
            if (!update) {
                return ResponseResult.errorResult(400,"删除失败");
            }
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 点赞 or 取消点赞
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult like(BehaviorDto dto) {
        if (dto == null || dto.getPortsId() == null || chackParam(dto)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        Ports ports = getById(dto.getPortsId());
        if (ports == null) {
            return ResponseResult.errorResult(400,"数据不存在");
        }

        Integer UserId = AppThreadLocalUtil.getUser().getUserId();

        String key = PortsConstants.PORTS_LIKE_BEHAVIOR + dto.getPortsId().toString();

        if (dto.getOperation() == 1) {
            Object object = cacheService.hGet(key, UserId.toString());
            if (object != null) {
                return ResponseResult.okResult(200, "已点赞");
            }
            cacheService.hPut(key, UserId.toString(), JSON.toJSONString(dto));
            ports.setLike(ports.getLike() + 1);
            boolean update = updateById(ports);
            if (!update) {
                cacheService.hDelete(key, UserId.toString());
                return ResponseResult.errorResult(400,"点赞失败");
            }
        } else {
            cacheService.hDelete(key, UserId.toString());
            ports.setLike(ports.getLike() - 1);
            boolean update = updateById(ports);
            if (!update) {
                cacheService.hPut(key, UserId.toString(), JSON.toJSONString(dto));
                return ResponseResult.errorResult(400,"取消点赞失败");
            }
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 收藏 or 取消收藏
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult collection(BehaviorDto dto) {
        if (dto == null || dto.getPortsId() == null || chackParam(dto)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        Ports ports = getById(dto.getPortsId());
        if (ports == null) {
            return ResponseResult.errorResult(400,"数据不存在");
        }

        Integer UserId = AppThreadLocalUtil.getUser().getUserId();

        String key = PortsConstants.PORTS_COLL_BEHAVIOR + dto.getPortsId().toString();

        if (dto.getOperation() == 1) {
            Object object = cacheService.hGet(key, UserId.toString());
            if (object != null) {
                return ResponseResult.okResult(200, "已收藏");
            }
            cacheService.hPut(key, UserId.toString(), JSON.toJSONString(dto));
            ports.setLike(ports.getCollection() + 1);
            boolean update = updateById(ports);
            if (!update) {
                cacheService.hDelete(key, UserId.toString());
                return ResponseResult.errorResult(400,"收藏失败");
            }
        } else {
            cacheService.hDelete(key, UserId.toString());
            ports.setLike(ports.getCollection() - 1);
            boolean update = updateById(ports);
            if (!update) {
                cacheService.hPut(key, UserId.toString(), JSON.toJSONString(dto));
                return ResponseResult.errorResult(400,"取消收藏失败");
            }
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);

    }

    private boolean chackParam(BehaviorDto dto) {
        if (dto.getType() < 1 || dto.getType() > 3 || dto.getOperation() < 0 || dto.getOperation() > 1) {
            return true;
        }
        return false;
    }

}
