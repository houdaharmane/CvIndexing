package com.example.demo.controller;

import com.example.demo.service.CandidateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Candidate;

@Controller
@RequestMapping("/candidates")
public class CandidateController {


    private final CandidateService service;

    public CandidateController(CandidateService service) {
        this.service = service;
    }

    private final Candidate candidate = new Candidate();

    @GetMapping("/candidate")
    public String CandidateForm(Model model) {

        model.addAttribute("candidate", candidate);
        return "CandidateForm";

    }

    @PostMapping
    public String CreateCandidate(Model model, @ModelAttribute("candidate") Candidate candidate) {

        Candidate candidateSaved = service.save(candidate);
        model.addAttribute("candidate", candidateSaved);
        return "ListCandidate";
    }

}