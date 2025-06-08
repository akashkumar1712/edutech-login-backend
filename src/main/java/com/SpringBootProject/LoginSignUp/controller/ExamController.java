package com.SpringBootProject.LoginSignUp.controller;

import com.SpringBootProject.LoginSignUp.dto.request.AnswerRequest;
import com.SpringBootProject.LoginSignUp.dto.response.ExamResultDTO;
import com.SpringBootProject.LoginSignUp.entity.Question;
import com.SpringBootProject.LoginSignUp.service.ExamService;
import com.SpringBootProject.LoginSignUp.service.QuestionService;
import com.SpringBootProject.LoginSignUp.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "*")
public class ExamController {

    @Autowired
    private QuestionService questionService;

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitExam(@RequestBody AnswerRequest answerRequest) {
        if (answerRequest == null || answerRequest.getAnswers() == null || answerRequest.getAnswers().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "No answers provided"));
        }

        ExamResultDTO result = examService.evaluateAnswers(answerRequest.getAnswers(), answerRequest.getEmailId());

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("result", result);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{type}/{course}")
   // @GetMapping("/exams/{type}/{course}")
    public ApiResponse<List<Question>> getExamQuestions(@PathVariable String type, @PathVariable String course,
                                                       @RequestParam(name = "count", required = false, defaultValue = "50") int count) {

        return questionService.getRandomQuestionsByCourseWithRatio(course, count);
    }

}
