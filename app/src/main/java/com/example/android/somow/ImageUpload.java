package com.example.android.somow;

import android.net.Uri;
public class ImageUpload {
    String time;
    Uri uri;
    String price;
    String description;

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Uri getUri() {
        return uri;
    }
    ImageUpload(String d,Uri u,String t,String p)
    {
        this.description=d;
        this.uri=u;
        time=t;
        price=p;

    }

    public String getPrice() {
        return price;
    }
}
