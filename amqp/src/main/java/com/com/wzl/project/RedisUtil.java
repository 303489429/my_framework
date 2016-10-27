package com.com.wzl.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

import java.util.*;

public class RedisUtil {	
	
	private static JedisPool pool;
	
	private static JedisPool poolUuid;
	
	private static JedisPool poolCall;
	
	private static Map<String, JedisPool> poolMap = new HashMap<String, JedisPool>();
	
	private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(1024);
		config.setMaxIdle(200);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		pool = new JedisPool(config,"172.16.1.142",6379, 10 * 60 * 1000);
		poolUuid = new JedisPool(config,"172.16.1.142",6379, 10 * 60 * 1000);
		poolCall = new JedisPool(config,"172.16.1.142",6379, 10 * 60 * 1000);
		poolMap.put(RedisDataSource.SOURCE_0013.name(), pool) ;
		poolMap.put(RedisDataSource.SOURCE_UUID.name(), poolUuid);
		poolMap.put(RedisDataSource.SOURCE_CALL.name(), poolCall);
	}

	/**
	 * Handle jedisException, write log and return whether the connection is broken.
	 */
	private static boolean handleJedisException(JedisPool jedisPool, JedisException jedisException) {
		if (jedisException instanceof JedisConnectionException) {
			logger.error("Redis connection lost.", jedisException);
		} else if (jedisException instanceof JedisDataException) {
			if ((jedisException.getMessage() != null) && (jedisException.getMessage().indexOf("READONLY") != -1)) {
				logger.error("Redis connection are read-only slave.", jedisException);
			} else {
				// dataException, isBroken=false
				return false;
			}
		} else {
			logger.error("Jedis exception happen.", jedisException);
		}
		return true;
	}

	/**
	 * Return jedis connection to the pool, call different return methods depends on the conectionBroken status.
	 */
	private static void closeResource(JedisPool jedisPool, Jedis jedis, boolean conectionBroken) {
		try {
			if (conectionBroken) {
				jedisPool.returnBrokenResource(jedis);
			} else {
				jedisPool.returnResource(jedis);
			}
		} catch (Exception e) {
			logger.error("return back jedis failed, will fore close the jedis.", e);
			destroyJedis(jedis);
		}
	}
	
	/**
	 * 在Pool以外强行销毁Jedis.
	 */
	private static void destroyJedis(Jedis jedis) {
		if ((jedis != null) && jedis.isConnected()) {
			try {
				try {
					jedis.quit();
				} catch (Exception e) {
					logger.error("jedis quit failed", e);
				}
				jedis.disconnect();
			} catch (Exception e) {
				logger.error("jedis disconnect failed", e);
			}
		}
	}
	
	public static String get(String key) {
		Jedis jedis = null;
		String value = null;
		boolean broken = false;
		try {
			jedis = pool.getResource();
			value = jedis.get(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return value;
	}
	
	public static String getApp(String key) {
		Jedis jedis = null;
		String value = null;
		boolean broken = false;
		try {
			jedis = pool.getResource();
			jedis.select(1);
			value = jedis.get(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return value;
	}
	
	public static void set(String key, String value){
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = pool.getResource();
			jedis.set(key, value);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static void hmset(String key, Map<String, String> cacheMap){
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = pool.getResource();
			jedis.hmset(key, cacheMap);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static List<String> hmget(String key, String... propertyName){
		Jedis jedis = null;
		List<String> values = null;
		boolean broken = false;
		try {
			jedis = pool.getResource();
			values = jedis.hmget(key, propertyName);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return values;
	}
	
	public static Set<String> keys(String keyPattern) {
		Jedis jedis = null;
		Set<String> keys = null;
		boolean broken = false;
		try {
			jedis = pool.getResource();
			keys = jedis.keys(keyPattern);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return keys;
	}
	
	public static Set<String> keys(RedisDataSource dataSource, int dbIndex, String keyPattern) {
		Jedis jedis = null;
		Set<String> keys = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			keys = jedis.keys(keyPattern);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return keys;
	}
	
	public static long del(String key) {
		Jedis jedis = null;
		long delCount = 0l;
		boolean broken = false;
		try {
			jedis = pool.getResource();
			delCount = jedis.del(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return delCount;
	}
	
	public static Set<Tuple> zrangeByScoreWithScores(String key, Double min, Double max) {
		Jedis jedis = null;
		Set<Tuple> ranges = new HashSet<Tuple>();
		boolean broken = false;
		try {
			jedis = pool.getResource();
			ranges = jedis.zrangeByScoreWithScores(key, min, max);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ranges;
	}
	
	public static Set<Tuple> zrevrangeByScoreWithScores(RedisDataSource dataSource, int dbIndex, String key, Double min, Double max, int offset, int count) {
		Jedis jedis = null;
		Set<Tuple> ranges = new HashSet<Tuple>();
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ranges = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ranges;
	}
	
	public static Set<String> zrevrange(RedisDataSource dataSource, int dbIndex, String key, long start, long end) {
		Jedis jedis = null;
		Set<String> ranges = new HashSet<String>();
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ranges = jedis.zrevrange(key, start, end);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ranges;
	}
	
	public static Long zrevrank(RedisDataSource dataSource, int dbIndex, String key, String member) {
		Jedis jedis = null;
		Long rank = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			rank = jedis.zrevrank(key, member);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return rank;
	}
	
	
	public static void zRem(String key, String field) {
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = pool.getResource();
			jedis.zrem(key, field);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static Long zcard(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		Long count = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			count = jedis.zcard(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return count;
	}
	
	public static long incr(String key , Long gap){
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = pool.getResource();
			return jedis.incrBy(key, gap);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static long incrBy(RedisDataSource dataSource, int dbIndex, String key , long gap){
		Jedis jedis = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			return jedis.incrBy(key, gap);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static long incr(RedisDataSource dataSource, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		long counter = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			counter = jedis.incr(key); 
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return counter;
	}
	
	public static void setex(RedisDataSource dataSource, int dbIndex, String key, String value, int seconds){
		JedisPool pool = null;
		Jedis jedis = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			jedis.setex(key, seconds, value);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static long setnx(RedisDataSource dataSource, int dbIndex, String key, String value){
		JedisPool pool = null;
		Jedis jedis = null;
		long ret = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.setnx(key, value);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static void set(RedisDataSource dataSource, int dbIndex, String key, String value){
		JedisPool pool = null;
		Jedis jedis = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			jedis.set(key, value);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static String get(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		String value = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			value = jedis.get(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return value;
	}
	
	public static long scard(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		long ret = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.scard(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static void subscribe(RedisDataSource dataSource, int dbIndex, JedisPubSub jedisPubSub, String channel) {
		JedisPool pool = null;
		Jedis jedis = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			jedis.subscribe(jedisPubSub, channel);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static void publish(RedisDataSource dataSource, int dbIndex, String channel, String message) {
		JedisPool pool = null;
		Jedis jedis = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			jedis.publish(channel, message);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static long lpush(RedisDataSource dataSource, int dbIndex, String key, String value) {
		JedisPool pool = null;
		Jedis jedis = null;
		long ret = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.lpush(key, value);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static String rpop(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		String value = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			value = jedis.rpop(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return value;
	}
	
	public static long sadd(RedisDataSource dataSource, int dbIndex, String key, String member) {
		JedisPool pool = null;
		Jedis jedis = null;
		long ret = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.sadd(key, member);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static String spop(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		String value = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			value = jedis.spop(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return value;
	}
	
	public static String srandmember(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		String value = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			value = jedis.srandmember(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return value;
	}
	
	public static List<String> srandmember(RedisDataSource dataSource, int dbIndex, String key, int count) {
		JedisPool pool = null;
		Jedis jedis = null;
		List<String> members = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			members = jedis.srandmember(key, count);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return members;
	}
	
	public static long srem(RedisDataSource dataSource, int dbIndex, String key, String member) {
		JedisPool pool = null;
		Jedis jedis = null;
		long ret = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.srem(key, member);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static Set<String> smembers(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		Set<String> members = new HashSet<String>();
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			members = jedis.smembers(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return members;
	}
	
	public static long llen(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		long ret = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.llen(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static String lindex(RedisDataSource dataSource, int dbIndex, String key, long index) {
		JedisPool pool = null;
		Jedis jedis = null;
		String value = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			value = jedis.lindex(key, index);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return value;
	}
	
	public static long lrem(RedisDataSource dataSource, int dbIndex, String key, int count, String value) {
		JedisPool pool = null;
		Jedis jedis = null;
		long ret = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.lrem(key, count, value);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static long del(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		long ret = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.del(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static boolean sismember(RedisDataSource dataSource, int dbIndex, String key, String member) {
		JedisPool pool = null;
		Jedis jedis = null;
		boolean ismember = false;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ismember = jedis.sismember(key, member);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ismember;
	}
	
	public static List<String> lrange(RedisDataSource dataSource, int dbIndex, String key, int start, int end) {
		JedisPool pool = null;
		Jedis jedis = null;
		List<String> values = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			values = jedis.lrange(key, start, end);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return values;
	}
	
	public static long ttl(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		long values = -1;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			values = jedis.ttl(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return values;
	}
	
	public static Long zadd(RedisDataSource dataSource, int dbIndex, String key, double score, String member) {
		JedisPool pool = null;
		Jedis jedis = null;
		Long ret = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.zadd(key, score, member);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static Long zrank(RedisDataSource dataSource, int dbIndex, String key, String member) {
		JedisPool pool = null;
		Jedis jedis = null;
		Long ret = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.zrank(key, member);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static Long decrby(RedisDataSource dataSource, int dbIndex, String key, long value) {
		JedisPool pool = null;
		Jedis jedis = null;
		Long ret = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.decrBy(key, value);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static Long append(RedisDataSource dataSource, int dbIndex, String key, String value) {
		JedisPool pool = null;
		Jedis jedis = null;
		Long ret = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.append(key, value);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	
	public static String getSet(RedisDataSource dataSource, int dbIndex, String key, String value){
		JedisPool pool = null;
		Jedis jedis = null;
		String ret = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			ret = jedis.getSet(key, value);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return ret;
	}
	public static void hmset(RedisDataSource dataSource, int dbIndex,String key, Map<String, String> cacheMap){
		Jedis jedis = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			jedis.hmset(key, cacheMap);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static List<String> hmget(RedisDataSource dataSource, int dbIndex,String key, String... fields){
		Jedis jedis = null;
		List<String> values = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			values = jedis.hmget(key, fields);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return values;
	}
	
	public static void hset(RedisDataSource dataSource, int dbIndex,String key,String field,String value){
		Jedis jedis = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			jedis.hset(key, field, value);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static String hget(RedisDataSource dataSource, int dbIndex,String key,String field){
		Jedis jedis = null;
		String value = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			value = jedis.hget(key, field);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return value;
	}
	
	public static Map<String, String> hgetAll(RedisDataSource dataSource, int dbIndex,String key){
		Jedis jedis = null;
		Map<String, String> pairs = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			pairs = jedis.hgetAll(key);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return pairs;
	}
	
	public static long hdel(RedisDataSource dataSource, int dbIndex,String key,String... fields){
		Jedis jedis = null;
		long value = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			value = jedis.hdel(key, fields);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return value;
	}
	
	public static void expire(RedisDataSource dataSource, int dbIndex, String key, int expireValue){
		JedisPool pool = null;
		Jedis jedis = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			jedis.expire(key, expireValue);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	public static boolean hexists(RedisDataSource dataSource, int dbIndex,String key,String field){
		Jedis jedis = null;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			return jedis.hexists(key, field);
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
	}
	
	public static long incr(RedisDataSource dataSource, int dbIndex, String key) {
		JedisPool pool = null;
		Jedis jedis = null;
		long counter = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			counter = jedis.incr(key); 
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return counter;
	}
	
	public static long incrby(RedisDataSource dataSource, int dbIndex, String key, long increment) {
		JedisPool pool = null;
		Jedis jedis = null;
		long counter = 0l;
		boolean broken = false;
		try {
			pool = poolMap.get(dataSource.name());
			jedis = pool.getResource();
			jedis.select(dbIndex);
			counter = jedis.incrBy(key,increment); 
		} catch (JedisException e) {
			broken = handleJedisException(pool, e);
			throw e;
		} finally {
			closeResource(pool, jedis, broken);
		}
		return counter;
	}
}