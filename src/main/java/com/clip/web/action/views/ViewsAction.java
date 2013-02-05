package com.clip.web.action.views;

import com.clip.web.utils.weibo.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weibo4j.model.WeiboException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("viewsAction")
public class ViewsAction {

    @RequestMapping("/")
    public ModelAndView index(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = new ModelAndView("index");
        TokenUtils tokenUtils = new TokenUtils();
        mav.addObject("token", tokenUtils.getToken(request));
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
