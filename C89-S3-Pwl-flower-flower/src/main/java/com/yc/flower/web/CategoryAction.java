package com.yc.flower.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.bean.Category;
import com.yc.flower.bean.CategoryExample;
import com.yc.flower.bean.Result;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.CategoryBiz;
import com.yc.flower.dao.CategoryMapper;

@RestController
public class CategoryAction {

	@Resource
	private CategoryMapper cm;
	// private CategoryDao cdao;

	@Resource
	private CategoryBiz cbiz;

	// 查看所有的分类
	@RequestMapping("queryCategory")
	public List<Category> queryCategory() {
		CategoryExample ce = new CategoryExample();
		return cm.selectByExample(ce);
	}

	// 查询cid 的分类信息
	@RequestMapping("queryByCid")
	public Category queryByCid(Integer cid) {
		return cm.queryByCid(cid);
	}

	// 给后台使用，category.html页面的保存功能
	@RequestMapping(path = "category.s", params = "op=save")
	public Result save(Integer cid, String cname, String intro) {
		Category c = new Category();
		c.setCid(cid);
		c.setCname(cname);
		c.setIntro(intro);
		try {
			cbiz.save(c);
			return Result.success("鲜花种类保存成功!", null);
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure("鲜花种类保存失败!", e.getMessage());
		}
	}

	// 新增商品分类
	@RequestMapping("insertCategory")
	public Result insertCategory(Category c) {
		if(cm.insertSelective(c) == 1) {
			return Result.success("鲜花种类添加成功!", null);
		}else {
			return Result.failure("鲜花种类添加失败!", null);
		}
	}
}
