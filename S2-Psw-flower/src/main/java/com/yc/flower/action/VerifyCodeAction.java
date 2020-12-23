package com.yc.flower.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.flower.util.VerifyCodeUtils;


@RestController
public class VerifyCodeAction  {

	@RequestMapping("verifyCode.s")
	public String getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String code = VerifyCodeUtils.outputImage(response);
		session.setAttribute("code", code);
		return code;
	}
   

}
