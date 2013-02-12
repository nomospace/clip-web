package com.clip.web.service;

import com.clip.web.dao.UserDao;
import com.clip.web.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public User getUser(String type, String token) {
        return userDao.getUser(type, token);
    }

    public User addUser(String type, String token) {
        return userDao.addUser(type, token);
    }

    public Boolean updateUsername(Integer id, String username) {
        return userDao.updateUsername(id, username);
    }
}
