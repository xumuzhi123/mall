package service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import mapper.TbItemParamMapper;
import pojo.EasyUIDataGrid;
import pojo.TbItemParam;
import pojo.TbItemParamExample;
import service.TbItemParamDubboService;

public class TbItemParamDubboServiceImpl implements TbItemParamDubboService{
	
	@Resource
	TbItemParamMapper tbItemParamMapper;
	
	
	@Override
	public EasyUIDataGrid getAllTbItemParam(int page,int rows) {
		PageHelper.startPage(page, rows);
		
		//List<TbItemParam> list = tbItemParamMapper.selectByExample(new TbItemParamExample());
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		
		PageInfo<TbItemParam> pi = new PageInfo<>(list);
		
		EasyUIDataGrid er = new EasyUIDataGrid();
		
		er.setRows(pi.getList());
		er.setTotal(pi.getTotal());
	
		return er;
	}
	
	
	@Override
	public int delByIds(String ids) throws Exception {
		int index = 0 ;
		String[] idStr = ids.split(",");
		for (String id : idStr) {
			index +=tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		if(index==idStr.length){
			return 1;
		}else{
			throw new Exception("错误");
		}
	}


	@Override
	public int delete(long[] ids) {
		int index = 0;
		for (long l : ids) {
			index += tbItemParamMapper.deleteByPrimaryKey(l);
		}
		if(index == ids.length) {
			return 1;
		}else {
			return 0;
		}
	}


	@Override
	public TbItemParam selByCid(long cid) {
		return tbItemParamMapper.selectByPrimaryKey(cid);
	}


	@Override
	public int insert(TbItemParam tbItemParam) {
		int index = tbItemParamMapper.insertSelective(tbItemParam);
		return index;
	}
	
	
	

}
