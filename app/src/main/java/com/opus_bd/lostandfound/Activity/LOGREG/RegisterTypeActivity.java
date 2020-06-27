package com.opus_bd.lostandfound.Activity.LOGREG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterTypeActivity extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;

    @BindView(R.id.sign_in_button)
    TextView sign_in_button;
    int RC_SIGN_IN=0;
    @SuppressLint("RestrictedApi")
    private CallbackManager callbackManager;
    private LoginButton fbLogin;
    private ImageView profile;
    //private TextView userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_type);
        ButterKnife.bind(this);
        //setTitle();
        ButterKnife.bind(this);

        sign_in_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.profile).setVisibility(View.GONE);
        //findViewById(R.id.userid).setVisibility(View.GONE);

        fbLogin =  findViewById(R.id.login_button);
        //userid =  findViewById(R.id.userid);
        profile =  findViewById(R.id.profile);

        callbackManager = CallbackManager.Factory.create();

        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                 //userid.setText("User ID: " + loginResult.getAccessToken().getUserId());
                //userid.setText("User ID: " + loginResult.getAccessToken().getUserId() + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken());
                String imageUrl = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?return_ssl_resources=1";
                Picasso.get().load(imageUrl).into(profile);

                findViewById(R.id.reg_with_google).setVisibility(View.GONE);
                findViewById(R.id.reg_with_email).setVisibility(View.GONE);
                findViewById(R.id.reg_with_mobile).setVisibility(View.GONE);
                findViewById(R.id.rl_terms_and_condition).setVisibility(View.GONE);

                findViewById(R.id.profile).setVisibility(View.VISIBLE);
                //findViewById(R.id.userid).setVisibility(View.VISIBLE);
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

    private void  signIn(){
        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        // When Login with Facebook
       // callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Intent intent=new Intent(RegisterTypeActivity.this, DashboardActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Utilities.showLogcatMessage("signInResult:failed code=" + e.getStatusCode());
        }
//
//
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