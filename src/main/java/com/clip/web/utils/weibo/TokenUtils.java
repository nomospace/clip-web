package com.clip.web.utils.weibo;

import com.clip.web.utils.CoreConstants;
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
    private HttpServletRequest request;

    public String getToken() {
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute(CoreConstants.WEIBO_TYPE);
        String code = (String) session.getAttribute(CoreConstants.SINA_WEIBO_CODE);
        if (token == null) {
            if (code != null) {
                if (type.equals(CoreConstants.SINA_WEIBO)) {
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
                } else if (type.equals(CoreConstants.QQ_WEIBO)) {
                    token = oauth4Qq.getAccessToken();
                }
            }
        }
        return token;
    }

}
