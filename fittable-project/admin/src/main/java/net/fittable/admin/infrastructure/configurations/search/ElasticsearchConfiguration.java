package net.fittable.admin.infrastructure.configurations.search;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfiguration {

    @Value("${elasticsearch.endpoint}")
    private String endpoint;

    @Value("${elasticsearch.port}")
    private String port;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(this.endpoint, Integer.parseInt(this.port), "http"))
        );
    }
}
