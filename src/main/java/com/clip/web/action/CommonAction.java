package com.clip.web.action;

import com.clip.web.model.User;
import com.clip.web.utils.CoreConstants;
import com.clip.web.utils.weibo.TokenUtils;
import org.springframework.web.servlet.ModelAndView;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonAction {

    public ModelAndView addObject(HttpServletRequest request, ModelAndView mav) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");
        if (user != null) {
            mav.addObject("user", user);
        }
        return mav;
    }

    // 获取当前登录用户
    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("userInfo");
    }

    public void updateCurrentUserInSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", user);
    }

    public StatusWapper getUserTimeline(Integer id, String type, String token) {
        if (type.equals(CoreConstants.SINA_WEIBO)) {
            Timeline tl = new Timeline();
            tl.client.setToken(token);
            StatusWapper status = null;
            try {
                status = tl.getUserTimeline();
                System.out.println(status);
//                for (Status s : status.getStatuses()) {
//                    Log.logInfo(s.toString());
//                }
//                System.out.println(status.getNextCursor());
//                System.out.println(status.getPreviousCursor());
//                System.out.println(status.getTotalNumber());
//                System.out.println(status.getHasvisible());
            } catch (WeiboException e) {
                e.printStackTrace();
            }
            return status;
        }
        return null;
    }

    public Object getUserFromOauth2Token(String uid, String type, String token) {
        Object user = null;
        if (type.equals(CoreConstants.SINA_WEIBO)) {
            Users um = new Users();
            um.client.setToken(token);
            try {
                user = um.showUserById(uid);
                System.out.println(user.toString());
            } catch (WeiboException e) {
                e.printStackTrace();
            }
        } else if (type.equals(CoreConstants.QQ_WEIBO)) {

        }
        return user;
    }

}
