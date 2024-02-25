package com.example.demoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;

import com.example.demoapp.R;
import com.example.demoapp.model.DogBreed;
import com.example.demoapp.viewmodel.ListViewModel;

import java.util.List;

public class DogsListActivity extends AppCompatActivity {

    private List<DogBreed> dogBreeds;
    private DogsListAdapter adapter;
    private ListViewModel listViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_list);

        RecyclerView dogList = findViewById(R.id.dogsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        dogList.setLayoutManager(layoutManager);
        DogsListAdapter adapter = new DogsListAdapter(dogBreeds, this);
        dogList.setAdapter(adapter);

        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        listViewModel.getDogsListObserver().observe(this, new Observer<List<DogBreed>>() {
            @Override
            public void onChanged(List<DogBreed> dogBreeds) {
                if (dogBreeds != null) {
                    dogBreeds = dogBreeds;
                    adapter.setDogsList(dogBreeds);
                } else {
                    Log.d("error","data is not found");
                }
            }
        });

    }
}