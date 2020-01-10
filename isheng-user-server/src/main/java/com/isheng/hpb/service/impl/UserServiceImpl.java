package com.isheng.hpb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isheng.common.base.convertor.DataConvertor;
import com.isheng.common.base.mapper.DataMapper;
import com.isheng.common.base.service.AbstractDataService;
import com.isheng.common.codec.Md5Util;
import com.isheng.common.constant.Constant;
import com.isheng.common.constant.enums.ErrMsg;
import com.isheng.common.idgen.IdGen;
import com.isheng.common.json.JsonMapper;
import com.isheng.common.util.StringUtil;
import com.isheng.common.valid.BeanValidator;
import com.isheng.common.valid.DefaultGroup;
import com.isheng.hpb.cache.RedisUtil;
import com.isheng.hpb.convertor.UserConvertor;
import com.isheng.hpb.exception.UserException;
import com.isheng.hpb.mapper.UserMapper;
import com.isheng.hpb.model.dto.UserDTO;
import com.isheng.hpb.model.dto.UserLoginDTO;
import com.isheng.hpb.model.entity.User;
import com.isheng.hpb.service.UserService;

@Service("userService")
public class UserServiceImpl extends AbstractDataService<UserDTO, User> implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserConvertor userConvertor;
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	protected DataMapper<UserDTO, User> getMapper() {
		return userMapper;
	}

	@Override
	protected DataConvertor<UserDTO, User> getConvertor() {
		return userConvertor;
	}

	@Override
	public String login(UserLoginDTO data) {
		BeanValidator.validateWithException(data, DefaultGroup.class);
		User user = userMapper.login(data);
		String inputPwd = Md5Util.md5(data.getPassword());
		if (null == user || !StringUtil.equals(inputPwd, user.getPassword())) {
			throw new UserException(ErrMsg.LOGIN_ERR);
		}
		user.setPassword(null);
		UserDTO userDTO = userConvertor.entityToDTO(user);
		String token = Constant.Default.SESSION + IdGen.uuid();
		redisUtil.set(token, JsonMapper.toJsonString(userDTO), Constant.Second.MINUTE30);
		return token;
	}

}
