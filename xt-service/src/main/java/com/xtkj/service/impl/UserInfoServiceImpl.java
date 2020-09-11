package com.xtkj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtkj.dao.DeletedUserMapper;
import com.xtkj.dao.UserInfoMapper;
import com.xtkj.pojo.UserInfo;
import com.xtkj.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private DeletedUserMapper deletedUserMapper;

    @Transactional
    @Override
    public void delUser(UserInfo userInfo) {
        userInfoMapper.deleteById(userInfo.getId());
        deletedUserMapper.addDeletedUser(userInfo);
    }

}
