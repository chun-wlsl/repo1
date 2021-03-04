package com.yc.flower.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.flower.bean.Administrator;
import com.yc.flower.bean.AdministratorExample;
import com.yc.flower.dao.AdministratorMapper;
import com.yc.flower.util.Utils;

@Service
public class AdministratorBiz {

	@Resource
	private AdministratorMapper am;
	
	// 登录
	public Administrator login(Administrator admin) throws BizException {
		Utils.checkNull(admin.getAname(), "请输入用户名");
		Utils.checkNull(admin.getApwd(), "请输入密码");

		AdministratorExample ae = new AdministratorExample();
		ae.createCriteria().andAnameEqualTo(admin.getAname());
		List<Administrator> list = am.selectByExample(ae);
		if (list.isEmpty()) { 
			throw new BizException("请检查用户名是否正确");
		}
		if (!list.get(0).getApwd().equals(admin.getApwd())) { 
			throw new BizException("密码错误"); 
		} 
		return list.get(0);
	}
}
