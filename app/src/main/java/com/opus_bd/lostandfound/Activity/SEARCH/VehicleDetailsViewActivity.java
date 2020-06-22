package com.opus_bd.lostandfound.Activity.SEARCH;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.opus_bd.lostandfound.Activity.PoliceInterface.PoliceMainActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleDetailsViewActivity extends AppCompatActivity {
    @BindView(R.id.tvVehicleType)
    TextView tvVehicleType;
    @BindView(R.id.tvMadeBy)
    TextView tvMadeBy;


    @BindView(R.id.tvModel)
    TextView tvModel;
    @BindView(R.id.tvRegNoName)
    TextView tvRegNoName;
    @BindView(R.id.tvEngineNo)
    TextView tvEngineNo;
    @BindView(R.id.tvChesisNo)
    TextView tvChesisNo;
    @BindView(R.id.tvCCNo)
    TextView tvCCNo;
    @BindView(R.id.tvMadeIn)
    TextView tvMadeIn;
    @BindView(R.id.tvColor)
    TextView tvColor;
    @BindView(R.id.tvMadeDate)
    TextView tvMadeDate;
    @BindView(R.id.tvIdentitySign)
    TextView tvIdentitySign;
    @BindView(R.id.tvSPDistrict)
    TextView tvSPDistrict;
    @BindView(R.id.tvSPThana)
    TextView tvSPThana;
    @BindView(R.id.tvAddressDetails)
    TextView tvAddressDetails;
    @BindView(R.id.tvVehicleDate)
    TextView tvVehicleDate;
    @BindView(R.id.tvVehicleTime)
    TextView tvVehicleTime;
    @BindView(R.id.rlApproval)
    RelativeLayout rlApproval;
    public int id, SELECCTED_STATUS_ID = 3;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.spnStatusType)
    Spinner spnStatusType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details_view);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("id");
            tvVehicleType.setText(bundle.getString("vehicleType"));
            tvMadeBy.setText(bundle.getString("vehicleBarnd"));
            /*  tvModel.setText(bundle.getString("vehicleModel"));*/
            tvRegNoName.setText(bundle.getString("vehicleRegNoName"));
            tvEngineNo.setText(bundle.getString("vehicleEngineNo"));
            tvChesisNo.setText(bundle.getString("vehicleChesisNo"));
            tvCCNo.setText(bundle.getString("vehicleCcNo"));
            tvColor.setText(bundle.getString("vehicleColor"));
            tvMadeIn.setText(bundle.getString("MadeIn"));
            tvMadeDate.setText(bundle.getString("tvMadeDate"));
            tvIdentitySign.setText(bundle.getString("tvIdentitySign"));
           /* tvSPDistrict.setText(bundle.getString("tvSPDistrict"));
            tvSPThana.setText(bundle.getString("vehicleBarnd"));*/
            tvAddressDetails.setText(bundle.getString("tvAddressDetails"));
            tvVehicleDate.setText(bundle.getString("tvVehicleDate"));
            tvVehicleTime.setText(bundle.getString("tvVehicleTime"));
        }
        Utilities.showLogcatMessage("IDD=" + id);
        if (Constants.isAdmin == 1) {
            rlApproval.setVisibility(View.VISIBLE);
        } else {
            rlApproval.setVisibility(View.GONE);
        }

        spnStatusType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                if (selectedItem.equals("Cancel") || selectedItem.equals("বাতিল")) {
                    SELECCTED_STATUS_ID = 4;
                } else if (selectedItem.equals("Investigation") || selectedItem.equals("তদন্তাধীন")) {
                    SELECCTED_STATUS_ID = 2;
                } else {
                    SELECCTED_STATUS_ID = 3;
                }
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }

    @OnClick(R.id.btnSubmit)
    public void btnSubmit() {
        submitToServer(id, SELECCTED_STATUS_ID);

    }

    private void submitToServer(int ids, int status) {

        String token = SharedPrefManager.getInstance(this).getToken();

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.UpdateGDInformationStatusById(SharedPrefManager.BEARER + token, ids, status);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Toast.makeText(VehicleDetailsViewActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        //   finish();
                        startActivity(new Intent(VehicleDetailsViewActivity.this, VehicleSearchActivity.class));

                    } else {
                        Toast.makeText(VehicleDetailsViewActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(VehicleDetailsViewActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(VehicleDetailsViewActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

//    private void submitToServer() {
//
//        String token = SharedPrefManager.getInstance(this).getToken();
//        String UserName = SharedPrefManager.getInstance(this).getUser();
//        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
//        Call<String> registrationRequest = retrofitService.UpdateGDInformationStatusById(SharedPrefManager.BEARER + token, id, SELECCTED_STATUS_ID);
//        registrationRequest.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                try {
//                    if (response.body() != null) {
//                        Utilities.showLogcatMessage("responce" + response.body());
//
//                        Toast.makeText(VehicleDetailsViewActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
//
//                        Intent intent = new Intent(VehicleDetailsViewActivity.this, VehicleSearchActivity.class);
//                        startActivity(intent);
//                        finish();
//
//                    } else {
//                        Toast.makeText(VehicleDetailsViewActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//                    Utilities.showLogcatMessage("Exception 2" + e.toString());
//                    Toast.makeText(VehicleDetailsViewActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
//                }
//                //            showProgressBar(false);
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Utilities.showLogcatMessage("Fail to connect " + t.toString());
//
//                Toast.makeText(VehicleDetailsViewActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        if (Constants.isAdmin == 1) {
            Intent intent = new Intent(this, PoliceMainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }

        super.onBackPressed();
    }

}
