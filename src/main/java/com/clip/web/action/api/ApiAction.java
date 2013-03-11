package com.clip.web.action.api;

import com.clip.core.bean.ReturnBean;
import com.clip.web.action.CommonAction;
import com.clip.web.model.Oauth2Token;
import com.clip.web.model.User;
import com.clip.web.model.UserAlias;
import com.clip.web.service.Oauth2TokenService;
import com.clip.web.service.UserAliasService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

@Controller("apiAction")
public class ApiAction extends CommonAction {
    @Resource
    private UserService userService;
    @Resource
    private UserAliasService userAliasService;
    @Resource
    private Oauth2TokenService oauth2TokenService;

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

    @RequestMapping("/api/{type}/code/{code}")
    public void api(@PathVariable("type") String type, @PathVariable("code") String code, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (type.equals(CoreConstants.SINA_WEIBO)) {
            session.setAttribute(CoreConstants.SINA_WEIBO_CODE, code);
        } else if (type.equals(CoreConstants.QQ_WEIBO)) {
            session.setAttribute(CoreConstants.QQ_WEIBO_CODE, code);
        }
        session.setAttribute(CoreConstants.WEIBO_TYPE, type);

        // TODO
        TokenUtils tokenUtils = new TokenUtils();
        String token = tokenUtils.getTokenByTypeAndCode(type, code);
        if (token != null) {
            String uid;
            uid = tokenUtils.getOauth2TokenUidByTypeAndToken(type, token);
            User user = userService.getUserByUid(uid);
            if (user != null) {
                // update token
                oauth2TokenService.updateTokenByUid(uid, token);
            } else {
                // add user & alias
                user = new User();
                Date date = new Date();
                Long now = date.getTime();
                user.setName(uid);
                user.setUid(uid);
                user.setSessionId(session.getId());
                user.setTime(now);
                user = userService.addUser(user);
                if (user != null) {
                    UserAlias userAlias = userAliasService.getUserByUid(uid);
                    if (userAlias == null) {
                        userAlias = new UserAlias();
                        userAlias.setType(type);
                        userAlias.setUserId(Integer.valueOf(uid));
                        userAlias.setAlias("");
                        userAlias.setTime(now);
                        userAliasService.addUserAlias(userAlias);
                    }
                }
            }
            session.setAttribute("userInfo", user);
        }

        // aad user_token
        // get status

        response.sendRedirect("/");
    }

    @RequestMapping("/updateName")
    @ResponseBody
    public String updateName(@RequestParam(value = "name") String name, final HttpServletRequest request) throws UnsupportedEncodingException {
        String result;
        name = name.toLowerCase();
        HashMap map = this.checkName(name);
        Boolean valid = (Boolean) map.get("valid");
        if (valid) {
            User user = this.getCurrentUser(request);
            JSONObject jsonObject = userService.updateName(user.getId(), name);
            Boolean success = (Boolean) jsonObject.get("success");
            if (success) {
                user.setName(name);
                this.updateCurrentUserInSession(user, request);
            }
            result = jsonObject.toString();
        } else {
            JSONObject resultJson = JSONObject.fromObject(false);
            JSONObject dataJson = resultJson.optJSONObject("data");
            result = new ReturnBean(false, (String) map.get("message"), dataJson).toJSONObject().toString();
        }
        return result;
    }

    public HashMap checkName(String name) throws UnsupportedEncodingException {
        Pattern patten = Pattern.compile("\\w{3,15}");
        Boolean valid = patten.matcher(name).matches();
        String message = null;
        if (!valid) {
            message = "a-z or 0-9 or .-_ is allowed and 4 words at least:)";
        } else {
            String[] prohibitedWords = {"user", "pdf", "explore", "home", "visual", "settings", "admin", "past", "connect", "bind",
                    "i", "notes", "note", "status", "share", "timeline", "post", "login", "logout", "sync", "about",
                    "connect", "dev", "api", "root", "clip", "clipweb"};
            for (String s : prohibitedWords) {
                if (s.equals(name)) {
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


    @RequestMapping("/updateEmail")
    @ResponseBody
    public String updateEmail(@RequestParam(value = "email") String email, HttpServletRequest request) throws UnsupportedEncodingException {
        User user = this.getCurrentUser(request);
        email = email.toLowerCase();
        JSONObject jsonObject = userService.updateEmail(user.getId(), email);
        Boolean success = (Boolean) jsonObject.get("success");
        if (success) {
            user.setEmail(email);
            this.updateCurrentUserInSession(user, request);
        }
        return jsonObject.toString();
    }

//    @RequestMapping("/updateRemind")
//    @ResponseBody
//    public String updateRemind(@RequestParam(value = "remind") Integer remind, HttpServletRequest request) throws UnsupportedEncodingException {
//        User user = this.getCurrentUser(request);
//        JSONObject jsonObject = userService.updateRemind(user.getId(), remind);
//        Boolean success = (Boolean) jsonObject.get("success");
//        if (success) {
//            user.setRemind(remind);
//            this.updateCurrentUserInSession(user, request);
//        }
//        return jsonObject.toString();
//    }

}
