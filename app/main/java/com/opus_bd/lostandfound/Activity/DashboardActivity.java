package com.opus_bd.lostandfound.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.opus_bd.lostandfound.R.string.navigation_drawer_close;
import static com.opus_bd.lostandfound.R.string.navigation_drawer_open;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
/*@BindView(R.id.llSeemore1)
    LinearLayout llSeemore1;@BindView(R.id.ll2)
    LinearLayout ll2;*/

    @BindView(R.id.tvWelcome)
    TextView tvWelcome;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivappLogo)
    ImageView ivappLogo;
    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    private MenuItem item;
    ArrayList<GDInformation> gdInformationArrayList = new ArrayList<>();


    // First row
    @BindView(R.id.tvAllegation)
    TextView tvAllegation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        BottomAppBar bar = (BottomAppBar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        setSupportActionBar(toolbar);
        tvWelcome.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tvWelcome.setSelected(true);
        toolbar.inflateMenu(R.menu.menu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getAllGDInfo();
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

    @OnClick({R.id.llTheft, R.id.fabTheft})
    public void llTheft() {
        Constants.ENTRY_TYPE_ID = Constants.THEFT;
        Intent intent = new Intent(DashboardActivity.this, InformationEntryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    @OnClick({R.id.fablost, R.id.llfablost})
    public void fablost() {
        Constants.ENTRY_TYPE_ID = Constants.LOST;
        Intent intent = new Intent(DashboardActivity.this, InformationEntryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
  @OnClick({R.id.ivFound, R.id.llFound})
    public void ivFound() {
        Constants.ENTRY_TYPE_ID = Constants.FOUND;
        Intent intent = new Intent(DashboardActivity.this, InformationEntryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        try {
            displaySelectedScreen(menuItem.getItemId());
        } catch (Exception e) {
            Utilities.showLogcatMessage("Logout " + e.toString());
        }

        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void getAllGDInfo() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<GDInformation>> listCall = retrofitService.GetGDInformationByUser(token, UserName);
        listCall.enqueue(new Callback<List<GDInformation>>() {
            @Override
            public void onResponse(Call<List<GDInformation>> call, Response<List<GDInformation>> response) {

                if (response.body() != null) {
                    tvAllegation.setText(getResources().getText(R.string.allegation)+" ("+response.body().size()+")");

//                    gdInformationArrayList.clear();
//                    gdInformationArrayList.addAll(response.body());
//
//                    for (int i = 0; i < response.body().size(); i++) {
//
//                    }
//
//                    addDocumentTypeNamePresentSpinnerData(response.body());

//                    List<GDInformation> myData = new ArrayList<>();
//                    for (int i = 0; i < response.body().size(); i++) {
//                        myData.add(new GDInformation(
//
//                        ));
//                    }


                }
            }

            @Override
            public void onFailure(Call<List<GDInformation>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
