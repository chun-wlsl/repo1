package com.yc.flower.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.flower.bean.Flower;
import com.yc.flower.dao.FlowerDao;
import com.yc.flower.util.Utils;


@Service
public class FlowerBiz{
	@Resource 
	private FlowerDao fdao;
	
	@Transactional
	public void create(Flower p) throws BizException{
		// 验证输入
		Utils.checkNull(p.getFname(), "商品名称不能为空");
		//Utils.checkNull(p.getImage(), "商品图片不能为空");
		Utils.checkNull(p.getMarketPrice(), "商品商城价不能为空");
		Utils.checkNull(p.getDiscount(), "商品折扣不能为空");
		Utils.checkNull(p.getShopPrice(), "商品销售价不能为空");
		Utils.checkNull(p.getIsHot(), "商品是否热门不能为空");
		Utils.checkNull(p.getFcount(), "商品库存量不能为空");
		Utils.checkNull(p.getAdvice(), "商品说明不能为空");
	
		
		// 添加到数据库
		fdao.insert(p);
	}
	
}
