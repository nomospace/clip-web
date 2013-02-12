package com.clip.web.action.api;

import com.clip.web.service.UserService;
import com.clip.web.utils.CoreConstants;
import com.clip.web.utils.weibo.Oauth4Qq;
import com.clip.web.utils.weibo.Oauth4Sina;
import com.tencent.weibo.oauthv2.OAuthV2Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import weibo4j.model.WeiboException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("apiAction")
public class ApiAction {
    @Resource
    private UserService userService;

    private static Oauth4Qq oauth4Qq = new Oauth4Qq();
    private static Oauth4Sina oauth4Sina = new Oauth4Sina();


    @RequestMapping("/connect/sina")
    public void connectSina(final HttpServletResponse response) throws WeiboException, IOException {
        response.sendRedirect(oauth4Sina.authorize(
                "code",
                "",
                "email,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog"));
    }

    @RequestMapping("/connect/qq")
    public void connectQq(final HttpServletResponse response) throws IOException {
        String authorizationUrl = OAuthV2Client.generateAuthorizationURL(oauth4Qq);
        response.sendRedirect(authorizationUrl);
    }

//    @RequestMapping("/api/code/{code}")
//    public void api(@PathVariable("code") String code, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession();
//        session.setAttribute(CoreConstants.SINA_WEIBO_CODE, code);
//        response.sendRedirect("/");
//    }

    @RequestMapping("/api/{type}/code/{code}")
    public void api(@PathVariable("type") String type, @PathVariable("code") String code, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (type.equals(CoreConstants.SINA_WEIBO)) {
            session.setAttribute(CoreConstants.SINA_WEIBO_CODE, code);
        } else if (type.equals(CoreConstants.QQ_WEIBO)) {
            session.setAttribute(CoreConstants.QQ_WEIBO_CODE, code);
        }
        session.setAttribute(CoreConstants.WEIBO_TYPE, type);
        response.sendRedirect("/");
    }

    @RequestMapping("/user/add/{username}")
    public void addUser(@PathVariable("username") String username, final HttpServletResponse response) {
        userService.addUser(username);
    }

}
