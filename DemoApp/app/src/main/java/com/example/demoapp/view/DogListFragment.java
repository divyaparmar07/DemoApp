package com.example.demoapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demoapp.R;
import com.example.demoapp.model.DogBreed;
import com.example.demoapp.viewmodel.ListViewModel;

import java.util.List;


public class DogListFragment extends Fragment {

    RecyclerView dogList;
    private List<DogBreed> dogBreeds;
    private DogsListAdapter adapter;
    private Context context;
    public DogListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dog_list, container, false);
        dogList = view.findViewById(R.id.dogsList);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        dogList.setLayoutManager(layoutManager);
        DogsListAdapter adapter = new DogsListAdapter(dogBreeds, this.getContext());
        dogList.setAdapter(adapter);

        ListViewModel listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        listViewModel.getDogsListObserver().observe(getViewLifecycleOwner(), new Observer<List<DogBreed>>() {
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