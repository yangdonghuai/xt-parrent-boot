package com.xtkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xtkj.pojo.UserInfo;

public interface IUserInfoService extends IService<UserInfo> {

    //删除后增加到已删除表
    void delUser(UserInfo userInfo);
}
