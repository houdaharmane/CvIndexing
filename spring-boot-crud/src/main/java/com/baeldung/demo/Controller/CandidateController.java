package com.baeldung.demo.Controller;


import com.baeldung.demo.model.Candidate;
import com.baeldung.demo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;



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
    public String createCandidate(@ModelAttribute("candidate") Candidate candidate,
                                  @RequestParam("files") MultipartFile [] files) throws IOException {
        File uploadDirectory = new File(UPLOAD_DIR);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + File.separator + fileName);
            Files.write(filePath, bytes);
            fileNames.add(fileName);
        }

        candidate.setFileNameList(fileNames);

        candidateService.save(candidate);

        return "redirect:/chercher";
    }

    @GetMapping("/download/{file}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("file") String fileName) throws IOException {
        File file = new File(UPLOAD_DIR + File.separator + fileName);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(resource);
    }
    @GetMapping("/file-type")
    @ResponseBody
    public String getFileType(@RequestParam("fileName") String fileName) {
        String fileType = "unknown";
        if (fileName != null && !fileName.isEmpty()) {
            String extension = FilenameUtils.getExtension(fileName);
            if (extension != null) {
                switch (extension.toLowerCase()) {
                    case "pdf":
                        fileType = "pdf";
                        break;
                    case "docx":
                    case "doc":
                        fileType = "word";
                        break;
                    case "xlsx":
                }
            }
        }
        return fileType;
    }
}

