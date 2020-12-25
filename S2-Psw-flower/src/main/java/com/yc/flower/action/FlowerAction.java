package com.yc.flower.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.yc.flower.bean.Flower;
import com.yc.flower.bean.Result;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.FlowerBiz;
import com.yc.flower.dao.FlowerDao;

@RestController
public class FlowerAction {
   @Resource
	private FlowerDao fdao;
   @Resource
   private FlowerBiz fbiz;
   
   //查找热销商品
   @RequestMapping("queryHot")
	public List<Flower> queryHot(){
		return fdao.selectHot();
	}
	
   //查找所有类型为cid 的商品（花）
   @RequestMapping("queryFlowerByCid")
	public List<Flower> queryProductByCid(int cid){
		return fdao.queryFlowerByCid(cid);
	}
   //查找商品fid的详细信息
   @RequestMapping("queryFlowerById")
	public Flower queryFlowerById(int fid){
		return fdao.queryFlowerById(fid);
	}
   
   //查找商品
   @RequestMapping("queryProduct")
	public List<Flower> queryProduct(){
		return fdao.selectFlower();
	}
   
   //查找折扣最低的商品
   @RequestMapping("queryDiscount")
	public Flower queryDiscount(){
		return fdao.selectByDiscount();
	}
   
   //添加商品
   @RequestMapping("create")
	public Result create(Flower f) {
		try {
			fbiz.create(f);
			return Result.success("商品添加成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure(e.getMessage());
		}
	}
   
   	//查找所有的商品
   	@RequestMapping("queryAllProduct")
	public List<Flower> queryAllProduct(){
		return fdao.selectAllFlower();
	}
}
