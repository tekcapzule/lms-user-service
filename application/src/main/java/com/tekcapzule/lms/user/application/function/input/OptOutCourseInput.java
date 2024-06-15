package com.tekcapzule.lms.user.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.lms.user.domain.model.Enrollment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class OptOutCourseInput {
    private String userId;
    private String tenantId;
    private String courseId;
}