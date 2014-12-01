package com.tuts.vijay.infinitescrolling;

/**
 * Created by vijay on 11/22/14.
 */

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyURLsLoader {
    RequestQueue queue;
    Context context;
    ArrayList<String> urls;
    ImageAdapter adapter;

    VolleyURLsLoader(Context context, ArrayList<String> urls, ImageAdapter adapter) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
        this.urls = urls;
        this.adapter = adapter;
    }

    public void load(String url) {
        JsonObjectRequest request = new JsonObjectRequest(url, null, new ResponseHandler(url),
                new ErrListener(url));
        queue.add(request);
    }

    class ResponseHandler implements Response.Listener<JSONObject> {

        String url;


        public ResponseHandler(String url) {
            this.url = url;
        }

        @Override
        public void onResponse(JSONObject response) {
            //parseAndAddToAdapter(response);
            //parseSimpleAndAddToAdapter(response);
            parseImgurSimpleAndAddToAdapter(response);
        }

        //Not used
        private void parseSimpleAndAddToAdapter(JSONObject response) {
            try {
                JSONObject photos = response.getJSONObject("photos");
                JSONArray photoArray = photos.getJSONArray("photo");
                for (int i = 0; i < photoArray.length(); i++) {
                    JSONObject obj = photoArray.getJSONObject(i);
                    String url = obj.getString("url_o");
                    urls.add(url);
                    Log.d("test", "url: " + url);
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                Log.d("test", "json exception: " + e);
            }
        }

        private void parseImgurSimpleAndAddToAdapter(JSONObject response) {
            try {
                  JSONArray photoArray = response.getJSONArray("data");
                for (int i = 0; i < photoArray.length(); i++) {
                    JSONObject obj = photoArray.getJSONObject(i);
                    String url = obj.getString("link");
                    urls.add(url);
                    Log.d("test", "url: " + url);
                }

//                JSONObject photos = response.getJSONObject("photos");
//                JSONArray photoArray = photos.getJSONArray("photo");
//                for (int i = 0; i < photoArray.length(); i++) {
//                    JSONObject obj = photoArray.getJSONObject(i);
//                    String url = obj.getString("url_o");
//                    urls.add(url);
//                    Log.d("test", "url: " + url);
//                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                Log.d("test", "json exception: " + e);
            }
        }

        //Not used
        private void parseAndAddToAdapter(JSONObject response) {
            final String URL = "https://farm";
            final String FLICKR_DOMAIN = ".staticflickr.com/";
            final String SLASH = "/";

            try {
                JSONObject photos = response.getJSONObject("photos");
                JSONArray photoArray = photos.getJSONArray("photo");
                for (int i = 0; i < photoArray.length(); i++) {
                    JSONObject obj = photoArray.getJSONObject(i);
                    StringBuffer url = new StringBuffer();
                    url.append(URL).append(obj.getInt("farm")).append(FLICKR_DOMAIN).append(obj.getString("server"))
                            .append(SLASH).append(obj.getString("id")).append("_").append(obj.getString("secret"))
                            .append("_").append("t.jpg");
                    urls.add(url.toString());
                }
            } catch (Exception e) {
                Log.d("test", "json exception: " + e);
            }
        }
    }

    class ErrListener implements Response.ErrorListener {

        String url;
        public ErrListener(String url) {
            this.url = url;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("test", "error: " + error);
        }
    }
}