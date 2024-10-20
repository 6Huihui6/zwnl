package com.zwnl.search.service;


import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.search.dtos.UserSearchDto;

import java.io.IOException;

public interface IAssociateWordsService {


    /**
     * 根据用户搜索词获取相关联的词
     * @param userSearchDto
     * @return
     */
    ResponseResult findAssociate(UserSearchDto userSearchDto);
}
