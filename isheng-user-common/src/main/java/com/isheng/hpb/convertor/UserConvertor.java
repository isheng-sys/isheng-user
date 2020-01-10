package com.isheng.hpb.convertor;

import org.mapstruct.Mapper;

import com.isheng.common.base.convertor.DataConvertor;
import com.isheng.hpb.model.dto.UserDTO;
import com.isheng.hpb.model.entity.User;

@Mapper
public interface UserConvertor extends DataConvertor<UserDTO, User> {

}
