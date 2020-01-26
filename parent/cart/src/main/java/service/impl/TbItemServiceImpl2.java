package service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;


import pojo.EgoResult;
import pojo.TbItem;
import pojo.TbItemChild;
import pojo.TbOrderItem;
import redis.dao.JedisDao;
import service.TbItemService;
import service.TbItemService2;
import utils.CookieUtils;
import utils.HttpClientUtil;
import utils.JsonUtils;

@Service
public class TbItemServiceImpl2 implements TbItemService2{
	
	@Reference
	TbItemService TbitemServiceImpl;
	
	@Value("${passport.url}")
	private String passportUrl;
	
	
	@Value("${cart.key}")
	private String cartKey;
	
	@Resource
	private JedisDao jedisDaoimpl; 
	
	@Override
	public TbItemChild getItem(long id) {
		TbItem item = TbitemServiceImpl.selById(id);
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
		
		return itemchild;
	}

	@Override
	public TbOrderItem getOrderItem(long id,int num) {
		TbItem item = TbitemServiceImpl.selById(id);
		
		TbOrderItem oi = new TbOrderItem();
		oi.setId(String.valueOf(id));
		oi.setItemId(String.valueOf(id));
		oi.setNum(num);
		oi.setPicPath(item.getImage());
		
		oi.setPrice(item.getPrice());
		oi.setTitle(item.getTitle());
		oi.setTotalFee(num*oi.getPrice());
		
		
		return null;
	}
	
	
	@Override
	public void addCart(long id, int num,HttpServletRequest request) {
		List<TbItemChild> list = new ArrayList<>();
		String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl+token);
		System.out.println(jsonUser);
		
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		
		String key = cartKey+((LinkedHashMap)er.getData()).get("username");

		if(jedisDaoimpl.exists(key)) {
			String json = jedisDaoimpl.get(key);
			
			
			if(json!= null && !json.equals("")) {
				list = JsonUtils.jsonToList(json, TbItemChild.class);
				for(TbItemChild ct:list) {
					if((long)ct.getId() == id) {
						ct.setNum(ct.getNum()+num);
						jedisDaoimpl.set(key, JsonUtils.objectToJson(list));
						return ;
					}
				}
				
			}
			
		}
		
		TbItem item = TbitemServiceImpl.selById(id);
		TbItemChild child = new TbItemChild();
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setImages(item.getImage()==null || item.getImage().equals("")?new String[1]:item.getImage().split(","));
		child.setNum(num);
		child.setPrice(item.getPrice());
		
		list.add(child);
		jedisDaoimpl.set(key,JsonUtils.objectToJson(list));
		
	}
	
	@Override
	public List<TbItemChild> showCart(HttpServletRequest request) {
		
		String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl+token);
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		String key = cartKey+((LinkedHashMap)er.getData()).get("username");
		
		String json = jedisDaoimpl.get(key);
		
		return JsonUtils.jsonToList(json, TbItemChild.class);
	}

	
	@Override
	public EgoResult update(long id, int num,HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl+token);
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		String key = cartKey+((LinkedHashMap)er.getData()).get("username");
		String json = jedisDaoimpl.get(key);
		List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
		
		for (TbItemChild tbItemChild : list) {
			if((long)tbItemChild.getId() == id) {
				tbItemChild.setNum(num);
			}
		}
		jedisDaoimpl.set(key, JsonUtils.objectToJson(list));
		EgoResult er2 = new EgoResult();
		er2.setStatus(200);
		return er2;
	}

	@Override
	public EgoResult delete(long id, HttpServletRequest request) {
		
		String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl+token);
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		String key = cartKey+((LinkedHashMap)er.getData()).get("username");
		String json = jedisDaoimpl.get(key);
		List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
		TbItemChild child = null;
		for (TbItemChild tbItemChild : list) {
			if((long)tbItemChild.getId()==id) {
				child = tbItemChild;
			}
		}
		list.remove(child);
		
		jedisDaoimpl.set(key, JsonUtils.objectToJson(list));
		EgoResult er2 = new EgoResult();
		er2.setStatus(200);
		return er2;
	}

}
