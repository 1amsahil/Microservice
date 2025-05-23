package com.eternity.quiz.service.controller;

import com.eternity.quiz.service.entity.QuizDTO;
import com.eternity.quiz.service.entity.Response;
import com.eternity.quiz.service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestBody QuizDTO quizDTO)
    {
        return quizService.createQuiz(quizDTO.getCategory(),quizDTO.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable int id)
    {
        return quizService.getQuiz(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses )
    {
        return quizService.calculate(id, responses);
    }

}
