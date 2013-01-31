package com.clip.web.action.api;

import com.clip.web.utils.CoreConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("apiAction")
public class ApiAction {

    @RequestMapping("/api/code/{token}")
    public ModelAndView api(@PathVariable("token") String token, final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("api");
        System.out.println(token);
        mav.addObject("token", token);
        HttpSession session = request.getSession();
        session.setAttribute(CoreConstants.WEIBO_TOKEN, token);
        return mav;
    }

}
