package com.opus_bd.lostandfound.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.opus_bd.lostandfound.Fragments.Registration.CitizenFragment;
import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserLoginModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationProcessActivity extends AppCompatActivity  {

    int c = 0;
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.iv5)
    ImageView iv5;
    @BindView(R.id.iv6)
    ImageView iv6;
    @BindView(R.id.iv7)
    ImageView iv7;

    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.v3)
    View v3;
    @BindView(R.id.v4)
    View v4;
    @BindView(R.id.v5)
    View v5;
    @BindView(R.id.v6)
    View v6;

    public static int Step;

    public static String citizen, NationalIdentityType, NationalIdentityNo, AddressType, PhoneNumber, UserName, Email, Password, ConfirmPassword;


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_process);
        ButterKnife.bind(this);
        //getAllList();
        switchFragment(new CitizenFragment());
        iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

    }

    @Override
    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }

    public void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.fragment_slide_left_enter,
                R.animator.fragment_slide_right_exit);
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
      /*  toolbar.setTitle(title);
        backPressed = false;*/
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.isUpdate()) {
            Utilities.showLogcatMessage("c"+citizen);
            if (Step == 1) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
            if (Step == 2) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
            if (Step == 3) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }

            if (Step == 4) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
            if (Step == 5) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            v4.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv5.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }

            if (Step == 6) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            v4.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv5.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
          v5.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv6.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }

            if (Step == 7) {
                iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                v3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            v4.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv5.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
          v5.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv6.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
             v6.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                iv7.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
        }
    }

    private void submitToServer() {
//        Utilities.showProgress(this);
        SharedPrefManager.getInstance(RegistrationProcessActivity.this).clearToken();
        final RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setCitizenship(citizen);

        //SharedPrefManager.getInstance(this).saveUserName(etUserName.getText().toString());
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<UserAuthModel> registrationRequest = retrofitService.Register(registrationModel);
        registrationRequest.enqueue(new Callback<UserAuthModel>() {
            @Override
            public void onResponse(Call<UserAuthModel> call, Response<UserAuthModel> response) {
                //              Utilities.hideProgress(LoginActivity.this);
                try {
                    if (response.body() != null) {

                        String auth=response.body().getJwt().replace("{\"auth_token\":\"","");
                        String auth1=auth.replace("\"}","");
                        Utilities.showLogcatMessage(" "+auth1);
                        SharedPrefManager.getInstance(RegistrationProcessActivity.this).saveToken(auth1);
                        Toast.makeText(RegistrationProcessActivity.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationProcessActivity.this, DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(RegistrationProcessActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(RegistrationProcessActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<UserAuthModel> call, Throwable t) {
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(RegistrationProcessActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
