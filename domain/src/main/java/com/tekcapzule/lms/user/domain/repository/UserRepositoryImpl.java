package com.tekcapzule.lms.user.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.Select;
import com.tekcapzule.lms.user.domain.model.LmsUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserDynamoRepository {

    private DynamoDBMapper dynamo;

    @Autowired
    public UserRepositoryImpl(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public List<LmsUser> findAll() {

        return dynamo.scan(LmsUser.class,new DynamoDBScanExpression());
    }

    @Override
    public LmsUser findBy(String userId) {
        return dynamo.load(LmsUser.class, userId);
    }

    @Override
    public LmsUser save(LmsUser lmsUser) {
        dynamo.save(lmsUser);
        return lmsUser;
    }

    @Override
    public int getAllUsersCount() {
        return dynamo.count(LmsUser.class,new DynamoDBScanExpression().withSelect(Select.COUNT));
    }
}
