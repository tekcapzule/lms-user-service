package com.tekcapzule.lms.user.domain.command;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.Command;
import com.tekcapzule.lms.user.domain.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UpdateCommand extends Command {
    private String userId;
    private String tenantId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private List<String> subscribedTopics;
    private Map<CourseStatus, List<Course>> courses;
    private String activeSince;
    private Status status;
    private int points;
    private String badges;
    private Address address;
    private List<Task> userActivity;
}
