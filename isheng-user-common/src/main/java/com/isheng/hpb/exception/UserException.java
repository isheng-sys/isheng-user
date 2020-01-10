package com.isheng.hpb.exception;

import com.isheng.common.constant.enums.ErrMsg;
import com.isheng.common.exception.IshengException;

public class UserException extends IshengException {

	private static final long serialVersionUID = -5172146357351605616L;
	
	public UserException() {
		super();
	}
	
	public UserException(ErrMsg errMsg) {
		super(errMsg.getCode(), errMsg.getMsg());
	};

}
