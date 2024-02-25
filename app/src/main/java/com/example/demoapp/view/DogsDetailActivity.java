package com.example.demoapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.palette.graphics.Palette;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.demoapp.R;

public class DogsDetailActivity extends AppCompatActivity {

    ConstraintLayout layout;

    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_detail);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView name = findViewById(R.id.nameDetail);
        TextView lifespan = findViewById(R.id.lifeSpanDetail);
        ImageView imageView = findViewById(R.id.imageViewDetail);
        layout = findViewById(R.id.dogDetailLayout);

        String getNameIntent = getIntent().getStringExtra("NAME");
        String getLifespanIntent = getIntent().getStringExtra("LIFESPAN");
        String getImageIntent = getIntent().getStringExtra("IMAGE");

        name.setText(getNameIntent);
        lifespan.setText(getLifespanIntent);
        Glide.with(this)
                .load(getImageIntent)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);

        if (getImageIntent != null) {
            setBackgroundColor(getImageIntent);
        }
    }

    private void setBackgroundColor(String image) {
        Glide.with(this)
                .asBitmap()
                .load(image)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Palette.from(resource)
                                .generate(palette -> {
                                    assert palette != null;
                                    Palette.Swatch color = palette.getVibrantSwatch();
                                    if (color != null) {
                                        int c = color.getRgb();
                                        layout.setBackgroundColor(c);
                                    }
                                });
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default: {
                this.finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}