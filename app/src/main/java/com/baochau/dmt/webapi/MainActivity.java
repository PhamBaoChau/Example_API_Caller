package com.baochau.dmt.webapi;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

//import com.squareup.moshi.JsonAdapter;
//import com.squareup.moshi.Moshi;
//import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvComment;
    //Moshi moshi;
    Type commentType;
    //JsonAdapter<List<Comment>> jsonAdapter;
    List<Comment> comments;
    Article article;
    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvComment = findViewById(R.id.rvComment);
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        ApiService.apiService.getArticle().enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                comments=response.body().data.items;
                adapter = new CommentAdapter(MainActivity.this, comments);
                rvComment.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Call Api erorr",Toast.LENGTH_SHORT).show();
            }

        });
    }
}