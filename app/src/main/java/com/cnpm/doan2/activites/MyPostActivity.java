package com.cnpm.doan2.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.AdapterPlace;
import com.cnpm.doan2.config.AdapterPost;
import com.cnpm.doan2.config.ImageConverter;
import com.cnpm.doan2.models.Post;
import com.cnpm.doan2.reponse.StatusPost;
import com.cnpm.doan2.reponse.StatusTourist;
import com.cnpm.doan2.service.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostActivity extends AppCompatActivity {
    private ListView listView;
    private AdapterPost adapterPost;
    private SharedPreferences sharedPreferences;
    private String fullname;
    private String urlAvatar;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        listView = (ListView) findViewById(R.id.id_list_post_account);

//        Intent intent =getIntent();
//        String touristId=intent.getStringExtra("fullname").toString();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String touristId=sharedPreferences.getString("Au_Token", "") ;

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.back);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        Call<StatusTourist> calltr = RetrofitClient
                .getInstance().getTouristApi().getTourist(touristId);
        calltr.enqueue(new Callback<StatusTourist>() {
            @Override
            public void onResponse(Call<StatusTourist> call, Response<StatusTourist> response) {
                if(response.isSuccessful()){
                    fullname=response.body().getData().getFullname();
                    urlAvatar=response.body().getData().getAvatar().getUrl();
                }
            }

            @Override
            public void onFailure(Call<StatusTourist> call, Throwable t) {

            }
        });

        Call<StatusPost> call = RetrofitClient
                .getInstance().getPostApi().getListPost(touristId);
        call.enqueue(new Callback<StatusPost>() {
            @Override
            public void onResponse(Call<StatusPost> call, Response<StatusPost> responseee) {
                if (!responseee.isSuccessful()) return;
                if ("success".equals(responseee.body().getStatus())) {
                    final ArrayList<Post> postList = (ArrayList) responseee.body().getData();
                    adapterPost = new AdapterPost(MyPostActivity.this, postList,fullname,urlAvatar);
                    listView.setAdapter(adapterPost);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(MyPostActivity.this,PostDetail.class);
                            intent.putExtra("idPost",postList.get(position).getId().toString());
                            intent.putExtra("fullname",fullname);
                            intent.putExtra("urlAvatar",urlAvatar);
                            intent.putExtra("urlPost",postList.get(position).getImages().get(0).getUrl());
                            intent.putExtra("about",postList.get(position).getContent());
                            intent.putExtra("numberLike",postList.get(position).getLikes().size()+"");
                            startActivity(intent);
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<StatusPost> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Interrupt connection!", Toast.LENGTH_LONG).show();
            }
        });
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // viewPager.setCurrentItem(0);
                        Intent intent = new Intent(MyPostActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_favorite:
                        Intent intent1=new Intent(MyPostActivity.this,FollowActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.navigation_profile:
                        //  viewPager.setCurrentItem(4);
                        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                        if (!"".equals(sharedPreferences.getString("Au_Token", ""))) {
                            Intent intentq = new Intent(MyPostActivity.this, AccountActivity.class);
                            intentq.putExtra("Au_Token", sharedPreferences.getString("Au_Token", ""));
                            startActivity(intentq);
                        } else {
                            Intent intentAccount = new Intent(MyPostActivity.this, LoginActivity.class);
                            startActivity(intentAccount);
                        }
                        return true;
                }
                return false;
            }
        });
    }
}