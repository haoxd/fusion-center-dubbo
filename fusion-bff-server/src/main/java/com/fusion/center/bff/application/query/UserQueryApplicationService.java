package com.fusion.center.bff.application.query;


import com.fusion.center.bff.domain.model.entity.UserPO;
import com.fusion.center.bff.domain.query.UserQuery;
import com.fusion.center.bff.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserQueryApplicationService {

    private final UserRepository userRepository;


    public UserPO queryUserInfo(UserQuery userQuery) {

        return userRepository.getByName(userQuery.getUserName());

    }

}
