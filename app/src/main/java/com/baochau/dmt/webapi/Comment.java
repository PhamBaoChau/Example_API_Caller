package com.baochau.dmt.webapi;

import java.util.ArrayList;

public class Comment {
    public String userid;
    public String full_name;
    public String content;
    public int total;
    public ArrayList<Comment>items;

    public Comment(String userid, String full_name, String content, int total, ArrayList<Comment> items) {
        this.userid = userid;
        this.full_name = full_name;
        this.content = content;
        this.total = total;
        this.items = items;
    }

    public Comment() {
    }
}
