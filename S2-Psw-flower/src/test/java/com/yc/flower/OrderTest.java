/*package com.yc.flower;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

import com.yc.flower.action.FlowerAction;
import com.yc.flower.bean.Flower;
import com.yc.flower.bean.Order;
import com.yc.flower.biz.BizException;
import com.yc.flower.biz.OrderBiz;



public class OrderTest {

	@SpringBootTest
	public class DaMaiTest {

		@Resource
		private OrderBiz obiz;
		
		@Test
		public void test1() throws SQLException {
			Order o = new Order();		
			o.setAddr("衡阳");
			o.setPhone("13800000111");
			o.setName("zhangsan");
			o.setUid(1);
			try {
				obiz.pay(o);
			} catch (BizException e) {
				e.printStackTrace();
			}
			
		}
		
		@Resource
		private FlowerAction paction;
		
		@Test
		public void test2() {
			Flower p = new Flower();
			p.setFname("最新男款冬装");
			p.setMarketPrice(500d);
			p.setShopPrice(128d);
			p.setImage("products/1/cs10008.jpg");
			p.setCid(1);
			paction.create(p);
		}
		
		
		@Resource
		private StringRedisTemplate srt;
		@Test
		public void test3() {
			paction.queryProductByCid(1);
			paction.queryProductByCid(1);
			paction.queryProductByCid(1);
			paction.queryProductByCid(2);
			paction.queryProductByCid(2);
			paction.queryProductByCid(3);
			paction.queryProductByCid(3);
			String pb1 = srt.opsForValue().get("product_bcount_1");
			String pb2 = srt.opsForValue().get("product_bcount_2");
			String pb3 = srt.opsForValue().get("product_bcount_3");
			Assert.isTrue("3".equals(pb1),"数量为3!");
			Assert.isTrue("2".equals(pb2),"数量为2!");
			Assert.isTrue("2".equals(pb3),"数量为2!");
		}
	}
}*/
