package com.opus_bd.lostandfound.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.opus_bd.lostandfound.Fragments.ForgetPassword.DocumentSubmitFragment;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.MessageEvent;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordActivity extends AppCompatActivity {

    int c = 0;
    @BindView(R.id.fpFregmentContainer)
    FrameLayout fpFregmentContainer;

    @BindView(R.id.ivfp1)
    ImageView ivfp1;
    @BindView(R.id.ivfp2)
    ImageView ivfp2;
    @BindView(R.id.ivfp3)
    ImageView ivfp3;
    @BindView(R.id.ivfp4)
    ImageView ivfp4;

    @BindView(R.id.vfp1)
    View vfp1;
    @BindView(R.id.vfp2)
    View vfp2;
    @BindView(R.id.vfp3)
    View vfp3;
    @BindView(R.id.vfp4)
    View vfp4;

    public static int Step;

    public static String NationalIdentityType, NationalIdentityNo, PhoneNumber, UserName, Email, Password, ConfirmPassword;

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
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);

        switchFragment(new DocumentSubmitFragment());
        ivfp1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
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
        fragmentTransaction.replace(R.id.fpFregmentContainer, fragment);
        fragmentTransaction.commit();
      /*  toolbar.setTitle(title);
        backPressed = false;*/
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.isUpdate()) {
            Utilities.showLogcatMessage("c"+NationalIdentityType);
            if (Step == 1) {
                ivfp1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
            if (Step == 2) {
                ivfp1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                vfp1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                ivfp2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
            if (Step == 3) {
                ivfp1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                vfp1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                ivfp2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                vfp2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                ivfp3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }
            if (Step == 4) {
                ivfp1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                vfp1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                ivfp2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                vfp2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                ivfp3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
                vfp3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                ivfp4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
            }


        }
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
