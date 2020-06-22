package com.opus_bd.lostandfound.Activity.DASHBOARD;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;
import com.opus_bd.lostandfound.Activity.ENRTY.FoundAndRecoveredDetailsActicity;
import com.opus_bd.lostandfound.Activity.ENRTY.InformationEntryActivity;
import com.opus_bd.lostandfound.Activity.ENRTY.VehicleEntryActivity;
import com.opus_bd.lostandfound.Activity.LOGREG.LoginActivity;
import com.opus_bd.lostandfound.Activity.SEARCH.VehicleSearchActivity;
import com.opus_bd.lostandfound.Adapter.GDInformationAdapter;
import com.opus_bd.lostandfound.Adapter.NewsFeedAdapter;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.Model.Dashboard.StatusTypeViewModel;
import com.opus_bd.lostandfound.Model.GDInfoModel.NewsFeedViewModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivappLogo)
    ImageView ivappLogo;
    @BindView(R.id.mDrawerLayout)
    DrawerLayout mDrawerLayout;
    private MenuItem item;
    GDInformationAdapter gdInformationAdapter;
    NewsFeedAdapter newsFeedAdapter;
    ArrayList<GDInformation> gdInformationArrayList = new ArrayList<>();
    ArrayList<NewsFeedViewModel> newsFeedViewModelArrayList = new ArrayList<>();

    ProgressDialog progress;
    // First row
    @BindView(R.id.tvAlligation)
    TextView tvAlligation;
    @BindView(R.id.tvReject)
    TextView tvReject;
    @BindView(R.id.tvInvestigation)
    TextView tvInvestigation;
    @BindView(R.id.tvFinish)
    TextView tvFinish;
    @BindView(R.id.rvGDInfo)
    RecyclerView rvGDInfo;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSION = 0x3;
    //LOCATION eNABLE
    private GoogleApiClient googleApiClient;
    private Location mylocation;

    @Override
    protected void onRestart() {
        gpsanable();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //LOCATION eNABLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        BottomAppBar bar = (BottomAppBar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.menu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //setProgress();
        getAllGDInfoStatus();
        //getAllGDInfo();
        getAllNewsFeedInfo();
        intRecyclerView();

        //LOCATION eNABLE
        setUpGClient();
        //LOCATION eNABLE
    }
    public void setProgress() {
        progress = new ProgressDialog(this);
        progress.setMessage("Data Loading.... ");
    }

    public void intRecyclerView() {
        newsFeedAdapter = new NewsFeedAdapter(newsFeedViewModelArrayList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        rvGDInfo.setLayoutManager(layoutManager);
        rvGDInfo.setAdapter(newsFeedAdapter);
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
                    startActivity(intent2);
                    finish();

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
        Intent intent = new Intent(DashboardActivity.this, FoundAndRecoveredDetailsActicity.class);
        startActivity(intent);
        finish();
    }

@OnClick({R.id.profile_Name})
    public void profile_Name() {
        Constants.ENTRY_TYPE_ID = Constants.THEFT;
        Intent intent = new Intent(DashboardActivity.this, VehicleEntryActivity.class);
        startActivity(intent);
        finish();
    }


    @OnClick({R.id.fablost, R.id.llfablost})
    public void fablost() {
        Constants.ENTRY_TYPE_ID = Constants.LOST;
        Intent intent = new Intent(DashboardActivity.this, InformationEntryActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.ivFound, R.id.llFound})
    public void ivFound() {
        Constants.ENTRY_TYPE_ID = Constants.FOUND;
        Intent intent = new Intent(DashboardActivity.this, InformationEntryActivity.class);
        startActivity(intent);
        finish();
    }


    // Search
    @OnClick({R.id.fabManSearch, R.id.llManSearch})
    public void ManSearch() {
       /* Intent intent = new Intent(DashboardActivity.this, MenSearchActivity.class);
        startActivity(intent);
        finish();*/
    }

    @OnClick({R.id.fabVehicleSearch, R.id.llVehicleSearch})
    public void VehicleSearch() {
        Constants.isAdmin = 0;
        Intent intent = new Intent(DashboardActivity.this, VehicleSearchActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.fabOthersSearch, R.id.llOthersSearch})
    public void OthersSearch() {
        Intent intent = new Intent(DashboardActivity.this, VehicleSearchActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick({R.id.ivInvestigation, R.id.llInvestigation})
    public void ivInvestigation() {
        Constants.GDStatusId = 2;
        Intent intent = new Intent(DashboardActivity.this, DeatailsViewActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.ivReject, R.id.llReject})
    public void ivReject() {
        Constants.GDStatusId = 4;
        Intent intent = new Intent(DashboardActivity.this, DeatailsViewActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.ivFinish, R.id.llFinish})
    public void ivFinish() {
        Constants.GDStatusId = 3;
        Intent intent = new Intent(DashboardActivity.this, DeatailsViewActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.ivAlligation, R.id.llAlligation})
    public void ivAlligation() {
        Constants.GDStatusId = 1;
        Intent intent = new Intent(DashboardActivity.this, DeatailsViewActivity.class);
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



    public void getAllGDInfoStatus() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<StatusTypeViewModel> listCall = retrofitService.GetCountGDInformationStatus(SharedPrefManager.BEARER + token, UserName);
        listCall.enqueue(new Callback<StatusTypeViewModel>() {
            @Override
            public void onResponse(Call<StatusTypeViewModel> call, Response<StatusTypeViewModel> response) {
                if (response.body() != null) {
                    tvAlligation.setText(getResources().getText(R.string.allegation) + " (" + response.body().getComplain() + ")");
                    tvReject.setText(getResources().getText(R.string.Cancel) + " (" + response.body().getReject() + ")");
                    tvInvestigation.setText(getResources().getText(R.string.investigation) + " (" + response.body().getInvestigation() + ")");
                    tvFinish.setText(getResources().getText(R.string.disposal) + " (" + response.body().getFinish() + ")");
                }
            }

            @Override
            public void onFailure(Call<StatusTypeViewModel> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getAllGDInfo() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();
        Utilities.showLogcatMessage(" token " + token);
        Utilities.showLogcatMessage(" UserName " + UserName);
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<GDInformation>> listCall = retrofitService.GetGDInformationByUser(SharedPrefManager.BEARER
                + token, UserName);
        listCall.enqueue(new Callback<List<GDInformation>>() {
            @Override
            public void onResponse(Call<List<GDInformation>> call, Response<List<GDInformation>> response) {
                Utilities.showLogcatMessage(" r " + response.body());
                if (response.body() != null) {
                    Utilities.showLogcatMessage("gdin" + response.body().size());
                    gdInformationArrayList.clear();
                    gdInformationArrayList.addAll(response.body());
                    gdInformationAdapter.notifyDataSetChanged();



                }
            }

            @Override
            public void onFailure(Call<List<GDInformation>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getAllNewsFeedInfo() {
        //progress.show();
        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();
        Utilities.showLogcatMessage(" token " + token);
        Utilities.showLogcatMessage(" UserName " + UserName);
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<NewsFeedViewModel>> listCall = retrofitService.GetALLNewFeedsInfo(SharedPrefManager.BEARER
                + token, UserName);
        listCall.enqueue(new Callback<List<NewsFeedViewModel>>() {
            @Override
            public void onResponse(Call<List<NewsFeedViewModel>> call, Response<List<NewsFeedViewModel>> response) {
                Utilities.showLogcatMessage(" r " + response.body());
                if (response.body() != null) {
                    //progress.dismiss();
                    Utilities.showLogcatMessage("gdin" + response.body().size());
                    newsFeedViewModelArrayList.clear();
                    newsFeedViewModelArrayList.addAll(response.body());
                    newsFeedAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<NewsFeedViewModel>> call, Throwable t) {
                //progress.dismiss();
                Toast.makeText(DashboardActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    //lOCATION ENABLE


    public void gpsanable() {
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // getMyLocation();
            // notify user
            new AlertDialog.Builder(this)
                    .setMessage(R.string.gps_network_not_enabled)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            // startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            getMyLocation();
                        }
                    }).setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
    }

    private synchronized void setUpGClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0, DashboardActivity.this)
                .addConnectionCallbacks(this)

                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        mylocation = location;
        if (mylocation != null) {
            /*Intent i = new Intent(HardwareInformationActivity.this, LoginActivity.class);
            startActivity(i);
            // close this activity
            finish();*/
        } else {
            Utilities.showLogcatMessage(" No Thanks 2");
            // showDialog();
            finish();
        }

    }


    private void getMyLocation() {
        if (googleApiClient != null) {
            if (googleApiClient.isConnected()) {
                int permissionLocation = ContextCompat.checkSelfPermission(DashboardActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    @SuppressLint("RestrictedApi") LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(3000);
                    locationRequest.setFastestInterval(3000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) DashboardActivity.this);
                    PendingResult<LocationSettingsResult> result =
                            LocationServices.SettingsApi
                                    .checkLocationSettings(googleApiClient, builder.build());
                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

                        @Override
                        public void onResult(LocationSettingsResult result) {
                            final Status status = result.getStatus();
                            switch (status.getStatusCode()) {
                                case LocationSettingsStatusCodes.SUCCESS:
                                    // All location settings are satisfied.
                                    // You can initialize location requests here.
                                    int permissionLocation = ContextCompat
                                            .checkSelfPermission(DashboardActivity.this,
                                                    Manifest.permission.ACCESS_FINE_LOCATION);
                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                                        mylocation = LocationServices.FusedLocationApi
                                                .getLastLocation(googleApiClient);
                                    }
                                    break;
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    // Location settings are not satisfied.
                                    // But could be fixed by showing the user a dialog.
                                    try {
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        // Ask to turn on GPS automatically
                                        status.startResolutionForResult(DashboardActivity.this,
                                                REQUEST_CHECK_SETTINGS_GPS);
                                    } catch (IntentSender.SendIntentException e) {
                                        // Ignore the error.
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    // Location settings are not satisfied.
                                    // However, we have no way
                                    // to fix the
                                    // settings so we won't show the dialog.
                                    // finish();
                                    break;
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_GPS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        updateGPSStatus("GPS is Enabled in your device");
                        getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // showDialog();
                        finish();
                        break;
                }
                break;
        }
    }


    private void updateGPSStatus(String status) {
        // gps_status.setText(status);
    }


    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(DashboardActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSION);
            }
        } else {
            getMyLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(DashboardActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            getMyLocation();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
