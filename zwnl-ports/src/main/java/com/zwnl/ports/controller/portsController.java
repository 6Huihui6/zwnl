package com.zwnl.ports.controller;

import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.ports.dtos.*;
import com.zwnl.ports.service.IportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 帖子Controller
 *
 * @date 2024-10-09
 */
@RestController
@RequestMapping("/ports")
public class portsController
{
    @Autowired
    private IportsService portsService;

    /**
     * 查询帖子列表
     */
    @GetMapping("/list")
    public ResponseResult list(@RequestBody PageDto pageDto)
    {

        return portsService.selectPortsList(pageDto);
    }

    /**
     * 获取帖子详细信息
     */
    @GetMapping("/{portsId}")
    public ResponseResult getInfo(@PathVariable("portsId") Long portsId)
    {
        return portsService.selectPortsByPortsId(portsId);
    }

    /**
     * 浏览帖子(浏览完后)
     */
    @PostMapping("/view")
    public ResponseResult read(@RequestBody ReadBehaviorDto dto)
    {
        return portsService.read(dto);
    }

    /**
     * 新增或修改帖子
     */
    @PostMapping("/addOrUpdate")
    public ResponseResult addOrUpdate(@RequestBody PortsDto portsDto)
    {
        return portsService.addOrUpdatePorts(portsDto);
    }

    /**
     * 删除帖子
     */
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
    @PostMapping("/like")
    public ResponseResult like(@RequestBody BehaviorDto dto){
        return portsService.like(dto);
    }

    @PostMapping("/collection")
    public ResponseResult collection(@RequestBody BehaviorDto dto) {
        return portsService.collection(dto);
    }

}
