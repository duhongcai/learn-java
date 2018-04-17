package cn.qtec.learn.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by duhc on 2018/4/17.
 */
public class Demo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.20.131",6379);
        System.out.println(jedis.get("foo"));
    }
}
