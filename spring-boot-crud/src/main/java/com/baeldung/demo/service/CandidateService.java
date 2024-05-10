package com.baeldung.demo.service;

import com.baeldung.demo.model.Candidate;

import java.util.List;

public interface CandidateService {

    Candidate save(Candidate candidate);

    List<Candidate> findAll();


}
