package com.zwnl.search.service;


import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.search.dtos.UserSearchDto;

public interface IJobsSearchService {

    /**
     * 搜索职位
     * @param dto
     * @return
     */
    ResponseResult search(UserSearchDto dto);
}
