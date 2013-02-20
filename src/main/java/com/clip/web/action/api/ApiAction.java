package com.clip.web.action.api;

import com.clip.core.bean.ReturnBean;
import com.clip.web.model.User;
import com.clip.web.service.UserService;
import com.clip.web.utils.CoreConstants;
import com.clip.web.utils.weibo.Oauth4Qq;
import com.clip.web.utils.weibo.Oauth4Sina;
import com.clip.web.utils.weibo.TokenUtils;
import com.tencent.weibo.oauthv2.OAuthV2Client;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import weibo4j.model.WeiboException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.regex.Pattern;

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

    @RequestMapping("/updateUsername")
    @ResponseBody
    public String updateUsername(@RequestParam(value = "username") String username, final HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        TokenUtils tokenUtils = new TokenUtils();
        String token = tokenUtils.getToken(request);
        User user = userService.getUser(tokenUtils.getTokenType(request), token);
        session.setAttribute("userInfo", user);
        HashMap map = this.checkUsername(username);
        username = username.toLowerCase();
        String result;
        Boolean valid = (Boolean) map.get("valid");
        if (valid) {
            result = userService.updateUsername(user.getId(), username).toString();
        } else {
            JSONObject resultJson = JSONObject.fromObject(false);
            JSONObject dataJson = resultJson.optJSONObject("data");
            result = new ReturnBean(false, (String) map.get("message"), dataJson).toJSONObject().toString();
        }
        return result;
    }

    public HashMap checkUsername(String username) throws UnsupportedEncodingException {
        Pattern patten = Pattern.compile("^[a-z][0-9a-zA-Z_.-]{3,15}");
        Boolean valid = patten.matcher(username).matches();
        String message = null;
        if (!valid) {
            message = "a-z or 0-9 or .-_ is allowed :)";
        } else {
            String[] prohibitedWords = new String[]{"user", "pdf", "explore", "home", "visual", "settings", "admin", "past", "connect", "bind",
                    "i", "notes", "note", "status", "share", "timeline", "post", "login", "logout", "sync", "about",
                    "connect", "dev", "api", "root", "clip", "clipweb"};
            for (String s : prohibitedWords) {
                if (s.equals(username)) {
                    valid = false;
                    message = "Ooops, " + s + " is already used :)";
                    break;
                }
            }
        }
        HashMap map = new HashMap();
        map.put("valid", valid);
        map.put("message", message);
        return map;
    }

}
