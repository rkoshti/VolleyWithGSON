package com.rajesh.volleysample.listeners;


public interface ServiceCallBack {

    public void onSuccess(String requestTag, Object data);

    public void onFailure(String requestTag,String message);

}
