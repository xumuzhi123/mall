package service;

import java.util.List;

import pojo.EasyUIDataGrid;
import pojo.TbItemParam;

public interface TbItemParamDubboService {
	
	EasyUIDataGrid getAllTbItemParam(int page,int rows);

	int delByIds(String ids) throws Exception;

	int delete(long ids[]);
	
	
	TbItemParam selByCid(long cid);
	
	int insert(TbItemParam tbItemParam);
}
