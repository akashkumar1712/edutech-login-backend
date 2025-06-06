package com.SpringBootProject.LoginSignUp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}

// LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 9.0/Uploads/expanded_options.csv'
//INTO TABLE options
//FIELDS TERMINATED BY ',' ENCLOSED BY '"'
//LINES TERMINATED BY '\n'
//IGNORE 1 ROWS
//(id, text, correct, question_id);



// LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 9.0/Uploads/expanded_questions.csv'
//INTO TABLE questions
//FIELDS TERMINATED BY ',' ENCLOSED BY '"'
//LINES TERMINATED BY '\n'
//IGNORE 1 ROWS
//(id, course, text, difficulty, created_at, updated_at);


//id, course, text, difficulty, created_at, updated_at