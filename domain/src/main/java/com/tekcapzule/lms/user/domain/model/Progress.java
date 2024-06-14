package com.tekcapzule.lms.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Progress {
    private String courseId;
    private String moduleId;
    private String chapterId;
    private double progressPercentage;
    private String lastAccessed;
    private double watchedDuration;
}
