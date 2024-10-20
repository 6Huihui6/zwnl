package com.zwnl.search.controller;

import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.search.dtos.UserSearchDto;
import com.zwnl.search.service.IJobsSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobsSearchControllerr {

    private  final IJobsSearchService jobsSearchService;

    @PostMapping("/search")
    public ResponseResult search(@RequestBody UserSearchDto dto ){
         return jobsSearchService.search(dto);
    }

}
