package com.baeldung.demo.service;

import com.baeldung.demo.model.Candidate;
import com.baeldung.demo.model.Resumes;
import org.springframework.stereotype.Service;

@Service
public class CandidateMapping {

    public Candidate mapCandidate(Candidate candidate, Resumes resumes){
        if( candidate.getFileName().equals(resumes.getResumeDetails().getFileTitle())
                && candidate.getFileName().equals(resumes.getResumeDetails().getNameCandidate()))
            return candidate;
        return null;
    }
}
