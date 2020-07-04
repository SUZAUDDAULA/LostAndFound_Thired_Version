package com.opus_bd.lostandfound.Activity.LOGREG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
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

    //LoginButton loginButton;
    LoginButton login_button_fb_2;
    TextView fb;

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

        //loginButton = findViewById(R.id.login_button_fb);
        fb = findViewById(R.id.sign_in_button_facebook);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_button_fb_2.performClick();
            }
        });

        login_button_fb_2 = findViewById(R.id.login_button_fb_2);
        login_button_fb_2.setReadPermissions(Arrays.asList("email", "public_profile"));
        //login_button_fb_2.setReadPermissions(Arrays.asList("email", "public_profile"));
        callbackManager = CallbackManager.Factory.create();

        login_button_fb_2.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                getUserProfile(AccessToken.getCurrentAccessToken());

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
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
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
            // When Login with Facebook
            // callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void getUserProfile(AccessToken currentAccessToken) {

        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        Log.d("TAG", object.toString());

                        try {

                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String email = object.getString("email");
                            String id = object.getString("id");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                            String image_url1 = "https://graph.facebook.com/" + id + "/picture?type=large";

                            submitToServerFacebookData(first_name + " "+ last_name,email,id,image_url);

                            /*image_url_public = image_url1;

                            txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
                            txtEmail.setText(email);

                            Picasso.get().load(image_url).into(imageView);*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

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

        Log.i("dfadsfdsf",acct.getDisplayName());

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

    public void submitToServerFacebookData(String personName, String personEmail, String personId, String imagePath ) {

        SharedPrefManager.getInstance(RegisterTypeActivity.this).clearToken();
        SharedPrefManager.getInstance(RegisterTypeActivity.this).clearotp();
        SharedPrefManager.getInstance(RegisterTypeActivity.this).clearUser();
        final RegistrationModel registrationModel = new RegistrationModel();

        /*String personName="";
        String personEmail="";
        String personId="";
        String imagePath="";*/

        /*GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);*/

        /*if (acct != null) {
            personName = acct.getDisplayName();
            personEmail = acct.getEmail();
            personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            imagePath=personPhoto.toString();

            SharedPrefManager.getInstance(RegisterTypeActivity.this).saveProfileName(personName);
            SharedPrefManager.getInstance(RegisterTypeActivity.this).saveImageUrl(imagePath);
            SharedPrefManager.getInstance(RegisterTypeActivity.this).saveLogInWith("facebook");
        }*/

        SharedPrefManager.getInstance(RegisterTypeActivity.this).saveProfileName(personName);
        SharedPrefManager.getInstance(RegisterTypeActivity.this).saveImageUrl(imagePath);
        SharedPrefManager.getInstance(RegisterTypeActivity.this).saveLogInWith("facebook");

        registrationModel.setUserName(personEmail);
        registrationModel.setPassword("123456");
        registrationModel.setConfirmPassword("123456");
        registrationModel.setFullName(personName);
        registrationModel.setUserFrom("facebook");
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
                    Utilities.showLogcatMessage("Exception Reg : " + response.toString());
                    Utilities.showLogcatMessage("Exception Reg : " + response.code());
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