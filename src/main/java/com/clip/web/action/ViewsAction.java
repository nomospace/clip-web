package com.clip.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller("hostViewsAction")
public class ViewsAction {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
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
