package com.zwnl.search.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.common.enums.AppHttpCodeEnum;
import com.zwnl.common.utils.StringUtils;
import com.zwnl.model.company.dtos.JobsDTO;
import com.zwnl.model.company.po.JobsDoc;
import com.zwnl.model.search.dtos.UserSearchDto;
import com.zwnl.search.service.IJobsSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobsSearchServiceImpl implements IJobsSearchService {


    /**
     * 搜索职位
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult search(UserSearchDto dto) {
        //检查参数
        if (dto == null || StringUtils.isBlank(dto.getSearchWords())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        //异步调用保存用户搜索记录
//        saveSearchRecord(dto);
        //设置查询条件
        SearchRequest searchRequest = new SearchRequest("jobs");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //关键字的分词之后查询
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(dto.getSearchWords()).field("description").field("requirements").defaultOperator(Operator.OR);
        boolQueryBuilder.must(queryStringQueryBuilder);

        //分页查询
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(dto.getPageSize());

        //按照发布时间倒序查询
        searchSourceBuilder.sort("publishTime", SortOrder.DESC);


        return null;
    }
}
