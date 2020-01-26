package service;

import java.util.List;

import pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	List<TbContentCategory> getAll(long id);
}
