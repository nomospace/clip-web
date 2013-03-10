package com.clip.web.service;

import com.clip.web.dao.UserAliasDao;
import com.clip.web.model.UserAlias;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAliasService {
    @Resource
    private UserAliasDao userAliasDao;

    public UserAlias getUserByUid(String uid) {
        return userAliasDao.getUserByUid(uid);
    }

    public UserAlias addUserAlias(UserAlias userAlias) {
        return userAliasDao.add(userAlias);
    }

}
