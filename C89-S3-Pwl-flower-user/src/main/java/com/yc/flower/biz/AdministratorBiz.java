package com.yc.flower.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.flower.bean.Administrator;
import com.yc.flower.dao.AdministratorDao;
import com.yc.flower.util.Utils;

@Service
public class AdministratorBiz {

	@Resource
	private AdministratorDao adao;
	
	// 登录
	public Administrator login(String aname, String apwd) throws BizException {
		Utils.checkNull(aname, "请输入用户名");
		Utils.checkNull(apwd, "请输入密码");

		Administrator a = new Administrator();
		a = adao.selectByAname(aname);
		if (a == null) { 
			throw new BizException("请检查用户名是否正确");
		}
		if (!a.getApwd().equals(apwd)) { 
			throw new BizException("密码错误"); 
		} 
		return a;
		
	}
}
