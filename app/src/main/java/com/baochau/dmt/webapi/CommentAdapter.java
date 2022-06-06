package com.baochau.dmt.webapi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentItemViewHolder> {
    private final List<Comment> comments;

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent,false);
        return new CommentItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentItemViewHolder holder, int position) {
        Comment item= comments.get(position);
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(holder.avatar);
        holder.fullName.setText(item.full_name);
        holder.content.setText(item.content);
//        holder.numReply.setText(String.valueOf(item.total)+" reply");

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentItemViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView avatar;
        public TextView fullName, content, numReply;
        public RecyclerView rvReply;
        public CommentItemViewHolder(@NonNull View itemView) {
            super(itemView);
            //***
            avatar=itemView.findViewById(R.id.avatar);
            fullName=itemView.findViewById(R.id.fullName);
            content=itemView.findViewById(R.id.content);
            numReply=itemView.findViewById(R.id.reply);
            rvReply=itemView.findViewById(R.id.rvReply);
        }
    }
}
