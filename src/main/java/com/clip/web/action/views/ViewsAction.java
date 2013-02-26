package com.clip.web.action.views;

import com.clip.web.action.CommonAction;
import com.clip.web.model.User;
import com.clip.web.service.UserService;
import com.clip.web.utils.weibo.TokenUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weibo4j.model.WeiboException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller("viewsAction")
public class ViewsAction extends CommonAction {
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = new ModelAndView("index");
        TokenUtils tokenUtils = new TokenUtils();
        String token = tokenUtils.getToken(request);
        String tokenType = tokenUtils.getTokenType(request);
        String uid = tokenUtils.getUid(request);
        if (token != null && tokenType != null) {
            User user = userService.getUserByUidAndType(uid, tokenType);
            if (uid != null) {
                if (user != null) {
                    userService.updateToken(user.getId(), tokenType, token);
                } else {
                    user = userService.addUser(uid, tokenType, token);
                }
                this.updateUserInSession(user, request);
                mav.addObject("token", token);
                mav.addObject("user", user);
                mav.addObject("uid", uid);
                // 获取 timeline
                mav.addObject("statuses", this.getUserTimeline(user.getId(), tokenType, request).getStatuses());
            }
        }
        return mav;
    }

    @RequestMapping("/home")
    public ModelAndView home(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = this.index(request);
        mav.addObject("isLogin", false);
        mav.addObject("fromHome", true);
        return mav;
    }

    @RequestMapping("/setting")
    public ModelAndView settings(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = new ModelAndView("setting");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");
        if (user == null) {
            mav = this.index(request);
        } else {
            mav.addObject("user", user);
        }
        return mav;
    }

    @RequestMapping("/user/{username}")
    public ModelAndView showUserUsername(@PathVariable("username") String username, HttpServletRequest request, HttpServletResponse response) {
        return this.showUser(username, request, response);
    }

    @RequestMapping("/{username}")
    public ModelAndView showUser(@PathVariable("username") String username, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("user");
        User user = userService.getUserByUsername(username);
        TokenUtils tokenUtils = new TokenUtils();
        String token = tokenUtils.getToken(request);
        String tokenType = tokenUtils.getTokenType(request);
        String uid = tokenUtils.getUid(request);
        tokenUtils.getUserInfo(request);
        if (user != null) {
            mav.addObject("token", token);
            mav.addObject("user", user);
            mav.addObject("uid", uid);
            // 获取 timeline
            mav.addObject("statuses", this.getUserTimeline(user.getId(), tokenType, request).getStatuses());
        } else {
            mav = this.to404(response);
        }
        return mav;
    }


    @RequestMapping("/404")
    public ModelAndView to404(HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("common/404");
        response.setStatus(404);
        return mav;
    }

    @RequestMapping("/500")
    public ModelAndView to500(HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("common/500");
        response.setStatus(500);
        return mav;
    }

    @RequestMapping("/403")
    public ModelAndView to403(HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("common/403");
        response.setStatus(403);
        return mav;
    }

}
