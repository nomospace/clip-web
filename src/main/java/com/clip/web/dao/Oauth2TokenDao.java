package com.clip.web.dao;

import com.clip.core.base.dao.BaseHibernateDao4;
import com.clip.web.model.Oauth2Token;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class Oauth2TokenDao extends BaseHibernateDao4<Oauth2Token, Integer> {

    public Oauth2TokenDao() {
        super(Oauth2Token.class);
    }

    public Oauth2Token getTokenByUid(String uid) {
        Criteria criteria = getSession().createCriteria(Oauth2Token.class);
        criteria.add(Restrictions.eq("aliasId", Integer.valueOf(uid)));
        return (Oauth2Token) criteria.uniqueResult();
    }

    public Oauth2Token updateTokenByUid(String uid, String token) {
        Criteria criteria = getSession().createCriteria(Oauth2Token.class);
        criteria.add(Restrictions.eq("aliasId", Integer.valueOf(uid)));
        Oauth2Token oauth2Token = (Oauth2Token) criteria.uniqueResult();
        if (oauth2Token != null) {
            oauth2Token.setAccessToken(token);
        } else {
            oauth2Token = new Oauth2Token();
            oauth2Token.setAliasId(Integer.valueOf(uid));
            oauth2Token.setAccessToken(token);
            oauth2Token.setRefreshToken(token);
            oauth2Token.setTime(new Date().getTime());
        }
        return autoSave(oauth2Token);
    }
}
