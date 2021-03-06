package com.cnpm.doan2.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.cnpm.doan2.R;

public class EditAccount extends AppCompatActivity {
    private BottomNavigationView navigation;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // viewPager.setCurrentItem(0);
                        Intent intent = new Intent(EditAccount.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_favorite:
                        Intent intent1=new Intent(EditAccount.this,FollowActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.navigation_profile:
                        //  viewPager.setCurrentItem(4);
                        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                        if (!"".equals(sharedPreferences.getString("Au_Token", ""))) {
                            Intent intent2 = new Intent(EditAccount.this, AccountActivity.class);
                            intent2.putExtra("Au_Token", sharedPreferences.getString("Au_Token", ""));
                            startActivity(intent2);
                        } else {
                            Intent intentAccount = new Intent(EditAccount.this, LoginActivity.class);
                            startActivity(intentAccount);
                        }
                        return true;
                }
                return false;
            }
        });
    }
}
