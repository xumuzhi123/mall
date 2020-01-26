package controller;

import javax.annotation.Resource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.People;
import pojo.PortalMenu;
import service.TbItemCatService2;

@Controller
public class TbItemCatController {
	
	@Resource
	TbItemCatService2 tbItemCatServiceImpl;
	
	@RequestMapping("rest/itemcat/all")
	@ResponseBody
	public MappingJacksonValue showItemCat(String callback) {
		
//		People p = new People();
//		p.setId(123);
//		p.setName("456");
		
		MappingJacksonValue mjv = new MappingJacksonValue(tbItemCatServiceImpl.showCatMenu());
		PortalMenu pm = tbItemCatServiceImpl.showCatMenu();
//		
//		
//		System.out.println(pm.getData().get(0));
//		System.out.println(mjv.getValue());
		
		mjv.setJsonpFunction(callback);
		return mjv;
	}
	
}
