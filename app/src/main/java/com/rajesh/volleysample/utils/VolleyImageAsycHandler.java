package com.rajesh.volleysample.utils;


import android.util.Log;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.rajesh.volleysample.AppController;

public class VolleyImageAsycHandler {


    public static void loadNetworkImageviewFromURL(String url, NetworkImageView networkImageView){

        ImageLoader networkImageLoader = AppController.getInstance().getImageLoader();

        networkImageView.setImageUrl(url, networkImageLoader);


    }

    public static void loadImageviewFromURL(String url, final ImageView imageViewmageView){

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        imageLoader.get(url, new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    imageViewmageView.setImageBitmap(response.getBitmap());
                }
            }
        });

    }
}
