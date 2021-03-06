package com.yc.flower.biz;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.flower.bean.Cart;
import com.yc.flower.bean.Flower;
import com.yc.flower.dao.CartDao;
import com.yc.flower.dao.FlowerDao;
import com.yc.flower.util.Utils;


@Service
public class FlowerBiz{
	@Resource 
	private FlowerDao fdao;
	@Resource
	private CartDao cdao;
	//添加商品
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
	
	
	//添加购物车，需先判断商品库存是否充足
	public void addCart(Integer uid,int fid, int count) throws SQLException, BizException {
		//获取商品的信息
	    Flower f=fdao.queryFlowerById(fid);//添加购物车商品的fid(主键）
	    System.out.println(f.toString());
		//库存是否大于添加购物车的数量
	    int counts=f.getFcount();
	    System.out.println("counts:"+counts);
	    System.out.println("count:"+count);
	    if(counts>=count) {
	    	//商品-添加购物车的数量
	    	fdao.updateCount(count,fid);
	    	cdao.addCart(uid, fid, count);
	    }else {
	    	throw new BizException("库存不足");
	    }
	}
	
	// 添加购物车，需先判断商品库存是否充足
	public void addCart1(Integer uid, Integer fid) throws SQLException, BizException {
		// 获取商品的信息
		Flower f = fdao.queryFlowerById(fid);// 添加购物车商品的fid(主键）
		System.out.println(f.toString());
		// 库存是否大于添加购物车的数量
		int counts = f.getFcount();
		if (counts >= 1) {
			// 商品-添加购物车的数量
			fdao.updateCount(1, fid);
			cdao.addCart(uid, fid, 1);
		} else {
			throw new BizException("库存不足");
		}
	}

	public void save(Flower f) throws BizException {
		Utils.checkNull(f.getFname(), "鲜花名称不能为空");
		Utils.checkNull(f.getShopPrice(), "鲜花销售价格必须大于0");
		Utils.checkNull(f.getDiscount(), "商品折扣不能为空");
		Utils.checkNull(f.getMarketPrice(), "鲜花商城价格必须大于0");
		Utils.checkNull(f.getFcount(), "商品库存量不能为空");
		Utils.checkNull(f.getIsHot(), "商品是否热门不能为空");
		if(f.getFid() == null || f.getFid() == 0) {
			fdao.insert(f);
		} else {
			fdao.update(f);
		}
	}
	
	
}
