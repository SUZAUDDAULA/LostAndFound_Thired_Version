package com.opus_bd.lostandfound.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InformationEntryActivity extends AppCompatActivity {
    //Stepper
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;

    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.v3)
    View v3;


    //Aplicant
    @BindView(R.id.cvApplicant)
    CardView cvApplicant;

    @BindView(R.id.llVOwn)
    LinearLayout llVOwn;

    @BindView(R.id.llVOthers)
    LinearLayout llVOthers;

    @BindView(R.id.LLInputForOthers)
    LinearLayout LLInputForOthers;

    //Items
    @BindView(R.id.cvItems)
    CardView cvItems;
    @BindView(R.id.fabOwn)
    ImageView fabOwn;
    @BindView(R.id.fabVihecal)
    ImageView fabVihecal;

    @BindView(R.id.fabOther)
    ImageView fabOther;
    @BindView(R.id.fabMan)
    ImageView fabMan;    @BindView(R.id.tvMan)
    TextView tvMan;
    @BindView(R.id.llVMan)
    LinearLayout llVMan;  @BindView(R.id.tvDocType)
    TextView tvDocType;@BindView(R.id.spnDocumentType)
    Spinner spnDocumentType;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_entry);
        ButterKnife.bind(this);
        cvItems.setVisibility(View.GONE);
        LLInputForOthers.setVisibility(View.GONE);
        iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        if (Constants.ENTRY_TYPE_ID != Constants.THEFT) {
            llVMan.setVisibility(View.VISIBLE);
        }

        if (Constants.ENTRY_TYPE_ID == Constants.FOUND) {

            tvMan.setText(getResources().getText(R.string.unknown_dead_body));
            Glide.with(this).load(R.drawable.ic_deadbody).into(fabMan);
        }
    }

    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }
    @OnClick(R.id.tvDocType)
    public void tvDocType() {
       tvDocType.setVisibility(View.GONE);
       spnDocumentType.setVisibility(View.VISIBLE);


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVOwn, R.id.fabOwn})
    public void LlVOwn() {
        Constants.GDFOR = Constants.OWN;
        cvApplicant.setVisibility(View.GONE);
        cvItems.setVisibility(View.VISIBLE);
        iv4.setVisibility(View.GONE);
        v3.setVisibility(View.GONE);
        iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVOthers, R.id.fabOther})
    public void llVOthers() {
        Constants.GDFOR = Constants.OTHERS;
        cvApplicant.setVisibility(View.GONE);
        LLInputForOthers.setVisibility(View.VISIBLE);
        iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.btnOthersInput)
    public void btnOthersInput() {
        LLInputForOthers.setVisibility(View.GONE);
        cvItems.setVisibility(View.VISIBLE);
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVVihecal, R.id.fabVihecal})
    public void llVVihecal() {
        Constants.PRODUCT_TYPE_ID = Constants.VEHICLE;
        iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        Intent intent = new Intent(InformationEntryActivity.this, VehicleEntryActivity.class);
        startActivity(intent);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVMan, R.id.fabMan})
    public void llVMan() {
        Constants.PRODUCT_TYPE_ID = Constants.MAN;
        iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
       if(Constants.ENTRY_TYPE_ID==Constants.LOST){
           Intent intent = new Intent(InformationEntryActivity.this, UnknownManActivity.class);
           startActivity(intent);
       }
       else {
           Intent intent = new Intent(InformationEntryActivity.this, DeadBodyFoundActivity.class);
           startActivity(intent);
       }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVVivid, R.id.fabVivid})
    public void llVVivid() {
        Constants.PRODUCT_TYPE_ID = Constants.DOCUMENT;
        iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        Intent intent = new Intent(InformationEntryActivity.this, OthersItemEntryActivity.class);

        startActivity(intent);

    }


    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(InformationEntryActivity.this, DashboardActivity.class);

        startActivity(intent);

    }
}
