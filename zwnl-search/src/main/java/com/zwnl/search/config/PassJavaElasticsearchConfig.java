package com.zwnl.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PassJavaElasticsearchConfig {

@Bean
// 给容器注册一个 RestHighLevelClient，用来操作 ES
// 参考官方文档：https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.9/java-rest-high-getting-started-initialization.html
public RestHighLevelClient restHighLevelClient() {
return new RestHighLevelClient(RestClient.builder(
        HttpHost.create("http://121.37.194.170:9200")
    ));
}
}