package com.hgl.myforum.test;

import com.hgl.myforum.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author HGL
 * @Date 2021/8/18
 */
@Component
public class CronTest {

    @Autowired
    private ITopicService topicService;

    /**
     * 定时任务（定时发帖）
     * 频率：每天12点到14点、17点到19点，每隔10分钟执行一次
     */
    @Scheduled(cron = "0 0/10 12,13,17,18 * * ?")
//    @Scheduled(test = "0 */1 * * * ?")
    private void regularPosting() throws Exception {
        topicService.regularPosting();
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123456");
        System.out.println(jedis.ping()+jedis.incr("hgl"));
    }
}
