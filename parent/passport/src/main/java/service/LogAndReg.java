package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.EgoResult;
import pojo.TbUser;

public interface LogAndReg {
	EgoResult log(TbUser user,HttpServletResponse response,HttpServletRequest request);
	
	EgoResult reg(HttpServletRequest request);
	
	EgoResult getCookie(String _ticket);
}
