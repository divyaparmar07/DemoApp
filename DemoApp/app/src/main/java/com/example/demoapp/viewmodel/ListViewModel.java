package com.example.demoapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.demoapp.model.DogBreed;
import com.example.demoapp.model.DogsApi;
import com.example.demoapp.model.DogsApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

    private final MutableLiveData<List<DogBreed>> dogsList;

    public ListViewModel() {
        dogsList = new MutableLiveData<>();
    }

    public MutableLiveData<List<DogBreed>> getDogsListObserver(){
        return dogsList;
    }

    public void makeApiCall() {
        DogsApi api = DogsApiService.getRetroClient().create(DogsApi.class);
        Call<List<DogBreed>> dog = api.getDogs();

        dog.enqueue(new Callback<List<DogBreed>>() {
            @Override
            public void onResponse(Call<List<DogBreed>> call, Response<List<DogBreed>> response) {
                dogsList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<DogBreed>> call, Throwable t) {
                dogsList.postValue(null);
            }
        });
    }

}
