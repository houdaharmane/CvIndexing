package com.baeldung.demo.service;


import com.baeldung.demo.model.Candidate;
import groovy.util.logging.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import org.springframework.stereotype.Service;




@Service
@Slf4j
public class KeywordSearchService {

    private static final String INDEX_NAME ="resumes";

    @Autowired

    private ElasticsearchOperations elasticsearchOperations;

    public void findByKeyword(final String keyword,float boost){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("fileName", keyword).boost(boost))
                .build();

    }
}