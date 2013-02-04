package com.clip.web.action.api;

import com.clip.web.utils.CoreConstants;
import com.clip.web.utils.Oauth4Sina;
import com.tencent.weibo.oauthv2.OAuthV2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import weibo4j.model.WeiboException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("apiAction")
public class ApiAction {
    private static OAuthV2 oauth4Qq = new OAuthV2();
    private static Oauth4Sina oauth4Sina = new Oauth4Sina();


    @RequestMapping("/connect/sina")
    public void connectSina(final HttpServletResponse response) throws WeiboException, IOException {
        response.sendRedirect(oauth4Sina.authorize(
                "code",
                "",
                "email,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog"));
    }

    @RequestMapping("/connect/qq")
    public void connectQq() {
        oauth4Qq.setClientId("801312468");
        oauth4Qq.setClientSecret("bdd3591fea17e6f622e188ef299babbe");
        oauth4Qq.setRedirectUri("/api/qq/code/");
    }

    @RequestMapping("/api/code/{code}")
    public void api(@PathVariable("code") String code, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute(CoreConstants.SINA_WEIBO_CODE, code);
        response.sendRedirect("/");
    }

//    @RequestMapping("/api/{type}/code/{code}")
//    public void api(@PathVariable("type") String type, @PathVariable("code") String code, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession();
//        if (type.equals(CoreConstants.SINA_WEIBO_CODE)) {
//            session.setAttribute(CoreConstants.SINA_WEIBO_CODE, code);
//        } else if (type.equals(CoreConstants.QQ_WEIBO_CODE)) {
//            session.setAttribute(CoreConstants.QQ_WEIBO_CODE, code);
//        }
//        response.sendRedirect("/");
//    }

}
