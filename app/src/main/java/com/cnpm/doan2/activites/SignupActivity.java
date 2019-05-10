package com.cnpm.doan2.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cnpm.doan2.R;
import com.cnpm.doan2.config.AdapterCommentPlace;
import com.cnpm.doan2.models.RequestSignUp;
import com.cnpm.doan2.models.User;
import com.cnpm.doan2.models.UserLogin;
import com.cnpm.doan2.reponse.StatusTourist;
import com.cnpm.doan2.service.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @BindView(R.id.id_fullname_creat)
    EditText _fullname;
    @BindView(R.id.id_username_creat)
    EditText _username;
    @BindView(R.id.id_password_creat)
    EditText _passwordText;
    @BindView(R.id.id_reEnterPassword_creat)
    EditText _reEnterPasswordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;
    private BottomNavigationView navigation;
    String Au_tocken;

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // viewPager.setCurrentItem(0);
                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_favorite:
                        //   viewPager.setCurrentItem(3);
                        Intent intent1 = new Intent(SignupActivity.this, FollowActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.navigation_profile:
                        //  viewPager.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        });
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String fullname = _fullname.getText().toString();
        String username = _username.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();
        if (!validate()) {
            onSignupFailed();
            return;
        }
        // TODO: Implement your own signup logic here.
        RequestSignUp requestSignUp=new RequestSignUp(username,password,fullname);
        Call<StatusTourist> call = RetrofitClient
                .getInstance().getTouristApi().postTourist(requestSignUp);
        call.enqueue(new Callback<StatusTourist>() {
            @Override
            public void onResponse(Call<StatusTourist> call, Response<StatusTourist> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getBaseContext(), "Sign Up failed ......", Toast.LENGTH_LONG).show();
                    return;
                }
                if("success".equals(response.body().getStatus())){
                    Toast.makeText(getBaseContext(), "Sign up's success", Toast.LENGTH_LONG).show();
                    Au_tocken=response.body().getStatus();
                }
            }

            @Override
            public void onFailure(Call<StatusTourist> call, Throwable t) {

            }
        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        if (Au_tocken != null)
                            onSignupSuccess();
                        else
                            onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // viewPager.setCurrentItem(0);
                        Intent intentHome= new Intent(SignupActivity.this, FollowActivity.class);
                        startActivity(intentHome);
                        return true;
                    case R.id.navigation_favorite:
                        //   viewPager.setCurrentItem(3);
                        Intent intentFollow = new Intent(SignupActivity.this, FollowActivity.class);
                        startActivity(intentFollow);
                        return true;
                    case R.id.navigation_profile:
                        //  viewPager.setCurrentItem(4);
                        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                        if (!"".equals(sharedPreferences.getString("Au_Token", ""))) {
                            Intent intent = new Intent(SignupActivity.this, AccountActivity.class);
                            intent.putExtra("Au_Token", sharedPreferences.getString("Au_Token", ""));
                            startActivity(intent);
                        } else {
                            Intent intentAccount = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intentAccount);
                        }
                        return true;
                }
                return false;
            }
        });
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(intent);
        //finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Sign Up failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String fullname = _fullname.getText().toString();
        String username = _username.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (fullname.isEmpty() || fullname.length() < 3) {
            _fullname.setError("at least 3 characters");
            valid = false;
        } else {
            _fullname.setError(null);
        }


//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

        if (username.isEmpty() || username.length() < 6) {
            _username.setError("at least 3 characters");
            valid = false;
        } else {
            _username.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }
}