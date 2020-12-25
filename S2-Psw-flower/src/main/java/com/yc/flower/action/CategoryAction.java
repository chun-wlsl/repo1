package com.yc.flower.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Category;
import com.yc.flower.bean.Result;
import com.yc.flower.dao.CategoryDao;

@RestController
public class CategoryAction {

	@Resource
	private CategoryDao cdao;
	
	//查看所有的分类
	@RequestMapping("queryCategory")
	public List<Category> queryCategory() {
	   return cdao. queryCategory();
	}
	
	
	//新增商品分类
	@RequestMapping("insertCategory")
	public Result insertCategory(Category c) {
		cdao.insertCategory(c);
		return Result.success("商品分类添加成功!");
		
	}
}
