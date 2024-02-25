package com.example.demoapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demoapp.R;
import com.example.demoapp.model.DogBreed;

import java.util.ArrayList;
import java.util.List;

public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.DogViewHolder> {

    List<DogBreed> dogsList;

    private Context context;

    public DogsListAdapter(List<DogBreed> dogsList, Context context) {
        this.dogsList = dogsList;
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDogsList(List<DogBreed> dogsList) {
        this.dogsList = dogsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dog, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        holder.name.setText((CharSequence) dogsList.get(position).getDogBreed().toString());
        holder.lifespan.setText((CharSequence) dogsList.get(position).getLifeSpan().toString());
        Glide.with(context)
                .load(this.dogsList.get(position).getImageUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }

    public static class DogViewHolder extends RecyclerView.ViewHolder {

        public View view;
        TextView name, lifespan;
        ImageView image;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            lifespan = itemView.findViewById(R.id.lifeSpan);
            image = itemView.findViewById(R.id.imageView);
        }

    }
}
