package com.tekcapzule.lms.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CourseType {
    VIDEO("Video"),
    AUDIO("Audio"),
    PDF("Pdf");

    @Getter
    private String value;
}