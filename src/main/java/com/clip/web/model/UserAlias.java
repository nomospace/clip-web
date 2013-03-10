package com.clip.web.model;

import javax.persistence.*;

@Entity

@Table(name = "user_alias")
public class UserAlias {
    private Integer id;
    private String type;
    private Integer userId;
    private String alias;
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

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "alias")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Column(name = "time")
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
