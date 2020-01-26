package redis.dao;

public interface JedisDao {
		/**
		 * �ж�key�Ƿ����
		 * @param key
		 * @return
		 */
		Boolean exists(String key);
		
		/**
		 * ɾ��
		 * @param key
		 * @return
		 */
		Long del(String key);
		
		/**
		 * ����ֵ
		 * @param key
		 * @param value
		 * @return
		 */
		String set(String key,String value);
		
		/**
		 * ȡֵ
		 * @param key
		 * @return
		 */
		String get(String key);
		
		Long expire(String key,int seconds);
}
