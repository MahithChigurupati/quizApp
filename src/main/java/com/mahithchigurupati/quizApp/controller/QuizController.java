package com.mahithchigurupati.quizApp.controller;

import com.mahithchigurupati.quizApp.model.Question;
import com.mahithchigurupati.quizApp.model.QuestionWrapper;
import com.mahithchigurupati.quizApp.model.Response;
import com.mahithchigurupati.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.String;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam String title, @RequestParam int numberOfQuestions){
        return quizService.createQuiz(category, title, numberOfQuestions);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
