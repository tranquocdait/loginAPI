package com.cnpm.doan2.config;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnpm.doan2.R;
import com.cnpm.doan2.activites.MainActivity;
import com.cnpm.doan2.models.Image;
import com.cnpm.doan2.models.Place;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterPlace extends ArrayAdapter<Place> {

    public AdapterPlace( Context context, ArrayList<Place> placeArrayList) {
        super(context, 0,placeArrayList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.activity_place,parent,false);
        }
        TextView namePlace=(TextView) convertView.findViewById(R.id.id_namePlace);
        TextView address=(TextView) convertView.findViewById(R.id.id_address);
        TextView about=(TextView) convertView.findViewById(R.id.id_about);
        ImageView image=(ImageView) convertView.findViewById(R.id.id_imagePlace);

        Place place=getItem(position);
        if (place!=null){
            List<Image> imageList=place.getImages();

            namePlace.setText(place.getNamePlace());
            address.setText(place.getAddress());
            String str=place.getAbout();
            String textAbout=str.substring(0,100)+"....";
            about.setText(textAbout);

            Picasso.with(getContext()).load(imageList.get(0).getUrl()).into(image);

        }
        return convertView;
    }
}
