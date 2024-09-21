package com.zwnl.company.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ElasticTest
{
private RestHighLevelClient client;

    @Test
    void TestConnection() {
        System.out.println("client= "+client);
    }

    @BeforeEach
    void setUp() {
        client=new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://121.37.194.170:9200")
        ));
    }

    @AfterEach
    void tearDown() {
        if (client!= null){
            try {
                client.close();
            } catch (IOException e) {
            }
        }
    }
}
