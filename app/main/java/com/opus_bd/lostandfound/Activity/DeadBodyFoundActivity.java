package com.opus_bd.lostandfound.Activity;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.github.dhaval2404.imagepicker.ImagePicker;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import com.opus_bd.lostandfound.Adapter.CustomColorAdapter;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.MDPersonalInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.Religion;
import com.opus_bd.lostandfound.Model.DressInfo.InTheBody;
import com.opus_bd.lostandfound.Model.DressInfo.InTheHead;
import com.opus_bd.lostandfound.Model.DressInfo.InTheLeg;
import com.opus_bd.lostandfound.Model.DressInfo.InTheThroat;
import com.opus_bd.lostandfound.Model.DressInfo.InTheWaist;
import com.opus_bd.lostandfound.Model.DressInfo.MDDressInformationModel;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.Model.PhysicalInfo.BeardType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.BodyChinType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.BodyColor;
import com.opus_bd.lostandfound.Model.PhysicalInfo.EarType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.EyeType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.FaceShapeType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.ForeHeadType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.HairType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.MDPhysicalInformationModel;
import com.opus_bd.lostandfound.Model.PhysicalInfo.MoustacheType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.NeckType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.NoseType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.SpecialBodyCondition;
import com.opus_bd.lostandfound.Model.PhysicalInfo.TeethType;
import com.opus_bd.lostandfound.Model.Vehichel.VehicleMasterModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.KEY_State;
import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.SHARED_PREF_NAME;

public class DeadBodyFoundActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;


    boolean isllPersonInfromationChecked = true;
    @BindView(R.id.llPersonInfromation)
    LinearLayout llPersonInfromation;
    @BindView(R.id.ivTPersonInfromation)
    ImageView ivTPersonInfromation;


    boolean isllPersonPhysicalChecked = true;
    @BindView(R.id.mcvPersonPhysical)
    MaterialCardView mcvPersonPhysical;
    @BindView(R.id.llPersonPhysical)
    LinearLayout llPersonPhysical;
    @BindView(R.id.ivTPersonPhysical)
    ImageView ivTPersonPhysical;


    boolean isllPersonAddressChecked = true;
    @BindView(R.id.mcvPersonAddress)
    MaterialCardView mcvPersonAddress;
    @BindView(R.id.llPersonAddress)
    LinearLayout llPersonAddress;
    @BindView(R.id.ivTPersonAddress)
    ImageView ivTPersonAddress;

    boolean isllDressDescriptionChecked = true;
    @BindView(R.id.mcvDressDescription)
    MaterialCardView mcvDressDescription;
    @BindView(R.id.llDressDescription)
    LinearLayout llDressDescription;
    @BindView(R.id.ivDressDescription)
    ImageView ivDressDescription;

    boolean isllDNAProfileChecked = true;
    @BindView(R.id.mcvDNAProfile)
    MaterialCardView mcvDNAProfile;
    @BindView(R.id.llDNAProfile)
    LinearLayout llDNAProfile;
    @BindView(R.id.ivDNAProfile)
    ImageView ivDNAProfile;

    //input field

    /*    (Person Information)*/
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.etFathersName)
    EditText etFathersName;
    @BindView(R.id.etSpouseName)
    EditText etSpouseName;
    @BindView(R.id.spnDocType)
    Spinner spnDocType;
    @BindView(R.id.etNidNum)
    EditText etNidNum;
    @BindView(R.id.spnNumberType)
    Spinner spnNumberType;
    @BindView(R.id.etNum)
    EditText etNum;
    @BindView(R.id.spnDeadBodyCondition)
    Spinner spnDeadBodyCondition;
    @BindView(R.id.etDateofdeath)
    EditText etDateofdeath;


    @BindView(R.id.spnReligion)
    Spinner spnReligion;

    /* (Location)*/

    @BindView(R.id.spnSPDistrict)
    Spinner spnSPDistrict;
    @BindView(R.id.spnSPThana)
    Spinner spnSPThana;
    @BindView(R.id.etVillage)
    EditText etVillage;
    @BindView(R.id.etAddressDetails)
    EditText etAddressDetails;
    @BindView(R.id.spnAddressType)
    Spinner spnAddressType;
    @BindView(R.id.etOneLineAddress)
    EditText etOneLineAddress;
    @BindView(R.id.etfoundDate)
    EditText etfoundDate;
    @BindView(R.id.etfoundTime)
    EditText etfoundTime;

    /*(Physical Description)*/

    @BindView(R.id.spnEye)
    Spinner spnEye;
    @BindView(R.id.spnNose)
    Spinner spnNose;
    @BindView(R.id.spnHair)
    Spinner spnHair;
    @BindView(R.id.spnforehead)
    Spinner spnForhead;
    @BindView(R.id.spnbeard)
    Spinner spnBeard;
    @BindView(R.id.etWeightkg)
    EditText etWeight;
    @BindView(R.id.spngender)
    Spinner spnGender;
    @BindView(R.id.spnphysical_constitution)
    Spinner spnphysical_constitution;
    @BindView(R.id.spnshape_of_face)
    Spinner spnFaceShape;
    @BindView(R.id.spnchin)
    Spinner spnChin;
    @BindView(R.id.spnskin_color)
    Spinner spnSkinColor;
    @BindView(R.id.spnmustache)
    Spinner spnMustache;
    @BindView(R.id.spnears)
    Spinner spnEar;
    @BindView(R.id.spnneck)
    Spinner spnNeck;
    @BindView(R.id.etFeet)
    EditText etFeet;
    @BindView(R.id.etInches)
    EditText etInches;
    @BindView(R.id.spndescription_of_teeth)
    Spinner spndescription_of_teeth;
    @BindView(R.id.spnspecial_physical_description)
    Spinner spnspecial_physical_description;
    @BindView(R.id.etidentification_mark)
    EditText etIdentityficationMark;

    /*Dress*/

    @BindView(R.id.spnDHead)
    Spinner spnDHead;
    @BindView(R.id.spnDHeadColor)
    Spinner spnDHeadColor;
    @BindView(R.id.spnDEye)
    Spinner spnDEye;
    @BindView(R.id.spnDEyeColor)
    Spinner spnDEyeColor;
    @BindView(R.id.spnDThroat)
    Spinner spnDThroat;
    @BindView(R.id.spnDThroatColor)
    Spinner spnDThroatColor;
    @BindView(R.id.spnDBody)
    Spinner spnDBody;
    @BindView(R.id.spnDBodyColor)
    Spinner spnDBodyColor;
    @BindView(R.id.spnDWaist)
    Spinner spnDWaist;
    @BindView(R.id.spnDWaistColor)
    Spinner spnDWaistColor;
    @BindView(R.id.spnDLeg)
    Spinner spnDLeg;
    @BindView(R.id.spnDLegColor)
    Spinner spnDLegColor;
    //ReportView

    /*    (Person Information)*/
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.tvFathersName)
    TextView tvFathersName;
    @BindView(R.id.tvSpouseName)
    TextView tvSpouseName;
    @BindView(R.id.tvDocType)
    TextView tvDocType;
    @BindView(R.id.tvNidNum)
    TextView tvNidNum;
    @BindView(R.id.tvNumberType)
    TextView tvNumberType;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvDeadBodyCondition)
    TextView tvDeadBodyCondition;
    @BindView(R.id.tvDateofdeath)
    TextView tvDateofdeath;
    @BindView(R.id.tvImageType)
    TextView tvImageType;

    @BindView(R.id.tvReligion)
    TextView tvReligion;
    /* (Location)*/

    @BindView(R.id.tvSPDistrict)
    TextView tvSPDistrict;
    @BindView(R.id.tvSPThana)
    TextView tvSPThana;
    @BindView(R.id.tvVillage)
    TextView tvVillage;
    @BindView(R.id.tvAddressDetails)
    TextView tvAddressDetails;
    @BindView(R.id.tvAddressType)
    TextView tvAddressType;
    @BindView(R.id.tvOneLineAddress)
    TextView tvOneLineAddress;
    @BindView(R.id.tvfoundDate)
    TextView tvfoundDate;
    @BindView(R.id.tvfoundTime)
    TextView tvfoundTime;

    /*(Physical Description)*/

    @BindView(R.id.tvEye)
    TextView tvEye;
    @BindView(R.id.tvNose)
    TextView tvNose;
    @BindView(R.id.tvHair)
    TextView tvHair;
    @BindView(R.id.tvforehead)
    TextView tvForhead;
    @BindView(R.id.tvbeard)
    TextView tvBeard;
    @BindView(R.id.tvWeightkg)
    TextView tvWeightkg;
    @BindView(R.id.tvgender)
    TextView tvGender;
    @BindView(R.id.tvphysical_constitution)
    TextView tvphysical_constitution;
    @BindView(R.id.tvshape_of_face)
    TextView tvFaceShape;
    @BindView(R.id.tvchin)
    TextView tvChin;
    @BindView(R.id.tvskin_color)
    TextView tvSkinColor;
    @BindView(R.id.tvmustache)
    TextView tvMustache;
    @BindView(R.id.tvears)
    TextView tvEar;
    @BindView(R.id.tvneck)
    TextView tvNeck;
    @BindView(R.id.tvFetv)
    TextView tvFeet;
    @BindView(R.id.tvInches)
    TextView tvInches;
    @BindView(R.id.tvdescription_of_tetvh)
    TextView tvdescription_of_teeth;
    @BindView(R.id.tvspecial_physical_description)
    TextView tvspecial_physical_description;
    @BindView(R.id.tvidentification_mark)
    TextView tvIdentityficationMark;
    /*Dress*/


    @BindView(R.id.tvDHead)
    TextView tvDHead;
    @BindView(R.id.tvDHeadColor)
    TextView tvDHeadColor;
    @BindView(R.id.tvDEye)
    TextView tvDEye;
    @BindView(R.id.tvDEyeColor)
    TextView tvDEyeColor;
    @BindView(R.id.tvDThroat)
    TextView tvDThroat;
    @BindView(R.id.tvDThroatColor)
    TextView tvDThroatColor;
    @BindView(R.id.tvDBody)
    TextView tvDBody;
    @BindView(R.id.tvDBodyColor)
    TextView tvDBodyColor;
    @BindView(R.id.tvDWaist)
    TextView tvDWaist;
    @BindView(R.id.tvDWaistColor)
    TextView tvDWaistColor;
    @BindView(R.id.tvDLegSize)
    TextView tvDLegSize;
    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();

    //time picker
    int mHour, mMin, mSec;

    ArrayList<District> districtArrayList = new ArrayList<>();
    ArrayList<Thana> thanaArrayList = new ArrayList<>();

    ArrayList<Colors> colorArrayList = new ArrayList<>();
    public int SELECTED_DISTRICT_ID;
    public int SELECTED_DISTRICT_ID_LP;
    public int SELECTED_THANA_ID;
    public int SELECTED_THANA_ID_LP;
    String selectOne;
    public int SELECTED_EYE_TYPE_ID,SELECTED_NOSE_TYPE_ID,SELECTED_HAIR_TYPE_ID,SELECTED_FOREHEAD_TYPE_ID,SELECTED_BEARD_TYPE_ID,
            SELECTED_PHYSICAL_CONDITION_ID,SELECTED_FACE_SHAPE_ID,SELECTED_BODY_CHIN_ID,SELECTED_BODY_COLOR_ID,SELECTED_MOUSTACE_ID,SELECTED_EAR_ID,
            SELECTED_NECK_ID,SELECTED_TEETH_ID,SELECTED_RELIGION_ID;
    public int SELECTED_INBODY_ID,SELECTED_INHEAD_ID,SELECTED_INEYE_ID,SELECTED_INTHROAT_ID,SELECTED_INWAIST_ID,SELECTED_INLEGS_ID;
//Image

    @BindView(R.id.ivFront)
    ImageView ivFront;   @BindView(R.id.ivBack)
    ImageView ivBack;   @BindView(R.id.ivRight)
    ImageView ivRight;   @BindView(R.id.ivLeft)
    ImageView ivLeft;
    ProgressDialog progress;
    public String Language,english,bangla;

ImageView imageView;

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dead_body_found);
        ButterKnife.bind(this);
        mcvReport.setVisibility(View.GONE);
        llPersonInfromation.setVisibility(View.VISIBLE);
        Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonInfromation);
        mcvPersonPhysical.setVisibility(View.GONE);
        mcvPersonAddress.setVisibility(View.GONE);
        mcvDressDescription.setVisibility(View.GONE);
        mcvDNAProfile.setVisibility(View.GONE);
        //date picker
        initializeVariables();
        selectOne = getResources().getString(R.string.select_option);
        Boolean languageStatus = getSharedPrefValue();
        english=getResources().getString(R.string.english);
        bangla=getResources().getString(R.string.bangla);
        if (languageStatus) {
            Language=english;
        } else {
            Language=bangla;
        }
        setProgress();
        getMDPersonalInfo();
        getMDDressInfo();
        getMDGlobalInfo();
        getMDPhysicalInfo();

        spnDLeg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem!=selectOne){
                    tvDLegSize.setText(selectedItem+" "+ getResources().getString(R.string.in_leg_text));
                }

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
//        getDistrict();
//        getAllColor();
    }

//Date Picker

    private void initializeVariables() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        etDateofdeath.setText(formatter.format(calendar.getTime()));

        etDateofdeath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(mYear, mMonth, mDay, R.style.DatePickerSpinner);
            }
        });
        etfoundDate.setText(formatter.format(calendar.getTime()));

        etfoundDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(mYear, mMonth, mDay, R.style.DatePickerSpinner);
            }
        });


        mHour = calendar.get(Calendar.HOUR);
        mMin = calendar.get(Calendar.MINUTE);
        mSec = calendar.get(Calendar.SECOND);
        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
        etfoundTime.setText(formatter1.format(calendar.getTime()));


    }

    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }

    private boolean getSharedPrefValue() {
        SharedPreferences tprefs = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return tprefs.getBoolean(KEY_State, true);
    }

    public void setProgress() {
        progress = new ProgressDialog(this);
        progress.setMessage("Loading.... ");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
    }

    public void getMDPersonalInfo() {

        progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<MDPersonalInformationModel> mdPersonalInformationModelCall = retrofitService.GetPersonalInformationMasterData();
        mdPersonalInformationModelCall.enqueue(new Callback<MDPersonalInformationModel>() {
            @Override
            public void onResponse(Call<MDPersonalInformationModel> call, Response<MDPersonalInformationModel> response) {

                if (response.body() != null) {
                    progress.dismiss();
                    addReligionSpinnerData(response.body().getReligions());
                } else {
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MDPersonalInformationModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getMDGlobalInfo() {

        progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<VehicleMasterModel> registrationRequest = retrofitService.GetVehicleMasterData();
        registrationRequest.enqueue(new Callback<VehicleMasterModel>() {
            @Override
            public void onResponse(Call<VehicleMasterModel> call, Response<VehicleMasterModel> response) {

                if (response.body() != null) {
                    progress.dismiss();

                    addDistrictSpinnerData(response.body().getDistricts());
                    addDistrictSpinnerDataLP(response.body().getDistricts());
                    addColorSpinnerData(response.body().getColors());

                } else {
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<VehicleMasterModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getMDPhysicalInfo() {
        progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<MDPhysicalInformationModel> mdPersonalInformationModelCall = retrofitService.GetPhysicalInformationMasterData();
        mdPersonalInformationModelCall.enqueue(new Callback<MDPhysicalInformationModel>() {
            @Override
            public void onResponse(Call<MDPhysicalInformationModel> call, Response<MDPhysicalInformationModel> response) {

                if (response.body() != null) {
                    progress.dismiss();
                    addEyeSpinnerData(response.body().getEyeTypes());
                    addNoseSpinnerData(response.body().getNoseTypes());
                    addHairSpinnerData(response.body().getHairTypes());
                    addForeHeadSpinnerData(response.body().getForeHeadTypes());
                    addBeardSpinnerData(response.body().getBeardTypes());
                    addFaceShapeSpinnerData(response.body().getFaceShapeTypes());
                    addBodyChinSpinnerData(response.body().getBodyChinTypes());
                    addBodyColorSpinnerData(response.body().getBodyColors());
                    addMustaceSpinnerData(response.body().getMoustacheTypes());
                    addEarsSpinnerData(response.body().getEarTypes());
                    addNeckSpinnerData(response.body().getNeckTypes());
                    addTeethSpinnerData(response.body().getTeethTypes());
                    addSpecialBodyConditionSpinnerData(response.body().getSpecialBodyConditions());
                } else {
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MDPhysicalInformationModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getMDDressInfo() {
        progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<MDDressInformationModel> mdPersonalInformationModelCall = retrofitService.GetDressInformationMasterData();
        mdPersonalInformationModelCall.enqueue(new Callback<MDDressInformationModel>() {
            @Override
            public void onResponse(Call<MDDressInformationModel> call, Response<MDDressInformationModel> response) {

                if (response.body() != null) {
                    progress.dismiss();
                    addInHeadSpinnerData(response.body().getInTheHeads());
                    addInBodySpinnerData(response.body().getInTheBodies());
                    addInThroadSpinnerData(response.body().getInTheThroats());
                    addInLegsSpinnerData(response.body().getInTheLegs());
                    addInWestSpinnerData(response.body().getInTheWaists());

                } else {
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MDDressInformationModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @OnClick(R.id.ivTPersonInfromation)
    public void ivTPersonInfromation() {
        if (isllPersonInfromationChecked) {
            // show password
            llPersonInfromation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonInfromation);
            isllPersonInfromationChecked = false;
        } else {
            // hide password
            llPersonInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonInfromation);
            isllPersonInfromationChecked = true;
        }

    }


    @OnClick({R.id.ivTPersonAddress, R.id.btnNext1})
    public void ivTPersonAddress() {
        if (isllPersonAddressChecked) {
            // show password
            llPersonInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonInfromation);
            isllPersonInfromationChecked = true;

            mcvPersonAddress.setVisibility(View.VISIBLE);
            llPersonAddress.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonAddress);
            isllPersonAddressChecked = false;

        } else {
            // hide password
            llPersonAddress.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonAddress);
            isllPersonAddressChecked = true;
        }

    }


    @OnClick({R.id.ivTPersonPhysical, R.id.btnNext2})
    public void ivTPersonPhysical() {
        if (isllPersonPhysicalChecked) {
            // show password
            llPersonAddress.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonAddress);
            isllPersonAddressChecked = true;

            mcvPersonPhysical.setVisibility(View.VISIBLE);
            llPersonPhysical.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonPhysical);
            isllPersonPhysicalChecked = false;

        } else {
            // hide password
            llPersonPhysical.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonPhysical);
            isllPersonPhysicalChecked = true;
        }

    }

    @OnClick({R.id.ivDressDescription, R.id.btnNext3})
    public void ivDressDescription() {
        if (isllDressDescriptionChecked) {
            // show password
            llPersonPhysical.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonPhysical);
            isllPersonPhysicalChecked = true;

            mcvDressDescription.setVisibility(View.VISIBLE);
            llDressDescription.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivDressDescription);
            isllDressDescriptionChecked = false;

        } else {
            // hide password
            llDressDescription.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDressDescription);
            isllDressDescriptionChecked = true;
        }

    }

  /*  @OnClick({R.id.ivDNAProfile, R.id.btnNext4})
    public void ivDNAProfile() {
        if (isllDNAProfileChecked) {
            // show password
            llDressDescription.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDressDescription);
            isllDressDescriptionChecked = true;

            mcvDNAProfile.setVisibility(View.VISIBLE);
            llDNAProfile.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivDNAProfile);
            isllDNAProfileChecked = false;

        } else {
            // hide password
            llDNAProfile.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDNAProfile);
            isllDNAProfileChecked = true;
        }

    }*/

    @OnClick(R.id.btnNext4)
    public void ReportShown() {
        llInput.setVisibility(View.GONE);
        mcvReport.setVisibility(View.VISIBLE);
        try {
            tvName.setText(etName.getText().toString());
            tvAge.setText(etAge.getText().toString());
            tvFathersName.setText(etFathersName.getText().toString());
            tvSpouseName.setText(etSpouseName.getText().toString());
            tvDocType.setText(spnDocType.getSelectedItem().toString());
            tvNidNum.setText(etNidNum.getText().toString());
            tvNumberType.setText(spnNumberType.getSelectedItem().toString());
            tvGender.setText(spnGender.getSelectedItem().toString());
            tvNum.setText(etNum.getText().toString());
            tvDeadBodyCondition.setText(spnDeadBodyCondition.getSelectedItem().toString());
            tvDateofdeath.setText(etDateofdeath.getText().toString());
            tvReligion.setText(spnReligion.getSelectedItem().toString());



        } catch (Exception e) {
           /* Utilities.showLogcatMessage(" Expetion 1: " + e.toString());*/
        }

        try {
            /* (Location)*/
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvSPThana.setText(spnSPThana.getSelectedItem().toString());
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvSPThana.setText(spnSPThana.getSelectedItem().toString());
            tvAddressDetails.setText(etAddressDetails.getText().toString());
            tvVillage.setText(etVillage.getText().toString());
            tvAddressType.setText(spnAddressType.getSelectedItem().toString());
            tvOneLineAddress.setText(etOneLineAddress.getText().toString());
            tvfoundDate.setText(etfoundDate.getText().toString());
            tvfoundTime.setText(etfoundTime.getText().toString());

        } catch (Exception e) {
            Utilities.showLogcatMessage(" Expetion 2: " + e.toString());
        }

        try {
            /*(Physical Description)*/
            tvEye.setText(spnEye.getSelectedItem().toString());
            tvNose.setText(spnNose.getSelectedItem().toString());
            tvHair.setText(spnHair.getSelectedItem().toString());
            tvForhead.setText(spnForhead.getSelectedItem().toString());
            tvBeard.setText(spnBeard.getSelectedItem().toString());
            tvWeightkg.setText(etWeight.getText().toString());
            tvGender.setText(spnGender.getSelectedItem().toString());
            tvphysical_constitution.setText(spnphysical_constitution.getSelectedItem().toString());
            tvFaceShape.setText(spnFaceShape.getSelectedItem().toString());
            tvChin.setText(spnChin.getSelectedItem().toString());
            tvSkinColor.setText(spnSkinColor.getSelectedItem().toString());
            tvMustache.setText(spnMustache.getSelectedItem().toString());

            tvEar.setText(spnEar.getSelectedItem().toString());
            tvNeck.setText(spnNeck.getSelectedItem().toString());
            tvFeet.setText(etFeet.getText().toString());
            tvInches.setText(etInches.getText().toString());
            tvdescription_of_teeth.setText(spndescription_of_teeth.getSelectedItem().toString());

            tvspecial_physical_description.setText(spnspecial_physical_description.getSelectedItem().toString());
            tvIdentityficationMark.setText(etIdentityficationMark.getText().toString());


        } catch (Exception e) {
            Utilities.showLogcatMessage(" Expetion 3: " + e.toString());
        }
        try {
            /*(Dress Description)*/
            tvDHead.setText(spnDHead.getSelectedItem().toString());
            tvDHeadColor.setText(spnDHeadColor.getSelectedItem().toString());
            tvDEye.setText(tvDEye.getText().toString());
            tvDEyeColor.setText(spnDEyeColor.getSelectedItem().toString());
            tvDThroat.setText(spnDThroat.getSelectedItem().toString());
            tvDThroatColor.setText(spnDThroatColor.getSelectedItem().toString());
            tvDBody.setText(spnDBody.getSelectedItem().toString());
            tvDBodyColor.setText(spnDBodyColor.getSelectedItem().toString());
            tvDWaist.setText(spnDWaist.getSelectedItem().toString());
            tvDWaistColor.setText(spnDWaistColor.getSelectedItem().toString());


        } catch (Exception e) {
            Utilities.showLogcatMessage(" Expetion 4: " + e.toString());
        }


       /* Intent intent = new Intent(DeadBodyFoundActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @OnClick(R.id.Edit)
    public void Edit() {
        llInput.setVisibility(View.VISIBLE);
        mcvReport.setVisibility(View.GONE);
       /* tvName.setText(etName.getText().toString());
        tvFathersName.setText(etFathersName.getText().toString());
        tvSpouseName.setText(etSpouseName.getText().toString());
        tvNidNum.setText(etNidNum.getText().toString());
        tvReligion.setText(spnReligion.getSelectedItem().toString());*/


       /* Intent intent = new Intent(DeadBodyFoundActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @OnClick(R.id.Submit)
    public void Submit() {
        //submitToServer();
        Intent intent = new Intent(DeadBodyFoundActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void submitToServer() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        final GDInformationModel gdInformationModel = new GDInformationModel();

        //GD Information
        gdInformationModel.setUserName(UserName);
        gdInformationModel.setGdFor(Constants.GDFOR);
        gdInformationModel.setGdDate("2020-04-14");
        gdInformationModel.setIdentityNo("3453453");
        gdInformationModel.setGDTypeId(Constants.ENTRY_TYPE_ID);
        gdInformationModel.setProductTypeId(Constants.PRODUCT_TYPE_ID);

        //gdInformationModel.setDocumentTypeId(SELECTED_DOCUMENT_ID);
        gdInformationModel.setDocumentDescription("");

        //Man Information

      /*  gdInformationModel.setName(tvName.getText().toString());
        gdInformationModel.setFatherName(tvFathersName.getText().toString());
        gdInformationModel.setSpouseName(tvSpouseName.getText().toString());
        gdInformationModel.setManIdentityNo(tvNidNum.getText().toString());
        gdInformationModel.setReligion(tvReligion.getText().toString());
        gdInformationModel.setHeightTo(tvHeight.getText().toString());
        gdInformationModel.setColor(tvColor.getText().toString());
        gdInformationModel.setEye(tvEye.getText().toString());
        gdInformationModel.setHeir(tvHair.getText().toString());*/
      /*  gdInformationModel.setMadeIn(etMadeIn.getText().toString());
        gdInformationModel.setModelDate(etMadeDate.getText().toString());
*/
       /* //Identity Info
        gdInformationModel.setColorsId(SELECTED_COLOR_ID);
        gdInformationModel.setIdentifySign(etIdentitySign.getText().toString());

        //Place And Time
        gdInformationModel.setDivisionId(SELECTED_DIVISION_ID);
        gdInformationModel.setDistrictId(SELECTED_DISTRICT_ID);
        gdInformationModel.setThanaId(SELECTED_THANA_ID);
        gdInformationModel.setPlaceDetails(etAddressDetails.getText().toString());
        gdInformationModel.setPlaceDetails(etAddressDetails.getText().toString());
        gdInformationModel.setLafDate(etVehicleDate.getText().toString());
        gdInformationModel.setLafTime(etVehicleTime.getText().toString());*/


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.SaveGDInformation(token, gdInformationModel);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce");

                        Toast.makeText(DeadBodyFoundActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DeadBodyFoundActivity.this, CodeGenerateActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(DeadBodyFoundActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(DeadBodyFoundActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void addEyeSpinnerData(final List<EyeType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEye.setAdapter(dataAdapter2);
        spnEye.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_EYE_TYPE_ID = body.get(i).getId();
                    } else {
                        SELECTED_EYE_TYPE_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }



    public void addNoseSpinnerData(final List<NoseType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNose.setAdapter(dataAdapter2);
        spnNose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_NOSE_TYPE_ID = body.get(i).getId();
                    } else {
                        SELECTED_NOSE_TYPE_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addHairSpinnerData(final List<HairType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHair.setAdapter(dataAdapter2);
        spnHair.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_HAIR_TYPE_ID = body.get(i).getId();
                    } else {
                        SELECTED_HAIR_TYPE_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addForeHeadSpinnerData(final List<ForeHeadType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnForhead.setAdapter(dataAdapter2);
        spnForhead.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_FOREHEAD_TYPE_ID = body.get(i).getId();
                    } else {
                        SELECTED_FOREHEAD_TYPE_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addBeardSpinnerData(final List<BeardType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBeard.setAdapter(dataAdapter2);
        spnBeard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_BEARD_TYPE_ID = body.get(i).getId();
                    } else {
                        SELECTED_BEARD_TYPE_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addFaceShapeSpinnerData(final List<FaceShapeType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFaceShape.setAdapter(dataAdapter2);
        spnFaceShape.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_FACE_SHAPE_ID = body.get(i).getId();
                    } else {
                        SELECTED_FACE_SHAPE_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addBodyChinSpinnerData(final List<BodyChinType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getChinTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getChinTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnChin.setAdapter(dataAdapter2);
        spnChin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_BODY_CHIN_ID = body.get(i).getId();
                    } else {
                        SELECTED_BODY_CHIN_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addBodyColorSpinnerData(final List<BodyColor> body) {
        List<String> lstData = new ArrayList<>();
        List<String> colorCode = new ArrayList<>();
        lstData.add(0, selectOne);
        colorCode.add(0, "#FFFFFF");
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getColorName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getColorNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSkinColor.setAdapter(dataAdapter2);
        spnSkinColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_BODY_COLOR_ID = body.get(i).getId();
                    } else {
                        SELECTED_BODY_COLOR_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addMustaceSpinnerData(final List<MoustacheType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMustache.setAdapter(dataAdapter2);
        spnMustache.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_MOUSTACE_ID = body.get(i).getId();
                    } else {
                        SELECTED_MOUSTACE_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addEarsSpinnerData(final List<EarType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEar.setAdapter(dataAdapter2);
        spnEar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_EAR_ID = body.get(i).getId();
                    } else {
                        SELECTED_EAR_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addNeckSpinnerData(final List<NeckType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNeck.setAdapter(dataAdapter2);
        spnNeck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_NECK_ID = body.get(i).getId();
                    } else {
                        SELECTED_NECK_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addTeethSpinnerData(final List<TeethType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spndescription_of_teeth.setAdapter(dataAdapter2);
        spndescription_of_teeth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_TEETH_ID = body.get(i).getId();
                    } else {
                        SELECTED_TEETH_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addSpecialBodyConditionSpinnerData(final List<SpecialBodyCondition> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getConditionName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getConditionNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spndescription_of_teeth.setAdapter(dataAdapter2);
        spndescription_of_teeth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_PHYSICAL_CONDITION_ID = body.get(i).getId();
                    } else {
                        SELECTED_PHYSICAL_CONDITION_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addInBodySpinnerData(final List<InTheBody> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDBody.setAdapter(dataAdapter2);
        spnDBody.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_INBODY_ID = body.get(i).getId();
                    } else {
                        SELECTED_INBODY_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addInHeadSpinnerData(final List<InTheHead> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDHead.setAdapter(dataAdapter2);
        spnDHead.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_INHEAD_ID = body.get(i).getId();
                    } else {
                        SELECTED_INHEAD_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addInThroadSpinnerData(final List<InTheThroat> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDThroat.setAdapter(dataAdapter2);
        spnDThroat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_INTHROAT_ID = body.get(i).getId();
                    } else {
                        SELECTED_INTHROAT_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addInWestSpinnerData(final List<InTheWaist> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDWaist.setAdapter(dataAdapter2);
        spnDWaist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_INWAIST_ID = body.get(i).getId();
                    } else {
                        SELECTED_INWAIST_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addInLegsSpinnerData(final List<InTheLeg> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDLeg.setAdapter(dataAdapter2);
        spnDLeg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_INLEGS_ID = body.get(i).getId();
                    } else {
                        SELECTED_INLEGS_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void addColorSpinnerData(final List<Colors> body) {
        List<String> colorList = new ArrayList<>();
        List<String> colorCode = new ArrayList<>();
        colorList.add(0, selectOne);
        colorCode.add(0, "#FFFFFF");
        for (int i = 0; i < body.size(); i++) {
            colorList.add(i + 1, body.get(i).getColorName());
            colorCode.add(i + 1, body.get(i).getColorCode());
        }


        CustomColorAdapter customAdapter = new CustomColorAdapter(getApplicationContext(), colorList, colorCode);
        spnDBodyColor.setAdapter(customAdapter);
        spnDEyeColor.setAdapter(customAdapter);
        spnDHeadColor.setAdapter(customAdapter);
        spnDWaistColor.setAdapter(customAdapter);
        spnDThroatColor.setAdapter(customAdapter);
        spnDLegColor.setAdapter(customAdapter);
        spnDBodyColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    //  SELECTED_COLOR_ID = body.get(i).getId();
                } else {
                    // SELECTED_COLOR_ID = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addReligionSpinnerData(final List<Religion> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getReligionName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getReligionNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnReligion.setAdapter(dataAdapter2);
        spnReligion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_RELIGION_ID = body.get(i).getId();
                    } else {
                        SELECTED_RELIGION_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

//    public void addColorSpinnerData(final List<Colors> body) {
//        List<String> colorList = new ArrayList<>();
//        List<String> colorCode = new ArrayList<>();
//        colorList.add(0, selectOne);
//        colorCode.add(0, "#FFFFFF");
//        for (int i = 0; i < body.size(); i++) {
//            colorList.add(i + 1, body.get(i).getColorName());
//            colorCode.add(i + 1, body.get(i).getColorCode());
//        }
//
//
//        CustomColorAdapter customAdapter = new CustomColorAdapter(getApplicationContext(), colorList, colorCode);
//        etDBodyColor.setAdapter(customAdapter);
//        etDEyeColor.setAdapter(customAdapter);
//        etDHeadColor.setAdapter(customAdapter);
//        etDWaistColor.setAdapter(customAdapter);
//        etDThroatColor.setAdapter(customAdapter);
//        spnDLegColor.setAdapter(customAdapter);
//        etDBodyColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (i >= 1) {
//                    //  SELECTED_COLOR_ID = body.get(i).getId();
//                } else {
//                    // SELECTED_COLOR_ID = 0;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }

    //InformationEntryActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, InformationEntryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }

    //DashBoard

    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }



    public void addDistrictSpinnerData(final List<District> body) {
        List<String> districtList = new ArrayList<>();
        districtList.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                districtList.add(i + 1, body.get(i).getDistrictName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                districtList.add(i + 1, body.get(i).getDistrictNameBn());
            }
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, districtList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPDistrict.setAdapter(dataAdapter2);
        spnSPDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_DISTRICT_ID = body.get(i).getId();
                        getAllThana(body.get(i).getId());
                    } else {
                        SELECTED_DISTRICT_ID = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getAllThana(int id) {

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<Thana>> thana = retrofitService.GetThanaByDistrictId(id);
        thana.enqueue(new Callback<List<Thana>>() {
            @Override
            public void onResponse(Call<List<Thana>> call, Response<List<Thana>> response) {

                if (response.body() != null) {

                    thanaArrayList.clear();
                    thanaArrayList.addAll(response.body());

                    addThanaSpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Thana>> call, Throwable t) {
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void addThanaSpinnerData(final List<Thana> body) {
        List<String> thanaList = new ArrayList<>();
        thanaList.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                thanaList.add(i + 1, body.get(i).getThanaName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                thanaList.add(i + 1, body.get(i).getThanaNameBn());
            }
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, thanaList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPThana.setAdapter(dataAdapter2);
        spnSPThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_THANA_ID = body.get(i).getId();
                } else {
                    SELECTED_THANA_ID = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addDistrictSpinnerDataLP(final List<District> body) {
        List<String> districtList = new ArrayList<>();
        districtList.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                districtList.add(i + 1, body.get(i).getDistrictName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                districtList.add(i + 1, body.get(i).getDistrictNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, districtList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPDistrict.setAdapter(dataAdapter2);
        spnSPDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_DISTRICT_ID_LP = body.get(i).getId();
                        getAllThanaLP(body.get(i).getId());
                    } else {
                        SELECTED_DISTRICT_ID_LP = 0;
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage(" " + e.toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getAllThanaLP(int id) {

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<Thana>> thana = retrofitService.GetThanaByDistrictId(id);
        thana.enqueue(new Callback<List<Thana>>() {
            @Override
            public void onResponse(Call<List<Thana>> call, Response<List<Thana>> response) {

                if (response.body() != null) {

                    thanaArrayList.clear();
                    thanaArrayList.addAll(response.body());

                    addThanaSpinnerDataLP(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Thana>> call, Throwable t) {
                Toast.makeText(DeadBodyFoundActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addThanaSpinnerDataLP(final List<Thana> body) {
        List<String> thanaList = new ArrayList<>();
        thanaList.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                thanaList.add(i + 1, body.get(i).getThanaName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                thanaList.add(i + 1, body.get(i).getThanaNameBn());
            }
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, thanaList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSPThana.setAdapter(dataAdapter2);
        spnSPThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_THANA_ID_LP = body.get(i).getId();
                } else {
                    SELECTED_THANA_ID_LP = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    //TIME AND dATE
    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback(DeadBodyFoundActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .showTitle(true)
                .build()
                .show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        etfoundDate.setText(formatter.format(calendar.getTime()));
        etDateofdeath.setText(formatter.format(calendar.getTime()));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @OnClick(R.id.etfoundTime)
    public void etfoundTime() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        int second = mcurrentTime.get(Calendar.SECOND);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String status = "AM";

                if (hour > 11) {
                    // If the hour is greater than or equal to 12
                    // Then the current AM PM status is PM
                    status = "PM";
                }

                // Initialize a new variable to hold 12 hour format hour value
                int hour_of_12_hour_format;

                if (hour > 11) {

                    // If the hour is greater than or equal to 12
                    // Then we subtract 12 from the hour to make it 12 hour format time
                    hour_of_12_hour_format = hour - 12;
                } else {
                    hour_of_12_hour_format = hour;
                }
                etfoundTime.setText(selectedHour + ":" + selectedMinute + " " + status);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    // Image set

    @OnClick(R.id.ivFront)
    public void ivFront(){
       setImageView(ivFront);
        ImagePicker();
    }  @OnClick(R.id.ivBack)
    public void ivBack(){
       setImageView(ivBack);
        ImagePicker();
    }  @OnClick(R.id.ivRight)
    public void ivRight(){
       setImageView(ivRight);
        ImagePicker();
    }  @OnClick(R.id.ivLeft)
    public void ivLeft(){
       setImageView(ivLeft);
        ImagePicker();
    }

    //ImagePicker

    public void ImagePicker(){
        com.github.dhaval2404.imagepicker.ImagePicker.Companion.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .cameraOnly()
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {

            super.onActivityResult(requestCode, resultCode, data);
        }
        catch (Exception e)
        {
            Utilities.showLogcatMessage("onActivityResult "+e.toString());
        }
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
           imageView.setImageURI(fileUri);

            //You can get File object from intent
            File file = ImagePicker.Companion.getFile(data);

            //You can also get File Path from intent
            String filePath = ImagePicker.Companion.getFilePath(data);


           /* mArrayUri.add(fileUri);
            galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
            gvGallery.setAdapter(galleryAdapter);*/
           /* gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                    .getLayoutParams();
            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);*/
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
