package com.zwnl.search.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.zwnl.model.company.dtos.JobsDTO;
import com.zwnl.model.company.po.JobsDoc;
import com.zwnl.search.service.ISearchService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl  implements ISearchService {


    private final RestHighLevelClient client;

    /**
     * 同步职位信息
     *
     * @param jobsDTO
     */
    @Async
    @Override
    public void syncJobs(JobsDTO jobsDTO) throws IOException {
        //1、转换对象
        JobsDoc jobsDoc = BeanUtil.copyProperties(jobsDTO, JobsDoc.class);

        //2、获取请求
        IndexRequest request = new IndexRequest("jobs").id(String.valueOf(jobsDoc.getJobId()));

        //3、准备请求参数
        request.source(JSONUtil.toJsonStr(jobsDoc), XContentType.JSON);

        //4、发送请求
            client.index(request, RequestOptions.DEFAULT);
    }

    /**
     * 同步职位变更信息
     *
     * @param jobsDTO
     */
    @Async
    @Override
    public void syncChangeJobs(JobsDTO jobsDTO) throws IOException {
        //1、转换对象
        JobsDoc jobsDoc = BeanUtil.copyProperties(jobsDTO, JobsDoc.class);

        //2、获取请求
        UpdateRequest request = new UpdateRequest("jobs", String.valueOf(jobsDoc.getJobId()));

        //3、准备请求参数
        request.doc(JSONUtil.toJsonStr(jobsDoc), XContentType.JSON);

        //4、发送请求
            client.update(request, RequestOptions.DEFAULT);
    }

    /**
     * 同步职位列表变更信息
     *
     * @param jobsDTOList
     */
    @Override
    public void jobsListChange(List<JobsDTO> jobsDTOList) throws IOException {
        if (jobsDTOList == null || jobsDTOList.isEmpty()) {
            return;
        }
        //1、准备请求
        //2、准备请求
        BulkRequest request = new BulkRequest();
        for (JobsDTO jobsDTO : jobsDTOList) {
            request.add(new IndexRequest("Jobs").id(jobsDTO.getJobId().toString())
                    .source(JSONUtil.toJsonStr(BeanUtil.copyProperties(jobsDTO, JobsDoc.class)),XContentType.JSON));
        }
        //3、发送请求
        client.bulk(request,RequestOptions.DEFAULT);
    }

}
