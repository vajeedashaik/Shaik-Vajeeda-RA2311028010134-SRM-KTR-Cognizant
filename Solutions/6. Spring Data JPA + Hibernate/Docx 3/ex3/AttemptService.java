package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.repository.AttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    // Docx 3 - Hands on 3: full attempt detail (questions, all options, and the ones the user selected)
    @Transactional
    public Attempt getAttemptDetail(int userId, int attemptId) {
        return attemptRepository.getAttempt(userId, attemptId);
    }
}
