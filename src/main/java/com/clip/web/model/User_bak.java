package com.clip.web.model;

import javax.persistence.*;

@Entity

@Table(name = "jam_user")
public class User_bak {
    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private Integer remind;
    private Integer privacy;
    private String description;
    private String sina_weibo_token;
    private String sina_weibo_uid;
    private String qq_weibo_token;
    private String qq_weibo_uid;
    private Long register_date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "remind")
    public Integer getRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
    }

    @Column(name = "privacy")
    public Integer getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "sina_weibo_token")
    public String getSina_weibo_token() {
        return sina_weibo_token;
    }

    public void setSina_weibo_token(String sina_weibo_token) {
        this.sina_weibo_token = sina_weibo_token;
    }

    @Column(name = "sina_weibo_uid")
    public String getSina_weibo_uid() {
        return sina_weibo_uid;
    }

    public void setSina_weibo_uid(String sina_weibo_uid) {
        this.sina_weibo_uid = sina_weibo_uid;
    }

    @Column(name = "qq_weibo_token")
    public String getQq_weibo_token() {
        return qq_weibo_token;
    }

    public void setQq_weibo_token(String qq_weibo_token) {
        this.qq_weibo_token = qq_weibo_token;
    }

    @Column(name = "qq_weibo_uid")
    public String getQq_weibo_uid() {
        return qq_weibo_uid;
    }

    public void setQq_weibo_uid(String qq_weibo_uid) {
        this.qq_weibo_uid = qq_weibo_uid;
    }

    public Long getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Long register_date) {
        this.register_date = register_date;
    }
}
