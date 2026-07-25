package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    // Docx 3 - Hands on 3: fetch full quiz attempt details via HQL
    // join order: user -> attempt -> attempt_question -> question -> attempt_option -> options
    // DISTINCT avoids NonUniqueResultException from the row multiplication caused by fetching multiple collections
    @Query("SELECT DISTINCT a FROM Attempt a " +
           "JOIN FETCH a.user u " +
           "JOIN FETCH a.attemptQuestions aq " +
           "JOIN FETCH aq.question q " +
           "LEFT JOIN FETCH q.options " +
           "LEFT JOIN FETCH aq.attemptOptions ao " +
           "LEFT JOIN FETCH ao.option " +
           "WHERE u.id = :userId AND a.id = :attemptId")
    Attempt getAttempt(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
