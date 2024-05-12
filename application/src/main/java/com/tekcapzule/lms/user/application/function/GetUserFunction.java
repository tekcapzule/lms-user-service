package com.tekcapzule.lms.user.application.function;

import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.lms.user.application.function.input.GetInput;
import com.tekcapzule.lms.user.application.config.AppConfig;
import com.tekcapzule.lms.user.domain.model.LmsUser;
import com.tekcapzule.lms.user.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetUserFunction implements Function<Message<GetInput>, Message<LmsUser>> {

    private final UserService userService;

    private final AppConfig appConfig;

    public GetUserFunction(final UserService userService, final AppConfig appConfig) {
        this.userService = userService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<LmsUser> apply(Message<GetInput> getInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        LmsUser lmsUser = new LmsUser();
        String stage = appConfig.getStage().toUpperCase();
        try {
            GetInput getInput = getInputMessage.getPayload();
            log.info(String.format("Entering get user Function -  User Id:%s", getInput.getUserId()));
            lmsUser = userService.get(getInput.getUserId(), getInput.getTenantId());
            Map<String, Object> responseHeader = new HashMap();
            if (lmsUser == null) {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.NOT_FOUND);
                lmsUser = LmsUser.builder().build();
            } else {
                responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage(lmsUser, responseHeaders);
    }
}