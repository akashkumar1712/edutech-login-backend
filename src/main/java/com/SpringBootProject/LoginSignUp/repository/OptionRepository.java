package com.SpringBootProject.LoginSignUp.repository;

import com.SpringBootProject.LoginSignUp.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    // Find the correct option by questionId
    Optional<Option> findByQuestionIdAndCorrectTrue(Long questionId);
}
