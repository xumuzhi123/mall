package service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import pojo.TbItem;
import service.TbItemService;
import service.TbItemService2;

@Service
public class TbItemServiceImpl2 implements TbItemService2{
	
	@Reference
	TbItemService TbitemServiceImpl;
	
	@Override
	public TbItem getItem(long id) {
		
		return TbitemServiceImpl.selById(id);
	}

}
