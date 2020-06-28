package com.opus_bd.lostandfound.Activity.LOGREG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            submitToServer();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Utilities.showLogcatMessage("signInResult:failed code=" + e.getStatusCode());
        }
//
//
    }


    public void submitToServer() {

        SharedPrefManager.getInstance(RegisterTypeActivity.this).clearToken();
        SharedPrefManager.getInstance(RegisterTypeActivity.this).clearotp();
        SharedPrefManager.getInstance(RegisterTypeActivity.this).clearUser();
        final RegistrationModel registrationModel = new RegistrationModel();

        String personName="";
        String personEmail="";
        String personId="";
        String imagePath="";

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            personName = acct.getDisplayName();
            personEmail = acct.getEmail();
            personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            imagePath=personPhoto.toString();

            SharedPrefManager.getInstance(RegisterTypeActivity.this).saveProfileName(personName);
            SharedPrefManager.getInstance(RegisterTypeActivity.this).saveImageUrl(imagePath);
            SharedPrefManager.getInstance(RegisterTypeActivity.this).saveLogInWith("google");
        }

        registrationModel.setUserName(personEmail);
        registrationModel.setPassword("123456");
        registrationModel.setConfirmPassword("123456");
        registrationModel.setFullName(personName);
        registrationModel.setUserFrom("google");
        registrationModel.setEmail(personEmail);
        registrationModel.setImagePath(imagePath);
        registrationModel.setNationalIdentityType(1);
        registrationModel.setAddressType(1);
        Utilities.showLogcatMessage("RegistrationModel :" + registrationModel.toString());

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<UserAuthModel> registrationRequest = retrofitService.Register(registrationModel);
        registrationRequest.enqueue(new Callback<UserAuthModel>() {
            @Override
            public void onResponse(Call<UserAuthModel> call, Response<UserAuthModel> response) {
                try {
                    Utilities.showLogcatMessage("Exception Reg : " + response.body());
                    //Toast.makeText(RegistrationActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    if (response.body() != null) {

                        Intent intent=new Intent(RegisterTypeActivity.this, DashboardActivity.class);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(RegisterTypeActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(RegisterTypeActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserAuthModel> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                Toast.makeText(RegisterTypeActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
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