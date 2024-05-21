package com.baeldung.demo.Controller;

import com.baeldung.demo.model.Candidate;
import com.baeldung.demo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/chercher")
    public String showSearchPage(Model model) {
        List<Candidate> candidates = candidateService.findAll();
        model.addAttribute("candidates", candidates);
        return "search";
    }
}
