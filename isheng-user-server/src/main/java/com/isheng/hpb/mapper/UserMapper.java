package com.isheng.hpb.mapper;

import com.isheng.common.base.mapper.DataMapper;
import com.isheng.hpb.model.dto.UserDTO;
import com.isheng.hpb.model.dto.UserLoginDTO;
import com.isheng.hpb.model.entity.User;

public interface UserMapper extends DataMapper<UserDTO, User> {
	
	User login(UserLoginDTO loginDTO);

}
