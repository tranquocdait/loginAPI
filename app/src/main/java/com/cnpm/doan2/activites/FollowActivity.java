package com.cnpm.doan2.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.AdapterFollow;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);


        listView = (ListView) findViewById(R.id.id_list_follow);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String touristId = sharedPreferences.getString("Au_Token", "");

        touristList = new ArrayList<Tourist>();
        Call<StatusFollow> call = RetrofitClient
                .getInstance().getFollowtApi().getFollow(touristId);
        call.enqueue(new Callback<StatusFollow>() {
            @Override
            public void onResponse(Call<StatusFollow> call, final Response<StatusFollow> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if ("success".equals(response.body().getStatus())) {
                    Toast.makeText(getBaseContext(), "" + response.body().getData().getAllFollowings().length, Toast.LENGTH_LONG).show();
                    for (int i = 0; i < response.body().getData().getAllFollowings().length; i++) {
                        Call<StatusTourist> callTourist = RetrofitClient
                                .getInstance().getTouristApi().getTourist(response.body().getData().getAllFollowings()[i] + "");
                        callTourist.enqueue(new Callback<StatusTourist>() {
                            @Override
                            public void onResponse(Call<StatusTourist> call, Response<StatusTourist> responseTourist) {
                                if (!responseTourist.isSuccessful()) return;
                                if ("success".equals(responseTourist.body().getStatus()))
                                    touristList.add(responseTourist.body().getData());
                            }

                            @Override
                            public void onFailure(Call<StatusTourist> call, Throwable t) {

                            }
                        });
                    }
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

            }
        });
    }
}
