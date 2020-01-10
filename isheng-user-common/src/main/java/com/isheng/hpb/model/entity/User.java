package com.isheng.hpb.model.entity;


import com.isheng.common.base.model.entity.DataEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * User
 * @author isheng92
 * @date 2019年11月24日 下午8:22:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"password"})
public class User extends DataEntity {

	private static final long serialVersionUID = -2282648741639402081L;
	
	/**
	 * 登录名
	 */
	private String username;
	
	/**
	 * 工号
	 */
	private String workNo;

	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 手机号：11位
	 */
	private String mobile;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 性别：0-未知，1-男，1-女
	 */
	private Integer gender;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 状态：0-禁用，1-启用
	 */
	private Integer status;

}
