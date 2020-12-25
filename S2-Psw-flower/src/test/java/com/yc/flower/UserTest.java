package com.yc.flower;
import javax.annotation.Resource;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.yc.flower.action.UserAction;
import com.yc.flower.bean.User;

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
}
