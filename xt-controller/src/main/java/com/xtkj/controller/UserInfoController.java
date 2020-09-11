package com.xtkj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xtkj.pojo.UserInfo;
import com.xtkj.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoController {

    @Autowired
    private IUserInfoService iUserInfoService;
    // 查询所有
    @RequestMapping("user/getUsers")
    public List<UserInfo> getUsers(){
        List<UserInfo> list = iUserInfoService.list();
        return list;
    }

    // 增加
    @RequestMapping("user/addUser")
    public boolean addUser(UserInfo user){
       return iUserInfoService.save(user);
    }

    // 修改
    @RequestMapping("user/updUser")
    public boolean updUser(UserInfo user){
        return iUserInfoService.updateById(user);
    }

    // 分页
    @RequestMapping("user/getPages")
    public List<UserInfo> getPages(int current,int size){
        IPage<UserInfo> page = new Page<>(current, size);
        IPage<UserInfo> page1 = iUserInfoService.page(page);
        return page1.getRecords();
    }

    // 删除后增加
    @RequestMapping("user/delUser")
    public void delUser(Integer id){
        UserInfo userInfo = iUserInfoService.getById(id);
        iUserInfoService.delUser(userInfo);
    }

}
