package com.opus_bd.lostandfound.Activity.ENRTY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoundAndRecoveredDetailsActicity extends AppCompatActivity {

    @BindView(R.id.Bus)
    ImageView Bus;

    @BindView(R.id.Car)
    ImageView Car;

    @BindView(R.id.Cng)
    ImageView Cng;

    @BindView(R.id.CoveredVan)
    ImageView CoveredVan;

    @BindView(R.id.Legona)
    ImageView Legona;

    @BindView(R.id.Microbus)
    ImageView Microbus;

    @BindView(R.id.Motorbike)
    ImageView Motorbike;

    @BindView(R.id.Pickup)
    ImageView Pickup;

    @BindView(R.id.Truck)
    ImageView Truck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_and_recovered_details_acticity);
        ButterKnife.bind(this);
    }

/*    @OnClick({R.id.llBus, R.id.llCar, R.id.llCng, R.id.llCoveredVan, R.id.llLegona, R.id.llMicrobus, R.id.llMotorbike, R.id.llPickup, R.id.llTruck})*/
    @OnClick({R.id.Bus, R.id.Car, R.id.Cng, R.id.CoveredVan, R.id.Legona, R.id.Microbus, R.id.Motorbike, R.id.Pickup, R.id.Truck})
    public void llTheft() {
        Constants.ENTRY_TYPE_ID = Constants.THEFT;
        Intent intent = new Intent(FoundAndRecoveredDetailsActicity.this, VehicleEntryActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.ivappLogo)
    public void ivappLogo() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}