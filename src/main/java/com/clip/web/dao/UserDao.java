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

    public User getUser(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        return (User) criteria.list().get(0);
    }

    public User addUser(String username) {
        User user = new User();
        user.setUsername(username);
        return autoSave(user);
    }

}
