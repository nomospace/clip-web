package com.clip.web.utils.weibo;

import com.clip.web.utils.SysProperties;
import com.tencent.weibo.oauthv2.OAuthV2;

public class Oauth4Qq extends OAuthV2 {
    public Oauth4Qq() {
        super(SysProperties.QQ_WEIBO_CLIENT_ID, SysProperties.QQ_WEIBO_CLIENT_SERCRET, SysProperties.QQ_WEIBO_REDIRECT_URI);
    }
}
