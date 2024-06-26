package com.baeldung.demo.Controller;


import com.baeldung.demo.service.KeywordSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FindByKeywordController {

    @Autowired
    private KeywordSearchService keywordSearchService;

    @GetMapping("/search")
    public ResponseEntity<Object> searchByKeyword(@RequestParam String keyword) {
        keywordSearchService.findByKeyword(keyword);
        return ResponseEntity.ok().build();
    }
}