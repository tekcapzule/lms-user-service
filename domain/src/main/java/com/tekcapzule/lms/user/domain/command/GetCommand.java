package com.tekcapzule.lms.user.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class GetCommand {
    private String userId;
    private String tenantId;
}
