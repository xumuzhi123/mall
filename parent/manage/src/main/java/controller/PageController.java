package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.TbAdmin;
import service.PageService;

@Controller
public class PageController {
	
	@Resource
	private PageService pageServiceImpl;
	
	@RequestMapping("/")
	public String login() {
		List<TbAdmin> admins = pageServiceImpl.getAdmins();
		
		return "login";
	}
	
	@RequestMapping("/index")
	public String index() {
		
		return "index";
	}
	
}
