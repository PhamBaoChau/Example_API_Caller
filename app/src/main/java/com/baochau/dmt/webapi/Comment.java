package com.baochau.dmt.webapi;

public class Comment {
    private String userid;
    private String full_name;
    private String content;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment(String userid, String full_name, String content) {
        this.userid = userid;
        this.full_name = full_name;
        this.content = content;
    }

    public Comment() {
    }
}
