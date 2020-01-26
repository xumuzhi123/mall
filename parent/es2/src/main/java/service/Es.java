package service;

import java.net.UnknownHostException;
import java.util.Map;

public interface Es {
	void init() throws UnknownHostException;
	
	Map<String,Object> query(String q,int page,int rows);
}
