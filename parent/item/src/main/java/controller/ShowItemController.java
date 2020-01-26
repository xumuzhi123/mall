package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.TbItem;
import pojo.TbItemChild;
import service.TbItemService2;

@Controller
public class ShowItemController {
	
	@Resource
	TbItemService2 tbItemService2Impl;
	
	@RequestMapping("item/{id}.html")
	public String showPage(@PathVariable long id,HttpServletRequest request) {
		TbItem item = tbItemService2Impl.getItem(id);
		
		TbItemChild itemchild = new TbItemChild();
		itemchild.setBarcode(item.getBarcode());
		itemchild.setCid(item.getCid());
		itemchild.setCreated(item.getCreated());
		itemchild.setId(item.getId());
		itemchild.setImage(item.getImage());
		itemchild.setImages(item.getImage() != null && !item.getImage().equals("")?new String[] {item.getImage()}:new String[] {});
		itemchild.setNum(item.getNum());
		itemchild.setPrice(item.getPrice());
		itemchild.setSellPoint(item.getSellPoint());
		itemchild.setStatus(item.getStatus());
		itemchild.setTitle(item.getTitle());
		itemchild.setUpdated(item.getUpdated());
		System.out.println(itemchild.getId());
		
		request.setAttribute("item",itemchild);
		
		return "item";
	}
	
}
