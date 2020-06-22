package com.opus_bd.lostandfound.Activity.PoliceInterface;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;
import com.opus_bd.lostandfound.Activity.DashboardActivity;
import com.opus_bd.lostandfound.Activity.InformationEntryActivity;
import com.opus_bd.lostandfound.Activity.LoginActivity;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PoliceMainActivity extends AppCompatActivity {

    @BindView(R.id.tvWelcome)
    TextView tvWelcome;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    private MenuItem item;

    @BindView(R.id.btnSorothal)
    Button btnSorothal;

    @BindView(R.id.lostfoundreport)
    TextView lostfoundreport;

    @BindView(R.id.tvsearchwithphoto)
    TextView tvsearchwithphoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_main2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSorothal})
    public void btnSorothal() {
        Intent intent = new Intent(PoliceMainActivity.this, SorothalReportActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick({R.id.lostfoundreport})
    public void lostfoundreport() {
        Intent intent = new Intent(PoliceMainActivity.this, ThiefLoastRescueActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick({R.id.tvsearchwithphoto})
    public void tvsearchwithphoto() {
        Intent intent = new Intent(PoliceMainActivity.this, IdentifyWithPhotoes.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.ivappLogo)
    public void ivappLogo() {
        mDrawerLayout.openDrawer(GravityCompat.END);
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

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
            mDrawerLayout.closeDrawer(GravityCompat.END);
        } else {
        }
    }

    private void displaySelectedScreen(int itemId) {

        switch (itemId) {
            case R.id.ihelp:

                break;
            case R.id.iNotification:
                break;
            case R.id.ilogout: {
                try {
                    SharedPrefManager.getInstance(this).clearToken();
                    Toast.makeText(this, "Logged out successfully!!", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(this, LoginActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent2);

                } catch (Exception e) {
                    Utilities.showLogcatMessage("Logout " + e.toString());
                }
            }
            break;


        }
        mDrawerLayout.closeDrawer(GravityCompat.END);
    }

}
