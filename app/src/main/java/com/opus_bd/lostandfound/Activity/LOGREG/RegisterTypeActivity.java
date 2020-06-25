package com.opus_bd.lostandfound.Activity.LOGREG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterTypeActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton fbLogin;
    private ImageView profile;
    private TextView userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_type);
        ButterKnife.bind(this);
        //setTitle();

        findViewById(R.id.profile).setVisibility(View.GONE);
        findViewById(R.id.userid).setVisibility(View.GONE);

        fbLogin =  findViewById(R.id.login_button);
        userid =  findViewById(R.id.userid);
        profile =  findViewById(R.id.profile);

        callbackManager = CallbackManager.Factory.create();

        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                //userid.setText("User ID: " + loginResult.getAccessToken().getUserId());
                userid.setText("User ID: " + loginResult.getAccessToken().getUserId() + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken());
                String imageUrl = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?return_ssl_resources=1";
                Picasso.get().load(imageUrl).into(profile);

                findViewById(R.id.reg_with_google).setVisibility(View.GONE);
                findViewById(R.id.reg_with_email).setVisibility(View.GONE);
                findViewById(R.id.reg_with_mobile).setVisibility(View.GONE);
                findViewById(R.id.rl_terms_and_condition).setVisibility(View.GONE);

                findViewById(R.id.profile).setVisibility(View.VISIBLE);
                findViewById(R.id.userid).setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancel() {
                // App code
                //fbUserId.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                //fbUserId.setText("Login attempt failed.");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode , resultCode , data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.tvMobile,R.id.reg_with_mobile})
    public void tvResigtration() {
        try {
            Intent intent = new Intent(RegisterTypeActivity.this, RegistrationActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Utilities.showLogcatMessage("Exception " + e.toString());
        }
    }

}