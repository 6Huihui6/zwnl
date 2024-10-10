package com.zwnl.company.controller;

import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.company.service.IJobsService;
import com.zwnl.model.company.dtos.JobsDTO;
import com.zwnl.model.company.po.Jobs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 职位表 前端控制器
 * </p>
 *
 * @author hui
 * @since 2024-09-18
 */
@RestController
@Slf4j
@RequestMapping("/jobs")
@Api(tags = "职位接口管理")
@RequiredArgsConstructor
public class JobsController {

    private final IJobsService jobsService;

    @PostMapping
    @ApiOperation("保存职位信息")
    public ResponseResult saveJob(@RequestBody JobsDTO JobsDTO) {
        log.info("保存职位信息：{}", JobsDTO);
        jobsService.saveJob(JobsDTO);
        return ResponseResult.okResult(200, "保存成功");
    }

    @PostMapping("/delete")
    @ApiOperation("删除职位信息")
    public ResponseResult deleteJob(@RequestBody JobsDTO JobsDTO) {
        jobsService.removeById(JobsDTO.getJobId());
        return ResponseResult.okResult(200, "删除成功");
    }

    @PutMapping
    @ApiOperation("更新职位信息")
    public ResponseResult updateJob(@RequestBody JobsDTO JobsDTO) {
        jobsService.updateJob(JobsDTO);
        return ResponseResult.okResult(200, "更新成功");
    }
    @GetMapping("/{jobId}")
    @ApiOperation("查询职位信息")
    public ResponseResult getJobById(@PathVariable("jobId") Long jobId) {
        Jobs Jobs = jobsService.getById(jobId);
        return ResponseResult.okResult(Jobs);
    }



}
