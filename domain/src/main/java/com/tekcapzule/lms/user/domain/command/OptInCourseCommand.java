package com.tekcapzule.lms.user.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.Command;
import com.tekcapzule.lms.user.domain.model.Enrollment;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class OptInCourseCommand extends Command {
    private String userId;
    private String tenantId;
    private String courseId;
}
