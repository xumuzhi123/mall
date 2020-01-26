package elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;

import pojo.People;
import pojo.TbItem;
import pojo.TbItemChild;
import service.impl.EsImpl;
import utils.JsonUtils;

public class test {
	
	
	public static void main(String[] args) throws UnknownHostException {
		String host1 = "121.36.16.7";
		String host2 = "192.168.77.129";
		
		//连接
		Client client = TransportClient.builder().build()
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host2), 9300));

		//提交数据
			//普通数据
//			IndexResponse response = client.prepareIndex("xujing","12","1").
//					setSource().execute().actionGet();
			//对象
//			People p = new People();
//			p.setName("xujing");
//			p.setAge(18);
//			String s = JsonUtils.objectToJson(p);
//			
//			IndexResponse response2 = client.prepareIndex("people","14").setSource(s).
//					execute().actionGet();
//		
//		
//		EsImpl e = new EsImpl();
//		e.init();
		
		//查询
		//根据id
//		GetResponse response = client.prepareGet("people", "13", "AW_WbBYNoqZ3hORfJCwv").get();
//		Map<String,Object> map = response.getSource();
//		System.out.println(map);
//		String s = JsonUtils.objectToJson(map);
//		System.out.println(s);
		
		SearchResponse response = client.prepareSearch("people")
		        .setTypes("13")
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//		        .setQuery(QueryBuilders.termQuery("title", "手机"))
		        .setQuery(QueryBuilders.queryStringQuery("苹果手机"))
		        .setFrom(0).setSize(70).setExplain(true)
		        
		        .setHighlighterFragmenter("苹果手机")
		        .execute()
		        .actionGet();
	
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		System.out.println(total);
		
		
		SearchHit[] searchHits = hits.hits();
		System.out.println(searchHits.length);
		Iterator<SearchHit> iterator = hits.iterator();
		int n = 0 ;
		while(iterator.hasNext()) {
			SearchHit h = iterator.next();
			System.out.println(h.getSourceAsString());
			n++;
		}
		System.out.println(n);
		
		List<TbItemChild> listchild = new ArrayList<>();
		
		for(SearchHit s : searchHits)
		{
			Map map = s.fields();
			
//			System.out.println(s.getSourceAsString());
			TbItemChild item = JsonUtils.jsonToPojo(s.getSourceAsString(),TbItemChild.class);
			//System.out.println(item.getId());
			listchild.add(item);
			System.out.println(s.getSourceAsString());
		
		}
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("itemList", listchild);
				
	}
}
