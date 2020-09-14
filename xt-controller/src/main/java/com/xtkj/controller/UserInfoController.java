package com.xtkj.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.gson.Gson;
import com.xtkj.pojo.UserInfo;
import com.xtkj.service.IJedisClient;
import com.xtkj.service.IUserInfoService;
import com.xtkj.tools.LoadEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoController {

    @Autowired
    private IUserInfoService iUserInfoService;

    @Autowired
    private IJedisClient jedisClient;

    // 查询所有
    @GetMapping("getUsers")
    public List<UserInfo> getUsers() {
        return jedisClient.hgetAll(LoadEnum.HOME.getClazz());
    }

    // 增加
    @PostMapping("addUser")
    @Transactional
    public void addUser(UserInfo userInfo) {
        iUserInfoService.add(userInfo);
    }

    // 修改
    @PostMapping("updUser")
    @Transactional
    public void updUser(UserInfo userInfo) {
        iUserInfoService.upd(userInfo);
    }

    // 删除
    @PostMapping("delUser")
    @Transactional
    public void delUser(Integer id){
        iUserInfoService.del(id);
    }

}
