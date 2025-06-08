package com.SpringBootProject.LoginSignUp.service;


import com.SpringBootProject.LoginSignUp.entity.Question;
import com.SpringBootProject.LoginSignUp.repository.QuestionRepository;
import com.SpringBootProject.LoginSignUp.utils.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public ApiResponse<List<Question>> getRandomQuestionsByCourseWithRatio(String course, int totalCount) {
        int easyCount = (int) Math.round(totalCount * 0.5);
        int mediumCount = (int) Math.round(totalCount * 0.3);
        int hardCount = totalCount - easyCount - mediumCount;

        List<Question> easyQs = questionRepository.findRandomQuestionsByCourseAndDifficulty(course, "Easy", easyCount);
        List<Question> mediumQs = questionRepository.findRandomQuestionsByCourseAndDifficulty(course, "Medium", mediumCount);
        List<Question> hardQs = questionRepository.findRandomQuestionsByCourseAndDifficulty(course, "Hard", hardCount);

        List<Question> all = new ArrayList<>();
        all.addAll(easyQs);
        all.addAll(mediumQs);
        all.addAll(hardQs);

        Collections.shuffle(all);
        return ApiResponse.success(all, "Questions found");
    }
}
