package com.yc.flower.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.yc.flower.bean.Flower;
import com.yc.flower.bean.FlowerExample;
import com.yc.flower.bean.Result;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.FlowerBiz;
import com.yc.flower.dao.FlowerMapper;

@RestController
public class FlowerAction {
	
	@Resource
	private FlowerMapper fm;
	//private FlowerDao fdao;
	
	@Resource
	private FlowerBiz fbiz;

	// 查找热销商品
	@RequestMapping("queryHot")
	public List<Flower> queryHot() {
		System.out.println("queryHot:" + fm.selectHot());
		return fm.selectHot();
	}

	// 查找所有类型为cid 的商品（花）
	@RequestMapping("queryFlowerByCid")
	public List<Flower> queryFlowerByCid(int cid) {
		FlowerExample fe = new FlowerExample();
		fe.createCriteria().andFcountGreaterThan(0).andCidEqualTo(cid);
		return fm.selectByExample(fe);
	}

	// 查找商品fid的详细信息
	@RequestMapping("queryFlowerById")
	public Flower queryFlowerById(int fid) {
		return fm.selectByPrimaryKey(fid);
	}

	// 查找商品
	@RequestMapping("queryProduct")
	public List<Flower> queryProduct() {
		FlowerExample fe = new FlowerExample();
		// 设置分页
		PageHelper.startPage(1, 8);
		return fm.selectByExample(fe);
	}

	// 查找折扣最低的商品
	@RequestMapping("queryDiscount")
	public Flower queryDiscount() {
		return fm.selectByDiscount();
	}

	// 添加商品
	@RequestMapping("create")
	public Result create(Flower f) {
		try {
			fbiz.create(f);
			return Result.success("商品添加成功!", null);
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure("商品添加失败!", e.getMessage());
		}
	}

	// 给后台使用，flower页面的save
	@RequestMapping(path = "flower.s", params = "op=save")
	public Result save(Integer fid, String fname, Double marketPrice, Double discount, Double shopPrice, String image,
			Integer cid, Integer isHot, Integer fcount, String advice) {
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
			return Result.success("鲜花保存成功!", null);
		} catch (BizException e) {
			e.printStackTrace();
			return Result.failure("鲜花保存成功!", e.getMessage());
		}

	}

	// 查找最新商品
	@RequestMapping("queryNewProduct")
	public List<Flower> queryNewProduct() {
		return fm.queryNewProduct();
	}
}
