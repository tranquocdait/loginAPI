package com.cnpm.doan2.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.cnpm.doan2.R;
import com.cnpm.doan2.models.Tourist;
import com.cnpm.doan2.reponse.StatusTourist;
import com.cnpm.doan2.service.RetrofitClient;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    Tourist tourist;
    String status;
    ImageView imageView;
    TextView fullname;
    TextView gender;
    TextView nationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        imageView = (ImageView) findViewById(R.id.id_avatar);
        fullname = (TextView) findViewById(R.id.id_fullname);
        gender = (TextView) findViewById(R.id.id_gender);
        nationality = (TextView) findViewById(R.id.id_nationality);

        Intent intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra("Au_Token"));
        Call<StatusTourist> call = RetrofitClient
                .getInstance().getTouristApi().getTourist("47");
        call.enqueue(new Callback<StatusTourist>() {
            @Override
            public void onResponse(Call<StatusTourist> call, Response<StatusTourist> response) {
                status = response.body().getStatus();
                if (status.equals("success")) {
                    tourist = response.body().getData();
                    Picasso.with(getApplicationContext()).load(tourist.getAvatar().getUrl()).into(imageView);
                    fullname.setText(tourist.getFullname());
//                    if (tourist.isGender()) gender.setText("Male");
//                    else gender.setText("Male");
                    gender.setText("Male");
                    nationality.setText("Vietnamese");
                }
            }

            @Override
            public void onFailure(Call<StatusTourist> call, Throwable t) {
            }
        });
    }
}
