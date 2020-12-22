package com.yc.flower.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Category;
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
	
}
