package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.EgoResult;
import pojo.TbItem;
import pojo.TbItemChild;
import pojo.TbOrderItem;
import service.TbItemService2;

@Controller
public class ShowCartController {
	
	@Resource
	TbItemService2 tbItemServiceImpl2;
	
//	List<TbItemChild> list = null;
	
	@RequestMapping("cart/add/{id}.html")
	public String show(@PathVariable long id,HttpServletRequest request,int num) {
		tbItemServiceImpl2.addCart(id, num, request);
		return "cartSuccess";
	}
	
	@RequestMapping("cart/cart.html")
	public String showCart(Model model,HttpServletRequest request) {
		model.addAttribute("cartList",tbItemServiceImpl2.showCart(request));
		return "cart";
	}  
	
	@RequestMapping("cart/update/num/{id}/{num}.action")
	@ResponseBody
	public EgoResult update(@PathVariable long id,@PathVariable int num,HttpServletRequest request) {	
		return tbItemServiceImpl2.update(id, num, request);
	}
	
	@RequestMapping("cart/delete/num/{id}.action")
	@ResponseBody
	public EgoResult del(@PathVariable long id,HttpServletRequest request) {	
		return tbItemServiceImpl2.delete(id, request);
	}
}
