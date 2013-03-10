package com.clip.web.model;

import javax.persistence.*;

@Entity

@Table(name = "oauth2_token")
public class Oauth2Token {
    private Integer id;
    private Integer aliasId;
    private String accessToken;
    private String refreshToken;
    private Long time;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "alias_id")
    public Integer getAliasId() {
        return aliasId;
    }

    public void setAliasId(Integer aliasId) {
        this.aliasId = aliasId;
    }

    @Column(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Column(name = "refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Column(name = "time")
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
