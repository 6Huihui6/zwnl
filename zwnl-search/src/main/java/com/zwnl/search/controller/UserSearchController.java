package com.zwnl.search.controller;

import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.search.dtos.HistorySearchDto;
import com.zwnl.search.service.IUserSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@Api(tags = "用户搜索接口")
public class UserSearchController {

    private final IUserSearchService userSearchService;

    @ApiOperation(value = "获取用户搜索记录接口")
    @PostMapping("/load")
    public ResponseResult findUserSearch() {
        return userSearchService.findUserSearch();
    }

    @ApiOperation(value = "删除用户搜索记录接口")
    @PostMapping("/delete")
    public ResponseResult deleteUserSearch(@RequestBody HistorySearchDto dto) {
        return userSearchService.deleteUserSearch(dto);
    }
}
