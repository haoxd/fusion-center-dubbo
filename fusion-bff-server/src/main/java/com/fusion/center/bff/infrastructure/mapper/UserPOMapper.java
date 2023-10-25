package com.fusion.center.bff.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fusion.center.bff.domain.model.entity.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPOMapper extends BaseMapper<UserPO> {
}
