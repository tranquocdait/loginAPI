package com.cnpm.doan2.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cnpm.doan2.R;
import com.cnpm.doan2.models.User;
import com.cnpm.doan2.models.UserLogin;
import com.cnpm.doan2.service.RetrofitClient;
import com.cnpm.doan2.service.UsersService;

import butterknife.BindView;
import butterknife.ButterKnife;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private String baseUrl = "https://travel-now-app.herokuapp.com/";
    private UsersService service;
    private String Au_Token;
    private SharedPreferences sharedPreferences;
//    private MyApplication myApplication;

    @BindView(R.id.input_email)
    EditText _emailText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("dataLogin",MODE_PRIVATE);
        if(!"".equals(sharedPreferences.getString("Au_Token",""))) {
            Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
               intent.putExtra("Au_Token", sharedPreferences.getString("Au_Token",Au_Token));
            startActivity(intent);
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String userName = _emailText.getText().toString().trim();
        String password = _passwordText.getText().toString().trim();

        // TODO: Implement your own authentication logic here.
        Call<User> call = RetrofitClient
                .getInstance().getUserApi().loginUser(new UserLogin(userName,password));
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://travel-now-app.herokuapp.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        UsersService usersService = retrofit.create(UsersService.class);
////        Call<User> call = usersService.loginUser("admin","secret123");
//        Call<User> call = usersService.loginUser(new UserLogin(userName,password));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Post's successful", Toast.LENGTH_LONG).show();
                    return;
                }
                User user=response.body();
                Au_Token = user.getId().toString();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Login failure: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        if (Au_Token != null) {
                            onLoginSuccess();
                        } else
                            onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }


    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Au_Token",Au_Token);
        editor.commit();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
     //   intent.putExtra("Au_Token", Au_Token);
        startActivity(intent);
  //      this.finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }
}
