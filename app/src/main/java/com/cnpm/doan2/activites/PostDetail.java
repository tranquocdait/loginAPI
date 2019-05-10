package com.cnpm.doan2.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.AdapterCommentPlace;
import com.cnpm.doan2.config.ImageConverter;
import com.cnpm.doan2.models.CommentPlace;
import com.cnpm.doan2.models.Post;
import com.cnpm.doan2.models.RequestComment;
import com.cnpm.doan2.reponse.StatusCommentPlace;
import com.cnpm.doan2.reponse.StatusPostCommentPlace;
import com.cnpm.doan2.service.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetail extends AppCompatActivity {
    ImageView imAvatar;
    ImageView imPost;
    TextView tvFullname;
    TextView tvPost;
    TextView tvNumberLike;
    EditText tvComment;
    SharedPreferences sharedPreferences;
    ImageView imSend;

    //   ArrayList<CommentPlace> commentPlaceArrayList;

    private ListView listView;
    private BottomNavigationView navigation;
    private AdapterCommentPlace adapterCommentPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        imAvatar = (ImageView) findViewById(R.id.id_avatar_tourist);
        imPost = (ImageView) findViewById(R.id.id_image_post);
        tvFullname = (TextView) findViewById(R.id.id_fullname);
        tvPost = (TextView) findViewById(R.id.id_about_post);
        tvComment = (EditText) findViewById(R.id.id_coment_post);
        tvNumberLike = (TextView) findViewById(R.id.id_number_like);

        imSend = (ImageView) findViewById(R.id.id_send);

        Intent intent = getIntent();
        final String idPost = intent.getStringExtra("idPost").toString();
        final String fullname = intent.getStringExtra("fullname").toString();
        final String urlAvatar = intent.getStringExtra("urlAvatar").toString();
        final String urlPost = intent.getStringExtra("urlPost").toString();
        final String about = intent.getStringExtra("about").toString();
        final String numberLike = intent.getStringExtra("numberLike").toString();
        // final ArrayList<String> imageList = (ArrayList<String>) intent.getStringArrayListExtra("imageList");

        Picasso.with(getApplicationContext()).load(urlAvatar).into(imAvatar);
        Picasso.with(getApplicationContext()).load(urlPost).into(imPost);
        tvFullname.setText(fullname);
       // tvPost.setText(about);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvPost.setText(Html.fromHtml(about, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvPost.setText(Html.fromHtml(about));
        }
        tvNumberLike.setText(numberLike + " likes");


        listView = (ListView) findViewById(R.id.id_comment_post);


        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.back);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        Call<StatusCommentPlace> call = RetrofitClient
                .getInstance().getCommentPostApi().getCommentPost(idPost);
        call.enqueue(new Callback<StatusCommentPlace>() {
            @Override
            public void onResponse(Call<StatusCommentPlace> call, Response<StatusCommentPlace> response) {
                if (!response.isSuccessful()) {
                    return;
                } else {
                    StatusCommentPlace statusCommentPlace = response.body();
                    if ("success".equals(statusCommentPlace.getStatus())) {
                        final ArrayList<CommentPlace> commentPlaceArrayList = (ArrayList) statusCommentPlace.getData();
                        adapterCommentPlace = new AdapterCommentPlace(PostDetail.this, commentPlaceArrayList);
                        listView.setAdapter(adapterCommentPlace);
                    }
                }
            }

            @Override
            public void onFailure(Call<StatusCommentPlace> call, Throwable t) {

            }
        });
        imSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                String Authorization = sharedPreferences.getString("Authorization", "");
                String comment=tvComment.getText().toString();
                Call<StatusPostCommentPlace> call = RetrofitClient
                        .getInstance().getCommentPostApi().postCommentPost(idPost, new RequestComment(comment), Authorization);
                call.enqueue(new Callback<StatusPostCommentPlace>() {
                    @Override
                    public void onResponse(Call<StatusPostCommentPlace> call, Response<StatusPostCommentPlace> response) {
                        if(!response.isSuccessful()) return;
                        Intent intent=new Intent(PostDetail.this,PostDetail.class);
                        intent.putExtra("idPost",idPost);
                        intent.putExtra("fullname",fullname);
                        intent.putExtra("urlAvatar",urlAvatar);
                        intent.putExtra("urlPost",urlPost);
                        intent.putExtra("about",about);
                        intent.putExtra("numberLike",numberLike);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<StatusPostCommentPlace> call, Throwable t) {

                    }
                });
            }
        });
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // viewPager.setCurrentItem(0);

                        Intent intentHome= new Intent(PostDetail.this, FollowActivity.class);
                        startActivity(intentHome);
                        return true;
                    case R.id.navigation_video:
                        //   viewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_favorite:
                        //   viewPager.setCurrentItem(3);
                        Intent intentFollow = new Intent(PostDetail.this, FollowActivity.class);
                        startActivity(intentFollow);
                        return true;
                    case R.id.navigation_profile:
                        //  viewPager.setCurrentItem(4);
                        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                        if (!"".equals(sharedPreferences.getString("Au_Token", ""))) {
                            Intent intent = new Intent(PostDetail.this, AccountActivity.class);
                            intent.putExtra("Au_Token", sharedPreferences.getString("Au_Token", ""));
                            startActivity(intent);
                        } else {
                            Intent intentAccount = new Intent(PostDetail.this, LoginActivity.class);
                            startActivity(intentAccount);
                        }
                        return true;
                }
                return false;
            }
        });
    }
}
