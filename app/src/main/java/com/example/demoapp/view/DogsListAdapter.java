package com.example.demoapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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

import java.util.List;

public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.DogViewHolder> {

    public List<DogBreed> dogsList;

    private final Context context;

    //    private final ItemClickListener itemClickListener;
    private RecyclerViewInterface recyclerViewInterface;

    public DogsListAdapter(List<DogBreed> dogsList, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.dogsList = dogsList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
//        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDogsList(List<DogBreed> dogsList) {
        this.dogsList = dogsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog, parent, false);
        return new DogViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        DogBreed currentDog = dogsList.get(position);
//        holder.bind(dogsList.get(position), listener);
        DogBreed currentDog = dogsList.get(position);
        holder.name.setText((CharSequence) dogsList.get(position).getDogBreed());
        holder.lifespan.setText((CharSequence) dogsList.get(position).getLifeSpan());
        Glide.with(context)
                .load(this.dogsList.get(position).getImageUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {
                    recyclerViewInterface.onItemClick(currentDog);
                } else {
                    Log.d("error", "error in code");
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return (dogsList != null) ? dogsList.size() : 0;
    }

//    @Override
//    public void onDogClicked(DogBreed dogBreed) {
//        Intent intent = new Intent(context, DogsDetailActivity.class);
//        intent.
//    }

    public static class DogViewHolder extends RecyclerView.ViewHolder {

        public View view;
        TextView name, lifespan;
        ImageView image;

        public DogViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            lifespan = itemView.findViewById(R.id.lifeSpan);
            image = itemView.findViewById(R.id.imageView);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (recyclerViewInterface != null) {
//                        int pos = getAdapterPosition();
//                        if (pos != RecyclerView.NO_POSITION) {
//                            recyclerViewInterface.onItemClick(do);
//                        }
//                    }
//                }
//            });
        }

//        public void bind(DogBreed dogBreed, DogClickListener listener) {
//            name.setText(dogBreed.dogBreed);
//            lifespan.setText(dogBreed.lifeSpan);
//            Glide.with(itemView.getContext()).load(dogBreed.imageUrl).into(image);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (listener != null) {
//                        listener.onDogClicked(dogBreed);
//                    }
//                }
//            });
//        }
    }
//    public interface ItemClickListener{
//        public void onDogClick(DogBreed dogBreed);
//    }
}
