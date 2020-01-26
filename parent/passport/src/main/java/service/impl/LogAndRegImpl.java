package service.impl;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import pojo.EgoResult;
import pojo.TbUser;
import redis.dao.JedisDao;

import service.LogAndReg;
import service.TbUserDubboService;
import utils.CookieUtils;
import utils.IDUtils;
import utils.JsonUtils;

@Service
public class LogAndRegImpl implements LogAndReg{
	@Reference
	TbUserDubboService tbUserDubboServiceImpl;
	
	@Resource
	JedisDao jedis;
	
	@Override
	public EgoResult log(TbUser user,HttpServletResponse response,HttpServletRequest request) {
		TbUser u=  null;
		String value = null;
		
		EgoResult result = new EgoResult();
		
		String c = CookieUtils.getCookieValue(request, "TT_TOKEN");
		
		if(c != null && !c.equals("")) {
			value = jedis.get(c);
			u = JsonUtils.jsonToPojo(value, TbUser.class);
			
		}else {
			u = tbUserDubboServiceImpl.selByNameAndPassword(user);
			String uuid = u.getUsername() + IDUtils.genItemId();
			
			CookieUtils.setCookie(request, response, "TT_TOKEN", uuid,7*24*60*60);
			
			value = JsonUtils.objectToJson(u);
			jedis.set(uuid, value);
			jedis.expire(uuid,7*24*60*60);
		}
		
		if(u != null) {
			result.setStatus(200);
		}else {
			result.setMsg("错误");
		}
		
		return result;
	}

	@Override
	public EgoResult reg(HttpServletRequest request) {
		
		
		return null;
	}

	@Override
	public EgoResult getCookie(String _ticket) {
		EgoResult result = new EgoResult();
		String value = jedis.get(_ticket);
		
		if(value != null && !value.equals("")) {
			result.setStatus(200);
			TbUser u = JsonUtils.jsonToPojo(value, TbUser.class);
			
			result.setData(u);
		}else {
			result.setStatus(500);
		}
		
		System.out.println("123");
		return result;
	}

}
