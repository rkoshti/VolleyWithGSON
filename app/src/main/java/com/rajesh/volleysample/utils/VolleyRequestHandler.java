package com.rajesh.volleysample.utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.rajesh.volleysample.AppController;
import com.rajesh.volleysample.listeners.ServiceCallBack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyRequestHandler {

    private static final String TAG = "VolleyRequestHandler";

    public void makeJsonRequest(Context context, int methodType, String url, final String requestTag, final ServiceCallBack listener, JSONObject requestObject) {

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(methodType,
                url, requestObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        listener.onSuccess(requestTag, response);

                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                listener.onFailure(requestTag, error.getMessage());

                pDialog.hide();
            }


        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };

        AppController.getInstance().addToRequestQueue(jsonObjReq, requestTag);

    }

    public void makeStringRequest(Context context, int methodType, String url, final String requestTag, final ServiceCallBack listener, final JSONObject requestObject) {

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest jsonObjReq = new StringRequest(methodType,
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                        listener.onSuccess(requestTag, response);

                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                listener.onFailure(requestTag, error.getMessage());

                pDialog.hide();
            }


        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {

                return requestObject.toString().getBytes();
            }
        };

        AppController.getInstance().addToRequestQueue(jsonObjReq, requestTag);

    }

    public void makeJsonArrayRequest(Context context, String url, final String requestTag, final ServiceCallBack listener) {

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();


        JsonArrayRequest jsonObjReq = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        listener.onSuccess(requestTag, response.toString());

                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                listener.onFailure(requestTag, error.getMessage());

                pDialog.hide();
            }


        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        AppController.getInstance().addToRequestQueue(jsonObjReq, requestTag);

    }

}
