package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.EgoResult;
import pojo.TbUser;
import service.LogAndReg;

@Controller
public class loginAndRegister {
	
	@Resource
	LogAndReg LogAndRegImpl;
	
	@RequestMapping("user/showLogin")
	public String login() {
		return "login";
	}
	
	@RequestMapping("user/login")
	@ResponseBody
	public EgoResult checkLogin(TbUser user,HttpServletResponse response,HttpServletRequest request) {
		return LogAndRegImpl.log(user,response,request);
	}
	
	@RequestMapping("user/token/{token}")
	@ResponseBody
	//跨域操作忘咯
	public Object showLogin(@PathVariable String token,String callback) {
		EgoResult er = new EgoResult();
		er = LogAndRegImpl.getCookie(token);
		
		if(callback != null && !callback.equals("")) {
			MappingJacksonValue mjv = new MappingJacksonValue(er);
			mjv.setJsonpFunction(callback);
			return mjv;
		}
		
		return er;
		
	}
	
	
	@RequestMapping("user/showRegister")
	public String register() {
		return "register";
	}
	
	
	
	
	
	
	
}
