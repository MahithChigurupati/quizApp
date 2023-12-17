package com.mahithchigurupati.quizApp.service;

import com.mahithchigurupati.quizApp.dao.QuestionDao;
import com.mahithchigurupati.quizApp.dao.QuizDao;
import com.mahithchigurupati.quizApp.model.Question;
import com.mahithchigurupati.quizApp.model.QuestionWrapper;
import com.mahithchigurupati.quizApp.model.Quiz;
import com.mahithchigurupati.quizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, String title, int numberOfQuestions){

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numberOfQuestions);

        try{
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return new ResponseEntity<>("Success", HttpStatusCode.valueOf(201));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatusCode.valueOf(400));
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question question : questionsFromDB) {
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionsForUser.add(questionWrapper);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatusCode.valueOf(200));

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questionsFromDB = quiz.getQuestions();
        int score = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questionsFromDB.get(i).getRightAnswer())) {
                score++;
            }

            i++;
        }
        return new ResponseEntity<>( score, HttpStatusCode.valueOf(200));
    }
}
