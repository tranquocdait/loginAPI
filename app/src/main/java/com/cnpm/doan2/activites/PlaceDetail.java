package com.cnpm.doan2.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnpm.doan2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaceDetail extends AppCompatActivity {
    TextView namePlace,address,about;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        TextView tvnamePlace=(TextView) findViewById(R.id.id_namePlace_detail);
        TextView tvaddress=(TextView) findViewById(R.id.id_address_detail);
        TextView tvabout=(TextView) findViewById(R.id.id_aboutPlace_detail);
        ImageView tvimage=(ImageView) findViewById(R.id.id_imagePlace_detail);

        Intent intent=getIntent();
        String namePlace=intent.getStringExtra("namePlace").toString();
        String address=intent.getStringExtra("address").toString();
        String about=intent.getStringExtra("about").toString();
        ArrayList<String> imageList=(ArrayList<String>) intent.getStringArrayListExtra("imageList");

        tvnamePlace.setText(namePlace);
        tvaddress.setText(address);
        tvabout.setText(about);

        Picasso.with(this).load(imageList.get(0)).into(tvimage);


    }
}
