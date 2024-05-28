package com.baeldung.demo.Controller;

import com.baeldung.demo.model.Candidate;
import com.baeldung.demo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/chercher")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<Candidate> candidates = candidateService.findByKeyword(keyword);
        model.addAttribute("candidates", candidates);
        return "search";
    }
    @PostMapping("/createCandidate")
    public String createCandidate(@RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName,
                                  @RequestParam("files") MultipartFile[] files) {
        CandidateService.saveCandidate( firstName, lastName, files );

        return "redirect:/chercher";
    }
}