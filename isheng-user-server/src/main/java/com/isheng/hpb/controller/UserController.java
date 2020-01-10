package com.isheng.hpb.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.isheng.common.annotator.IshengController;
import com.isheng.common.base.controller.BaseController;
import com.isheng.common.model.Response;
import com.isheng.hpb.model.dto.UserDTO;
import com.isheng.hpb.service.UserService;

/**
 * user controller
 * @author isheng92
 * @date 2019年11月25日 下午9:00:34
 */
@IshengController
@RequestMapping("/{version}/sys/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public Response<PageInfo<UserDTO>> list(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, UserDTO userDTO) {
		return Response.ok(userService.getPaging(pageNum, pageSize, userDTO));
	}
	
	@GetMapping(value = "/{userId}")
	public Response<UserDTO> getById(@PathVariable("userId") String userId) {
		return Response.ok(userService.getById(userId));
	}
	
	@PostMapping
	public Response<Integer> add(UserDTO userDTO) {
		return Response.ok(userService.save(userDTO));
	}
	
	@PutMapping
	public Response<Integer> update(UserDTO userDTO) {
		return Response.ok(userService.update(userDTO));
	}
	
	@DeleteMapping
	public Response<Integer> delete(List<Serializable> ids) {
		return Response.ok(userService.deleteByIds(ids));
	}
	

}
