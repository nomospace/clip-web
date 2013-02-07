package com.clip.web.dao;

import com.clip.core.base.dao.BaseHibernateDao4;
import com.clip.web.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseHibernateDao4<User, Integer> {

    public UserDao() {
        super(User.class);
    }

    public User getUser(String type, String uid) {
        Criteria criteria = getSession().createCriteria(User.class);
        // get one and update one
        if (type == "sina") {
            criteria.add(Restrictions.eq("sina_weibo_uid", uid));
        } else if (type == "qq") {
            criteria.add(Restrictions.eq("qq_weibo_uid", uid));
        }
        return (User) criteria.list().get(0);
    }

    public User addUser(String username) {
        User user = new User();
        user.setUsername(username);
        return autoSave(user);
    }

}
