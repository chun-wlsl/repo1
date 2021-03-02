package com.yc.flower.web.remote;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("flower-flowers")
public interface ICategoryAction {

	
}
