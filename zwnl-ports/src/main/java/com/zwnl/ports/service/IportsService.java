package com.zwnl.ports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.ports.dtos.*;
import com.zwnl.model.ports.po.Ports;

/**
 * 帖子Service接口
 * 
 * @author ruoyi
 * @date 2024-10-09
 */
public interface IportsService extends IService<Ports>
{
    /**
     * 查询帖子
     * 
     * @param portsId 帖子主键
     * @return 帖子
     */
    public ResponseResult selectPortsByPortsId(Long portsId);

    /**
     * 浏览帖子(浏览完后)
     *
     * @param dto 帖子主键
     * @return 帖子
     */
    public ResponseResult read(ReadBehaviorDto dto);

    /**
     * 查询帖子列表
     * 
     * @param pageDto 帖子
     * @return 帖子集合
     */
    public ResponseResult selectPortsList(PageDto pageDto);

    /**
     * 新增或修改帖子
     * 
     * @param portsDto 帖子
     * @return 结果
     */
    public ResponseResult addOrUpdatePorts(PortsDto portsDto);


    /**
     * 批量删除帖子
     * 
     * @param portsIds 需要删除的帖子主键集合
     * @return 结果
     */
    public ResponseResult deletePortsByPostIds(Long[] portsIds);

    /**
     * 点赞 or 取消点赞
     * @param dto
     * @return
     */
    ResponseResult like(BehaviorDto dto);

    /**
     * 收藏 or 取消收藏
     * @param dto
     * @return
     */
    ResponseResult collection(BehaviorDto dto);
}
