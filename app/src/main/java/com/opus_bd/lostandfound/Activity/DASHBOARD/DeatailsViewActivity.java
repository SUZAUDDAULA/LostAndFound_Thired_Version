package com.opus_bd.lostandfound.Activity.DASHBOARD;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.material.card.MaterialCardView;
import com.opus_bd.lostandfound.Adapter.GDInformationAdapter;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.Model.Dashboard.GDTypeStatusModel;
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

public class DeatailsViewActivity extends AppCompatActivity  {
    @BindView(R.id.tvTheft)
    TextView tvTheft;
    @BindView(R.id.tvlost)
    TextView tvlost;
    @BindView(R.id.tvFound)
    TextView tvFound;

    @BindView(R.id.rvGDInfo)
    RecyclerView rvGDInfo;

    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;

    GDInformationAdapter gdInformationAdapter;
    private ArrayList<GDInformation> gdInformationArrayList = new ArrayList<>();
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSION = 0x3;
    //LOCATION eNABLE
    private GoogleApiClient googleApiClient;
    //private Location mylocation;
//
//    @Override
//    protected void onRestart() {
//        gpsanable();
//        super.onRestart();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }

    //LOCATION eNABLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatails_view);
        ButterKnife.bind(this);
        getAllGDInfoTypes();
        intRecyclerView();
        mcvReport.setVisibility(View.GONE);
        //LOCATION eNABLE
        //setUpGClient();
        //LOCATION eNABLE
    }

    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }
    public void intRecyclerView() {
        gdInformationAdapter = new GDInformationAdapter(gdInformationArrayList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvGDInfo.setLayoutManager(layoutManager);
        rvGDInfo.setAdapter(gdInformationAdapter);
    }

    @OnClick({R.id.llTheft, R.id.fabTheft})
    public void fabTheft() {
        getAllGDInformationReport(3);
    }

    @OnClick({R.id.llfablost, R.id.fablost})
    public void llfablost() {
        getAllGDInformationReport(1);
    }

    @OnClick({R.id.llFound, R.id.ivFound})
    public void llFound() {
        getAllGDInformationReport(2);
    }

    public void getAllGDInfoTypes() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<GDTypeStatusModel> listCall = retrofitService.GetCountGDInformationByGDType(SharedPrefManager.BEARER + token, UserName, Constants.GDStatusId);
        listCall.enqueue(new Callback<GDTypeStatusModel>() {
            @Override
            public void onResponse(Call<GDTypeStatusModel> call, Response<GDTypeStatusModel> response) {

                if (response.body() != null) {
                    tvlost.setText(getResources().getText(R.string.to_lose) + " (" + response.body().getLostCnt() + ")");
                    tvTheft.setText(getResources().getText(R.string.theft) + " (" + response.body().getTheftCnt() + ")");
                    tvFound.setText(getResources().getText(R.string.found) + " (" + response.body().getFoundCnt() + ")");
                }
            }

            @Override
            public void onFailure(Call<GDTypeStatusModel> call, Throwable t) {
                Toast.makeText(DeatailsViewActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getAllGDInformationReport(int gdTypeId) {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<GDInformation>> listCall = retrofitService.GetAllGDInformationByFiltering(SharedPrefManager.BEARER + token, UserName, Constants.GDStatusId, gdTypeId);
        listCall.enqueue(new Callback<List<GDInformation>>() {
            @Override
            public void onResponse(Call<List<GDInformation>> call, Response<List<GDInformation>> response) {

                if (response.body() != null) {
                    gdInformationArrayList.clear();
                    gdInformationArrayList.addAll(response.body());
                    gdInformationAdapter.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<GDInformation>> call, Throwable t) {
                Toast.makeText(DeatailsViewActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
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
                            //getMyLocation();
                        }
                    }).setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
    }
//
//    private synchronized void setUpGClient() {
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, 0, DeatailsViewActivity.this)
//                .addConnectionCallbacks(this)
//
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//        googleApiClient.connect();
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        mylocation = location;
//        if (mylocation != null) {
//            /*Intent i = new Intent(HardwareInformationActivity.this, LoginActivity.class);
//            startActivity(i);
//            // close this activity
//            finish();*/
//        } else {
//            Utilities.showLogcatMessage(" No Thanks 2");
//            // showDialog();
//            finish();
//        }
//
//    }

//
//    private void getMyLocation() {
//        if (googleApiClient != null) {
//            if (googleApiClient.isConnected()) {
//                int permissionLocation = ContextCompat.checkSelfPermission(DeatailsViewActivity.this,
//                        Manifest.permission.ACCESS_FINE_LOCATION);
//                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
//                    mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//                    @SuppressLint("RestrictedApi") LocationRequest locationRequest = new LocationRequest();
//                    locationRequest.setInterval(3000);
//                    locationRequest.setFastestInterval(3000);
//                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                            .addLocationRequest(locationRequest);
//                    builder.setAlwaysShow(true);
//                    LocationServices.FusedLocationApi
//                            .requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) DeatailsViewActivity.this);
//                    PendingResult<LocationSettingsResult> result =
//                            LocationServices.SettingsApi
//                                    .checkLocationSettings(googleApiClient, builder.build());
//                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
//
//                        @Override
//                        public void onResult(LocationSettingsResult result) {
//                            final Status status = result.getStatus();
//                            switch (status.getStatusCode()) {
//                                case LocationSettingsStatusCodes.SUCCESS:
//                                    // All location settings are satisfied.
//                                    // You can initialize location requests here.
//                                    int permissionLocation = ContextCompat
//                                            .checkSelfPermission(DeatailsViewActivity.this,
//                                                    Manifest.permission.ACCESS_FINE_LOCATION);
//                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
//                                        mylocation = LocationServices.FusedLocationApi
//                                                .getLastLocation(googleApiClient);
//                                    }
//                                    break;
//                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                                    // Location settings are not satisfied.
//                                    // But could be fixed by showing the user a dialog.
//                                    try {
//                                        // Show the dialog by calling startResolutionForResult(),
//                                        // and check the result in onActivityResult().
//                                        // Ask to turn on GPS automatically
//                                        status.startResolutionForResult(DeatailsViewActivity.this,
//                                                REQUEST_CHECK_SETTINGS_GPS);
//                                    } catch (IntentSender.SendIntentException e) {
//                                        // Ignore the error.
//                                    }
//                                    break;
//                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                                    // Location settings are not satisfied.
//                                    // However, we have no way
//                                    // to fix the
//                                    // settings so we won't show the dialog.
//                                    // finish();
//                                    break;
//                            }
//                        }
//                    });
//                }
//            }
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_GPS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        //updateGPSStatus("GPS is Enabled in your device");
                        //getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // showDialog();
                        finish();
                        break;
                }
                break;
        }
    }

//
//    private void updateGPSStatus(String status) {
//        // gps_status.setText(status);
//    }
//
//
//    private void checkPermissions() {
//        int permissionLocation = ContextCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION);
//        List<String> listPermissionsNeeded = new ArrayList<>();
//        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
//            if (!listPermissionsNeeded.isEmpty()) {
//                ActivityCompat.requestPermissions(this,
//                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSION);
//            }
//        } else {
//            getMyLocation();
//        }
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        int permissionLocation = ContextCompat.checkSelfPermission(DeatailsViewActivity.this,
//                Manifest.permission.ACCESS_FINE_LOCATION);
//        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
//            getMyLocation();
//        }
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
}
