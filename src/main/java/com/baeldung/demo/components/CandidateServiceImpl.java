package com.baeldung.demo.components;

import com.baeldung.demo.model.Candidate;
import com.baeldung.demo.repository.CandidateRepository;
import com.baeldung.demo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl  implements CandidateService {


    @Autowired
    private CandidateRepository repository;

    public Candidate save(Candidate candidate) {
        return repository.save(candidate);
    }
    public List<Candidate> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Candidate> findByKeyword(String keyword) {
        return null;
    }

}

