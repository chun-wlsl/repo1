package com.yc.flower.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Category;
import com.yc.flower.bean.Flower;
import com.yc.flower.bean.Result;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.CategoryBiz;
import com.yc.flower.dao.CategoryDao;

@RestController
public class CategoryAction {

	@Resource
	private CategoryDao cdao;
	@Resource
	private CategoryBiz cbiz;
	
	//查看所有的分类
	@RequestMapping("queryCategory")
	public List<Category> queryCategory() {
	   return cdao. queryCategory();
	}
	
	//查询cid 的分类信息
	@RequestMapping("queryByCid")
	public Category queryByCid(Integer  cid) {
	   return cdao.queryByCid(cid);
	}
	
	
	//给后台使用，category.html页面的保存功能
	@RequestMapping(path="category.s",params="op=save")
	public Result save(Integer cid, String cname, String intro){
		Category c = new Category();
		c.setCid(cid);
		c.setCname(cname);
		c.setIntro(intro);
		try {
			cbiz.save(c);
			return new Result(1,"鲜花种类保存成功!");
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0,e.getMessage());
		}
	}
	
	//查找所有的鲜花种类，给后台使用
   	@RequestMapping("queryAllCategory")
	public Map<String, Object> queryAllCategory(Integer cid,String cname, String intro, String page, String rows){
   		System.out.println("cid:"+cid+"  cname:"+cname+"  intro:"+intro+"  page:"+page+"  rows:"+rows);
    	List<?> list = cdao.selectAllCategory(cid, cname, intro, page, rows);
    	System.out.println("list:"+list);
    	int total = cdao.count(cid, cname, intro);
    	System.out.println("total:"+total);
    	HashMap<String, Object> data = new HashMap<>();
    	data.put("rows", list);
    	data.put("total", total);
		return data;
	}
	
	//新增商品分类
	@RequestMapping("insertCategory")
	public Result insertCategory(Category c) {
		cdao.insertCategory(c);
		return Result.success("商品分类添加成功!");
	}
}
