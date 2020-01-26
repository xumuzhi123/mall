package service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import pojo.TbItem;
import pojo.TbItemChild;
import service.Es;

import service.TbItemService;

import utils.JsonUtils;

@Service
public class EsImpl implements Es{

	String host1 = "121.36.16.7";
	String host2 = "192.168.77.129";
	
	Client client = null;
	
	@Reference
	TbItemService tbItemServiceImpl;
	
	{	
		
		try {
			client = TransportClient.builder().build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host2), 9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//连接

	@Override
	public void init() throws UnknownHostException {

//		IndexResponse response2 = client.prepareIndex("people","13","2").setSource(s).
//				execute().actionGet();
//		
		List<TbItem> list = tbItemServiceImpl.selByStatus((byte)1);
		for (TbItem tbItem : list) {
			String s= JsonUtils.objectToJson(tbItem);
			IndexResponse response2 = client.prepareIndex("people","13").setSource(s).
					execute().actionGet();
		}
		
		
		
	}

	@Override
	public Map<String, Object> query(String q,int page,int rows) {
		SearchResponse response = client.prepareSearch("people")
		        .setTypes("13")
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//		        .setQuery(QueryBuilders.termQuery("title", "手机"))
		        .setQuery(QueryBuilders.queryStringQuery(q))
		        .setFrom((page-1)*rows).setSize(rows).setExplain(true)
		        .setHighlighterFragmenter(q)
		        .execute()
		        .actionGet();
	
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		System.out.println(total);
		SearchHit[] searchHits = hits.hits();
	
		List<TbItemChild> listchild = new ArrayList<>();
		for(SearchHit s : searchHits)
		{
			
//			System.out.println(s.getSourceAsString());
			TbItemChild item = JsonUtils.jsonToPojo(s.getSourceAsString(),TbItemChild.class);
			
			
			item.setImages(item.getImage() != null && !item.getImage().equals("")?new String[] {item.getImage()}:new String[] {});
			System.out.println(item.getImages()[0]);
			listchild.add(item);
			
			System.out.println(s.getSourceAsString());
			
			
		}
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("itemList", listchild);
		resultMap.put("totalPages", total%rows==0?total/rows:total/rows+1);
		System.out.println(searchHits.length);
		return resultMap;
	}


	
	

}
