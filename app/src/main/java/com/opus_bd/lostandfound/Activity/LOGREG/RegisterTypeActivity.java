package com.opus_bd.lostandfound.Activity.LOGREG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.opus_bd.lostandfound.Activity.OtherItem.SocialLoginFacebook;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Utilities;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_type);
        ButterKnife.bind(this);
        //setTitle();
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

    @OnClick({R.id.tvFacebook,R.id.reg_with_facebook})
    public void facebookRegistration() {
        try {
            Intent intent = new Intent(RegisterTypeActivity.this, SocialLoginFacebook.class);
            startActivity(intent);
        } catch (Exception e) {
            Utilities.showLogcatMessage("Exception " + e.toString());
        }
    }

}