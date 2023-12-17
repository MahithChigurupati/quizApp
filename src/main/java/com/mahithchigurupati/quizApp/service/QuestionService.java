package com.mahithchigurupati.quizApp.service;

import com.mahithchigurupati.quizApp.model.Question;
import com.mahithchigurupati.quizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatusCode.valueOf(200));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatusCode.valueOf(400));

    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category){
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatusCode.valueOf(200));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatusCode.valueOf(400));
    }

    public ResponseEntity<String> addQuestion(Question question){
        try{
            questionDao.save(question);
            return new ResponseEntity<>("Success", HttpStatusCode.valueOf(201));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatusCode.valueOf(400));
    }
}
