package com.clip.web.service;

import com.clip.web.dao.Oauth2TokenDao;
import com.clip.web.model.Oauth2Token;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Oauth2TokenService {
    @Resource
    private Oauth2TokenDao oauth2TokenDao;

    public Oauth2Token updateTokenByUid(Integer uid, String token) {
        return oauth2TokenDao.updateTokenByUid(uid, token);
    }

}
