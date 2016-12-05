package com.rajesh.volleysample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rajesh.volleysample.R;
import com.rajesh.volleysample.bean.ArrayResponseBean;
import com.rajesh.volleysample.bean.GetListResponseBean;
import com.rajesh.volleysample.bean.Response;
import com.rajesh.volleysample.listeners.ServiceCallBack;
import com.rajesh.volleysample.utils.AppConstants;
import com.rajesh.volleysample.utils.VolleyImageAsycHandler;
import com.rajesh.volleysample.utils.VolleyRequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyActivity extends AppCompatActivity implements View.OnClickListener, ServiceCallBack {

    private NetworkImageView ivLoadNetworkImage;
    private ImageView ivLoadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button btnGet = (Button) findViewById(R.id.btnGet);
        Button btnPost = (Button) findViewById(R.id.btnPost);
        Button btnJSONArray = (Button) findViewById(R.id.btnJSONArray);
        Button btnString = (Button) findViewById(R.id.btnString);
        Button btnLoadImage = (Button) findViewById(R.id.btnLoadImage);
        Button btnLoadImageView = (Button) findViewById(R.id.btnLoadImageView);
        ivLoadNetworkImage = (NetworkImageView) findViewById(R.id.ivLoad);
        ivLoadImage = (ImageView) findViewById(R.id.ivLoadImageView);

        setSupportActionBar(toolbar);
        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        btnJSONArray.setOnClickListener(this);
        btnString.setOnClickListener(this);
        btnLoadImage.setOnClickListener(this);
        btnLoadImageView.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnGet:

                try {
                    VolleyRequestHandler getRequestHandler = new VolleyRequestHandler();
                    getRequestHandler.makeJsonRequest(VolleyActivity.this, Request.Method.GET, AppConstants.GET_URL, AppConstants.GET_TAG, this, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case R.id.btnPost:

                try {

                    JSONObject requestObject = new JSONObject();
                    requestObject.put("email", "peter@klaven");
                    requestObject.put("password", "cityslicka");

                    VolleyRequestHandler postRequestHandler = new VolleyRequestHandler();
                    postRequestHandler.makeJsonRequest(VolleyActivity.this, Request.Method.POST, AppConstants.POST_URL, AppConstants.POST_TAG, this, requestObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.btnJSONArray:

                try {

                    VolleyRequestHandler postRequestHandler = new VolleyRequestHandler();
                    postRequestHandler.makeJsonArrayRequest(VolleyActivity.this, AppConstants.JSON_ARRAY_URL, AppConstants.ARRAY_TAG, this);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;


            case R.id.btnString:

                try {

                    JSONObject requestObject = new JSONObject();
                    requestObject.put("email", "peter@klaven");
                    requestObject.put("password", "cityslicka");

                    VolleyRequestHandler postRequestHandler = new VolleyRequestHandler();
                    postRequestHandler.makeStringRequest(VolleyActivity.this, Request.Method.POST, AppConstants.POST_URL, AppConstants.STRING_TAG, this, requestObject);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.btnLoadImage:

                VolleyImageAsycHandler.loadNetworkImageviewFromURL("http://microblogging.wingnity.com/JSONParsingTutorial/brad.jpg",
                        ivLoadNetworkImage);

                break;

            case R.id.btnLoadImageView:

                VolleyImageAsycHandler.loadImageviewFromURL("http://microblogging.wingnity.com/JSONParsingTutorial/brad.jpg",
                        ivLoadImage);

                break;
        }

    }


    /**
     * Response CallBack
     */

    @Override
    public void onSuccess(String requestTag, Object data) {

        Toast.makeText(VolleyActivity.this, requestTag + ">>>" + data, Toast.LENGTH_LONG).show();
        Gson gson = new Gson();
        switch (requestTag) {

            case AppConstants.POST_TAG:

                Response responseDataModel = gson.fromJson(String.valueOf(data), Response.class);
                Log.e("TAG", "onSuccess: " + responseDataModel);

                break;

            case AppConstants.STRING_TAG:

                Response response = gson.fromJson(String.valueOf(data), Response.class);
                Log.e("TAG", "onSuccess: " + response);

                break;

            case AppConstants.GET_TAG:

                GetListResponseBean responseGEt = gson.fromJson(String.valueOf(data), GetListResponseBean.class);
                Log.e("TAG", "onSuccess: " + responseGEt);

                break;

            case AppConstants.ARRAY_TAG:


                ArrayList<ArrayResponseBean> responseList = gson.fromJson(String.valueOf(data),
                        new TypeToken<ArrayList<ArrayResponseBean>>() {
                        }.getType());

                Log.e("TAG", "onSuccess: " + responseList);

                break;
        }

    }

    @Override
    public void onFailure(String requestTag, String message) {

        Toast.makeText(VolleyActivity.this, requestTag + ">>>" + message, Toast.LENGTH_LONG).show();

    }
}
