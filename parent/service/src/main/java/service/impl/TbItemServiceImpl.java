package service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import mapper.TbItemDescMapper;
import mapper.TbItemMapper;
import mapper.TbItemParamMapper;
import pojo.EasyUIDataGrid;
import pojo.EgoResult;
import pojo.TbItem;
import pojo.TbItemDesc;
import pojo.TbItemExample;
import pojo.TbItemParam;
import service.TbItemService;

public class TbItemServiceImpl implements TbItemService{
	
	@Resource
	TbItemMapper tbItemMapper;

	@Resource
	TbItemDescMapper tbItemDescMapper;
	
	@Resource
	TbItemParamMapper tbItemParamMapper;
	
	@Override
	public EasyUIDataGrid getAllItem(int page,int rows) {
		PageHelper.startPage(page, rows);
		
		TbItemExample tbItemExample = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
		
		PageInfo<TbItem> pi = new PageInfo<>(list); 
		
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		return datagrid;
	}
	@Override
	public EgoResult del(String ids) {
		String id[] = ids.split(",");
		int j = 0;
		int len = id.length;
		EgoResult result = new EgoResult();
		
		for (String string : id) {
			long l = Long.parseLong(string);
			j +=  tbItemMapper.deleteByPrimaryKey(l);
		}

		if(j == len) {
			result.setData(200);
		}else {
			result.setData(201);
			result.setMsg("ʧ��");
		}
		result.setStatus(200);
		return result;
	}
	@Override
	public int update(String ids,byte status) {
		int i = 0;
		String id[] = ids.split(",");
		for (String string : id) {
			long l = Long.parseLong(string);
			TbItem item = new TbItem();
			item.setId(l);
			item.setStatus((byte)status);
			item.setUpdated(new Date(System.currentTimeMillis()));
			
			i = i + tbItemMapper.updateByPrimaryKeySelective(item);
		}
		return i;
	}
	
	public EgoResult update(TbItem item) {
		EgoResult result = new EgoResult();
		
		item.setUpdated(new Date(System.currentTimeMillis()));
		
		tbItemMapper.updateByPrimaryKey(item);
		result.setStatus(200);
		
		return result;
		
	}


	@Override
	public int insItem(TbItem item, TbItemDesc desc, TbItemParam itemparams) throws Exception {
		int index = 0;
		try {
			index =  tbItemMapper.insertSelective(item);
			index += tbItemDescMapper.insertSelective(desc);
			index += tbItemParamMapper.insertSelective(itemparams);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(index == 3) {
			return 1;
		}
		return 0;
	}
	@Override
	public List<TbItem> selByStatus(byte i) {
		TbItemExample example = new TbItemExample();
		example.createCriteria().andStatusEqualTo(i);
		
		
		return tbItemMapper.selectByExample(example);
	}
	@Override
	public TbItem selById(long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}




}
