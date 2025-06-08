package com.SpringBootProject.LoginSignUp.repository;

import com.SpringBootProject.LoginSignUp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM questions WHERE course = :course AND difficulty = :difficulty ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Question> findRandomQuestionsByCourseAndDifficulty(@Param("course") String course,
                                                            @Param("difficulty") String difficulty,
                                                            @Param("limit") int limit);
}
