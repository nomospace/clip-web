package com.clip.web.service;

import com.clip.core.bean.ReturnBean;
import com.clip.web.dao.UserDao;
import com.clip.web.model.User;
import net.sf.json.JSONObject;
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

    public JSONObject updateUsername(Integer id, String username) {
        Boolean success = userDao.updateUsername(id, username);
        JSONObject resultJson = JSONObject.fromObject(success);
        JSONObject dataJson = resultJson.optJSONObject("data");
        JSONObject result = new ReturnBean(true, null, dataJson).toJSONObject();
        return result;
    }
}
