package com.clip.web.action.api;

import com.clip.web.utils.CoreConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import weibo4j.Oauth;
import weibo4j.model.WeiboException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("apiAction")
public class ApiAction {
    private static Log logger = LogFactory.getLog(ApiAction.class);

    @RequestMapping("/connect/sina")
    public void connectSina(final HttpServletResponse response) throws WeiboException, IOException {
        Oauth oauth = new Oauth();
        response.sendRedirect(oauth.authorize(
                "code",
                "",
                "email,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog"));
    }

    @RequestMapping("/api/code/{code}")
    public void api(@PathVariable("code") String code, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute(CoreConstants.WEIBO_CODE, code);
        response.sendRedirect("/");
    }

}
