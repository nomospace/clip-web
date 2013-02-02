package com.clip.core.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanTools extends BeanUtils {

    public static void setPropertyNotNull(Object bean, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if (value != null) {
            BeanUtilsBean.getInstance().setProperty(bean, name, value);
        }
    }

    public static Map convertBeanToMap(Object bean) {
        if (bean != null) {
            try {
                Map result = PropertyUtils.describe(bean);
                result.remove("class");
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
