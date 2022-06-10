package com.baochau.dmt.webapi;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyItemViewHolder> {
    private Context context;
    private final List<Comment> replys;

    public ReplyAdapter(Context context, List<Comment> replys) {
        this.context = context;
        this.replys = replys;
    }

    @NonNull
    @Override
    public ReplyItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment, viewGroup, false);
        return new ReplyAdapter.ReplyItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyItemViewHolder replyItemViewHolder, int i) {
        Comment item = replys.get(i);
        replyItemViewHolder.fullName.setText(item.full_name);
        replyItemViewHolder.content.setText(android.text.Html.fromHtml(item.content));
        String url = "https://my.vnexpress.net/apifrontend/getusersprofile?myvne_users_id%5B%5D=" + item.userid;;
        new AsyncTaskNetwork(context, new CallAPI() {
            @Override
            public void showListComment(String contentApi) throws IOException, JSONException {
                JSONObject jsonObject = new JSONObject(contentApi);
                String url_avatar=jsonObject.getJSONObject("arrUsers").getJSONObject(String.valueOf(item.userid)).getString("user_avatar");
                System.out.println("Chau: "+url_avatar);
                Picasso.get().load(url_avatar).into(replyItemViewHolder.avatar);
            }
        }).execute(url);
    }

    @Override
    public int getItemCount() {
        return replys==null?0: replys.size();
    }

    public class ReplyItemViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView avatar;
        public TextView fullName, content, numReply;
        public RecyclerView rvReply;
        public LinearLayout layoutReply;

        public ReplyItemViewHolder(@NonNull View itemView) {
            super(itemView);
            //***
            avatar = itemView.findViewById(R.id.avatar);
            fullName = itemView.findViewById(R.id.fullName);
            content = itemView.findViewById(R.id.content);
            numReply = itemView.findViewById(R.id.reply);
            rvReply = itemView.findViewById(R.id.rvReply);
            layoutReply = itemView.findViewById(R.id.layoutReply);

            layoutReply.setVisibility(View.GONE);
            rvReply.setVisibility(View.GONE);
        }
    }
}