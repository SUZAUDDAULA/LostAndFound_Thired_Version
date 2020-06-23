package com.opus_bd.lostandfound.Activity.ENRTY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.opus_bd.lostandfound.Activity.DASHBOARD.ItemWiseNewsFeedActivity;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoundAndRecoveredDetailsActicity extends AppCompatActivity implements View.OnClickListener {

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

    @BindView(R.id.tvCategoryTitle)
    TextView tvCategoryTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_and_recovered_details_acticity);
        ButterKnife.bind(this);
        Bus.setOnClickListener(this);
        Car.setOnClickListener(this);
        Cng.setOnClickListener(this);
        CoveredVan.setOnClickListener(this);
        Legona.setOnClickListener(this);
        Microbus.setOnClickListener(this);
        Motorbike.setOnClickListener(this);
        Pickup.setOnClickListener(this);
        Truck.setOnClickListener(this);
        tvCategoryTitle.setText(Constants.ENTRY_TYPE_Name);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()) {
            case R.id.Bus:
                Constants.VEHICLE_TYPE_ID = 1;
                Constants.VEHICLE_TYPE_NAME = "Bus";
                Intent intent = new Intent(FoundAndRecoveredDetailsActicity.this, ItemWiseNewsFeedActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Car:
                Constants.VEHICLE_TYPE_ID = 7;
                Constants.VEHICLE_TYPE_NAME = "Car";
                Intent intent1 = new Intent(FoundAndRecoveredDetailsActicity.this, ItemWiseNewsFeedActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.Cng:
                Constants.VEHICLE_TYPE_ID = 11;
                Constants.VEHICLE_TYPE_NAME = "CNG";
                Intent intent2 = new Intent(FoundAndRecoveredDetailsActicity.this, ItemWiseNewsFeedActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.CoveredVan:
                Constants.VEHICLE_TYPE_ID = 13;
                Constants.VEHICLE_TYPE_NAME = "Covered Van";
                Intent intent3 = new Intent(FoundAndRecoveredDetailsActicity.this, ItemWiseNewsFeedActivity.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.Legona:
                Constants.VEHICLE_TYPE_ID = 14;
                Constants.VEHICLE_TYPE_NAME = "Legona";
                Intent intent4 = new Intent(FoundAndRecoveredDetailsActicity.this, ItemWiseNewsFeedActivity.class);
                startActivity(intent4);
                finish();
                break;
            case R.id.Microbus:
                Constants.VEHICLE_TYPE_ID = 12;
                Constants.VEHICLE_TYPE_NAME = "Microbus";
                Intent intent5 = new Intent(FoundAndRecoveredDetailsActicity.this, ItemWiseNewsFeedActivity.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.Motorbike:
                Constants.VEHICLE_TYPE_ID = 5;
                Constants.VEHICLE_TYPE_NAME = "Motorbike";
                Intent intent6 = new Intent(FoundAndRecoveredDetailsActicity.this, ItemWiseNewsFeedActivity.class);
                startActivity(intent6);
                finish();
                break;
            case R.id.Pickup:
                Constants.VEHICLE_TYPE_ID = 8;
                Constants.VEHICLE_TYPE_NAME = "Pickup Van";
                Intent intent7 = new Intent(FoundAndRecoveredDetailsActicity.this, ItemWiseNewsFeedActivity.class);
                startActivity(intent7);
                finish();
                break;
            case R.id.Truck:
                Constants.VEHICLE_TYPE_ID = 4;
                Constants.VEHICLE_TYPE_NAME = "Truck";
                Intent intent8 = new Intent(FoundAndRecoveredDetailsActicity.this, ItemWiseNewsFeedActivity.class);
                startActivity(intent8);
                finish();
                break;
        }


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