package service;

import pojo.TbUser;

public interface TbUserDubboService {
	TbUser selByNameAndPassword(TbUser user);
	
	int insUser(TbUser user);
}
