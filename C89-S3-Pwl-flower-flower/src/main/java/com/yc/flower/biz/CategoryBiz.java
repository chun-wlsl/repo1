package com.yc.flower.biz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.flower.bean.Category;
import com.yc.flower.dao.CategoryMapper;
import com.yc.flower.util.Utils;

@Service
public class CategoryBiz {

	@Resource
	private CategoryMapper cm;
	// private CategoryDao cdao;

	// 给后台使用
	public void save(Category c) throws BizException {
		// 验证输入
		Utils.checkNull(c.getCname(), "鲜花种类名称不能为空");
		Utils.checkNull(c.getIntro(), "介绍不能为空");
		if (c.getCid() == null || c.getCid() == 0) {
			cm.insertSelective(c);
			// cdao.insertCategory(c);
		} else {
			cm.updateByPrimaryKeySelective(c);
			// cdao.update(c);
		}
	}

	
}
