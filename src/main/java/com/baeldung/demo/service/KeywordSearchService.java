package com.baeldung.demo.service;


import com.baeldung.demo.model.Resumes;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class KeywordSearchService {

    private static final String INDEX_NAME ="resumes";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void findByKeyword(final String keyword){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("content", keyword))
                .build();



        SearchHits<Resumes> resumes = elasticsearchOperations.search(
                searchQuery,  Resumes.class,
                IndexCoordinates.of(INDEX_NAME));


        System.out.println(resumes.getSearchHits().size() +" result  = size ");

        resumes.forEach(hit -> {
            Resumes resume = hit.getContent();
            System.out.println("Content: " + resume.getContent());
            System.out.println("id: " + resume.getId());

            Map<String, Object> meta = resume.getMeta();
            if (meta != null) {
                System.out.println("Author: " + meta.get("author"));
                System.out.println("Title: " + meta.get("title"));
            } else {
                System.out.println("Meta: null");
            }

            Map<String, Object> file = resume.getFile();
            if (file != null) {
                System.out.println("File Extension: " + file.get("extension"));
            } else {
                System.out.println("File: null");
            }
        });




        }



        // Execute the search query
//        List<Resumes> searchResults = elasticsearchOperations.queryForList(searchQuery, Resumes.class);



        // Process search results as needed
//        for (Resumes document : searchResults) {
//            System.out.println(document);
//        }



    }
