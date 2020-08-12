package com.example.management.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * */
@Component
public final class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;
    //===============================common===============================
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间（秒）
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     * @param key 键，不能为空
     * @return 时间秒，返回0代表永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return 存在返回true，不存在返回false
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值，或多个值
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            }
            else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //内部类
    //===============================String===============================
    @Component
    public class redisString {

        /**
         * 获取缓存
         * @param key 键
         * @return 值
         */
        public Object get(String key) {
            try {
                return key == null ? null : redisTemplate.opsForValue().get(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 缓存存入
         * @param key 键
         * @param value 值
         * @return 操作成功返回true，失败返回false
         */
        public boolean set(String key, Object value) {
            try {
                redisTemplate.opsForValue().set(key, value);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 普通缓存放入并设置时间
         * @param key 键
         * @param value 值
         * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
         * @return 操作成功返回true，失败返回false
         */
        public boolean set(String key, Object value, long time) {
            try {
                if (time > 0) {
                    redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
                }
                else {
                    set(key, value);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 递增
         * @param key 键
         * @param delta 要增加的值
         * @return
         */
        public long incr(String key, long delta) {
            if (delta < 0) {
                throw new RuntimeException("递增因子必须大于0");
            }
            return redisTemplate.opsForValue().increment(key, delta);
        }

        /**
         * 递减
         * @param key 键
         * @param delta 要减小的值
         * @return
         */
        public long decr(String key, long delta) {
            if (delta < 0) {
                throw new RuntimeException("递减因子必须小于0");
            }
            return redisTemplate.opsForValue().increment(key, -delta);
        }

    }


}
