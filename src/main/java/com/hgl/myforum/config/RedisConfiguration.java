package com.hgl.myforum.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxTotal;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWaitMillis;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String passwd;

    public RedisConfiguration(){

    }
    //redis 分布式连接池
    /*@Bean
    public ShardedJedisPool shardedJedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(this.getMaxTotal());
        jedisPoolConfig.setMaxIdle(this.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(this.getMaxWaitMillis());
        List<JedisShardInfo> jedisShardInfos = new ArrayList<>();
        JedisShardInfo jedisShardInfo =new JedisShardInfo(host, port, 3000);
        jedisShardInfo.setPassword(passwd);
        jedisShardInfos.add(jedisShardInfo);
        ShardedJedisPool jedisPool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfos);
        return jedisPool;
    }*/

    //redis 连接池
    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        JedisPool pool = new JedisPool(config, host, port, 3000, passwd);
        return pool;
    }
}
