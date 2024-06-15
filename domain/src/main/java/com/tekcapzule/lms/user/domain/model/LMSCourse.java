package com.tekcapzule.lms.user.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tekcapzule.core.domain.AggregateRoot;
import com.tekcapzule.core.domain.BaseDomainEntity;
import lombok.*;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LMSCourse extends BaseDomainEntity implements AggregateRoot {
    private String courseId;
    private int watchedDuration;
    private String status;
    @DynamoDBAttribute(attributeName="modules")
    private List<Module> modules;
}

