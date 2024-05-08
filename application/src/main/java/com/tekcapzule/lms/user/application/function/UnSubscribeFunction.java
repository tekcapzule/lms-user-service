package com.tekcapzule.lms.user.application.function;

import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.lms.user.application.config.AppConfig;
import com.tekcapzule.lms.user.application.function.input.UnsubscribeTopicInput;
import com.tekcapzule.lms.user.application.mapper.InputOutputMapper;
import com.tekcapzule.lms.user.domain.command.UnSubscribeTopicCommand;
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
public class UnSubscribeFunction implements Function<Message<UnsubscribeTopicInput>, Message<Void>> {
    private final UserService userService;

    private final AppConfig appConfig;

    public UnSubscribeFunction(final UserService userService, final AppConfig appConfig) {
        this.userService = userService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<UnsubscribeTopicInput> unfollowTopicInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            UnsubscribeTopicInput unsubscribeTopicInput = unfollowTopicInputMessage.getPayload();
            log.info(String.format("Entering unfollow topic Function - User Id:%s, Topic Id:%s", unsubscribeTopicInput.getUserId(), unsubscribeTopicInput.getTopicCodes()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(unfollowTopicInputMessage.getHeaders());
            UnSubscribeTopicCommand unSubscribeTopicCommand = InputOutputMapper.buildUnfollowTopicCommandFromUnfollowTopicInput.apply(unsubscribeTopicInput, origin);
            userService.unsubscribeTopic(unSubscribeTopicCommand);
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            payload = PayloadUtil.composePayload(Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
            payload = PayloadUtil.composePayload(Outcome.ERROR);
        }
        return new GenericMessage(payload, responseHeaders);
    }
}
