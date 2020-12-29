package com.yc.flower;
import javax.annotation.Resource;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.yc.flower.action.UserAction;
import com.yc.flower.bean.Administrator;
import com.yc.flower.bean.User;
import com.yc.flower.dao.AdministratorDao;

@SpringBootTest
public class UserTest {

	@Resource
	private UserAction uAction;
	
	@Test
	public void test1() {
		uAction.login("张三", "123",null);
	}
	
	@Test
	public void test2() throws Exception {
		User user =new User();
		user.setName("李四");
		user.setPwd("aaa");
		user.setSex("男");
		user.setAddr("湖南省长沙市");
		user.setPhone("112233");
		user.setEmail("1111@qq.com");
		uAction.reg(user,null, null);
	}
	
	
	@Test
	public void test3() {
	   int i=uAction.queryAll();
	   System.out.println("总用户数："+i);
	}
	
	@Test
	public void test4() {
		AdministratorDao adao = new AdministratorDao();
		String aname = "admin";
		System.out.println("aname是：" + aname);
		System.out.println("结果是：" + adao.selectByAnameAndApwd(aname));
	}
}
