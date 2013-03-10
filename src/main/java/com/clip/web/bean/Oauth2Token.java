package com.clip.web.bean;

public class Oauth2Token {
    private Integer id;
    private Integer aliasId;
    private String accessToken;
    private String refreshToken;
    private Long time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAliasId() {
        return aliasId;
    }

    public void setAliasId(Integer aliasId) {
        this.aliasId = aliasId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
