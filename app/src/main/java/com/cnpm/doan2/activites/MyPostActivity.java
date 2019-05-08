package com.cnpm.doan2.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.AdapterPlace;
import com.cnpm.doan2.config.AdapterPost;
import com.cnpm.doan2.models.Post;
import com.cnpm.doan2.reponse.StatusPost;
import com.cnpm.doan2.service.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostActivity extends AppCompatActivity {
    private ListView listView;
    private AdapterPost adapterPost;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        listView = (ListView) findViewById(R.id.id_list_post_account);

//        Intent intent =getIntent();
//        String touristId=intent.getStringExtra("id").toString();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String touristId=sharedPreferences.getString("Au_Token", "") ;
        Call<StatusPost> call = RetrofitClient
                .getInstance().getPosttApi().getListPost(touristId);
        call.enqueue(new Callback<StatusPost>() {
            @Override
            public void onResponse(Call<StatusPost> call, Response<StatusPost> response) {
                if (!response.isSuccessful()) return;
                if ("success".equals(response.body().getStatus())) {
                    final ArrayList<Post> postList = (ArrayList) response.body().getData();
                    adapterPost = new AdapterPost(MyPostActivity.this, postList);
                    listView.setAdapter(adapterPost);
                }
            }

            @Override
            public void onFailure(Call<StatusPost> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Interrupt connection!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
