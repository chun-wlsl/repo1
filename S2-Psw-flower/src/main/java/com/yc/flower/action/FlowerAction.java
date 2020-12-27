package com.yc.flower.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   
   	//给后台使用，查找所有的商品
   	@RequestMapping("queryAllProduct")
	public Map<String, Object> queryAllProduct(String fname, Integer cid, Double discount,Integer fcount, String page, String rows){
   		System.out.println("fname:"+fname+"  cid:"+cid+"  discount:"+discount+"  fcount:"+fcount+"  page:"+page+"  rows:"+rows);
    	List<?> list = fdao.selectAllFlower(fname, cid, discount, fcount, page, rows);
    	System.out.println("list:"+list);
    	int total = fdao.count(fname, cid, discount, fcount);
    	System.out.println("total:"+total);
    	HashMap<String, Object> data = new HashMap<>();
    	data.put("rows", list);
    	data.put("total", total);
		return data;
	}
   	
   	//给后台使用，flower页面的save
   	@RequestMapping(path="flower.s",params="op=save")
	public Result save(Integer fid, String fname, Double marketPrice, Double discount, Double shopPrice,
			 String image, Integer cid, Integer isHot,  Integer fcount, String advice){
   		Flower f = new Flower();
		f.setFid(fid);
		f.setFname(fname);
		f.setMarketPrice(marketPrice);
		f.setDiscount(discount);
		f.setShopPrice(shopPrice);
		f.setImage(image);
		f.setIsHot(isHot);
		f.setCid(cid);
		f.setFcount(fcount);
		f.setAdvice(advice);
		try {
			fbiz.save(f);
			return new Result(1,"鲜花保存成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0,e.getMessage());
		}
		
	}
}
