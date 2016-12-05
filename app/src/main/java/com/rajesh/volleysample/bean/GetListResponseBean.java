package com.rajesh.volleysample.bean;

import java.util.ArrayList;

public class GetListResponseBean {

    private int page, per_page, total, total_pages;
    private ArrayList<GetDataResponseBean> data;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<GetDataResponseBean> getData() {
        return data;
    }

    public void setData(ArrayList<GetDataResponseBean> data) {
        this.data = data;
    }
}
