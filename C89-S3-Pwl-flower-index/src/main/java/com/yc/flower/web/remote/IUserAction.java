package com.yc.flower.web.remote;

import org.springframework.cloud.openfeign.FeignClient;

//远程调用服务接口--user工程的所有方法（包括Administrator、Cart、Order、User）
@FeignClient("flower-user")
public interface IUserAction {

	
}
