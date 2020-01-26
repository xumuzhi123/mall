package service.impl;

import java.util.List;

import javax.annotation.Resource;

import mapper.TbUserMapper;
import pojo.TbUser;
import pojo.TbUserExample;
import service.TbUserDubboService;

public class TbUserDubboServiceImpl implements TbUserDubboService{
	
	@Resource
	TbUserMapper tbUserMapper;
	
	
	@Override
	public TbUser selByNameAndPassword(TbUser user) {
		
		TbUserExample example = new TbUserExample();
		example.createCriteria()
		.andUsernameEqualTo(user.getUsername());
		
		List<TbUser> list = tbUserMapper.selectByExample(example);
		
		return tbUserMapper.selectByPrimaryKey((long)37);
	}


	@Override
	public int insUser(TbUser user) {
		tbUserMapper.insert(user);
		tbUserMapper.insertSelective(user);
		
		return tbUserMapper.insert(user);
	}

}
