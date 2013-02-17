package com.clip.web.action.views;

import com.clip.web.model.User;
import com.clip.web.service.UserService;
import com.clip.web.utils.CoreConstants;
import com.clip.web.utils.weibo.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weibo4j.model.WeiboException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller("viewsAction")
public class ViewsAction {
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = new ModelAndView("index");
        TokenUtils tokenUtils = new TokenUtils();
        String token = tokenUtils.getToken(request);
        mav.addObject("weiboToken", token);
        String tokenType = tokenUtils.getTokenType(request);
        if (token != null && tokenType != null) {
            User user = userService.getUser(tokenType, token);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userInfo", user);
                mav.addObject("user", user);
            } else {
                userService.addUser(tokenType, token);
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

    @RequestMapping("/404")
    public String to400(HttpServletResponse response) {
        response.setStatus(404);
        return "common/404";
    }

    @RequestMapping("/500")
    public String to500(HttpServletResponse response) {
        response.setStatus(500);
        return "common/500";
    }

    @RequestMapping("/403")
    public String to403(HttpServletResponse response) {
        response.setStatus(403);
        return "common/403";
    }

}
