package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;


import pojo.PortalMenu;
import pojo.PortalMenuNode;
import pojo.TbItemCat;
import service.TbItemCatService;
import service.TbItemCatService2;

@Service
public class TbItemCatServiceImpl implements TbItemCatService2{
	@Reference
	TbItemCatService tbItemCatServiceImpl;
	
	@Override
	public PortalMenu showCatMenu() {
		List<TbItemCat> list = tbItemCatServiceImpl.show(0);
		
		PortalMenu menu = new PortalMenu();
		
		menu.setData(selAllMenu(list));
		
		return menu;
	}
	
	private List<Object> selAllMenu(List<TbItemCat> list){
		List<Object> listNode = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			if(tbItemCat.getIsParent()) {
				PortalMenuNode pmd = new PortalMenuNode();
				pmd.setU("/products/"+tbItemCat.getId()+".html");
				pmd.setN("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				pmd.setI(selAllMenu(tbItemCatServiceImpl.show(tbItemCat.getId())));
				listNode.add(pmd);
				
			
			
			}else {
				listNode.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		return listNode;
	}
	
}
