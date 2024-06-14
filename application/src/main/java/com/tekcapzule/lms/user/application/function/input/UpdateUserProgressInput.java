package com.tekcapzule.lms.user.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.lms.user.domain.model.Address;
import com.tekcapzule.lms.user.domain.model.Enrollment;
import com.tekcapzule.lms.user.domain.model.Status;
import com.tekcapzule.lms.user.domain.model.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UpdateUserProgressInput {
    private String userId;
    private String tenantId;
    private String courseId;
    private String moduleId;
    private String chapterId;
    private double progressPercentage;
    private String lastAccessed;
    private double watchedDuration;
}
