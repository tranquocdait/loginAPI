package com.cnpm.doan2.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cnpm.doan2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @BindView(R.id.id_fullname_creat)
    EditText _fullname;
    @BindView(R.id.id_gerden_creat)
    EditText _gerden;
    @BindView(R.id.id_nationality_creat)
    EditText _nationality;
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
        String address = _gerden.getText().toString();
        String nationality = _nationality.getText().toString();
        String username = _username.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String fullname = _fullname.getText().toString();
        String gender = _gerden.getText().toString();
        String nationality = _nationality.getText().toString();
        String username = _username.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (fullname.isEmpty() || fullname.length() < 3) {
            _fullname.setError("at least 3 characters");
            valid = false;
        } else {
            _fullname.setError(null);
        }

        if (gender.isEmpty()) {
            _gerden.setError("Enter Valid Address");
            valid = false;
        } else {
            _gerden.setError(null);
        }


//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        if (nationality.isEmpty()) {
            _nationality.setError("enter a valid email address");
            valid = false;
        } else {
            _nationality.setError(null);
        }

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