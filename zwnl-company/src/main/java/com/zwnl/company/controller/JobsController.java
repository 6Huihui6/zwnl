package com.zwnl.company.controller;

import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.common.enums.AppHttpCodeEnum;
import com.zwnl.company.service.IJobsService;
import com.zwnl.model.company.VO.JobsVO;
import com.zwnl.model.company.dtos.JobsDTO;
import com.zwnl.model.company.po.Jobs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/list")
    @ApiOperation("查询所有职位信息")
    public ResponseResult<List<JobsVO>> getAllJobs() {
        return  ResponseResult.okResult(jobsService.getAllJobs());
    }


    @PostMapping
    @ApiOperation("保存职位信息,公司的详细信息可以不传，公司id必传")
    public ResponseResult saveJob(@RequestBody JobsDTO JobsDTO) {
        log.info("保存职位信息：{}", JobsDTO);
        jobsService.saveJob(JobsDTO);
        return ResponseResult.okResult(200, "保存成功");
    }

    @PostMapping("/delete")
    @ApiOperation("删除职位信息")
    public ResponseResult deleteJob(@RequestParam("jobId") Integer jobId  ) {
        jobsService.removeById(jobId);
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
    public ResponseResult<JobsVO> getJobById(@PathVariable("jobId") Integer jobId) {
        return jobsService.getJobById(jobId);
    }



}
