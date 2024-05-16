package com.baeldung.demo.service;



import com.baeldung.demo.model.Resumes;
import groovy.util.logging.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;

import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class KeywordSearchService {

    private static final String INDEX_NAME ="resumes";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void findByKeyword(final String keyword){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("fileName", keyword))
                .build();

//
//
//        SearchHits<Resumes> products = elasticsearchOperations.search(
//                searchQuery,
//                Resumes.class,
//                IndexCoordinates.of(PRODUCT_INDEX_NAME));

        // Execute the search query
        List<Resumes> searchResults = elasticsearchOperations.queryForList(searchQuery, Resumes.class);

        System.out.println(searchResults.size() +" result  = size ");

        // Process search results as needed
        for (Resumes document : searchResults) {
            System.out.println(document);
        }



    }
}