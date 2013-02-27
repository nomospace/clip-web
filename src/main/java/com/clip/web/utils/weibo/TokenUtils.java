package com.clip.web.utils.weibo;

import com.clip.web.utils.CoreConstants;
import com.tencent.weibo.oauthv2.OAuthV2Client;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weibo4j.Account;
import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TokenUtils {
    private static Log logger = LogFactory.getLog(TokenUtils.class);
    private static Oauth4Sina oauth4Sina = new Oauth4Sina();
    private static Oauth4Qq oauth4Qq = new Oauth4Qq();
    protected static String token;
//    protected static AccessToken accessToken;

    public String getToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute(CoreConstants.WEIBO_TYPE);
        String code;
        if (token == null && type != null) {
            if (type.equals(CoreConstants.SINA_WEIBO)) {
                session.setAttribute(CoreConstants.WEIBO_TYPE, CoreConstants.SINA_WEIBO);
                code = (String) session.getAttribute(CoreConstants.SINA_WEIBO_CODE);
                if (code != null) {
                    try {
                        token = oauth4Sina.getAccessTokenByCode(code).getAccessToken();
                    } catch (WeiboException e) {
                        if (401 == e.getStatusCode()) {
                            logger.info("Unable to get the access token.");
                        } else {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (type.equals(CoreConstants.QQ_WEIBO)) {
                session.setAttribute(CoreConstants.WEIBO_TYPE, CoreConstants.QQ_WEIBO);
                code = (String) session.getAttribute(CoreConstants.QQ_WEIBO_CODE);
                OAuthV2Client.parseAuthorization(code, oauth4Qq);
                oauth4Qq.setGrantType("authorize_code");
                try {
                    OAuthV2Client.accessToken(oauth4Qq);
                    token = oauth4Qq.getAccessToken();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return token;
    }

    public String getTokenType(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(CoreConstants.WEIBO_TYPE);
    }

    public String getUid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute(CoreConstants.WEIBO_TYPE);
        String uid = null;
        if (type != null && token != null) {
            if (type.equals(CoreConstants.SINA_WEIBO)) {
                Account account = new Account();
                account.client.setToken(token);
                try {
                    uid = String.valueOf(account.getUid().get("uid"));
                } catch (WeiboException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (type.equals(CoreConstants.QQ_WEIBO)) {
                uid = oauth4Qq.getClientId();
            }
        }
        return uid;
    }

    public String getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute(CoreConstants.WEIBO_TYPE);
        if (type != null && token != null) {
            if (type.equals(CoreConstants.SINA_WEIBO)) {
                String uid = this.getUid(request);
                Users users = new Users();
                users.client.setToken(token);
                try {
                    User user = users.showUserById(uid);
                    logger.info(user.toString());
                } catch (WeiboException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
