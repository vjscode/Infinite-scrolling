package com.tuts.vijay.infinitescrolling;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class ImageGridActivity extends Activity {

    ImageAdapter adapter;
    GridView gridView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ImageAdapter(this);
        setContentView(R.layout.activity_image_grid);
        gridView = (GridView) findViewById(R.id.imgGrid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(itemClickListener);
        context = this;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String url = (String) view.getTag(R.id.fullscreen_image);
            Intent intent = new Intent(context, ImageDetailActivity.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    };
}
