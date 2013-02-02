package com.clip.web.annotation;

import com.clip.web.bean.enums.ActionType;
import com.clip.web.bean.enums.ServiceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogIt {
    ServiceType targetService();

    ActionType action();
}
