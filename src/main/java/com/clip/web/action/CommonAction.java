package com.clip.web.action;

import com.clip.web.model.User;
import com.clip.web.utils.CoreConstants;
import com.clip.web.utils.weibo.TokenUtils;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

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

    public StatusWapper getUserTimeline(Integer id, String type, HttpServletRequest request) {
        if (type.equals(CoreConstants.SINA_WEIBO)) {
            Timeline tl = new Timeline();
            TokenUtils tokenUtils = new TokenUtils();
            String token = tokenUtils.getToken(request);
            tl.client.setToken(token);
            StatusWapper status = null;
            try {
                status = tl.getUserTimeline();
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

}
