package com.clip.web.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;

public class UserInfoUtils {

    private static String defaultName = "test-user";

    public static String getCUserBySession(HttpSession session) {
        String user = "test-user";
        if (session.getAttribute(CoreConstants.USER_NAME) != null) {
            user = session.getAttribute(CoreConstants.USER_NAME).toString();
        }
        return user;
    }


    public static String getDomainUser() {
        try {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
            return defaultName;
        }
    }

}
