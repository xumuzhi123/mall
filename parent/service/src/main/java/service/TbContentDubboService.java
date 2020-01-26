package service;
import java.util.List;

import pojo.EasyUIDataGrid;
import pojo.TbContent;


public interface TbContentDubboService {
	EasyUIDataGrid selContents(long id,int page,int rows);
	int insert(TbContent content);
	List<TbContent> selByCount(int count,boolean isSort);
}
