package com.cnpm.doan2.activites;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.AdapterPlace;
import com.cnpm.doan2.models.Image;
import com.cnpm.doan2.models.Place;
import com.cnpm.doan2.models.User;
import com.cnpm.doan2.service.RetrofitClient;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private ListView listView;
    private AdapterPlace adapterPlace;
    public String Au_Token = null;
    private BottomNavigationView navigation;
//    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.id_list);
        Call<List<Place>> call = RetrofitClient
                .getInstance().getPlaceApi().getListPlace();
        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code:" + response.code());
                    return;
                }
                final ArrayList<Place> placeList = (ArrayList) response.body();
                adapterPlace = new AdapterPlace(MainActivity.this, placeList);
                listView.setAdapter(adapterPlace);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ArrayList<String> arrayListUrlImage = new ArrayList<String>();
                        for (Image image : placeList.get(position).getImages()) {
                            arrayListUrlImage.add(image.getUrl());
                        }
                        Intent intent = new Intent(MainActivity.this, PlaceDetail.class);
                        intent.putExtra("namePlace", placeList.get(position).getNamePlace());
                        intent.putExtra("address", placeList.get(position).getAddress());
                        intent.putExtra("about", placeList.get(position).getAbout());
                        intent.putExtra("imageList", arrayListUrlImage);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // viewPager.setCurrentItem(0);

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
//                        myApplication = MyApplication.getInstance();
//                        if (myApplication.getIsLogin()) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
//                        }
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        Intent intent = getIntent();
//        if (intent != null) {
//            Au_Token = intent.getStringExtra("Au_Token").toString();
//        }
//        if (Au_Token == null)
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        else getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_exit) {
            alertExit();
            return true;
        } else if (id == R.id.action_login) {
            loginView();
            return true;
        } else if (id == R.id.action_account) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void alertExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Announcement");
        builder.setMessage("Would you exit?");
        builder.setIcon(R.drawable.dauchamhoi);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void loginView() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
