package com.mahithchigurupati.quizApp.dao;

import com.mahithchigurupati.quizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {


}
