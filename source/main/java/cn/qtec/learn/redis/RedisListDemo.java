package cn.qtec.learn.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by duhc on 2018/4/18.
 */
public class RedisListDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.20.131", 6379);
        /**
         * list
         *   v为 list 有序集合
         *          jedis.lpush
         *          jedis.lrang
         */
        for (int i = 0; i < 10; i++) {
            jedis.lpush("site-list", "Runoob - " + i);
            jedis.lpush("site-list", "Google - " + i);
            jedis.lpush("site-list", "Taobao - " + i);
        }
        jedis.lrange("site-list", 0, 2);
        /**
         *   hash
         * v 为map 适合存储对象
         *          jedis.hmset
         *          jedis.hgetAll    {key1=val1, aaa=bbb, key2=val2, ccc=ddd}
         */
        Map<String, String> param = new HashMap<>();
        param.put("aaa", "bbb");
        param.put("ccc", "ddd");
        String maps = jedis.hmset("maps", param);
        System.out.println(jedis.hgetAll("maps"));
        /**
         *  set
         *      jedis.sadd
         *      jedis.smembers
         */
        jedis.sadd("setE","dad","cdcd","deede","cddad","deaed");
        Set<String> setE = jedis.smembers("setE");
        System.out.println(setE);
    }
}
