package com.zwnl.search.service.impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.common.enums.AppHttpCodeEnum;
import com.zwnl.common.utils.StringUtils;
import com.zwnl.model.search.dtos.HistorySearchDto;
import com.zwnl.model.search.dtos.UserSearchDto;
import com.zwnl.model.search.po.UserSearch;
import com.zwnl.model.user.pos.Users;
import com.zwnl.search.service.IJobsSearchService;
import com.zwnl.search.service.IUserSearchService;
import com.zwnl.utils.thread.AppThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSearchServiceImpl implements IUserSearchService {

    private final MongoTemplate mongoTemplate;


    /**
     * 保存用户搜索记录
     *
     * @param searchWords
     * @param userId
     */
    @Override
    @Async
    public void insert(String searchWords, Integer userId) {
        log.info("insert searchWords:{},userId:{}", searchWords, userId);
        //1，查询是否存在相同记录
        Query query = Query.query(Criteria.where("userId").is(userId).and("searchWords").is(searchWords));
        UserSearch userSearch = mongoTemplate.findOne(query, UserSearch.class);
        if (userSearch != null) {
            //更新
            userSearch.setCreatedTime(new DateTime());
            mongoTemplate.save(userSearch);
            return;
        }
        //2，不存在则插入，存在则更新
        userSearch = new UserSearch();
        userSearch.setUserId(userId);
        userSearch.setKeyword(searchWords);
        userSearch.setCreatedTime(new DateTime());
        //3，保存搜索记录
        Query query1 = Query.query(Criteria.where("userId").is(userId));
        query1.with(Sort.by(Sort.Direction.DESC, "createdTime"));
        List<UserSearch> userSearchList = mongoTemplate.find(query1, UserSearch.class);
        if (userSearchList == null || userSearchList.size() < 10) {
            mongoTemplate.save(userSearch);
        } else {
            UserSearch userSearch1 = userSearchList.get(userSearchList.size() - 1);
            mongoTemplate.findAndReplace(Query.query(Criteria.where("_id").is(userSearch1.getId())), userSearch);
        }

    }

    /**
     * 获取用户搜索记录
     *
     * @return
     */
    @Override
    public ResponseResult findUserSearch() {
        //获取当前用户
        Users user = AppThreadLocalUtil.getUser();
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        //根据用户查询数据，按照时间倒序
        List<UserSearch> apUserSearches = mongoTemplate.find(Query.query(Criteria.where("userId").is(user.getUserId())).with(Sort.by(Sort.Direction.DESC, "createdTime")), UserSearch.class);
        return ResponseResult.okResult(apUserSearches);
    }

    /**
     * 删除用户搜索记录 根据id
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult deleteUserSearch(HistorySearchDto dto) {
        //1.检查参数
        if(dto.getId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.判断是否登录
        Users user = AppThreadLocalUtil.getUser();
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        //3.删除
        mongoTemplate.remove(Query.query(Criteria.where("userId").is(user.getUserId()).and("id").is(dto.getId())),UserSearch.class);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}
