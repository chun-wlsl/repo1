package com.yc.flower.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.flower.bean.Category;
import com.yc.flower.bean.Flower;
import com.yc.flower.bean.Msg;
import com.yc.flower.dao.CategoryDao;
import com.yc.flower.dao.FlowerDao;
import com.yc.flower.dao.MsgDao;
import com.yc.flower.util.Utils;


@Service
public class CategoryBiz{
	
	@Resource
	private CategoryDao cdao;
	
	//给后台使用
	public void save(Category c) throws BizException{
		// 验证输入
		Utils.checkNull(c.getCname(), "鲜花种类名称不能为空");
		Utils.checkNull(c.getIntro(), "介绍不能为空");
		if(c.getCid() == null || c.getCid() == 0) {
			cdao.insertCategory(c);
		} else {
			cdao.update(c);
		}
	}
	
}
