package com.isheng.hpb.cache;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.isheng.common.json.JsonMapper;

public class RedisUtil {

	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
	@Resource
	private JsonMapper jsonMapper;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 指定缓存失效时间
	 * @param key
	 * @param time
	 * @return
	 */
	public boolean expire(String key, long time) {
		try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            logger.error("redis指定缓存时间，key={}，time={}", key, time);
            return Boolean.FALSE;
        }
	}

	/**
	 * 获取缓存过期时间
	 * 
	 * @param key
	 * @return
	 */
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			logger.error("redis判断key是否存在异常，key={}, 异常=", key, e);
			return false;
		}
	}

	/**
	 * 删除指定缓存
	 * 
	 * @param key
	 */
	public void del(String... key) {
		if (null != key && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(Arrays.asList(key));
			}
		}
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return (null == key) ? null : redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 获取指定类型的缓存对象
	 * @param <T>
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T get(String key, Class<T> clazz) {
		if (null == key) {
			return null;
		}
		
		Object obj = redisTemplate.opsForValue().get(key);
		return (null == obj) ? null : jsonMapper.fromJson(jsonMapper.toJson(obj), clazz);
	}

	/**
	 * 放入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return Boolean.TRUE;
		} catch (Exception e) {
			logger.error("redis放入缓存异常，key={}，异常=", key, e);
			return Boolean.FALSE;
		}
	}

	/**
	 * 普通缓存放入并设置时间(分钟)
	 * 
	 * @param key
	 * @param value
	 * @param time
	 * @return
	 */
	public boolean set(String key, Object value, long time) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				set(key, value);
			}
			return Boolean.TRUE;
		} catch (Exception e) {
			logger.error("redis放入缓存异常，key={}，time={}，异常=", key, time, e);
			return Boolean.FALSE;
		}
	}

	/**
	 * 递增
	 * 
	 * @param key
	 * @param delta 要增加几(大于0)
	 * @return
	 */
	public long incr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("redis递增因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * 递减
	 * 
	 * @param key
	 * @param delta 要减几（大于0）
	 * @return
	 */
	public long decr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("redis递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	/**
	 * HashGet
	 * 
	 * @param key
	 * @param item
	 * @return
	 */
	public Object hget(String key, String item) {
		return redisTemplate.opsForHash().get(key, item);
	}

	/**
	 * 获取hashKey对应的所有键值
	 * 
	 * @param key
	 * @return
	 */
	public Map<Object, Object> hmget(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * HashSet
	 * 
	 * @param key
	 * @param map
	 * @return
	 */
	public boolean hmset(String key, Map<String, Object> map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return Boolean.TRUE;
		} catch (Exception e) {
			logger.error("redis HashSet异常，key={}，map={}", key, map);
			return Boolean.FALSE;
		}
	}

}
