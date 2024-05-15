package com.baeldung.demo.Controller;


import com.baeldung.demo.model.Candidate;
import com.baeldung.demo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/candidates")
public class CandidateController {


    @Autowired
    private CandidateService candidateService;

    private static final String UPLOAD_DIR = "C:\\Users\\hp\\.fscrawler\\resumes";

    public CandidateController(CandidateService service) {
        this.candidateService = service;
    }

    private final Candidate candidate = new Candidate();

    @GetMapping("/candidate")
    public String CandidateForm(Model model) {

        model.addAttribute("candidate", candidate);
        return "CandidateForm";

    }

    @PostMapping
    public String CreateCandidate(Model model, @ModelAttribute("candidate") Candidate candidate,@RequestParam("file") MultipartFile file ) throws IOException {

        File uploadDirectory = new File(UPLOAD_DIR);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        byte[] bytes = file.getBytes();
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + File.separator + fileName);

        Files.write(filePath, bytes);

        candidate.setFileName(fileName.toString());

        candidateService.save(candidate);

        List<Candidate> listCandidate = candidateService.findAll();

        listCandidate.forEach(candidate1 -> System.out.println(candidate1.getId()));

        model.addAttribute("candidates", listCandidate);
        return "ListCandidate";
    }

}