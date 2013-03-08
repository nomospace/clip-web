package com.clip.web.dao;

import com.clip.core.base.dao.BaseHibernateDao4;
import com.clip.web.model.Oauth2Token;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class Oauth2TokenDao extends BaseHibernateDao4<Oauth2Token, Integer> {

    public Oauth2TokenDao() {
        super(Oauth2Token.class);
    }

    public Oauth2Token updateTokenByUid(Integer uid, String token) {
        Criteria criteria = getSession().createCriteria(Oauth2Token.class);
        criteria.add(Restrictions.eq("alias_id", uid));
        Oauth2Token oauth2Token = (Oauth2Token) criteria.uniqueResult();
        oauth2Token.setAccessToken(token);
        return autoSave(oauth2Token);
    }
}
