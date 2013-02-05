package com.clip.web.utils.weibo;

import com.clip.web.utils.CoreConstants;
import com.tencent.weibo.oauthv2.OAuthV2Client;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weibo4j.model.WeiboException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TokenUtils {
    private static Log logger = LogFactory.getLog(TokenUtils.class);
    private static Oauth4Sina oauth4Sina = new Oauth4Sina();
    private static Oauth4Qq oauth4Qq = new Oauth4Qq();
    private String token;

    public String getToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute(CoreConstants.WEIBO_TYPE);
        String code;
        if (token == null && type != null) {
            if (type.equals(CoreConstants.SINA_WEIBO)) {
                code = (String) session.getAttribute(CoreConstants.SINA_WEIBO_CODE);
                if (code != null) {
                    try {
                        token = oauth4Sina.getAccessTokenByCode(code).getAccessToken();
                        System.out.println(token);
                    } catch (WeiboException e) {
                        if (401 == e.getStatusCode()) {
                            logger.info("Unable to get the access token.");
                        } else {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (type.equals(CoreConstants.QQ_WEIBO)) {
                code = (String) session.getAttribute(CoreConstants.QQ_WEIBO_CODE);
                OAuthV2Client.parseAuthorization(code, oauth4Qq);
                if (oauth4Qq.getStatus() == 2) {
                    logger.info("Get Authorization Code failed!");
                } else {
                    oauth4Qq.setGrantType("authorize_code");
                    try {
                        System.out.println(OAuthV2Client.accessToken(oauth4Qq));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    oauth4Qq.setAuthorizeCode(code);
//                    token = oauth4Qq.getAccessToken();
//                    System.out.println(token);
                }
            }
        }
        return token;
    }

}
