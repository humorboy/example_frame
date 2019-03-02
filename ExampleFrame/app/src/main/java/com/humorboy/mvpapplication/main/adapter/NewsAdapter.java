package com.humorboy.mvpapplication.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.humorboy.mvpapplication.R;
import com.humorboy.mvpapplication.main.model.News;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

public class NewsAdapter extends Adapter {

    List<News> data;
    Context mContext;
    public NewsAdapter(List<News> data, Context context) {
        this.data = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder mViewHolder = (ViewHolder)holder;
        mViewHolder.title.setText(data.get(position).getTitle());
        mViewHolder.content.setText(data.get(position).getContent());
        if(position%2 == 0){
            mViewHolder.image.setBackground(mContext.getDrawable(R.mipmap.rabbit));
        }

        if(position%3 == 0){
            mViewHolder.image.setBackground(mContext.getDrawable(R.mipmap.dog));
        }

        if(position%5 == 0){
            mViewHolder.image.setBackground(mContext.getDrawable(R.mipmap.cat));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView content;
        public ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title);
            content = view.findViewById(R.id.content);
            image = view.findViewById(R.id.image);
        }

    }

}
