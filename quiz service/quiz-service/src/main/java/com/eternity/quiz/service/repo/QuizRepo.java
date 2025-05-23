package com.eternity.quiz.service.repo;

import com.eternity.quiz.service.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
