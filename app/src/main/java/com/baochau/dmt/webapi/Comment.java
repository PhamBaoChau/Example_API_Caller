package com.baochau.dmt.webapi;


public class Comment {
    public String comment_id;
    public String content;
    public String userid;
    public String full_name;
    public Reply replys;

    public Comment(String comment_id, String content, String userid, String full_name, Reply replys) {
        this.comment_id = comment_id;
        this.content = content;
        this.userid = userid;
        this.full_name = full_name;
        this.replys = replys;
    }

    public Comment() {
    }
}
