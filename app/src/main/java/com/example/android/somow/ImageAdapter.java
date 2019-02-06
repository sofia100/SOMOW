package com.example.android.somow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter<ImageUpload> {

    ImageAdapter(Context context, ArrayList<ImageUpload> w)
    {
        super(context,0,w);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View list = convertView;
        if(list==null)
        {
            list= LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        }
        ImageUpload currAdhy=getItem(position);


        ImageView img=list.findViewById(R.id.imageView2);
        img.setImageURI(currAdhy.getUri());

        TextView description= list.findViewById(R.id.textView);
        description.setText(currAdhy.getDescription());

        TextView bidtime= list.findViewById(R.id.textView2);
        bidtime.setText(currAdhy.getTime()+"        "+currAdhy.getPrice());


        return list;
    }
}
