package com.example.Question.Repo;


import com.example.Question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>
{
    List<Question> findByCategory(String category);

    @Query("SELECT q.id FROM Question q WHERE q.category=:category")
    List<Integer> findRandomQuestionByCategory(String category);
}
