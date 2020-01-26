package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import pojo.TbContent;
import redis.dao.JedisDao;
import service.TbContentDubboService;
import service.TbContentService;
import utils.JsonUtils;
@Service
public class TbContentServiceImpl implements TbContentService{
	@Reference
	private TbContentDubboService tbContentDubboServiceImpl;
	@Resource
	private JedisDao JedisDaoImpl;
	
	@Value("${redis.bigpic.key}")
	private String key;
	
	@Override
	public String showBigPic() {
		
		if(JedisDaoImpl.exists(key)){
			String value = JedisDaoImpl.get(key);
			if(value!=null&&!value.equals("")){
				return value;
			}
			System.out.println("123");
		}
		
		
		List<TbContent> list = tbContentDubboServiceImpl.selByCount(6, true);
		
		List<Map<String,Object>> listResult = new ArrayList<>();
		
		for (TbContent tc : list) {
			Map<String,Object> map = new HashMap<>();
			
			map.put("srcB", tc.getPic2());
			
			map.put("height", 240);
			map.put("alt", ",");
			map.put("width", 670);
			
			map.put("src", tc.getPic());
			
			map.put("widthB", 550);
			
			map.put("href", tc.getUrl() );
			
			map.put("heightB", 240);
			
			listResult.add(map);
		}
		
		String listJson = JsonUtils.objectToJson(listResult);
		
		JedisDaoImpl.set(key, listJson);
		return listJson;
	}

}
