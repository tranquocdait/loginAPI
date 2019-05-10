package com.cnpm.doan2.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.ImageConverter;
import com.cnpm.doan2.models.Tourist;
import com.cnpm.doan2.reponse.StatusTourist;
import com.cnpm.doan2.service.RetrofitClient;
import com.squareup.picasso.Picasso;

import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    Tourist tourist;
    String status;
    ImageView imageView;
    TextView fullname;
    TextView gender;
    TextView nationality;
    String idAccount;
    private int mMenuId;
    private TextView post;
    private TextView edit;
    private TextView logout;
    private BottomNavigationView navigation;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        imageView = (ImageView) findViewById(R.id.id_avatar_tourist);
        fullname = (TextView) findViewById(R.id.id_fullname);
        gender = (TextView) findViewById(R.id.id_gender);
        nationality = (TextView) findViewById(R.id.id_nationality);

        post = (TextView) findViewById(R.id.id_post_account);
        edit = (TextView) findViewById(R.id.id_edit_account);
        logout = (TextView) findViewById(R.id.id_logout_account);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.back);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("Au_Token");
        idAccount = id;
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
                        if (tourist.getGender() == Boolean.TRUE) gender.setText("Male");
                        else if (tourist.getGender() == Boolean.FALSE) gender.setText("Female");
                        else gender.setText("");
                        if (tourist.getNationality() != null)
                            nationality.setText(tourist.getNationality());
                        else nationality.setText("");
                    }
                } else {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<StatusTourist> call, Throwable t) {
            }
        });


        navigation = findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setCheckable(true);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.getMenu().findItem(R.id.navigation_home).setCheckable(true);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, EditAccount.class);
                intent.putExtra("id", tourist.getId().toString());
                intent.putExtra("fullname", tourist.getFullName());
                startActivity(intent);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, MyPostActivity.class);
                intent.putExtra("id", idAccount);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Au_Token", "");
                editor.putString("Authorization", "");
                editor.commit();
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mMenuId = item.getItemId();
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            MenuItem menuItem = navigation.getMenu().getItem(i);
            boolean isChecked = menuItem.getItemId() == item.getItemId();
            menuItem.setChecked(isChecked);
        }

        switch (item.getItemId()) {
            case R.id.navigation_home:
                // viewPager.setCurrentItem(0);
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_favorite:
                Intent intent1 = new Intent(AccountActivity.this, FollowActivity.class);
                startActivity(intent1);
                break;
            case R.id.navigation_profile:
                //  viewPager.setCurrentItem(4);
                break;
        }
        return true;
    }
}
