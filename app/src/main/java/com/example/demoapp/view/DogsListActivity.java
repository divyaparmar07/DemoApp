package com.example.demoapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.ShareActivity;
import com.example.demoapp.model.DogBreed;
import com.example.demoapp.viewmodel.ListViewModel;

import java.util.List;

public class DogsListActivity extends AppCompatActivity implements RecyclerViewInterface {

    List<DogBreed> dogBreeds;
    private DogsListAdapter adapter;
    private static final String CHANNEL_ID = "Dog's channel id";
    private static final int NOTIFICATION_ID = 1234;

    public DogsListActivity() {
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_list);

        RecyclerView dogList = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        dogList.setLayoutManager(layoutManager);
        adapter = new DogsListAdapter(dogBreeds, this, this);
        dogList.setAdapter(adapter);

        ListViewModel listViewModel = new ViewModelProvider(this).get(ListViewModel.class);

        listViewModel.getDogsListObserver().observe(this, dogBreeds -> {
            if (dogBreeds != null) {
                adapter.setDogsList(dogBreeds);
            } else {
                Toast.makeText(getApplicationContext(), "Not receiving data", Toast.LENGTH_SHORT).show();
                Log.d("error", "data is not found");
            }
        });
        listViewModel.makeApiCall();
    }

    @Override
    public void onItemClick(DogBreed dogBreed) {
//        Toast.makeText(getApplicationContext(),"Dog is clicked",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DogsListActivity.this, DogsDetailActivity.class);
        intent.putExtra("NAME", dogBreed.getDogBreed());
        intent.putExtra("LIFESPAN", dogBreed.getLifeSpan());
        intent.putExtra("IMAGE", dogBreed.getImageUrl());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.notification) {
            createNotification();
        } else if (item.getItemId() == R.id.sendsms) {
            Intent intent = new Intent(this, SendSmsActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.share) {
            Intent intent = new Intent(this, ShareActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

//    private void goToSendSmsActivity() {
//        Intent intent = new Intent(this, SendSmsActivity.class);
//        startActivity(intent);
//    }

    private void createNotification() {

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.dog_icon, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        assert bitmapDrawable != null;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.dog_icon)
                    .setContentTitle("Dog retrieved")
                    .setChannelId(CHANNEL_ID)
                    .setLargeIcon(bitmap)
                    .setContentText("This is a notification to let you know that the dog information has been retrieved")
                    .build();
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.dog_icon)
                    .setLargeIcon(bitmap)
                    .setContentTitle("Dog retrieved")
                    .setContentText("This is a notification to let you know that the dog information has been retrieved")
                    .build();
        }

        notificationManager.notify(NOTIFICATION_ID, notification);

    }

}

