package com.example.Question.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotEmpty(message = "Question title cannot be empty")
    @Column(nullable = false, unique = true)
    private String questionTitle;

    @NotEmpty(message = "Option 1 is required")
    @Column(nullable = false)
    private String option1;

    @NotEmpty(message = "Option 2 is required")
    @Column(nullable = false)
    private String option2;

    @NotEmpty(message = "Option 3 is required")
    @Column(nullable = false)
    private String option3;

    @NotEmpty(message = "Option 4 is required")
    @Column(nullable = false)
    private String option4;

    @NotEmpty(message = "Right answer is required")
    @Column(nullable = false)
    private String rightAnswer;

    @NotEmpty(message = "Difficulty level is required")
    @Column(nullable = false)
    private String difficultyLevel;

    @NotEmpty(message = "Category is required")
    @Column(nullable = false)
    private String category;
}