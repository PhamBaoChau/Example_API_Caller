package com.baochau.dmt.webapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService {

    Gson gson=new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService=new Retrofit.Builder()
            .baseUrl("https://usi-saas.vnexpress.net/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("index/get?offset=0&limit=25&frommobile=0&sort=like&is_onload=1&objectid=4470485&objecttype=1&siteid=1000000&categoryid=1001014&sign=b4f1d09cb3a119da6fc6cac4a417b205&tab_active=most_like&cookie_aid=gix29diio21vbm7d.1654087277.des&usertype=4&template_type=1&app_mobile_device=0&title=Gia+%C4%91%C3%ACnh+ng%C3%A0y+n%C3%A0o+c%C5%A9ng+h%E1%BB%8Fi+t%C3%B4i+chuy%E1%BB%87n+ch%E1%BB%93ng+con+-+VnExpress")
    Call<Article>getArticle();

    @GET("index/getreplay?siteid=1000000&objectid=4470485&objecttype=1&limit=12&offset=0&cookie_aid=7yrpja7cci6u00om.1654269095.des&sort_by=like&template_type=1")
    Call<Article>getRepliesByCommentId(@Query("id") int commentId);
}
