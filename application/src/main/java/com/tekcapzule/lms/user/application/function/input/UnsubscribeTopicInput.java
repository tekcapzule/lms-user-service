package com.tekcapzule.lms.user.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UnsubscribeTopicInput {
    private String userId;
    private String tenantId;
    private List<String> topicCodes;
}
