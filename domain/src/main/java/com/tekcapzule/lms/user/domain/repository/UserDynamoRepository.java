package com.tekcapzule.lms.user.domain.repository;

import com.tekcapzule.core.domain.CrudRepository;
import com.tekcapzule.lms.user.domain.model.LmsUser;

import java.util.List;


public interface UserDynamoRepository extends CrudRepository<LmsUser, String> {
    int getAllUsersCount();
}
