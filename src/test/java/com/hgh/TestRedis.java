package com.hgh;

import cn.wanghaomiao.seimi.model.User;
import cn.wanghaomiao.seimi.struct.Request;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class TestRedis {


    public static void main(String[] args) {


        Jedis jedis = new Jedis("192.168.111.130",6379);
        Request request = new Request();
        request.setCallBack("abc");
        request.setUseSeimiAgent(true);
        request.setSeimiAgentUseCookie(true);
        request.setSeimiAgentRenderTime(2500);
        System.out.println("request----" + JSON.toJSONString(request));
        jedis.lpush("request", JSON.toJSONString(request));
        List<String> requests = jedis.brpop(0,"request");
        System.out.println("resultRequest----" + JSON.toJSONString(request));
        Request resultRequest =  JSON.parseObject(requests.get(1),Request.class);
        System.out.println("useSeimiAgent---"+resultRequest.setUseSeimiAgent(true));

        User user = new User();
        user.setAddress("ningbo");
        user.setName("hgh");
        user.setUseSeimiAgent();
        System.out.println("user----" + JSON.toJSONString(request));
        jedis.lpush("user", JSON.toJSONString(user));
        List<String> users = jedis.brpop(0,"user");
        User resultUser =  JSON.parseObject(users.get(1),User.class);
        System.out.println("sex-----"+resultUser.isUseSeimiAgent());
        jedis.close();
    }
}
