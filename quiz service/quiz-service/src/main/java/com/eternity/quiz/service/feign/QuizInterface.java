package com.eternity.quiz.service.feign;

import com.eternity.quiz.service.entity.QuizQuestions;
import com.eternity.quiz.service.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForUser(@RequestParam String category,@RequestParam String title);

    @PostMapping("/question/get/questions")
    public ResponseEntity<List<QuizQuestions>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("/question/get/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses );
}
