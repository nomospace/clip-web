package com.clip.web.dao.common;

import com.clip.core.base.dao.BaseHibernateDao4;
import com.clip.web.bean.enums.ActionType;
import com.clip.web.bean.enums.ServiceType;
import com.clip.web.model.common.ServiceRequest;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ServiceRequestDao extends BaseHibernateDao4<ServiceRequest, String> {

    public ServiceRequestDao() {
        super(ServiceRequest.class);
    }

    /**
     * 通过服务名和 action 列表（可选）获取请求列表
     *
     * @param targetService 模块服务名称
     * @param actions       动作，包含 C,R,U,D；不填表示获取全部
     * @return ServiceRequest 列表
     */
    public List<ServiceRequest> listByServiceNameAndActions(ServiceType targetService, ActionType... actions) {
        Criteria criteria = getSession().createCriteria(ServiceRequest.class);
        criteria.add(Restrictions.eq("targetService", targetService.toString()));
        if (actions != null && actions.length > 0) {
            List<Character> actionArray = new ArrayList<Character>();
            for (int i = 0; i < actions.length; i++) {
                ActionType actionType = actions[i];
                actionArray.add(actionType.toString().substring(0, 1).toCharArray()[0]);
            }
            criteria.add(Restrictions.in("action", actionArray));
        }
        return criteria.list();
    }

    public ServiceRequest addRequest(ServiceType service, ActionType type, String uuid, String targetObj, Long taskId) {
        ServiceRequest request = new ServiceRequest();
        request.setAction((type.toString().substring(0, 1).toCharArray()[0]));
        request.setTargetService(service.toString());
        if (taskId != null) {
            request.setTaskId(taskId);
        }
        request.setUuid(uuid);
        request.setTargetObj(targetObj);
        request.setStartTime(new Date());
        return add(request);
    }

}
