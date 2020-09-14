package com.xtkj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xtkj.pojo.UserInfo;

public interface IUserInfoService extends IService<UserInfo> {

    void upd(UserInfo userInfo);

    void add(UserInfo userInfo);

    void del(Integer id);
}
