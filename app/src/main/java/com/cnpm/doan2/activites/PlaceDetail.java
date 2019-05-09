package com.cnpm.doan2.activites;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.AdapterCommentPlace;
import com.cnpm.doan2.config.ImageConverter;
import com.cnpm.doan2.models.CommentPlace;

import com.cnpm.doan2.models.RatePalce;
import com.cnpm.doan2.models.RequestComment;
import com.cnpm.doan2.reponse.StatusCommentPlace;
import com.cnpm.doan2.reponse.StatusPostCommentPlace;
import com.cnpm.doan2.reponse.StatusRatePlace;
import com.cnpm.doan2.service.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceDetail extends AppCompatActivity {
    TextView namePlace, address, about;
    ImageView imageView;
    private ListView listView;
    private TextView textViewResult;
    private AdapterCommentPlace adapterCommentPlace;
    private SharedPreferences sharedPreferences;
    private String idPlace;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        TextView tvnamePlace = (TextView) findViewById(R.id.id_namePlace_detail);
        TextView tvaddress = (TextView) findViewById(R.id.id_address_detail);
        TextView tvabout = (TextView) findViewById(R.id.id_aboutPlace_detail);
        ImageView tvimage = (ImageView) findViewById(R.id.id_imagePlace_detail);
        ImageView tvimageback = (ImageView) findViewById(R.id.id_back);
        final TextView tvmeniumrate = (TextView) findViewById(R.id.id_rate_medium);
        final TextView tvnumberrate = (TextView) findViewById(R.id.id_number_accout_rate);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.back);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        Intent intent = getIntent();
        idPlace = intent.getStringExtra("id").toString();
        final String namePlace = intent.getStringExtra("namePlace").toString();
        final String address = intent.getStringExtra("address").toString();
        final String about = intent.getStringExtra("about").toString();
        final ArrayList<String> imageList = (ArrayList<String>) intent.getStringArrayListExtra("imageList");

        tvnamePlace.setText(namePlace);
        tvaddress.setText(address);
        tvabout.setText(about);
        Picasso.with(this).load(imageList.get(0)).into(tvimage);

        listView = (ListView) findViewById(R.id.id_comment_place);
        Call<StatusCommentPlace> call = RetrofitClient
                .getInstance().getCommentPlaceApi().getCommentPlace(idPlace);
        call.enqueue(new Callback<StatusCommentPlace>() {
            @Override
            public void onResponse(Call<StatusCommentPlace> call, Response<StatusCommentPlace> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code:" + response.code());
                    return;
                } else {
                    StatusCommentPlace statusCommentPlace = response.body();
                    if ("success".equals(statusCommentPlace.getStatus())) {
                        final ArrayList<CommentPlace> commentPlaceArrayList = (ArrayList) statusCommentPlace.getData();
                        adapterCommentPlace = new AdapterCommentPlace(PlaceDetail.this, commentPlaceArrayList);
                        listView.setAdapter(adapterCommentPlace);
                    }
                }
            }

            @Override
            public void onFailure(Call<StatusCommentPlace> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        Call<StatusRatePlace> statusRatePlaceCall = RetrofitClient
                .getInstance().getRatePlaceApi().getRatePlace(idPlace);
        statusRatePlaceCall.enqueue(new Callback<StatusRatePlace>() {
            @Override
            public void onResponse(Call<StatusRatePlace> call, Response<StatusRatePlace> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code:" + response.code());
                    return;
                } else {
                    StatusRatePlace statusRatePlace = response.body();
                    if ("success".equals(statusRatePlace.getStatus())) {
                        ArrayList<RatePalce> ratePalceArrayList = (ArrayList) statusRatePlace.getData();
                        tvnumberrate.setText("(" + ratePalceArrayList.size() + " reviews)");
                        float menitumrate = 0;
                        for (RatePalce ratePalce : ratePalceArrayList) {
                            menitumrate += ratePalce.getNumberStar();
                        }
                        if (menitumrate == 0) {
                            tvmeniumrate.setText("0");
                        } else {
                            float medium = Math.round(menitumrate / (float) ratePalceArrayList.size() * 10) / 10;
                            tvmeniumrate.setText(Math.round(menitumrate / (float) ratePalceArrayList.size() * 10) / 10 + "");
                            setStar(medium);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<StatusRatePlace> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        tvimageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(PlaceDetail.this,MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        //send comment
        ImageView sendComment = (ImageView) findViewById(R.id.id_send);
        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                String Authorization = sharedPreferences.getString("Authorization", "");
                if ("".equals(Authorization)) isLogin();
                else {

                    EditText tvComment = findViewById(R.id.id_coment_account);
                    String comment = tvComment.getText().toString();
                    Call<StatusPostCommentPlace> call = RetrofitClient
                            .getInstance().getCommentPlaceApi().postCommentPlace(idPlace, new RequestComment(comment), Authorization);
                    call.enqueue(new Callback<StatusPostCommentPlace>() {
                        @Override
                        public void onResponse(Call<StatusPostCommentPlace> call, Response<StatusPostCommentPlace> response) {
                            Intent intent = new Intent(getApplicationContext(), PlaceDetail.class);
                            intent.putExtra("id", idPlace);
                            intent.putExtra("namePlace", namePlace);
                            intent.putExtra("address", address);
                            intent.putExtra("about", about);
                            intent.putExtra("imageList", imageList);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<StatusPostCommentPlace> call, Throwable t) {
                            Toast.makeText(getBaseContext(), "don't comment", Toast.LENGTH_LONG).show();
                        }

                    });
                }
            }
        });

        //Evaluate
        TextView evaluate = (TextView) findViewById(R.id.id_evaluate);
        evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                String Authorization = sharedPreferences.getString("Authorization", "");
                if ("".equals(Authorization)) isLogin();
                else {
                    Toast.makeText(getBaseContext(), "da vao day!", Toast.LENGTH_LONG).show();
                    setEvaluate();
                }
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
                        Intent intentFollow = new Intent(PlaceDetail.this, FollowActivity.class);
                        startActivity(intentFollow);
                        return true;
                    case R.id.navigation_profile:
                        //  viewPager.setCurrentItem(4);
                        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                        if (!"".equals(sharedPreferences.getString("Au_Token", ""))) {
                            Intent intent = new Intent(PlaceDetail.this, AccountActivity.class);
                            intent.putExtra("Au_Token", sharedPreferences.getString("Au_Token", ""));
                            startActivity(intent);
                        } else {
                            Intent intentAccount = new Intent(PlaceDetail.this, LoginActivity.class);
                            startActivity(intentAccount);
                        }
                        return true;
                }
                return false;
            }
        });
    }

    public void setStar(float numberStar) {
        ImageView star1 = (ImageView) findViewById(R.id.star1);
        ImageView star2 = (ImageView) findViewById(R.id.star2);
        ImageView star3 = (ImageView) findViewById(R.id.star3);
        ImageView star4 = (ImageView) findViewById(R.id.star4);
        ImageView star5 = (ImageView) findViewById(R.id.star5);
        if (numberStar < 0.4) {
        }
        if (numberStar >= 0.4 && numberStar <= 0.6) {
            star1.setImageResource(R.drawable.ic_star_half_black_24dp);
        }
        if (numberStar > 0.6 && numberStar < 1.4) {
            star1.setImageResource(R.drawable.ic_star_light_);
        }
        if (numberStar >= 1.4 && numberStar <= 1.6) {
            star1.setImageResource(R.drawable.ic_star_light_);
            star2.setImageResource(R.drawable.ic_star_half_black_24dp);
        }
        if (numberStar > 1.6 && numberStar < 2.4) {
            star1.setImageResource(R.drawable.ic_star_light_);
            star2.setImageResource(R.drawable.ic_star_light_);
        }
        if (numberStar >= 2.4 && numberStar <= 2.6) {
            star1.setImageResource(R.drawable.ic_star_light_);
            star2.setImageResource(R.drawable.ic_star_light_);
            star3.setImageResource(R.drawable.ic_star_half_black_24dp);
        }
        if (numberStar > 2.6 && numberStar < 3.4) {
            star1.setImageResource(R.drawable.ic_star_light_);
            star2.setImageResource(R.drawable.ic_star_light_);
            star3.setImageResource(R.drawable.ic_star_light_);
        }
        if (numberStar >= 3.4 && numberStar <= 3.6) {
            star1.setImageResource(R.drawable.ic_star_light_);
            star2.setImageResource(R.drawable.ic_star_light_);
            star3.setImageResource(R.drawable.ic_star_light_);
            star4.setImageResource(R.drawable.ic_star_half_black_24dp);
        }
        if (numberStar > 3.6 && numberStar < 4.4) {
            star1.setImageResource(R.drawable.ic_star_light_);
            star2.setImageResource(R.drawable.ic_star_light_);
            star3.setImageResource(R.drawable.ic_star_light_);
            star4.setImageResource(R.drawable.ic_star_light_);
        }
        if (numberStar >= 4.4 && numberStar <= 4.6) {
            star1.setImageResource(R.drawable.ic_star_light_);
            star2.setImageResource(R.drawable.ic_star_light_);
            star3.setImageResource(R.drawable.ic_star_light_);
            star4.setImageResource(R.drawable.ic_star_light_);
            star5.setImageResource(R.drawable.ic_star_half_black_24dp);
        }
        if (numberStar > 4.6) {
            star1.setImageResource(R.drawable.ic_star_light_);
            star2.setImageResource(R.drawable.ic_star_light_);
            star3.setImageResource(R.drawable.ic_star_light_);
            star4.setImageResource(R.drawable.ic_star_light_);
            star5.setImageResource(R.drawable.ic_star_light_);

        }
    }

    public void isLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PlaceDetail.this);
        builder.setTitle("You must login");
        builder.setMessage("Do you want to login?");
//        builder.setIcon(R.drawable.dau);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(PlaceDetail.this, LoginActivity.class);
                startActivity(intent);
//                System.exit(1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    public void setEvaluate() {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Evaluate");
        dialog.setContentView(R.layout.dialog_star);
        final ImageView star1 = (ImageView) dialog.findViewById(R.id.rate_star1);
        final ImageView star2 = (ImageView) dialog.findViewById(R.id.rate_star2);
        final ImageView star3 = (ImageView) dialog.findViewById(R.id.rate_star3);
        final ImageView star4 = (ImageView) dialog.findViewById(R.id.rate_star4);
        final ImageView star5 = (ImageView) dialog.findViewById(R.id.rate_star5);
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setImageResource(R.drawable.ic_star_light_);
            }
        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setImageResource(R.drawable.ic_star_light_);
                star2.setImageResource(R.drawable.ic_star_light_);
            }
        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setImageResource(R.drawable.ic_star_light_);
                star2.setImageResource(R.drawable.ic_star_light_);
                star3.setImageResource(R.drawable.ic_star_light_);
            }
        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setImageResource(R.drawable.ic_star_light_);
                star2.setImageResource(R.drawable.ic_star_light_);
                star3.setImageResource(R.drawable.ic_star_light_);
                star4.setImageResource(R.drawable.ic_star_light_);
            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setImageResource(R.drawable.ic_star_light_);
                star2.setImageResource(R.drawable.ic_star_light_);
                star3.setImageResource(R.drawable.ic_star_light_);
                star4.setImageResource(R.drawable.ic_star_light_);
                star5.setImageResource(R.drawable.ic_star_light_);
            }
        });
        dialog.show();
    }
}
