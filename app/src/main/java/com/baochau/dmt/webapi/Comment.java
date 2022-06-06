package com.baochau.dmt.webapi;

import java.util.ArrayList;

public class Comment {
    public String comment_id;
    public String content;
    public String userid;
    public String full_name;

    public Comment(String comment_id, String content, String userid, String full_name) {
        this.comment_id = comment_id;
        this.content = content;
        this.userid = userid;
        this.full_name = full_name;
    }

    public Comment() {
    }
}
