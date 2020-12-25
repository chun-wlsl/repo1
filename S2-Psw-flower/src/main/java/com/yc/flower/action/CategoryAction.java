package com.yc.flower.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Category;
<<<<<<< HEAD
import com.yc.flower.bean.Flower;
=======
>>>>>>> branch 'main' of https://github.com/chun-wlsl/repo1.git
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
	
<<<<<<< HEAD
	@RequestMapping(path="category.s",params="op=save")
	public Result save(){
		Category f = new Category();
		if(f.getCname()==null||f.getCname().trim().isEmpty()) {
			return new Result(0,"鲜花名称不能为空！");
		}
		
		if(f.getCid() == null || f.getCid() == 0) {
			cdao.insert(f);
		} else {
			cdao.update(f);
		}
		return new Result(1,"商品保存成功!");
=======
	
	//新增商品分类
	@RequestMapping("insertCategory")
	public Result insertCategory(Category c) {
		cdao.insertCategory(c);
		return Result.success("商品分类添加成功!");
		
>>>>>>> branch 'main' of https://github.com/chun-wlsl/repo1.git
	}
}
