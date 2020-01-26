package service.impl;

import java.util.List;

import javax.annotation.Resource;

import mapper.TbContentCategoryMapper;
import pojo.TbContentCategory;
import pojo.TbContentCategoryExample;
import service.TbContentCategoryDubboService;


public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService{
	
	@Resource
	TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<TbContentCategory> getAll(long id) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
		return tbContentCategoryMapper.selectByExample(example);
	}

}
