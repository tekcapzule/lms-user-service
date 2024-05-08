package com.tekcapzule.lms.user.domain.service;

import com.tekcapzule.lms.user.domain.command.*;
import com.tekcapzule.lms.user.domain.model.Course;
import com.tekcapzule.lms.user.domain.model.LmsUser;

import java.util.List;


public interface UserService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);

    void optInCourse(OptInCourseCommand optInCourseCommand);

    void optOutCourse(OptOutCourseCommand optOutCourseCommand);

    void subscribeTopic(SubscribeTopicCommand subscribeTopicCommand);

    void unsubscribeTopic(UnSubscribeTopicCommand unSubscribeTopicCommand);

    LmsUser get(String userId, String tenantId);
    List<Course> getCourseByStatus(String userId, String tenantId, String status);

    int getAllUsersCount();
}
