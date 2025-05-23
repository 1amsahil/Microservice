package com.example.Question.service;

import com.example.Question.Repo.QuestionRepo;
import com.example.Question.entity.Question;
import com.example.Question.entity.QuizQuestions;
import com.example.Question.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<?> getAllQuestions()
    {
        List<Question> questions = questionRepo.findAll();

       try
        {
            return new ResponseEntity<>(questions,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("NOT FOUND, Check URL",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> load(List<Question> questions)
    {
        try {
            questionRepo.saveAll(questions);
            return new ResponseEntity<>("Successful",HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Failed to Load",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getQuestionsByCategory(String category) {
        List<Question> questions = questionRepo.findByCategory(category);

        if(questions == null || questions.isEmpty())
        {
            return new ResponseEntity<>("Not Found "+category,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepo.save(question);
            return new ResponseEntity<>("Successful",HttpStatus.CREATED);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Add");
        }
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz( String category, String title)
    {
        List<Integer> question = questionRepo.findRandomQuestionByCategory(category);
        return new ResponseEntity<>(question,HttpStatus.OK);
    }

    public ResponseEntity<List<QuizQuestions>> getQuestionsFromId(List<Integer> questionIds) {

        List<Question> quesDB = new ArrayList<>();
        for(Integer id : questionIds)
        {
            quesDB.add(questionRepo.findById(id).get());
        }

        List<QuizQuestions> quizQuestions = new ArrayList<>();
        for(Question ques : quesDB)
        {
            quizQuestions
                    .add( new QuizQuestions(ques.getId(), ques.getQuestionTitle(), ques.getOption1(), ques.getOption2(),
                    ques.getOption3(), ques.getOption4()) );
        }

        return new ResponseEntity<>(quizQuestions,HttpStatus.FOUND);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right = 0;

        for(Response response : responses)
        {
            Optional<Question> ques = questionRepo.findById(response.getId());
            if(response.getResponse().equals(ques.get().getRightAnswer()))
            {
                right++;
            }
        }

        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
