package service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pojo.EgoResult;
import pojo.TbItemChild;
import pojo.TbOrderItem;

public interface TbItemService2 {
	TbItemChild getItem(long id);
	TbOrderItem getOrderItem(long id,int num);
	
	List<TbItemChild> showCart(HttpServletRequest request);
	void addCart(long id, int num,HttpServletRequest request);
	EgoResult update(long id, int num,HttpServletRequest request);
	EgoResult delete(long id, HttpServletRequest request);
}
