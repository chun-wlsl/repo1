package com.yc.flower.web.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.yc.flower.bean.Administrator;
import com.yc.flower.bean.Result;
import com.yc.flower.bean.User;

//远程调用服务接口--user工程的所有方法（包括Administrator、User）
@FeignClient("flower-user")
public interface IUserAction {

	@PostMapping("login.s")
	Result login(User user);
	
	@PostMapping("reg")
	Result reg(User user);

//---AdministratorAction	
	@PostMapping("login1.s")
	Result login1(Administrator admin);
}
