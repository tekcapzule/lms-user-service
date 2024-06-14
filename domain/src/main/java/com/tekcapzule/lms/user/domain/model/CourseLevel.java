package com.tekcapzule.lms.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CourseLevel {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Proficient"),
    MIXED("Mixed");

    @Getter
    private String value;
}