package com.xtkj.config;

import com.google.gson.Gson;
import com.xtkj.pojo.UserInfo;
import com.xtkj.service.IJedisClient;
import com.xtkj.service.IUserInfoService;
import com.xtkj.tools.LoadEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class LoadData {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IJedisClient jedisClient;

    @PostConstruct
    public void load() {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();

        new Thread(() -> {
            log.debug("---------------启动线程准备缓存数据----------------");
            log.debug("---------------删除 之前数据----------------");
            Set<String> keys = jedisClient.keys(LoadEnum.HOME.getClazz() + "*");
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                long ms = jedisClient.del(key);
                log.debug(key + "---------------删除 ----------------" + ms);
            }
            log.debug("---------------删除完毕开始缓存----------------");
            List<UserInfo> list = userInfoService.list();
            Gson gson = new Gson();
            for (UserInfo userInfo : list) {
                jedisClient.hset(LoadEnum.HOME.getClazz(), userInfo.getId().toString(), gson.toJson(userInfo));
            }
            log.debug("---------------缓存完毕----------------");

        }).start();

    }
}
