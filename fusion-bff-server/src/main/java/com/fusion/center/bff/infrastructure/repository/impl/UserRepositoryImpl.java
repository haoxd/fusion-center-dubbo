package com.fusion.center.bff.infrastructure.repository.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fusion.center.bff.domain.model.entity.UserPO;
import com.fusion.center.bff.infrastructure.mapper.UserPOMapper;
import com.fusion.center.bff.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserPOMapper userPOMapper;

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public UserPO getByName(String userName) {
        LambdaQueryWrapper<UserPO> queryWrapper = Wrappers.lambdaQuery(UserPO.class)
                .eq(UserPO::getUserName, userName);
        UserPO userPO = userPOMapper.selectOne(queryWrapper);
        redisTemplate.opsForValue().set(userName, JSONObject.toJSONString(userPO));
        return userPO;
    }


}
