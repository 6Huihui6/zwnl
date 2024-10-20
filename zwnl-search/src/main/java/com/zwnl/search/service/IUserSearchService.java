package com.zwnl.search.service;

import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.company.dtos.JobsDTO;
import com.zwnl.model.search.dtos.HistorySearchDto;

import java.io.IOException;
import java.util.List;

public interface IUserSearchService {

    /**
     * 保存用户搜索记录
     * @param searchWords
     * @param userId
     */
    void insert(String searchWords, Integer userId);

    /**
     * 获取用户搜索记录
     * @return
     */
    ResponseResult findUserSearch();

    /**
     * 删除用户搜索记录 根据id
     * @param dto
     * @return
     */
    ResponseResult deleteUserSearch(HistorySearchDto dto);
}
