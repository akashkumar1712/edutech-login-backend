package com.SpringBootProject.LoginSignUp.service;

import com.SpringBootProject.LoginSignUp.dto.response.AnswerDTO;
import com.SpringBootProject.LoginSignUp.dto.response.ExamResultDTO;
import com.SpringBootProject.LoginSignUp.entity.Option;
import com.SpringBootProject.LoginSignUp.repository.OptionRepository;
import com.SpringBootProject.LoginSignUp.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    private final OptionRepository optionRepository;

    @Autowired
    UsersRepo usersRepo;

    public ExamService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public ExamResultDTO evaluateAnswers(List<AnswerDTO> answers, String emailId) {
        int total = answers.size();
        int correct = 0;
        int incorrect = 0;
        int notAttempted = 0;

        for (AnswerDTO answer : answers) {
            if (answer.getAnswerOptionId() == null) {
                notAttempted++;
                continue;
            }

            // Get correct option id from DB for this question
            Optional<Option> correctOptionOpt = optionRepository.findByQuestionIdAndCorrectTrue(answer.getQuestionId());

            if (correctOptionOpt.isEmpty()) {
                // No correct option found for question? Treat as incorrect or log error
                incorrect++;
                continue;
            }

            Option correctOption = correctOptionOpt.get();

            if (answer.getAnswerOptionId().equals(correctOption.getId())) {
                correct++;
            } else {
                incorrect++;
            }
        }


        double percentage = total > 0 ? (correct * 100.0) / total : 0;
        int creditsEarned = 0;

        if (percentage >= 6) {
            // Update user's credits by 1 (you need a method to do this)
            usersRepo.incrementCreditsByUserId(emailId);
            creditsEarned = 2;
        }

        return new ExamResultDTO(total, correct, incorrect, notAttempted, percentage, creditsEarned);
    }
}
