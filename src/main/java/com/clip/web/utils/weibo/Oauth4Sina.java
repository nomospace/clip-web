package com.clip.web.utils.weibo;

import com.clip.web.utils.SysProperties;
import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class Oauth4Sina extends Oauth {
    public Oauth4Sina() {
        super();
    }

    @Override
    public AccessToken getAccessTokenByCode(String code) throws WeiboException {
        return new AccessToken(client.post(
                WeiboConfig.getValue("accessTokenURL"),
                new PostParameter[]{
                        new PostParameter("client_id", SysProperties.SINA_WEIBO_CLIENT_ID),
                        new PostParameter("client_secret", SysProperties.SINA_WEIBO_CLIENT_SERCRET),
                        new PostParameter("grant_type", "authorization_code"),
                        new PostParameter("code", code),
                        new PostParameter("redirect_uri", SysProperties.SINA_WEIBO_REDIRECT_URI)}, false));
    }

    @Override
    public String authorize(String response_type, String state, String scope) throws WeiboException {
        return WeiboConfig.getValue("authorizeURL").trim() + "?client_id="
                + SysProperties.SINA_WEIBO_CLIENT_ID + "&redirect_uri="
                + SysProperties.SINA_WEIBO_REDIRECT_URI
                + "&response_type=" + response_type
                + "&state=" + state
                + "&scope=" + scope;
    }

}
