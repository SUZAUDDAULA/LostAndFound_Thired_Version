package com.opus_bd.lostandfound.Activity.LOGREG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginWithGoogleActivity extends AppCompatActivity  {
//
//    GoogleSignInClient mGoogleSignInClient;
//
//    @BindView(R.id.sign_with_google)
//    SignInButton sign_with_google;
//    int RC_SIGN_IN=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_google);
        ButterKnife.bind(this);

//        sign_with_google.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.sign_in_button:
//                        signIn();
//                        break;
//                }
//            }
//        });
//
//        // Configure sign-in to request the user's ID, email address, and basic
//        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

//    private void  signIn(){
//        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent,RC_SIGN_IN);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//           Intent intent=new Intent(LoginWithGoogleActivity.this, DashboardActivity.class)
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Utilities.showLogcatMessage("signInResult:failed code=" + e.getStatusCode());
//        }
//    }

}