package service.impl;

 import java.util.List;

import javax.annotation.Resource;

import mapper.TbAdminMapper;
import pojo.TbAdmin;
import service.TbAdminService;

public class TbAdminServiceImpl implements TbAdminService{
	
	@Resource
	private TbAdminMapper tbMapper;
	
	public TbAdminServiceImpl() {
	}


	public TbAdminServiceImpl(TbAdminMapper tbMapper) {
		this.tbMapper = tbMapper;
	}

	@Override
	public List<TbAdmin> getAdmins() {
		return tbMapper.seladmin();
	}
	
	public TbAdminMapper getTbMapper() {
		return tbMapper;
	}

	public void setTbMapper(TbAdminMapper tbMapper) {
		this.tbMapper = tbMapper;
	}
	
}
