package com.baochau.dmt.webapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CallAPI{
    RecyclerView rvComment;
    ArrayList<Comment> comments;
    CommentAdapter commentAdapter;
    Moshi moshi;
    Type commentType;
    JsonAdapter<ArrayList<Comment>> jsonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvComment = findViewById(R.id.rvComment);
        String url="https://usi-saas.vnexpress.net/index/get?offset=0&limit=25&frommobile=0&sort=like&is_onload=1&objectid=4470485&objecttype=1&siteid=1000000&categoryid=1001014&sign=b4f1d09cb3a119da6fc6cac4a417b205&tab_active=most_like&cookie_aid=gix29diio21vbm7d.1654087277.des&usertype=4&template_type=1&app_mobile_device=0&title=Gia+%C4%91%C3%ACnh+ng%C3%A0y+n%C3%A0o+c%C5%A9ng+h%E1%BB%8Fi+t%C3%B4i+chuy%E1%BB%87n+ch%E1%BB%93ng+con+-+VnExpress";
        moshi = new Moshi.Builder().build();
        commentType = Types.newParameterizedType(ArrayList.class, Comment.class);
        jsonAdapter = moshi.adapter(commentType);
        new AsyncTaskNetwork(this,this).execute(url);
    }

    @Override
    public void showListComment(String contentApi) throws IOException {

        comments=jsonAdapter.fromJson(contentApi);
        CommentAdapter adapter=new CommentAdapter(comments);
        rvComment.setAdapter(adapter);
    }
}