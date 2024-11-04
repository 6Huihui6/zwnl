package com.zwnl.search.es;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zwnl.company.service.IJobsService;
import com.zwnl.company.service.IJobsSkillsService;
import com.zwnl.model.company.po.Jobs;
import com.zwnl.model.company.po.JobsDoc;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;


@SpringBootTest(properties = "spring.profiles.active=local")
public class TestEsDocument {

    private RestHighLevelClient client;
    @Autowired
    private IJobsService JobsService;

    @Test
    void testIndex() throws IOException {
        //1、在数据库中查询数据
        Jobs Jobs = JobsService.getById(49);
        JobsDoc JobsDoc = BeanUtil.copyProperties(Jobs, JobsDoc.class);

        //2、获取请求
        IndexRequest request = new IndexRequest("Jobs").id(JobsDoc.getJobId().toString());

        //3、准备请求参数
        request.source(JSONUtil.toJsonStr(JobsDoc),XContentType.JSON);

        //4、发送请求
        client.index(request,RequestOptions.DEFAULT);
    }

    @Test
    void testGet() throws IOException {
        //1、获取请求
        GetRequest request = new GetRequest("Jobss").id("317578");

        //2、发送请求
        GetResponse response = client.get(request, RequestOptions.DEFAULT);

        //3、解析结果
        String sourceAsString = response.getSourceAsString();
        JobsDoc JobsDoc = JSONUtil.toBean(sourceAsString, JobsDoc.class);
        System.out.println("JobsDoc = " + JobsDoc);
    }

    @Test
    void testDelete() throws IOException {
        //1、获取请求
        DeleteRequest request = new DeleteRequest("Jobss").id("317578");
        //2、发送请求
        client.delete(request,RequestOptions.DEFAULT);
    }

    @Test
    void testUpdate() throws IOException {
        //1、获取请求
        UpdateRequest request = new UpdateRequest("Jobss", "317578");

        request.doc(
                "price",8848
        );

        //2、发送请求
        client.update(request,RequestOptions.DEFAULT);
    }


    @Test
    void testBluk() throws IOException {
        int pageNo = 1;
        int pageSize = 500;

        while (true) {
            //1、准备文档数据
            Page<Jobs> page = JobsService.lambdaQuery().eq(Jobs::getJobId, 1)
                    .page(Page.of(pageNo, pageSize));

            List<Jobs> JobsList = page.getRecords();
            if (JobsList == null || JobsList.isEmpty()) {
                return;
            }
            //2、准备请求
            BulkRequest request = new BulkRequest();
            for (Jobs Jobs : JobsList) {
                request.add(new IndexRequest("Jobss").id(Jobs.getJobId().toString())
                        .source(JSONUtil.toJsonStr(BeanUtil.copyProperties(Jobs, JobsDoc.class)),XContentType.JSON));
            }
            //3、发送请求
            client.bulk(request,RequestOptions.DEFAULT);

            //4、翻页
            pageNo++;
        }
    }



    @BeforeEach
    void setUp() {
        client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://121.37.242.119:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        if (client != null) {
            client.close();
        }
    }
}