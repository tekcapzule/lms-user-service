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
public class UpdateInput {
    private String userId;
    private String tenantId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private List<String> subscribedTopics;
    private String activeSince;
    private Status status;
    private int points;
    private String badges;
    private Address address;
    private List<Task> userActivity;
}
