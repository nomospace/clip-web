package com.clip.web.action.api;

import com.clip.web.utils.CoreConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("apiAction")
public class ApiAction {

    @RequestMapping("/api/code/{token}")
    public void api(@PathVariable("token") String token, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute(CoreConstants.WEIBO_TOKEN, token);
        response.sendRedirect("/");
    }

}
