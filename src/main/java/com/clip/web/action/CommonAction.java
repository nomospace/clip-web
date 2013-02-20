package com.clip.web.action;

import com.clip.web.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonAction {
    public User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("userInfo");
    }

    public void updateUserInSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", user);
    }
}
