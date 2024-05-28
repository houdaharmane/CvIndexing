
package com.baeldung.demo.service;

import com.baeldung.demo.model.Candidate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateService {

    static void saveCandidate(String firstName, String lastName, MultipartFile[] files) {
    }

    Candidate save(Candidate candidate);

    List<Candidate> findAll();


    List<Candidate> findByKeyword(String keyword);
}
