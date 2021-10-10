package com.example.youtube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<MediaObject> mediaObjects;
    private Context mContext;
    private OnMovieListener mOnMovieListener;

    public RecyclerViewAdapter(Context context, ArrayList<MediaObject> mediaObjects, OnMovieListener onMovieListener) {
        this.mediaObjects = mediaObjects;
        mContext = context;
        mOnMovieListener = onMovieListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_video_list_item, parent, false);
        return new ViewHolder(view, mOnMovieListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mediaObjects.get(position).getThumbnail())
                .into(holder.image);

        holder.name.setText(mediaObjects.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mediaObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView name;
        OnMovieListener onMovieListener;

        public ViewHolder(View itemView, OnMovieListener onMovieListener) {
            super(itemView);
            image = itemView.findViewById(R.id.thumbnail);
            name = itemView.findViewById(R.id.videoTitle);
            this.onMovieListener = onMovieListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMovieListener.onMovieClick(getAdapterPosition()); //pass position of the item that was clicked
        }
    }

    public interface OnMovieListener {  //for detecting a click inside the recycler
        void onMovieClick(int position);
    }
}
