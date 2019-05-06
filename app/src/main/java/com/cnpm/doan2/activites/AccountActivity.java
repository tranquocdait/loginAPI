package com.cnpm.doan2.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.cnpm.doan2.R;
import com.cnpm.doan2.models.Tourist;
import com.cnpm.doan2.reponse.StatusTourist;
import com.cnpm.doan2.service.RetrofitClient;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.widget.ListView;
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

    private TextView post;
    private TextView edit;
    private TextView logout;
    private BottomNavigationView navigation;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        imageView = (ImageView) findViewById(R.id.id_avatar);
        fullname = (TextView) findViewById(R.id.id_fullname);
        gender = (TextView) findViewById(R.id.id_gender);
        nationality = (TextView) findViewById(R.id.id_nationality);

        post = (TextView) findViewById(R.id.id_post_account);
        edit = (TextView) findViewById(R.id.id_edit_account);
        logout = (TextView) findViewById(R.id.id_logout_account);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("Au_Token");
        Call<StatusTourist> call = RetrofitClient
                .getInstance().getTouristApi().getTourist(id);
        call.enqueue(new Callback<StatusTourist>() {
            @Override
            public void onResponse(Call<StatusTourist> call, Response<StatusTourist> response) {
                if (response.isSuccessful()) {
                    status = response.body().getStatus();
                    if (status.equals("success")) {
                        tourist = response.body().getData();
                        Picasso.with(getApplicationContext()).load(tourist.getAvatar().getUrl()).into(imageView);
                        fullname.setText(tourist.getFullname());
//                    if (tourist.isGender()) gender.setText("Male");
//                    else gender.setText("Male");
                        gender.setText(id);
                        nationality.setText("Vietnamese");
                    }
                }
                else{
                    finish();
                }
            }

            @Override
            public void onFailure(Call<StatusTourist> call, Throwable t) {
            }
        });


        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // viewPager.setCurrentItem(0);
                        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_category:
                        // viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_video:
                        //   viewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_favorite:
                        //   viewPager.setCurrentItem(3);
                        return true;
                    case R.id.navigation_profile:
                        //  viewPager.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, EditAccount.class);
                intent.putExtra("id", tourist.getId());
                intent.putExtra("fullname", tourist.getFullName());
                // intent.putExtra("fullname",tourist.getFullName());
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Au_Token","");
                editor.commit();
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
