package com.yc.flower.web.remote;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("flower-user")
public interface IOrderAction {

	
}
