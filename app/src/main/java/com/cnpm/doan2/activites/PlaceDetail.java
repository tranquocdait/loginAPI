package com.cnpm.doan2.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.AdapterCommentPlace;
import com.cnpm.doan2.models.CommentPlace;
import com.cnpm.doan2.models.Image;
import com.cnpm.doan2.models.Place;
import com.cnpm.doan2.reponse.StatusCommentPlace;
import com.cnpm.doan2.reponse.StatusTourist;
import com.cnpm.doan2.service.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceDetail extends AppCompatActivity {
    TextView namePlace,address,about;
    ImageView imageView;
    private ListView listView;
    private TextView textViewResult;
    private AdapterCommentPlace adapterCommentPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        TextView tvnamePlace=(TextView) findViewById(R.id.id_namePlace_detail);
        TextView tvaddress=(TextView) findViewById(R.id.id_address_detail);
        TextView tvabout=(TextView) findViewById(R.id.id_aboutPlace_detail);
        ImageView tvimage=(ImageView) findViewById(R.id.id_imagePlace_detail);
        ImageView tvimageback=(ImageView) findViewById(R.id.id_back);

        Intent intent=getIntent();
        String id=intent.getStringExtra("id").toString();
        String namePlace=intent.getStringExtra("namePlace").toString();
        String address=intent.getStringExtra("address").toString();
        String about=intent.getStringExtra("about").toString();
        ArrayList<String> imageList=(ArrayList<String>) intent.getStringArrayListExtra("imageList");

        tvnamePlace.setText(namePlace);
        tvaddress.setText(address);
        tvabout.setText(about);
        Picasso.with(this).load(imageList.get(0)).into(tvimage);

        listView=(ListView)findViewById(R.id.id_comment_place);
        Call<StatusCommentPlace> call = RetrofitClient
                .getInstance().getCommentPlaceApi().getCommentPlace(id);
        call.enqueue(new Callback<StatusCommentPlace>() {
            @Override
            public void onResponse(Call<StatusCommentPlace> call, Response<StatusCommentPlace> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code:" + response.code());
                    return;
                }
                else {
                    StatusCommentPlace statusCommentPlace=response.body();
                    if("success".equals(statusCommentPlace.getStatus())) {
                        final ArrayList<CommentPlace> commentPlaceArrayList = (ArrayList)statusCommentPlace.getData();
                        adapterCommentPlace = new AdapterCommentPlace(PlaceDetail.this, commentPlaceArrayList);
                        listView.setAdapter(adapterCommentPlace);
                    }
                }
            }
            @Override
            public void onFailure(Call<StatusCommentPlace> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        tvimageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(PlaceDetail.this,MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
    }
}
