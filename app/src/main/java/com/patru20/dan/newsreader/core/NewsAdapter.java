package com.patru20.dan.newsreader.core;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.patru20.dan.newsreader.NewsApplication;
import com.patru20.dan.newsreader.R;
import com.patru20.dan.newsreader.activities.DisplayNewsElementActivity;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{

    private Context mContext;
    private int width;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, date;
        ImageView newsImage;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.news_title);
            newsImage = view.findViewById(R.id.news_photo);
            description = view.findViewById(R.id.news_description);
            date = view.findViewById(R.id.news_date);
        }
    }

    public NewsAdapter(Context context, int width){
        this.mContext = context;
        this.width = width;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_news_display_cards, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        if(width != 0){
            NewsItem newsItem = NewsApplication.getNewsManager().getNewsItem(position);
            Glide
                    .with(mContext)
                    .load(newsItem.getImageUrl())
                    .apply(new RequestOptions().override(width / 3, 200))
                    .into(holder.newsImage);

            holder.title.setText(newsItem.getTitle());
            holder.title.setWidth(width - width / 3 - 60);

            holder.description.setText(newsItem.getDescription());
            holder.description.setSingleLine(true);
            holder.description.setWidth(width - width / 3 - 60);

            holder.date.setText(newsItem.getPubDate());
            holder.date.setWidth(width - width / 3 - 60);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent viewNewsIntent = new Intent(mContext, DisplayNewsElementActivity.class);
                    viewNewsIntent.putExtra("position", holder.getAdapterPosition());
                    viewNewsIntent.putExtra("width", width);
                    mContext.startActivity(viewNewsIntent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return NewsApplication.getNewsManager().getNewsList().size();
    }
}
