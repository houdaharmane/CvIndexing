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


@Service
public class KeywordSearchService {

    private static final String INDEX_NAME ="resumes";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public void findByKeyword(final String keyword){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("fileName", keyword))
                .build();



        SearchHits<Resumes> resumes = elasticsearchOperations.search(
                searchQuery,  Resumes.class,
                IndexCoordinates.of(INDEX_NAME));


        System.out.println(resumes.getSearchHits().size() +" result  = size ");

        resumes.getSearchHits().forEach(searchHit->{
            System.out.println((searchHit.getContent().getContent()));
        });


        // Execute the search query
//        List<Resumes> searchResults = elasticsearchOperations.queryForList(searchQuery, Resumes.class);



        // Process search results as needed
//        for (Resumes document : searchResults) {
//            System.out.println(document);
//        }



    }
}