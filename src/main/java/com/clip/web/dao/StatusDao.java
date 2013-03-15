package com.clip.web.dao;

import com.clip.core.base.dao.BaseHibernateDao4;
import com.clip.web.model.Status;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class StatusDao extends BaseHibernateDao4<Status, Integer> {

    public StatusDao() {
        super(Status.class);
    }

    public Boolean addStatus(List statuses) {
        for (Iterator iterator = statuses.iterator(); iterator.hasNext(); ) {
            weibo4j.model.Status weibo4jStatus = (weibo4j.model.Status) iterator.next();
            String originId = weibo4jStatus.getMid();
            if (!this.statusExists(originId)) {
                Status status = new Status();
                status.setContent(weibo4jStatus.getText());
                status.setCategory(1);
                status.setSite("1");
                status.setUserId(Integer.valueOf(weibo4jStatus.getUser().getId()));
                status.setOriginId(originId);
                status.setCreateTime(weibo4jStatus.getCreatedAt().getTime());
                status.setTime(new Date().getTime());
                add(status);
            }
        }
        return true;
    }

    public Boolean statusExists(String originId) {
        Criteria criteria = getSession().createCriteria(Status.class);
        criteria.add(Restrictions.eq("originId", originId));
        Status status = (Status) criteria.uniqueResult();
        return status != null;
    }
}
