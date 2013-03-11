package com.clip.web.dao;

import com.clip.core.base.dao.BaseHibernateDao4;
import com.clip.web.model.User;
import com.clip.web.model.UserAlias;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;

@Repository
public class UserAliasDao extends BaseHibernateDao4<UserAlias, Integer> {

    public UserAliasDao() {
        super(UserAlias.class);
    }

    public UserAlias getUserByUid(String uid) {
        Criteria criteria = getSession().createCriteria(UserAlias.class);
        criteria.add(Restrictions.eq("userId", Integer.valueOf(uid)));
        return (UserAlias) criteria.uniqueResult();
    }

}
