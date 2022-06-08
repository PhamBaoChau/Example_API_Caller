package com.baochau.dmt.webapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentItemViewHolder>{


    private final List<Comment> comments;
    private Context context;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.comments = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentItemViewHolder holder, int position) {
        Comment item = comments.get(position);
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(holder.avatar);
        holder.fullName.setText(item.full_name);
        holder.content.setText(item.content);
        holder.numReply.setText(String.valueOf(item.replys.total));

        holder.rvReply.setVisibility(View.GONE);
        if ((item.replys.total>0) && (item.replys.items!=null)){
            holder.layoutReply.setVisibility(View.VISIBLE);
            holder.layoutReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.rvReply.setVisibility(View.VISIBLE);
                    holder.layoutReply.setVisibility(View.GONE);
                    String url = "https://usi-saas.vnexpress.net/index/getreplay?siteid=1000000&objectid=4470485&objecttype=1&id="
                            + item.comment_id
                            + "&limit=12&offset=0&cookie_aid=7yrpja7cci6u00om.1654269095.des&sort_by=like&template_type=1";
                    new AsyncTaskNetwork(context, new CallAPI() {
                        @Override
                        public void showListComment(String contentApi) throws IOException {
                            Moshi moshi = new Moshi.Builder().build();
                            Type type = Types.newParameterizedType(List.class, Comment.class);
                            JsonAdapter<List<Comment>> jsonAdapter = moshi.adapter(type);
                            contentApi = contentApi.substring(49, contentApi.length() - 34);
                            final List<Comment> replies = jsonAdapter.fromJson(contentApi);
                            item.replys.items=replies;
                            holder.rvReply.setLayoutManager(new LinearLayoutManager(context));
                            holder.rvReply.setAdapter( new ReplyAdapter(context, item.replys.items));
                        }
                    }).execute(url);
                }
            });
        }
        else {
            holder.layoutReply.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentItemViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView avatar;
        public TextView fullName, content, numReply;
        public RecyclerView rvReply;
        public LinearLayout layoutReply;

        public CommentItemViewHolder(@NonNull View itemView) {
            super(itemView);
            //***
            avatar = itemView.findViewById(R.id.avatar);
            fullName = itemView.findViewById(R.id.fullName);
            content = itemView.findViewById(R.id.content);
            numReply = itemView.findViewById(R.id.reply);
            rvReply = itemView.findViewById(R.id.rvReply);
            layoutReply=itemView.findViewById(R.id.layoutReply);
        }
    }
}