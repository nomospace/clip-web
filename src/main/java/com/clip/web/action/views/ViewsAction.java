package com.clip.web.action.views;

import com.clip.web.service.UserService;
import com.clip.web.utils.weibo.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weibo4j.model.WeiboException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("viewsAction")
public class ViewsAction {
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = new ModelAndView("index");
        TokenUtils tokenUtils = new TokenUtils();
        mav.addObject("weiboToken", tokenUtils.getToken(request));
        System.out.println(tokenUtils.getUserId());
        return mav;
    }

    @RequestMapping("/home")
    public ModelAndView home(final HttpServletRequest request) throws WeiboException {
        return this.index(request);
    }

    @RequestMapping("/setting")
    public ModelAndView settings() throws WeiboException {
        ModelAndView mav = new ModelAndView("setting");
        mav.addObject("xxx", "xxx");
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
