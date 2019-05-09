package com.cnpm.doan2.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.AdapterFollow;
import com.cnpm.doan2.config.AdapterPost;
import com.cnpm.doan2.config.ImageConverter;
import com.cnpm.doan2.models.Follow;
import com.cnpm.doan2.models.Post;
import com.cnpm.doan2.models.Tourist;
import com.cnpm.doan2.reponse.StatusFollow;
import com.cnpm.doan2.reponse.StatusPost;
import com.cnpm.doan2.service.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private ListView listView;
    private AdapterPost adapterPost;
    private SharedPreferences sharedPreferences;
    private TextView fullname;
    private ImageView avatar;
    private TextView follow;
    private ArrayList<Post> postArrayList;
    private ArrayList<Tourist> touristArrayList;
    private ArrayList<Tourist> touristFollow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_tourist);
        listView = (ListView) findViewById(R.id.id_list_post_account);

        Intent intent = getIntent();

        postArrayList = new ArrayList<Post>();

        final String idTourist = intent.getStringExtra("Au_Token").toString();
        final String fullnameTourist = intent.getStringExtra("fullnameTourist");
        final String urlAvatar = intent.getStringExtra("avatarTourist");

        avatar = (ImageView) findViewById(R.id.id_avatar_tourist);
        Picasso.with(getApplicationContext()).load(urlAvatar).into(avatar);
        fullname = (TextView) findViewById(R.id.id_fullname);
        fullname.setText(fullnameTourist);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.back);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);


        Call<StatusFollow> call = RetrofitClient
                .getInstance().getFollowtApi().getFollow(idTourist);
        call.enqueue(new Callback<StatusFollow>() {
            @Override
            public void onResponse(Call<StatusFollow> call, final Response<StatusFollow> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if ("success".equals(response.body().getStatus())) {
                    touristFollow = (ArrayList<Tourist>) response.body().getData().getAllFollowings();
                    for (int i = 0; i < touristFollow.size(); i++) {
                        Call<StatusPost> callee = RetrofitClient
                                .getInstance().getPostApi().getListPost(touristFollow.get(i).getId().toString());
                        callee.enqueue(new Callback<StatusPost>() {
                            @Override
                            public void onResponse(Call<StatusPost> call, Response<StatusPost> responseee) {
                                if (!responseee.isSuccessful()) return;
                                if ("success".equals(responseee.body().getStatus())) {
                                    //postArrayList.add(responseee.body().getData().get(i));
                                }
                            }

                            @Override
                            public void onFailure(Call<StatusPost> call, Throwable t) {
                                Toast.makeText(getBaseContext(), "Interrupt connection!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

            }

            @Override
            public void onFailure(Call<StatusFollow> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Interrupt connection!", Toast.LENGTH_LONG).show();
            }
        });


    }
}
