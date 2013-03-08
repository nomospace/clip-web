package com.clip.web.dao;

import com.clip.core.base.dao.BaseHibernateDao4;
import com.clip.web.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UserDao_bak extends BaseHibernateDao4<User, Integer> {

    public UserDao_bak() {
        super(User.class);
    }

    public User getUser(String type, String token) {
        Criteria criteria = getSession().createCriteria(User.class);
        SimpleExpression tokenExpression = null;
        if (type.equals("sina")) {
            tokenExpression = Restrictions.eq("sina_weibo_token", token);
        } else if (type.equals("qq")) {
            tokenExpression = Restrictions.eq("qq_weibo_token", token);
        }
        criteria.add(tokenExpression);
        return (User) criteria.uniqueResult();
    }

    public User getUserByUidAndType(String uid, String type) {
        Criteria criteria = getSession().createCriteria(User.class);
        SimpleExpression tokenExpression = null;
        if (type.equals("sina")) {
            tokenExpression = Restrictions.eq("sina_weibo_uid", uid);
        } else if (type.equals("qq")) {
            tokenExpression = Restrictions.eq("qq_weibo_uid", uid);
        }
        criteria.add(tokenExpression);
        return (User) criteria.uniqueResult();
    }

    public User getUserById(Integer id) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        return (User) criteria.uniqueResult();
    }

    public User getUserByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("username", username));
        return (User) criteria.uniqueResult();
    }

    public User addUser(String uid, String type, String token) {
        User user = new User();
        user.setUsername(uid);
        if (type.equals("sina")) {
            user.setSina_weibo_token(token);
            user.setSina_weibo_uid(uid);
        } else if (type.equals("qq")) {
            user.setQq_weibo_token(token);
            user.setQq_weibo_uid(uid);
        }
        Date date = new Date();
        user.setRegister_date(date.getTime());
        return autoSave(user);
    }

    public Boolean updateUsername(Integer id, String username) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        User user = (User) criteria.uniqueResult();
        Boolean success = false;
        if (user != null) {
            user.setUsername(username);
            autoSave(user);
            success = true;
        }
        return success;
    }

    public Boolean updateEmail(Integer id, String email) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        User user = (User) criteria.uniqueResult();
        Boolean success = false;
        if (user != null) {
            user.setEmail(email);
            autoSave(user);
            success = true;
        }
        return success;
    }

    public Boolean updateRemind(Integer id, Integer remind) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        User user = (User) criteria.uniqueResult();
        Boolean success = false;
        if (user != null) {
            user.setRemind(remind);
            autoSave(user);
            success = true;
        }
        return success;
    }

    public Boolean updateToken(Integer id, String type, String token) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        User user = (User) criteria.uniqueResult();
        Boolean success = false;
        if (user != null) {
            if (type.equals("sina")) {
                user.setSina_weibo_token(token);
            } else if (type.equals("qq")) {
                user.setQq_weibo_token(token);
            }
            autoSave(user);
            success = true;
        }
        return success;
    }

}
