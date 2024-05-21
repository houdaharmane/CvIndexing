package com.baeldung.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SearchController {

    @GetMapping("/chercher")
    public String showSearchPage() {
        return "search";
    }
}