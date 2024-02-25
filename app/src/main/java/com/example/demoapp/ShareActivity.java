package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demoapp.databinding.ActivityShareBinding;
import com.example.demoapp.view.MainActivity;

public class ShareActivity extends AppCompatActivity {
    private ActivityShareBinding activityShareBinding;
    Button shareApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityShareBinding = ActivityShareBinding.inflate(getLayoutInflater());
        setContentView(activityShareBinding.getRoot());

        shareApp = activityShareBinding.shareApp;
        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareApp(ShareActivity.this);
            }
        });
    }

    private void ShareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,"Check this app now : https://play.google.com/store/apps/details?id" + appPackageName);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }
}