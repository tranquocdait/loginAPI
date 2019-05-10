package com.cnpm.doan2.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.AdapterFollow;
import com.cnpm.doan2.config.ImageConverter;
import com.cnpm.doan2.models.Follow;
import com.cnpm.doan2.models.Image;
import com.cnpm.doan2.models.Tourist;
import com.cnpm.doan2.reponse.StatusFollow;
import com.cnpm.doan2.reponse.StatusTourist;
import com.cnpm.doan2.service.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowActivity extends AppCompatActivity {
    private ListView listView;
    private AdapterFollow adapterFollow;
    private Toolbar toolbar;
    public String Au_Token = null;
    private BottomNavigationView navigation;
    private SharedPreferences sharedPreferences;
    private ArrayList<Tourist> touristList;
    private EditText etSearch;
    private ImageView btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);


        listView = (ListView) findViewById(R.id.id_list_follow);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String touristId = sharedPreferences.getString("Au_Token", "");


        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.back);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        Call<StatusFollow> call = RetrofitClient
                .getInstance().getFollowtApi().getFollow(touristId);
        call.enqueue(new Callback<StatusFollow>() {
            @Override
            public void onResponse(Call<StatusFollow> call, final Response<StatusFollow> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if ("success".equals(response.body().getStatus())) {
                    touristList=(ArrayList<Tourist>) response.body().getData().getAllFollowings();

                    adapterFollow = new AdapterFollow(FollowActivity.this, touristList);
                    listView.setAdapter(adapterFollow);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(FollowActivity.this, VisitTouristActivity.class);
                            intent.putExtra("idTourist",touristList.get(position).getId().toString());
                            intent.putExtra("fullnameTourist",touristList.get(position).getFullName());
                            intent.putExtra("avatarTourist",touristList.get(position).getAvatar().getUrl());
                            startActivity(intent);
                          //  Toast.makeText(getBaseContext(), ""+touristList.get(position).getId(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<StatusFollow> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Interrupt connection!", Toast.LENGTH_LONG).show();
            }
        });
        btnSearch=(ImageView)findViewById(R.id.id_search_submit);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch=(EditText)findViewById(R.id.id_search);
                String search=etSearch.getText().toString();
                Intent intentSearch=new Intent(FollowActivity.this,SearchActivity.class);
                intentSearch.putExtra("search",search);
                startActivity(intentSearch);
            }
        });
        navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setCheckable(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // viewPager.setCurrentItem(0);
                        Intent intentHome= new Intent(FollowActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        return true;
                    case R.id.navigation_favorite:
                        //   viewPager.setCurrentItem(3);
                        return true;
                    case R.id.navigation_profile:
                        //  viewPager.setCurrentItem(4);
                        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                        if (!"".equals(sharedPreferences.getString("Au_Token", ""))) {
                            Intent intent = new Intent(FollowActivity.this, AccountActivity.class);
                            intent.putExtra("Au_Token", sharedPreferences.getString("Au_Token", ""));
                            startActivity(intent);
                        } else {
                            Intent intentAccount = new Intent(FollowActivity.this, LoginActivity.class);
                            startActivity(intentAccount);
                        }
                        return true;
                }
                return false;
            }
        });
    }
}
