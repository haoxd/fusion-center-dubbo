package com.fusion.center.bff.infrastructure.repository;

import com.fusion.center.bff.domain.model.entity.UserPO;

public interface UserRepository {

    UserPO getByName(String userName);
}
