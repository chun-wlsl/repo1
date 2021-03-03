package com.yc.flower.web.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yc.flower.bean.Category;
import com.yc.flower.bean.Flower;
import com.yc.flower.bean.Result;

//远程调用服务接口--flower工程的所有方法（包括Category、Flower、Msg、Cart、Order）
@FeignClient("flower-flowers")
public interface IFlowerAction {

	@PostMapping("addMsg")
	Result addMsg(@RequestParam String content, @RequestParam Integer uid, @RequestParam Integer fid);
	
	@GetMapping("queryMsg")
	List<?> queryMsg();
	
	@GetMapping("queryMsgByfid")
	List<?> queryMsgByfid(@RequestParam Integer fid);
	
//---------------------以下方法还未验证-------------------------------
	@GetMapping("queryCategory")
	List<Category> queryCategory();
	
	@GetMapping("queryByCid")
	Category queryByCid(@RequestParam Integer cid);
	
	@PostMapping("insertCategory")
	Result insertCategory(Category c);
	
	@GetMapping("queryHot")
	List<Flower> queryHot();
	
	@GetMapping("queryFlowerByCid")
	List<Flower> queryFlowerByCid(@RequestParam int cid);
	
	@GetMapping("queryFlowerById")
	Flower queryFlowerById(@RequestParam int fid);
	
	@GetMapping("queryProduct")
	List<Flower> queryProduct();
	
	@GetMapping("queryDiscount")
	Flower queryDiscount();
	
	@PostMapping("create")
	Result create(Flower f);
	
	@GetMapping("queryNewProduct")
	List<Flower> queryNewProduct();
	
	@PostMapping("addCart")
	Result addCartdetail(@RequestParam int fid, @RequestParam int count);
	
	@GetMapping(path = "cart.s", params = "op=queryCart")
	List<?> queryCart();
	
	@PostMapping(path = "cart.s", params = "op=upCart")
	Result upCart(@RequestParam int fid, @RequestParam int count);
	
	@PostMapping(path = "cart.s", params = "op=clearCart")
	Result clearCart();
	
	@PostMapping(path = "cart.s", params = "op=deleteCart")
	Result deleteCart(@RequestParam int fid);
	
	
	
	
	
	
	
	
}
