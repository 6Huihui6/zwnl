package com.zwnl.ports.controller;

import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.ports.dtos.BehaviorDto;
import com.zwnl.model.ports.dtos.PageDto;
import com.zwnl.model.ports.dtos.PortsDto;
import com.zwnl.model.ports.dtos.ReadBehaviorDto;
import com.zwnl.ports.service.IportsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 帖子Controller
 *
 * @date 2024-10-09
 */
@RestController
@RequestMapping("/ports")
@Api(tags = "帖子管理")
public class portsController
{
    @Autowired
    private IportsService portsService;

    /**
     * 查询帖子列表
     */
    @ApiModelProperty(value = "帖子列表", notes = "根据条件分页查询帖子列表")
    @GetMapping("/list")
    public ResponseResult list(@RequestBody PageDto pageDto)
    {
        return portsService.selectPortsList(pageDto);
    }

    /**
     * 获取帖子详细信息
     */
    @ApiModelProperty(value = "帖子详细信息", notes = "根据帖子ID获取帖子详细信息")
    @GetMapping("/{portsId}")
    public ResponseResult getInfo(@PathVariable("portsId") Long portsId)
    {
        return portsService.selectPortsByPortsId(portsId);
    }

    /**
     * 浏览帖子(浏览完后)
     */
    @ApiModelProperty(value = "浏览帖子", notes = "浏览帖子(浏览完后)")
    @PostMapping("/view")
    public ResponseResult read(@RequestBody ReadBehaviorDto dto)
    {
        return portsService.read(dto);
    }

    /**
     * 新增或修改帖子
     */
    @ApiModelProperty(value = "新增或修改帖子", notes = "新增或修改帖子")
    @PostMapping("/addOrUpdate")
    public ResponseResult addOrUpdate(@RequestBody PortsDto portsDto)
    {
        return portsService.addOrUpdatePorts(portsDto);
    }

    /**
     * 删除帖子
     */
    @ApiModelProperty(value = "删除帖子", notes = "根据帖子ID删除帖子")
	@DeleteMapping("/{postIds}")
    public ResponseResult remove(@PathVariable Long[] portsIds)
    {
        return portsService.deletePortsByPostIds(portsIds);
    }

    /**
     * 点赞 or 取消点赞
     * @param dto
     * @return
     */
    @ApiModelProperty(value = "点赞 or 取消点赞", notes = "点赞 or 取消点赞")
    @PostMapping("/like")
    public ResponseResult like(@RequestBody BehaviorDto dto){
        return portsService.like(dto);
    }

    @ApiModelProperty(value = "收藏 or 取消收藏", notes = "收藏 or 取消收藏")
    @PostMapping("/collection")
    public ResponseResult collection(@RequestBody BehaviorDto dto) {
        return portsService.collection(dto);
    }

}
