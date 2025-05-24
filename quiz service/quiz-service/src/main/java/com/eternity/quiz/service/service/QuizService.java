package com.eternity.quiz.service.service;

import com.eternity.quiz.service.feign.QuizInterface;
import com.eternity.quiz.service.repo.QuizRepo;
import com.eternity.quiz.service.entity.Quiz;
import com.eternity.quiz.service.entity.QuizQuestions;
import com.eternity.quiz.service.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<?> createQuiz( String category, String title ) {

        List<Integer> questions = quizInterface.getQuestionsForUser(category, title).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        quizRepo.save(quiz);
        return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuizQuestions>> getQuiz(int id)
    {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Integer> questionsIds = quiz.get().getQuestionIds();
        ResponseEntity<List<QuizQuestions>> questions = quizInterface.getQuestionsFromId(questionsIds);
        return questions;
    }

    public ResponseEntity<Integer> calculate(List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
