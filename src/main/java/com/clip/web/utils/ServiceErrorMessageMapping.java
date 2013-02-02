package com.clip.web.utils;

import com.clip.web.bean.enums.ServiceType;
import com.clip.web.utils.i18n.MultiSystemI18NSupport;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 解决多子系统与错误消息资源关联的类
 */
@Service
public class ServiceErrorMessageMapping {

    public static HashMap<ServiceType, MultiSystemI18NSupport> i18NSupportHashMap = new HashMap<ServiceType, MultiSystemI18NSupport>();

    public MultiSystemI18NSupport getI18NSupport(ServiceType serviceType) {
        if (!i18NSupportHashMap.containsKey(serviceType)) {
            // 如果缓存中不存在
            String postFix = "MessageSource";
            String findKey = serviceType.toString().substring(0, 1).toLowerCase() + serviceType.toString().substring(1) + postFix;
            ReloadableResourceBundleMessageSource messageSource = ClassBeanFactory.getBean(findKey, ReloadableResourceBundleMessageSource.class);
            MultiSystemI18NSupport multiSystemI18NSupport = new MultiSystemI18NSupport(messageSource);
            i18NSupportHashMap.put(serviceType, multiSystemI18NSupport);
        }
        return i18NSupportHashMap.get(serviceType);
    }

    public String getMessage(ServiceType serviceType, String code, String... args) {
        MultiSystemI18NSupport multiSystemI18NSupport = getI18NSupport(serviceType);
        if (multiSystemI18NSupport != null) {
            return multiSystemI18NSupport.getMessage(code, args);
        }
        return null;
    }

}
