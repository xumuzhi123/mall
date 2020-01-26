package service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import mapper.TbItemCatMapper;
import pojo.EasyUIDataGrid;
import pojo.EasyUiTree;
import pojo.TbItemCat;
import pojo.TbItemCatExample;
import service.TbItemCatService;


public class TbItemCatServiceImpl implements TbItemCatService{
	@Resource
	TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<TbItemCat> show(long pid) {
		TbItemCatExample example  = new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(pid);
		
		return tbItemCatMapper.selectByExample(example);
	}

	@Override
	public TbItemCat selById(long id) {
		
		return tbItemCatMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbItemCat> showAllCat() {

		return tbItemCatMapper.selectByExample(new TbItemCatExample());
	}

}
