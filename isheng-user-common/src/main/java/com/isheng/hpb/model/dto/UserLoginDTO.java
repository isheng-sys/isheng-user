package com.isheng.hpb.model.dto;

import javax.validation.constraints.NotBlank;

import com.isheng.common.base.model.dto.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 	用户登录参数封装
 * @author isheng92
 * @date 2019年11月25日 下午9:12:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"password"})
public class UserLoginDTO extends BaseDTO {

	private static final long serialVersionUID = -6527624337970238796L;
	
	/**
	 * 用户名/工号/手机号
	 */
	@NotBlank(message = "登录名不能为空")
	private String loginName;
	
	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	private String password;

}
