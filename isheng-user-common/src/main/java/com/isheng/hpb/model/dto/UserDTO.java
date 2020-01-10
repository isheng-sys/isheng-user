package com.isheng.hpb.model.dto;

import com.isheng.common.base.model.dto.DataDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserDTO extends DataDTO {

	private static final long serialVersionUID = -7202636900892372695L;
	
	private String username;
	
	private String mobile;

}
