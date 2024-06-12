package com.tekcapzule.lms.user.domain.client;

import com.tekcapzule.lms.user.domain.model.LMSCourse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@Slf4j
public class CourseServiceClientImpl implements CourseServiceClient{

    @Override
    public LMSCourse getCourseByCourseId(String courseId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        //return restTemplate.exchange("apiUrl", HttpMethod.GET, entity, String.class).getBody();
        return null;
    }
}
