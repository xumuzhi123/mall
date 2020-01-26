package service;

import java.util.List;

import pojo.EasyUIDataGrid;
import pojo.EasyUiTree;
import pojo.TbItemCat;

public interface TbItemCatService {
	List<TbItemCat> show(long pid);
	
	TbItemCat selById(long id);
	
	List<TbItemCat> showAllCat();
}
