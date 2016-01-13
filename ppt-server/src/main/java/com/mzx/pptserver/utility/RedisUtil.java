package com.mzx.pptserver.utility;

import com.mzx.pptcommon.constant.SystemConstant;
import com.mzx.pptcommon.exception.PPTshowException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.awt.image.BufferedImage;
import java.util.*;

public class RedisUtil {
	private static JedisPool REDIS_POOL = null;
	
	/**
	 * 过期时间，单位秒，默认3600一小时，小于等于0不生效
	 */
	private static int EXPIRETIME = 3600;

	/**
	 * 全局清理时的正则，为空时删除所有数据
	 */
	private static String CLEAR_PATTERN = "";
	private String key;
	private String field;

	public void setREDIS_POOL(JedisPool rEDIS_POOL) {
		REDIS_POOL = rEDIS_POOL;
	}

	public void setEXPIRETIME(int eXPIRETIME) {
		EXPIRETIME = eXPIRETIME;
	}

	public void setCLEAR_PATTERN(String cLEAR_PATTERN) {
		CLEAR_PATTERN = cLEAR_PATTERN;
	}

	private static Logger log = LoggerFactory.getLogger(RedisUtil.class);

	/**
	 * redis服务正常与否标志，true正常，写失败时将服务置为false
	 */
	private static volatile boolean REDIS_FLAG = true;

	public static boolean isREDIS_FLAG() {
		return REDIS_FLAG;
	}

	/**
	 * 最大连接数
	 */
	private static final int MAXCONNUM = 8;

	/**
	 * 最长等待时间，单位ms
	 */
	private static final long MAXWAIT = 1000;

	public static void main(String[] args) {
		RedisUtil redis = new RedisUtil();
		redis.createPool("192.168.252.3");
		try {
			redis.setObject("test_cfg_0001", "cfg_0001");
			redis.setObject("test_cfg_0002", "cfg_0002");
			System.out.println(redis.getObject("test_cfg_0001"));
			System.out.println(redis.getObject("test_cfg_0002"));
			System.out.println(redis.hGetBytes("hh","color"));

//			redis.delKeysLike("est_cfg_");
//			System.out.println(redis.getObject("test_cfg_0001"));
//			System.out.println(redis.getObject("test_cfg_0002"));
			redis.ListOperate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 描 叙: 开启redis服务，创建redis连接池
	 * 时 间: Aug 2, 2014 1:25:07 PM
	 *//*
	public void startRedis(RedisStartParaBean para) throws Exception {
		try {
			if (!StringUtils.isEmpty(para.password)) {
				createPool(para.IP, para.port, para.password);
			} else if (para.port > 0) {
				createPool(para.IP, para.port);
			} else {
				createPool(para.IP);
			}
			returnRedisConn(getRedisConn());
		} catch (Exception e) {
			throw e;
		}

	}*/

	/**
	 * 
	 * 描 叙: 创建REDIS资源池
	 * 时 间: 2014-1-8 下午02:52:53
	 */
	public void createPool(String ip) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(MAXCONNUM);
		config.setMaxWait(MAXWAIT);
		REDIS_POOL = new JedisPool(config, ip);
	}

	/**
	 * 
	 * 描 叙: 创建REDIS资源池
	 * 时 间: 2014-1-8 下午02:52:57
	 */
	public void createPool(String ip, int port) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(MAXCONNUM);
		config.setMaxWait(MAXWAIT);
		REDIS_POOL = new JedisPool(config, ip, port);
	}

	/**
	 * 
	 * 描 叙: 创建REDIS资源池
	 * 时 间: 2014-1-24 下午02:52:57
	 */
	public void createPool(String ip, int port, String password) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(MAXCONNUM);
		config.setMaxWait(MAXWAIT);
		REDIS_POOL = new JedisPool(config, ip, port, 2000, password);
	}

	/**
	 * 
	 * 描 叙: 销毁REDIS资源池
	 * 时 间: 2014-1-8 下午05:02:16
	 */
	public static void destoryPool() {
		if (REDIS_POOL != null) {
			REDIS_POOL.destroy();
		}
	}

	/**
	 * 
	 * 描 叙: 获取REDIS连接，并在redis从服务异常转为正常时清空redis
	 * 时 间: 2014-1-8 下午02:55:46
	 */
	public Jedis getRedisConn() throws Exception {
		Jedis jedis = null;
		if (REDIS_POOL != null) {
			try {
				jedis = REDIS_POOL.getResource();
				log.info("connect to redis success");
//				if ((!REDIS_FLAG) && (jedis != null)) {// 如果之前redis服务异常，现在正常拿到连接则将redis中數據清空
//					REDIS_FLAG = true;
//					if (StringUtils.isEmpty(CLEAR_PATTERN)) {
//						RedisUtil.clearRedis(jedis);
//					} else {
//						delByPattern(CLEAR_PATTERN);
//					}
//					log.error("redis服务已从异常状态恢复到正常，现在正常拿到连接并已将redis中数据清空");
//				}
			} catch (Exception e) {
				throw e;
			}
		} else {
			throw new Exception("REDIS资源池未成功创建");
		}
		return jedis;
	}

	/**
	 * 
	 * 描 叙: 获取redis中key对应的value
	 * 时 间: 2014-1-8 下午05:28:56
	 */
	public String get(String key) {
		Jedis conn = null;
		String result = null;
		try {
			conn = getRedisConn();
			result = conn.get(key);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * 
	 * 描 叙: 根据正则匹配获取一个set集合的值
	 * 时 间: Jul 31, 2014 10:53:50 AM
	 */
	public Set<String> getByPattern(String pattern) {
		Jedis conn = null;
		Set<String> result = new HashSet<String>();
		try {
			conn = getRedisConn();
			result = conn.keys(pattern);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * 
	 * 描 叙: 写redis
	 * <p>
	 * 时 间: 2014-1-8 下午05:28:56
	 */
	public boolean set(String key, String value) {
		Jedis conn = null;
		boolean result = false;
		try {
			conn = getRedisConn();
			if ("OK".equalsIgnoreCase(conn.set(key, value))) {
				if (EXPIRETIME > 0) {
					conn.expire(key, EXPIRETIME);
				}
				result = true;
			}
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
			REDIS_FLAG = false;
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * 
	 * 描 叙: 根据key值获取T类型对象 通过将对象转json字符串格式存储读取
	 * <p>
	 * 时 间: Aug 1, 2014 3:36:06 PM
	 */
	@SuppressWarnings("rawtypes")
	public Object getByJson(String key, Class T) {
		Jedis conn = null;
		Object result = null;
		try {
			conn = getRedisConn();
			result = JSONObject.toBean(JSONObject.fromObject(conn.get(key)), T);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * 
	 * 描 叙: 根据key值获取T类型对象集合 通过将对象转json字符串格式存储读取
	 * <p>
	 * 时 间: Aug 1, 2014 3:36:06 PM
	 */
	@SuppressWarnings("rawtypes")
	public Collection getCollectionByJson(String key, Class T) {
		Jedis conn = null;
		Collection result = null;
		try {
			conn = getRedisConn();
			result = JSONArray.toCollection(
					JSONArray.fromObject(conn.get(key)), T);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * 
	 * 描 叙: 写对象 通过将对象转json字符串格式存储读取
	 * 时 间: Aug 1, 2014 2:16:38 PM
	 */
	public boolean setByJson(String key, Object value) {
		boolean result = false;
		if (value == null) {
			return result;
		}
		Jedis conn = null;
		try {
			conn = getRedisConn();
			if (value instanceof Collection) {
				if ("OK".equalsIgnoreCase(conn.set(key,
						JSONArray.fromObject(value).toString()))) {
					if (EXPIRETIME > 0) {
						conn.expire(key, EXPIRETIME);
					}
					result = true;
				}
			}
			// 将对象转化为json字符串格式存储
			else if ("OK".equalsIgnoreCase(conn.set(key,
					JSONObject.fromObject(value).toString()))) {
				result = true;
			}
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
			REDIS_FLAG = false;
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * 
	 * 描 叙: 获取序列化存储的对象
	 * 时 间: Aug 2, 2014 11:03:24 AM
	 */
	public Object getObject(String key) {
		Object result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result = SerializeUtil.unserialize(conn.get(key.getBytes()));
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * 
	 * 描 叙: 存储序列化的对象
	 * 时 间: Aug 2, 2014 11:05:11 AM
	 */
	public boolean setObject(String key, Object value) {
		Jedis conn = null;
		boolean result = false;
		try {
			conn = getRedisConn();
			if ("OK".equalsIgnoreCase(conn.set(key.getBytes(),
					SerializeUtil.serialize(value)))) {
				if (EXPIRETIME > 0) {
					conn.expire(key, EXPIRETIME);
				}
				result = true;
			}
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
			REDIS_FLAG = false;
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	/**
	 * 
	 * 描  叙: 左边插入队列(the head (LPUSH) of the list)
	 * key:队列的key值
	 * value:队列的value
	 * 时  间: 2014-12-1 下午3:03:33
	 */
	public long lPushString(String key,String value){
		Jedis conn = null;
		Long result = null;
		try {
			conn = getRedisConn();
			result=conn.lpush(key, value);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	/**
	 * 
	 * 描  叙: 右边出队列(the tail (RPUSH) of the list)
	 * key:队列的key值
	 * 时  间: 2014-12-1 下午3:04:31
	 */
	public String rPopString(String key){
		String result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result =conn.rpop(key);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	/**
	 * 
	 * 描  叙: 右边出阻塞队列
	 * timeout 超时时间，超出超时时间返回null
	 * key:队列的key值
	 * <p>
	 * 时  间: 2014-12-1 下午3:04:59
	 */
	public String bRPopString(int timeout,String key){
		String result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			List<String> list=conn.brpop(timeout,key);
			result =list.get(1);		
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	/**
	 * 
	 * 描  叙: 从左边开始获取队列别表
	 * key:队列的key值
	 * <p>
	 * 时  间: 2014-12-1 下午3:05:28
	 */
	public List<String> lRangeStringList(String key){
		List<String> result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result =conn.lrange(key, 0, -1);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	/**
	 * 
	 * 描  叙: 重置队列某个值
	 * key:队列的key值
	 * index:索引值
	 * value:重置后的值
	 * <p>
	 * 时  间: 2014-12-1 下午3:06:06
	 */
	public String lSetString(String key,int index,String value){
		String result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result=conn.lset(key, index, value);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	/**
	 * 
	 * 描  叙:根据索引获取队列某个值
	 * key:队列的key值
	 * index:索引值
	 * <p>
	 * 时  间: 2014-12-1 下午3:06:26
	 */
	public String lIndexString(String key,int index){
		String result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result=conn.lindex(key, index);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	/**
	 * 
	 * 描  叙: 获取队列长度
	 * key:队列的key值
	 * <p>
	 * 时  间: 2014-12-1 下午3:06:50
	 */
	public long lLengthString(String key){
		Long result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result=conn.llen(key);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result==null?0:result;
	}
	
	/**
	 * 
	 * 描  叙: 把队列某个值移除，count是移除的个数，比如队列可能有重复的数据
	 * <p>
	 * 时  间: 2014-12-1 下午3:07:04
	 */
	public long lRemoveString(String key,int count,String value){
		Long result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result=conn.lrem(key, count, value);
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result==null?0:result;
	}
	
	public long lPushObject(String key,Object value){
		Jedis conn = null;
		Long result = null;
		try {
			conn = getRedisConn();
			result=conn.lpush(key.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	public Object bRPopObject(int timeout,String key){
		Object result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			List<byte[]> list=conn.brpop(timeout,key.getBytes());
			result =SerializeUtil.unserialize(list.get(1));		
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	/**
	 * 
	 * 描  叙: hash设置key的field域值为value
	 * <p>
	 * 时  间: 2014-12-8 下午12:56:45
	 */
	public long hSetObject(String key,String field,Object value){
		Long result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result=conn.hset(key.getBytes(), field.getBytes(), SerializeUtil.serializeImg((BufferedImage) value));
		} catch (Exception ex) {
			log.info("connect to redis fail");
			returnBrokenRedisConn(conn);
			throw new PPTshowException(SystemConstant.ResponseStatusCode.ABNORMAL.getCode(),"redis 连接失败");
		} finally {
			returnRedisConn(conn);
		}
		return result==null?0:result;
	}
	/**
	 * 
	 * 描  叙: hash获取key的field域值

	 * 时  间: 2014-12-8 下午3:02:39
	 */
	public Object hGetObject(String key,String field){
		Object result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result=SerializeUtil.unserialize(conn.hget(key.getBytes(), field.getBytes()));
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}


	/**
	 * hash获取key的field域值
	 * @param key
	 * @param field
	 * @return
	 */
	public byte[] hGetBytes(String key,String field) {
		byte[] result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result = conn.hget(key.getBytes(), field.getBytes());
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
			log.error("redis 连接失败");
			throw new PPTshowException(SystemConstant.ResponseStatusCode.ABNORMAL.getCode(),"redis 连接失败");
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * Return the number of items in a hash.
	 * @param key
	 * @return
	 */
	public Long hGetLen(String key) {
		Long result = 0L;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			result = conn.hlen(key.getBytes());
		} catch (Exception ex) {
			log.error("redis 连接失败");
			returnBrokenRedisConn(conn);
			throw new PPTshowException(SystemConstant.ResponseStatusCode.ABNORMAL.getCode(),"redis 连接失败");
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	/**
	 * 
	 * 描  叙: 删除key的field域

	 * 时  间: 2014-12-8 下午1:01:22
	 */
	public long hDelObject(String key,String field){
		Long result = null;
		Jedis conn = null;
		try {
			conn = getRedisConn();
			if(conn.hexists(key.getBytes(), field.getBytes())){
				result=conn.hdel(key.getBytes(), field.getBytes());
			}
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result==null?0:result;
	}

	/**
	 * 
	 * 描 叙: 保存redis中数据到文件(此方法可以不显式调用，因为redis服务器有自动的保存数据策略)
	 * 时 间: 2014-1-8 下午05:28:56
	 */
	public boolean save() {
		Jedis conn = null;
		boolean result = false;
		try {
			conn = getRedisConn();
			if ("OK".equalsIgnoreCase(conn.save())) {
				result = true;
			}
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * 
	 * 描 叙: 将当前所有redis中数据清理掉
	 * <p>
	 * 时 间: Aug 2, 2014 1:09:27 PM
	 */
	private static boolean clearRedis(Jedis conn) {
		boolean result = false;
		if ("OK".equalsIgnoreCase(conn.flushAll())) {
			result = true;
		}
		return result;
	}

	/**
	 * 删除某记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean del(String value) {
		Jedis conn = null;
		boolean result = false;
		try {
			conn = getRedisConn();
			conn.del(value);
			conn.del(value.getBytes());
			result = true;
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
			REDIS_FLAG = false;
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * 
	 * 描 叙: 根据正则匹配删除所有匹配的key
	 * <p>
	 * 时 间: Aug 6, 2014 10:17:34 AM
	 */
	public boolean delByPattern(String pattern) {
		Jedis conn = null;
		boolean result = false;
		try {
			conn = getRedisConn();
			Set<String> set = getByPattern(pattern);
			Iterator<String> it = set.iterator();
			String[] array = new String[set.size()];
			int i =0;
			while(it.hasNext()){
				array[i]= it.next();
				i++;
			}
			conn.del(array);
			result = true;
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
			REDIS_FLAG = false;
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}
	
	/**
	 * 批量模糊删除redis的缓存
	 * @param likeKey
	 * @return
	 */
	public boolean delKeysLike(String likeKey){
		return delByPattern("*"+likeKey+"*");
	}
	
	@SuppressWarnings("null")
	public List<Object> batchSetString(List<Pair<String,String>> list){
		Jedis jedis=null;
		try {
			Pipeline pipeline=jedis.pipelined();
			for(Pair<String,String> pair:list){
				pipeline.set(pair.getKey(),pair.getValue());
			}
			return pipeline.syncAndReturnAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			returnBrokenRedisConn(jedis);
		} finally {
			returnRedisConn(jedis);
		}
		return null;
	}

	/**
	 * 
	 * 描 叙: 获取redis中数据量
	 * <p>
	 * 时 间: 2014-1-8 下午05:28:56
	 */
	public long size() {
		Jedis conn = null;
		long result = 0;
		try {
			conn = getRedisConn();
			result = conn.dbSize();
		} catch (Exception ex) {
			returnBrokenRedisConn(conn);
		} finally {
			returnRedisConn(conn);
		}
		return result;
	}

	/**
	 * 
	 * 描 叙: 归还使用完毕的redis连接
	 * 时 间: 2014-1-8 下午05:19:56
	 */
	public void returnRedisConn(Jedis jedis) {
		if (jedis != null && REDIS_POOL != null) {
			REDIS_POOL.returnResource(jedis);
		}
	}

	/**
	 * 
	 * 描 叙: 归还异常的redis连接
	 * 时 间: 2014-1-8 下午05:19:56
	 */
	public void returnBrokenRedisConn(Jedis jedis) {
		if (jedis != null && REDIS_POOL != null) {
			REDIS_POOL.returnBrokenResource(jedis);
		}
	}
	
	/**
	 * 
	 * 描  叙: 清空缓存
	 * 时  间: Aug 6, 2014 3:30:49 PM
	 */
	public boolean clearAllCache() throws Exception{
		try {
			if (StringUtils.isEmpty(CLEAR_PATTERN)) {
				clearRedis(getRedisConn());
			} else {
				delByPattern(CLEAR_PATTERN);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("clearAllCache", e);
			throw e;
		}
	}
	
	public void ListOperate() throws Exception {
//		delKeysLike("TEST_UPDATE_DEVCIE_MSG_LIST");
//		delKeysLike("TEST_SEND_MSG_LIST");
//		delKeysLike("TEST_SEND_MSG_MAP");
		//del("TEST_SEND_MSG_LIST");
		//System.out.println(lPushString("TEST_SEND_MSG_LIST", "1"));
		//System.out.println(bRPopString(1000,"TEST_SEND_MSG_LIST"));
    }
}
