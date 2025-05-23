package com.example.Question.controller;


import com.example.Question.entity.Question;
import com.example.Question.entity.QuizQuestions;
import com.example.Question.entity.Response;
import com.example.Question.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<?> allQuestion()
    {
        return questionService.getAllQuestions();
    }

    @PostMapping("/load")
    public ResponseEntity<String> load(@Valid @RequestBody List<Question> questions)
    {
        return questionService.load(questions);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<?> getQuestionByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@Valid @RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForUser(@RequestParam String category, @RequestParam String title)
    {
        return questionService.getQuestionsForQuiz(category,title);
    }

    @PostMapping("/get/questions")
    public ResponseEntity<List<QuizQuestions>> getQuestionsFromId(@RequestBody List<Integer> questionIds)
    {
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("/get/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses )
    {
        return questionService.getScore(responses);
    }
}
