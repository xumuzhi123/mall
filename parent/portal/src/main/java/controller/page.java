package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.TbContentService;

@Controller
public class page {
	@Resource
	TbContentService tbContentServiceImpl;
	
	@RequestMapping("/")
	public String showPage(HttpServletRequest request) {
		request.setAttribute("ad1",tbContentServiceImpl.showBigPic());	
		return "index";
	}
	
}
