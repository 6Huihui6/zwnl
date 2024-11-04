package com.zwnl.search.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class TestEsIndexs {

    private RestHighLevelClient client;


    @Test
    void testCreateIndex() throws IOException {
        //1、准备请求对象
        CreateIndexRequest request = new CreateIndexRequest("jobs");
        //2、准备参数
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        //3、发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    @Test
    void testGetIndex() throws IOException {
        //1、准备请求对象
        GetIndexRequest request = new GetIndexRequest("jobs");
        //3、发送请求
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

        System.out.println("exists = " + exists);
    }

    @Test
    void testDeleteIndex() throws IOException {
        //1、准备请求对象
        DeleteIndexRequest request = new DeleteIndexRequest("jobs");
        //3、发送请求
        client.indices().delete(request, RequestOptions.DEFAULT);
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


    private static final String MAPPING_TEMPLATE = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"jobId\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"title\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"description\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"requirements\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"location\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"salary\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"education\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"companyId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"userId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"industry\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"address\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"contactName\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"contactEmail\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"contactPhone\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"cdescription\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"image\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"isOnline\":{\n" +
            "        \"type\": \"boolean\"\n" +
            "      },\n" +
            "      \"skills\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}