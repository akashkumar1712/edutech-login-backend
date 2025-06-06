package com.SpringBootProject.LoginSignUp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class ExamResultDTO {
    private int total;
    private int correct;
    private int incorrect;
    private int notAttempted;
    private double percentage;
    private int creditsEarned;

    public ExamResultDTO(int total, int correct, int incorrect, int notAttempted, double percentage, int creditsEarned) {
        this.total = total;
        this.correct = correct;
        this.incorrect = incorrect;
        this.notAttempted = notAttempted;
        this.percentage = percentage;
        this.creditsEarned = creditsEarned;
    }
    // getters/setters
}

