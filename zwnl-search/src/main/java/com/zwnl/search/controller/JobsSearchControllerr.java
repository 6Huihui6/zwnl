package com.zwnl.search.controller;

import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.search.dtos.UserSearchDto;
import com.zwnl.search.service.IJobsSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@Api(tags = "搜索接口")
public class JobsSearchControllerr {

    private  final IJobsSearchService jobsSearchService;

    @ApiOperation(value = "搜索岗位信息", notes = "搜索岗位信息")
    @PostMapping("/search")
    public ResponseResult search(@RequestBody UserSearchDto dto ) throws IOException {
         return jobsSearchService.search(dto);
    }

}
