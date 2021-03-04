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
	public Administrator login(String aname, String apwd) throws BizException {
		Utils.checkNull(aname, "请输入用户名");
		Utils.checkNull(apwd, "请输入密码");

		AdministratorExample ae = new AdministratorExample();
		ae.createCriteria().andAnameEqualTo(aname);
		List<Administrator> list = am.selectByExample(ae);
		if (list.isEmpty()) { 
			throw new BizException("请检查用户名是否正确");
		}
		if (!list.get(0).getApwd().equals(apwd)) { 
			throw new BizException("密码错误"); 
		} 
		return list.get(0);
	}
}
