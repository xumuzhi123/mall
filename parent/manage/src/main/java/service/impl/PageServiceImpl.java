package service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import pojo.TbAdmin;
import service.PageService;
import service.TbAdminService;

@Service
public class PageServiceImpl implements PageService {

	@Reference
	TbAdminService tbAdminServiceImpl;
	
	@Override
	public List<TbAdmin> getAdmins() {

		return tbAdminServiceImpl.getAdmins();
	}

}
