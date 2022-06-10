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

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentItemViewHolder> {

    private Context context;
    private final List<Comment> comments;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
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
        holder.fullName.setText(item.full_name);
        holder.content.setText(android.text.Html.fromHtml(item.content));
        holder.numReply.setText(String.valueOf(item.replys.total));
        holder.rvReply.setVisibility(View.GONE);
        holder.layoutReply.setVisibility(View.GONE);

        String url = "https://my.vnexpress.net/apifrontend/getusersprofile?myvne_users_id%5B%5D=" + item.userid;
        ;
        new AsyncTaskNetwork(context, new CallAPI() {
            @Override
            public void showListComment(String contentApi) throws IOException, JSONException {
                JSONObject jsonObject = new JSONObject(contentApi);
                String url_avatar = jsonObject.getJSONObject("arrUsers").getJSONObject(String.valueOf(item.userid)).getString("user_avatar");
                System.out.println("Chau: " + url_avatar);
                Picasso.get().load(url_avatar).into(holder.avatar);
            }
        }).execute(url);

        if (item.replys.total > 0 && item.replys.items != null) {
            holder.layoutReply.setVisibility(View.VISIBLE);
            holder.layoutReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.layoutReply.setVisibility(View.GONE);
                    ApiService.apiService.getRepliesByCommentId(Integer.parseInt(item.comment_id)).enqueue(new Callback<Article>() {
                        @Override
                        public void onResponse(Call<Article> call, Response<Article> response) {
                            holder.rvReply.setVisibility(View.VISIBLE);
                            List<Comment> replies = response.body().data.items;
                            ReplyAdapter replyAdapter = new ReplyAdapter(context, replies);
                            holder.rvReply.setLayoutManager(new LinearLayoutManager(context));
                            holder.rvReply.setAdapter(replyAdapter);
                        }

                        @Override
                        public void onFailure(Call<Article> call, Throwable t) {

                        }
                    });
                }
            });
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
            layoutReply = itemView.findViewById(R.id.layoutReply);
        }
    }
}