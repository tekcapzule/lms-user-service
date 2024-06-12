package com.tekcapzule.lms.user.domain.client;

import com.tekcapzule.lms.user.domain.model.LMSCourse;

public interface CourseServiceClient {
    LMSCourse getCourseByCourseId(String courseId);
}
