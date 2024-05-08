package com.tekcapzule.lms.user.domain.service;

import com.tekcapzule.lms.user.domain.command.*;
import com.tekcapzule.lms.user.domain.model.Address;
import com.tekcapzule.lms.user.domain.model.Course;
import com.tekcapzule.lms.user.domain.model.LmsUser;
import com.tekcapzule.lms.user.domain.model.Status;
import com.tekcapzule.lms.user.domain.repository.UserDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private static final String HASH = "#";
    private UserDynamoRepository userDynamoRepository;

    @Autowired
    public UserServiceImpl(UserDynamoRepository userDynamoRepository) {
        this.userDynamoRepository = userDynamoRepository;
    }

    @Override
    public void create(CreateCommand createCommand) {

        log.info(String.format("Entering create user service - Phone No.:%s", createCommand.getPhoneNumber()));
        log.info(String.format("Entering create user service - User Id:%s", createCommand.getEmailId()));


        LmsUser lmsUser = LmsUser.builder()
                .userId(createCommand.getEmailId())
                .emailId(createCommand.getEmailId())
                .firstName(createCommand.getFirstName())
                .lastName(createCommand.getLastName())
                .phoneNumber(createCommand.getPhoneNumber())
                .activeSince(DateTime.now(DateTimeZone.UTC).toString())
                .status(Status.ACTIVE)
                .build();

        lmsUser.setAddedOn(createCommand.getExecOn());
        lmsUser.setUpdatedOn(createCommand.getExecOn());
        lmsUser.setAddedBy(createCommand.getExecBy().getUserId());

        userDynamoRepository.save(lmsUser);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update user service - User Id:%s", updateCommand.getUserId()));

        LmsUser lmsUser = userDynamoRepository.findBy(updateCommand.getUserId());
        if (lmsUser != null) {
            lmsUser.setEmailId(updateCommand.getEmailId());
            lmsUser.setFirstName(updateCommand.getFirstName());
            lmsUser.setLastName(updateCommand.getLastName());
            lmsUser.setPhoneNumber(updateCommand.getPhoneNumber());
            lmsUser.setAddress(Address.builder()
                    .addressLine1(updateCommand.getAddress().getAddressLine1())
                    .addressLine2(updateCommand.getAddress().getAddressLine2())
                    .city(updateCommand.getAddress().getCity())
                    .state(updateCommand.getAddress().getState())
                    .country(updateCommand.getAddress().getCountry())
                    .zipCode(updateCommand.getAddress().getZipCode())
                    .build()
            );
            lmsUser.setSubscribedTopics(updateCommand.getSubscribedTopics());
            //lmsUser.setCourses(updateCommand.getCourses());

            lmsUser.setUpdatedOn(updateCommand.getExecOn());
            lmsUser.setUpdatedBy(updateCommand.getExecBy().getUserId());
            userDynamoRepository.save(lmsUser);
        }
    }

    @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable user service - User Id:%s", disableCommand.getUserId()));

        LmsUser lmsUser = userDynamoRepository.findBy(disableCommand.getUserId());
        if (lmsUser != null) {

            lmsUser.setStatus(Status.INACTIVE);

            lmsUser.setUpdatedOn(disableCommand.getExecOn());
            lmsUser.setUpdatedBy(disableCommand.getExecBy().getUserId());

            userDynamoRepository.save(lmsUser);
        }
    }

    @Override
    public void optInCourse(OptInCourseCommand optInCourseCommand) {

        log.info(String.format("Entering register course service - User Id:%s, course Id:%s, course name:%s", optInCourseCommand.getUserId(),
                optInCourseCommand.getCourse().getCourseId(), optInCourseCommand.getCourse().getTitle()));

        LmsUser lmsUser = userDynamoRepository.findBy(optInCourseCommand.getUserId());
        if (lmsUser != null) {
            List<Course> courses = new ArrayList<>();
            if ( lmsUser.getCourses() != null) {
                //courses.addAll(lmsUser.getCourses());
            }
            courses.add(optInCourseCommand.getCourse());
            //lmsUser.setCourses(courses);

            lmsUser.setUpdatedOn(optInCourseCommand.getExecOn());
            lmsUser.setUpdatedBy(optInCourseCommand.getExecBy().getUserId());

            userDynamoRepository.save(lmsUser);
        }
    }

    @Override
    public void optOutCourse(OptOutCourseCommand optOutCourseCommand) {

        log.info(String.format("Entering remove bookmark service - User Id:%s, course Id:%s", optOutCourseCommand.getUserId(),
                optOutCourseCommand.getCourse().getCourseId()));

        LmsUser lmsUser = userDynamoRepository.findBy(optOutCourseCommand.getUserId());
        if (lmsUser != null) {
            /*List<Course> courses = lmsUser.getCourses();
            if ( courses != null) {
                courses.removeIf(course -> course.getCourseId().equals(optOutCourseCommand.getCourse().getCourseId()));
                lmsUser.setCourses(courses);
            }
*/
            lmsUser.setUpdatedOn(optOutCourseCommand.getExecOn());
            lmsUser.setUpdatedBy(optOutCourseCommand.getExecBy().getUserId());

            userDynamoRepository.save(lmsUser);
        }
    }

    @Override
    public void subscribeTopic(SubscribeTopicCommand subscribeTopicCommand) {
        log.info(String.format("Entering follow topic service - User Id:%s, Topic Code:%s", subscribeTopicCommand.getUserId(), subscribeTopicCommand.getTopicCodes()));

        LmsUser lmsUser = userDynamoRepository.findBy(subscribeTopicCommand.getUserId());
        if (lmsUser != null) {

            List<String> followedTopics = new ArrayList<>();
            followedTopics.addAll(subscribeTopicCommand.getTopicCodes());
            lmsUser.setSubscribedTopics(followedTopics);

            lmsUser.setUpdatedOn(subscribeTopicCommand.getExecOn());
            lmsUser.setUpdatedBy(subscribeTopicCommand.getExecBy().getUserId());

            userDynamoRepository.save(lmsUser);
        }
    }

    @Override
    public void unsubscribeTopic(UnSubscribeTopicCommand unSubscribeTopicCommand) {
        log.info(String.format("Entering unfollow topic service - User Id:%s, Topic Code:%s", unSubscribeTopicCommand.getUserId(), unSubscribeTopicCommand.getTopicCodes()));

        LmsUser lmsUser = userDynamoRepository.findBy(unSubscribeTopicCommand.getUserId());
        if (lmsUser != null) {

            List<String> followedTopics = new ArrayList<>();
            if (lmsUser.getSubscribedTopics() != null) {
                followedTopics = lmsUser.getSubscribedTopics();
            }

            followedTopics.removeAll(unSubscribeTopicCommand.getTopicCodes());
            lmsUser.setSubscribedTopics(followedTopics);

            lmsUser.setUpdatedOn(unSubscribeTopicCommand.getExecOn());
            lmsUser.setUpdatedBy(unSubscribeTopicCommand.getExecBy().getUserId());

            userDynamoRepository.save(lmsUser);
        }
    }

    @Override
    public LmsUser get(String userId, String tenantId) {

        log.info(String.format("Entering get user service - User Id:%s", userId));

        return userDynamoRepository.findBy(tenantId+ HASH +userId);
    }

    @Override
    public List<Course> getCourseByStatus(String userId, String tenantId, String status) {
        return null;
    }

    @Override
    public int getAllUsersCount() {
        log.info("Entering getall users count service");
        return userDynamoRepository.getAllUsersCount();
    }


}
