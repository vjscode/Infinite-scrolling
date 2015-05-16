package com.tuts.vijay.infinitescrolling;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by vijay on 11/22/14.
 */
public class ImageAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String> urls = new ArrayList<String>();
    VolleyURLsLoader loader;
    private boolean doneLoading = false;

    int pageCount = 0;
    public ImageAdapter(Context context) {
        this.context = context;
        loader = new VolleyURLsLoader(context, urls, this);
        newPageLoad();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("test", "getview called: " + position + ", size: " + urls.size());

        if (position >= urls.size() - 1) {
            newPageLoad();
        }

        ImageView view = (ImageView) convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = (ImageView) inflater.inflate(R.layout.grid_image, null);
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        // Get the image URL for the current position.
        String url = getItem(position);

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(url) //
                .placeholder(R.drawable.place_holder) //
                .error(R.drawable.ic_launcher) //
                .fit() //
                .tag(url) //
                .into(view);

        view.setTag(R.id.fullscreen_image, url);

        return view;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public String getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void newPageLoad() {
        loader.load(("https://api.imgur.com/3/gallery/random/random/" + pageCount++));
    }

    public void setLoading(boolean doneLoading) {
        this.doneLoading = doneLoading;
    }
}
