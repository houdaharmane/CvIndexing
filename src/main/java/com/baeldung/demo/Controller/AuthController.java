package com.baeldung.demo.Controller;

import com.baeldung.demo.model.Candidate;
import com.baeldung.demo.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Candidate candidate, Model model) {
        Candidate foundCandidate = candidateRepository.findByEmail(candidate.getEmail());
        if (foundCandidate != null && foundCandidate.getPassword().equals(candidate.getPassword())) {
            return "redirect:/candidateForm";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/candidateForm")
    public String showCandidateForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "CandidateForm";
    }
}
