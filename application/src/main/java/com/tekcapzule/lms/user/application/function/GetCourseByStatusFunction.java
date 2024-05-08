package com.tekcapzule.lms.user.application.function;

import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.lms.user.application.config.AppConfig;
import com.tekcapzule.lms.user.application.function.input.GetCourseByStatusInput;
import com.tekcapzule.lms.user.application.function.input.GetInput;
import com.tekcapzule.lms.user.domain.model.Course;
import com.tekcapzule.lms.user.domain.model.LmsUser;
import com.tekcapzule.lms.user.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetCourseByStatusFunction implements Function<Message<GetCourseByStatusInput>, Message<List<Course>>> {

    private final UserService userService;

    private final AppConfig appConfig;

    public GetCourseByStatusFunction(final UserService userService, final AppConfig appConfig) {
        this.userService = userService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<List<Course>> apply(Message<GetCourseByStatusInput> getInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        List<Course> courseList = null;
        String stage = appConfig.getStage().toUpperCase();
        try {
            GetCourseByStatusInput getCourseByStatusInput = getInputMessage.getPayload();
            log.info(String.format("Entering get user Function -  User Id:%s", getCourseByStatusInput.getUserId()));
            courseList = userService.getCourseByStatus(getCourseByStatusInput.getUserId(), getCourseByStatusInput.getTenantId(), getCourseByStatusInput.getStatus());
            Map<String, Object> responseHeader = new HashMap();
            if (courseList == null) {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.NOT_FOUND);
                courseList = new ArrayList<>();
            } else {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage(courseList, responseHeaders);
    }
}