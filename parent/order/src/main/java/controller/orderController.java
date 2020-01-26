package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class orderController {
	
	@RequestMapping("order/order-cart.html")
	public String showpage() {
		
		return "success";
	}
}
