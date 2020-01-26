package service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import mapper.TbContentMapper;
import pojo.EasyUIDataGrid;
import pojo.TbContent;
import pojo.TbContentExample;
import service.TbContentDubboService;

public class TbContentDubboServiceImpl implements TbContentDubboService{
	@Resource
	TbContentMapper tbContentMapper;
	
	@Override
	public EasyUIDataGrid selContents(long id,int page,int rows) {
		PageHelper.startPage(page, rows);
		
		
		TbContentExample example = 	new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		List<TbContent> list = tbContentMapper.selectByExample(example);
		
		PageInfo<TbContent> pi = new PageInfo<>();
		pi.setList(list);
		
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		
		return datagrid;
	}

	@Override
	public int insert(TbContent content) {
		
		int index = 0;
		
		try {
			index = tbContentMapper.insert(content);
		}catch(Exception e) {
			new Throwable("≤Â»Î ß∞‹");
		}
		
		return index;
	}

	@Override
	public List<TbContent> selByCount(int count, boolean isSort) {
		TbContentExample example = new TbContentExample();
		//ÊéíÂ∫è
		if(isSort){
			example.setOrderByClause("updated desc");
		}
		if(count!=0){
			PageHelper.startPage(1, count);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			PageInfo<TbContent> pi = new PageInfo<>(list);
			return pi.getList();
		}else{
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
		
	}

}
