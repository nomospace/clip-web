package com.clip.web.utils;

public class SysProperties {
    public static final String SYS_UUID = ConfigurationManager.getConfig().getString("clip.uuid");
    public static final String SINA_WEIBO_CLIENT_ID = ConfigurationManager.getConfig().getString("sina_weibo_client_ID");
    public static final String SINA_WEIBO_CLIENT_SERCRET = ConfigurationManager.getConfig().getString("sina_weibo_client_SERCRET");
    public static final String SINA_WEIBO_REDIRECT_URI = ConfigurationManager.getConfig().getString("sina_weibo_redirect_URI");
    public static final String QQ_WEIBO_CLIENT_ID = ConfigurationManager.getConfig().getString("qq_weibo_client_ID");
    public static final String QQ_WEIBO_CLIENT_SERCRET = ConfigurationManager.getConfig().getString("qq_weibo_client_SERCRET");
    public static final String QQ_WEIBO_REDIRECT_URI = ConfigurationManager.getConfig().getString("qq_weibo_redirect_URI");
    public static final String SYS_LANG = "zh";
    public static final String SYS_COUNTRY = "CN";
}
