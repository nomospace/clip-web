package com.clip.web.service;

import com.clip.web.dao.UserDao;
import com.clip.web.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public User getUser(String type, String uid) {
        return userDao.getUser(type, uid);
    }

    public User addUser(String username) {
        return userDao.addUser(username);
    }
}
