package com.clip.web.utils.weibo;

import weibo4j.http.AccessToken;
import weibo4j.http.Response;
import weibo4j.model.WeiboException;

public class AccessToken4Sina extends AccessToken {
    private String uid;

    public AccessToken4Sina(Response res) throws WeiboException {
        super(res);
    }

    public String getUserId() {
        return this.uid;
    }
}
