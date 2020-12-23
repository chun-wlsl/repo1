package com.yc.flower;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.yc.flower.action.FlowerAction;
import com.yc.flower.bean.Flower;

@SpringBootTest
public class FlowerTest {
     @Resource
	private FlowerAction faction;
     
     //新增商品测试
     @Test
     public void createTest() {
    	 Flower f=new Flower();
    	 f.setFname("玫瑰花");
    	 f.setMarketPrice(24d);
    	 f.setDiscount(8.8);
    	 f.setShopPrice(15d);
    	
    	 f.setCid(1);
    	 f.setIsHot(1);
    	 f.setFcount(3);
    	 f.setAdvice("养玫瑰花注意事项有三点。第一点：玫瑰花在浇水的时候，要掌握干了就浇水，湿度过大就排水的原则。第二点：在玫瑰花发芽的时候，要施一些氮肥，到了玫瑰花开花以后需要施入一定的磷钾肥。第三点：在每年的春季四、五月份的时候，需要给它进行修剪。");
    	faction.create(f);
     }
     

     //测试通过fid找到商品信息
	@Test
     public void test1() {
    	 Flower f=new Flower();
       f=faction.queryFlowerById(1);
       System.out.println(f.toString());
     }
     
	
}
