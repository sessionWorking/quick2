package com.wsp.quick;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsp.dao.UserDao;
import com.wsp.domain.User;
import com.wsp.springbootjpa.SpringbootJpaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJpaApplication.class)
public class QuickApplicationTests {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        String key = (String) redisTemplate.boundValueOps("user.queryList").getKey();
        if (null==key){
            /*从数据库中拿到数据存储到Redis中*/
            List<User> all = userDao.findAll();
            ObjectMapper objectMapper=new ObjectMapper();
            try {
                org.springframework.core.annotation.AnnotationUtils a;
                key = objectMapper.writeValueAsString(all);
                redisTemplate.boundValueOps("user.queryList").set(key);
                System.out.println("从数据库中查找数据");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }else {
            System.out.println("从数据库中查询数据!");
        }
    }

}
