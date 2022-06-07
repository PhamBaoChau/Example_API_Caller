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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyItemViewHolder>{
    private Context context;
    private final List<Comment>replys;

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
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(replyItemViewHolder.avatar);
        replyItemViewHolder.fullName.setText(item.full_name);
        replyItemViewHolder.content.setText(item.content);
    }

    @Override
    public int getItemCount() {
        return replys.size();
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
            layoutReply=itemView.findViewById(R.id.layoutReply);
        }
    }
}
