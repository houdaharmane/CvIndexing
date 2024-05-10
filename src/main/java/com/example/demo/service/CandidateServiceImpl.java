package com.example.demo.service;

import com.example.demo.repository.CandidateRepository;
import com.example.demo.model.Candidate;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {


    private final CandidateRepository repository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.repository = candidateRepository;
    }

    @Override
    public Candidate save(Candidate candidate) {
        return repository.save(candidate);
    }
}
