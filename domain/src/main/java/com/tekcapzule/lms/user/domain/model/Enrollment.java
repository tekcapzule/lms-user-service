package com.tekcapzule.lms.user.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Enrollment {
    private String courseId;
    @DynamoDBAttribute(attributeName="enrollmentStatus")
    @DynamoDBTypeConvertedEnum
    private EnrollmentStatus enrollmentStatus;
    @DynamoDBAttribute(attributeName = "modules")
    private List<Module> modules;
}
