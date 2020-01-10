package com.isheng.hpb.service;

import com.isheng.common.base.service.DataService;
import com.isheng.hpb.model.dto.UserDTO;
import com.isheng.hpb.model.dto.UserLoginDTO;

/**
 * userService服务接口
 * @author isheng92
 * @date 2019年11月25日 下午8:58:21
 */
public interface UserService extends DataService<UserDTO> {
	
	/**
	 * 用户登录
	 * @param userLoginDTO
	 * @return token
	 */
	String login(UserLoginDTO userLoginDTO);
}
