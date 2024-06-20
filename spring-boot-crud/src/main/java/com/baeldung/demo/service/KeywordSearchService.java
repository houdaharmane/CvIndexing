package com.baeldung.demo.service;


import com.baeldung.demo.model.Candidate;
import com.baeldung.demo.model.Resumes;
import com.baeldung.demo.repository.CandidateRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class KeywordSearchService {

    private static final String INDEX_NAME ="resumes";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private   CandidateRepository candidateRepository;

    public List<Candidate> findByKeyword(final String keyword){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("content", keyword))
                .build();



        SearchHits<Resumes> resumes = elasticsearchOperations.search(
                searchQuery,  Resumes.class,
                IndexCoordinates.of(INDEX_NAME));


        System.out.println(resumes.getSearchHits().size() +" result  = size ");

        List<String> fileNames = new ArrayList<>();
        resumes.forEach(hit -> {
            Resumes resume = hit.getContent();

            System.out.println("id: " + resume.getId());

            Map<String, Object> meta = resume.getMeta();
//            if (meta != null) {
//                System.out.println("Author: " + meta.get("author"));
//                System.out.println("Title: " + meta.get("title"));
//            } else {
//                System.out.println("Meta: null");
//            }

            Map<String, Object> file = resume.getFile();
            if (file != null) {
                fileNames.add((String) file.get("filename"));
            } else {
                System.out.println("File: null");
            }
        });


        fileNames.forEach(System.out::println);

        List<Candidate> candidates = candidateRepository.findCandidateByFileNameList(fileNames);
        return candidates;

        }



        // Execute the search query
//        List<Resumes> searchResults = elasticsearchOperations.queryForList(searchQuery, Resumes.class);



        // Process search results as needed
//        for (Resumes document : searchResults) {
//            System.out.println(document);
//        }



    }
