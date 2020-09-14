package com.xtkj.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.xtkj.dao.UserInfoMapper;
import com.xtkj.pojo.UserInfo;
import com.xtkj.service.IJedisClient;
import com.xtkj.service.IUserInfoService;
import com.xtkj.tools.LoadEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private IUserInfoService iUserInfoService;

    @Autowired
    private IJedisClient jedisClient;

    @Override
    public void upd(UserInfo userInfo) {
        Integer version = iUserInfoService.getById(userInfo.getId()).getVersion();
        userInfo.setVersion(version+1);
        boolean update = iUserInfoService.update(userInfo,
                new UpdateWrapper<UserInfo>().eq("version",version).eq("id",userInfo.getId()));
        if (update) {
            jedisClient.hset(LoadEnum.HOME.getClazz(), userInfo.getId().toString(), new Gson().toJson(userInfo));
        }
    }

    @Override
    public void add(UserInfo userInfo) {
        boolean save = iUserInfoService.save(userInfo);
        if (save && userInfo.getId() != null) {
            jedisClient.hset(LoadEnum.HOME.getClazz(), userInfo.getId().toString(), new Gson().toJson(userInfo));
        }
    }

    @Override
    public void del(Integer id) {
        boolean remove = iUserInfoService.removeById(id);
        if (remove) {
            jedisClient.hdel(LoadEnum.HOME.getClazz(),id.toString());
        }
    }

}
