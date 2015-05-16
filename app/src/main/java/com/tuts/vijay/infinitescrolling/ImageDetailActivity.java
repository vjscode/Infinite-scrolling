package com.tuts.vijay.infinitescrolling;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;


public class ImageDetailActivity extends Activity {

    ImageView imageViewDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_detail);

        imageViewDetail = (ImageView) findViewById(R.id.fullscreen_image);

        setImage(getIntent().getStringExtra("url"));
    }

    private void setImage(String url) {
        Picasso.with(this)
                .load(url)
                .into(imageViewDetail);
    }
}
