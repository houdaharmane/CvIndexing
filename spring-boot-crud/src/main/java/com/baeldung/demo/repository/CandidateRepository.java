package com.baeldung.demo.repository;

import com.baeldung.demo.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    Candidate findByEmail(String email);

    @Query("from Candidate where fileNames in (:filenames)")
    List<Candidate> findCandidateByFileNameList(List<String> filenames);

}
