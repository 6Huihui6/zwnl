package com.zwnl.search.controller;

import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.search.dtos.UserSearchDto;
import com.zwnl.search.service.IAssociateWordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associate")
@Api(tags = "用户关联词接口")
public class AssociateWordsController {

    @Autowired
    private IAssociateWordsService associateWordsService;

    @ApiModelProperty(value = "用户关联词接口", notes = "根据用户搜索词获取关联词")
    @PostMapping("/search")
    public ResponseResult findAssociate(@RequestBody UserSearchDto userSearchDto) {
        return associateWordsService.findAssociate(userSearchDto);
    }
}
