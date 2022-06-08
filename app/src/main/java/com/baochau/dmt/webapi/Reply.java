package com.baochau.dmt.webapi;

import java.util.List;

public class Reply {
    int total;
    List<Comment> items;

    public Reply(int total, List<Comment> items) {
        this.total = total;
        this.items = items;
    }
}
