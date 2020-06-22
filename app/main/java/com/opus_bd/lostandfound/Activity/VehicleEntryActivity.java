package com.opus_bd.lostandfound.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.card.MaterialCardView;
import com.hbb20.CountryCodePicker;
import com.opus_bd.lostandfound.Adapter.CustomAdapter;
import com.opus_bd.lostandfound.Adapter.CustomColorAdapter;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleType;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Division;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.Model.Vehichel.MetropolitanArea;
import com.opus_bd.lostandfound.Model.Vehichel.RegistrationLevel;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

public class VehicleEntryActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    // Layout
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;

    // Information View
    @BindView(R.id.mcvVehicleInformation)
    MaterialCardView mcvVehicleInformation;
    @BindView(R.id.mcvVehicleIdendityInformation)
    MaterialCardView mcvVehicleIdendityInformation;
    @BindView(R.id.mcvVehicleAttachment)
    MaterialCardView mcvVehicleAttachment;
    @BindView(R.id.mcvVehiclePlaceTimeInformation)
    MaterialCardView mcvVehiclePlaceTimeInformation;
    private static final int PICK_FILE_REQUEST = 1;
    private String selectedFilePath;
    ProgressDialog dialog;
    //Edit Text
    @BindView(R.id.etModel)
    EditText etModel;

    @BindView(R.id.etRegNoName)
    EditText etRegNoName;

    @BindView(R.id.etEngineNo)
    EditText etEngineNo;

    @BindView(R.id.etChesisNo)
    EditText etChesisNo;

    @BindView(R.id.etCCNo)
    EditText etCCNo;


    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.etMadeDate)
    EditText etMadeDate;

    @BindView(R.id.etIdentitySign)
    EditText etIdentitySign;

    @BindView(R.id.etAddressDetails)
    EditText etAddressDetails;

    @BindView(R.id.etVehicleDate)
    EditText etVehicleDate;

    @BindView(R.id.etVehicleTime)
    EditText etVehicleTime;
    @BindView(R.id.btnAddPhotoes)
    Button btnAddPhotoes;


    boolean isllVehicleEntryChecked = true;
    @BindView(R.id.llVehicleInfromation)
    LinearLayout llVehicleInfromation;

    @BindView(R.id.ivVehicleInformation)
    ImageView ivVehicleInformation;

    boolean isllVehicleIdentificationChecked = true;
    @BindView(R.id.llVIdentityInfo)
    LinearLayout llVIdentityInfo;

    @BindView(R.id.ivVIdentityInfo)
    ImageView ivVIdentityInfo;

    boolean isllVPATChecked = true;
    @BindView(R.id.llVPATInfo)
    LinearLayout llVPATInfo;

    @BindView(R.id.ivVPATInfo)
    ImageView ivVPATInfo;

    boolean isllVehicleAttachment = true;
    @BindView(R.id.llVehicleAttachment)
    LinearLayout llVehicleAttachment;

    @BindView(R.id.ivVehicleAttachment)
    ImageView ivVehicleAttachment;

    //Spinner

    @BindView(R.id.spnSPDivision)
    Spinner spnSPDivision;

    @BindView(R.id.spnSPDistrict)
    Spinner spnSPDistrict;

    @BindView(R.id.spnSPThana)
    Spinner spnSPThana;

    @BindView(R.id.spnDocumentType)
    Spinner spnDocumentType;

    @BindView(R.id.spnVehicleType)
    Spinner spnVehicleType;

    @BindView(R.id.spnMadeBy)
    Spinner spnMadeBy;

    @BindView(R.id.spnRegNoName1)
    Spinner spnRegNoName1;

    @BindView(R.id.spnRegNoName2)
    Spinner spnRegNoName2;

    @BindView(R.id.spnColor)
    Spinner spnColor;


    @BindView(R.id.etBlueBook)
    TextView etBlueBook;

    String[] metropoliton, regipartTwo;
    char[] engNoArray, chesisNoArray;
    //Address List
    ArrayList<Division> divisionArrayList = new ArrayList<>();
    ArrayList<District> districtArrayList = new ArrayList<>();
    ArrayList<Thana> thanaArrayList = new ArrayList<>();

    ArrayList<DocumentType> documentTypeArrayList = new ArrayList<>();
    ArrayList<VehicleType> vehicleTypeArrayList = new ArrayList<>();
    ArrayList<VehicleModel> VehicleModelArrayList = new ArrayList<>();
    ArrayList<Color> colorArrayList = new ArrayList<>();
    ArrayList<MetropolitanArea> metroAreaModelArrayList = new ArrayList<>();
    ArrayList<RegistrationLevel> registrationLevelModels = new ArrayList<>();
    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    public int SELECTED_DOCUMENT_ID;
    public int SELECTED_VEHICLETYPE_ID;
    public String SELECTED_VEHICLEMODEL_Name;
    public String SELECTED_REGNO_1;
    public String SELECTED_REGNO_2;
    public int SELECTED_COLOR_ID;
    public int SELECTED_DIVISION_ID;
    public int SELECTED_DISTRICT_ID;
    public int SELECTED_THANA_ID;

    ProgressDialog progress;
    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay,backYear;
    Calendar calendar = Calendar.getInstance();

    //time picker
    int mHour, mMin, mSec;

//Report view textView

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

    @BindView(R.id.tvMadeDate)
    TextView tvMadeDate;

    @BindView(R.id.tvIdentitySign)
    TextView tvIdentitySign;

    @BindView(R.id.tvAddressDetails)
    TextView tvAddressDetails;

    @BindView(R.id.tvVehicleDate)
    TextView tvVehicleDate;

    @BindView(R.id.tvVehicleTime)
    TextView tvVehicleTime;

//    @BindView(R.id.tvSPDivision)
//    TextView tvSPDivision;

    @BindView(R.id.tvSPDistrict)
    TextView tvSPDistrict;

    @BindView(R.id.tvSPThana)
    TextView tvSPThana;

    @BindView(R.id.tvPhoto)
    TextView tvPhoto;


    @BindView(R.id.tvVehicleType)
    TextView tvVehicleType;

    @BindView(R.id.tvMadeBy)
    TextView tvMadeBy;
    @BindView(R.id.tvColor)
    TextView tvColor;
    @BindView(R.id.tvBlueBook)
    TextView tvBlueBook;
    @BindView(R.id.tvVSATInfo)
    TextView tvVSATInfo;
    @BindView(R.id.reportPlaceTv)
    TextView reportPlaceTv;


//Multiple Image add

    private Button btn;
    int PICK_IMAGE_MULTIPLE = 27;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery, gvGallery1;
    private GalleryAdapter galleryAdapter;
    public String Language,english,bangla;
    String selectOne, engNoString, chesisNoString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_entry);
        ButterKnife.bind(this);
        Boolean languageStatus = getSharedPrefValue();
        english=getResources().getString(R.string.english);
        bangla=getResources().getString(R.string.bangla);
        if (languageStatus) {
            Language=english;
        } else {
            Language=bangla;
        }
        ccp.setDefaultCountryUsingNameCode("JP");
        ccp.resetToDefaultCountry();
        setProgress();
        mcvVehicleInformation.setVisibility(View.VISIBLE);
        mcvVehicleIdendityInformation.setVisibility(View.GONE);
        mcvVehicleAttachment.setVisibility(View.GONE);
        mcvVehiclePlaceTimeInformation.setVisibility(View.GONE);
        mcvReport.setVisibility(View.GONE);
        if (Constants.ENTRY_TYPE_ID == Constants.LOST) {
            tvVSATInfo.setText(getResources().getText(R.string.lostplaceAndTimeInfo1));
            reportPlaceTv.setText(getResources().getText(R.string.lostplaceAndTimeInfo1));
        }
        selectOne = getResources().getString(R.string.select_option);

        //Spinner
        getAllDocument();
        getVehicleMasterData();

       /* getAllVehicleType();
        getAllColor();*/
        //getDivision();
       /* GetAllMetropolitanArea();
        getDistrict();
        GetAllRegistrationLevel();*/

        //date picker
        initializeVariables();
        etRegNoName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // if user is typing string one character at a time
                if (count == 1) {
                    // auto insert dashes while user typing their ssn
                    if (start == 1 || start == 7) {
                        etRegNoName.setText(etRegNoName.getText() + "-");
                        etRegNoName.setSelection(etRegNoName.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etEngineNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (etEngineNo.getText().length() == 3 || etEngineNo.getText().length() == 6 || etEngineNo.getText().length() == 9 || etEngineNo.getText().length() == 12 || etEngineNo.getText().length() == 15 || etEngineNo.getText().length() == 18 || etEngineNo.getText().length() == 21 || etEngineNo.getText().length() == 24 || etEngineNo.getText().length() == 27) {
                    engNoString = etEngineNo.getText().toString().toUpperCase() + "-";
                    char c = engNoString.charAt(engNoString.length() - 2);

                    if (c != '-') {
                        engNoArray = engNoString.toCharArray();
                        engNoArray[engNoString.length() - 2] = engNoArray[engNoString.length() - 1];
                        engNoArray[engNoString.length() - 1] = c;

                        //code to convert charArray back to String..
                        engNoString = new String(engNoArray);
                        etEngineNo.setText(engNoString);
                        etEngineNo.setSelection(engNoString.length());
                        engNoString = null;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etChesisNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (etChesisNo.getText().length() == 3 || etChesisNo.getText().length() == 6 || etChesisNo.getText().length() == 9 || etChesisNo.getText().length() == 12 || etChesisNo.getText().length() == 15 || etChesisNo.getText().length() == 18 || etChesisNo.getText().length() == 21 || etChesisNo.getText().length() == 24 || etChesisNo.getText().length() == 27) {
                    chesisNoString = etChesisNo.getText().toString().toUpperCase() + "-";
                    char c = chesisNoString.charAt(chesisNoString.length() - 2);

                    if (c != '-') {
                        chesisNoArray = chesisNoString.toCharArray();
                        chesisNoArray[chesisNoString.length() - 2] = chesisNoArray[chesisNoString.length() - 1];
                        chesisNoArray[chesisNoString.length() - 1] = c;

                        //code to convert charArray back to String..
                        chesisNoString = new String(chesisNoArray);
                        etChesisNo.setText(chesisNoString);
                        etChesisNo.setSelection(chesisNoString.length());
                        chesisNoString = null;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//Multiple image

        gvGallery = (GridView) findViewById(R.id.gv);
        gvGallery1 = (GridView) findViewById(R.id.gv1);

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

    //Multiple image
    @OnClick(R.id.btnAddPhotoes)
    public void ImageAdd() {
        ImagePicker();
    }


    //ImagePicker

    public void ImagePicker() {
        ImagePicker.Companion.with(this)
                .crop()//Crop image(Optional), Check Customization for more option
                .galleryOnly()
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {

            super.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            Utilities.showLogcatMessage("onActivityResult " + e.toString());
        }
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
            // ivImage.setImageURI(fileUri);

            //You can get File object from intent
            File file = ImagePicker.Companion.getFile(data);

            //You can also get File Path from intent
            String filePath = ImagePicker.Companion.getFilePath(data);


            mArrayUri.add(fileUri);
            galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
            gvGallery.setAdapter(galleryAdapter);
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

    @OnClick(R.id.ivVehicleInformation)
    public void ivVehicleInformation() {
        if (isllVehicleEntryChecked) {
            // show password
            llVehicleInfromation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivVehicleInformation);
            isllVehicleEntryChecked = false;
        } else {
            // hide password
            llVehicleInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVehicleInformation);
            isllVehicleEntryChecked = true;
        }

    }

    @OnClick({R.id.ivVIdentityInfo, R.id.btnNext1})
    public void ivVIdentityInfo() {

        if (isllVehicleIdentificationChecked) {
            // show password
            llVehicleInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVehicleInformation);
            isllVehicleEntryChecked = true;

            mcvVehicleIdendityInformation.setVisibility(View.VISIBLE);
            llVIdentityInfo.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivVIdentityInfo);
            isllVehicleIdentificationChecked = false;

        } else {
            // hide password
            llVIdentityInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVIdentityInfo);
            isllVehicleIdentificationChecked = true;
        }
    }

    @OnClick({R.id.ivVehicleAttachment, R.id.btnNext2})
    public void ivVehicleAttachment() {
        if (isllVehicleAttachment) {
            // show password
            llVIdentityInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVIdentityInfo);
            isllVehicleIdentificationChecked = true;

            mcvVehicleAttachment.setVisibility(View.VISIBLE);
            llVehicleAttachment.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivVehicleAttachment);
            isllVehicleAttachment = false;

        } else {
            // hide password
            llVehicleAttachment.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVehicleAttachment);
            isllVehicleAttachment = true;
        }

    }


    @OnClick({R.id.ivVPATInfo, R.id.btnNext3})
    public void ivVPATInfo() {
        if (isllVPATChecked) {
            // show password
            llVehicleAttachment.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVehicleAttachment);
            isllVehicleAttachment = true;

            mcvVehiclePlaceTimeInformation.setVisibility(View.VISIBLE);
            llVPATInfo.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivVPATInfo);
            isllVPATChecked = false;

        } else {
            // hide password
            llVPATInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivVPATInfo);
            isllVPATChecked = true;
        }

    }


    @OnClick(R.id.btnNext4)
    public void btnNext4() {
        llInput.setVisibility(View.GONE);
        mcvReport.setVisibility(View.VISIBLE);
        try {
            tvVehicleType.setText(spnVehicleType.getSelectedItem().toString());
            tvVehicleDate.setText(etVehicleDate.getText().toString());
            tvModel.setText(etModel.getText().toString());
            //tvMadeBy.setText(spnMadeBy.getSelectedItem().toString());
            tvMadeIn.setText(ccp.getSelectedCountryName());
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvAddressDetails.setText(etAddressDetails.getText().toString());
            tvRegNoName.setText(spnRegNoName1.getSelectedItem().toString() + " " + spnRegNoName2.getSelectedItem().toString()
                    + " " + etRegNoName.getText().toString());
            tvEngineNo.setText(etEngineNo.getText().toString());
            tvChesisNo.setText(etChesisNo.getText().toString());
            tvCCNo.setText(etCCNo.getText().toString());
            tvMadeIn.setText(ccp.getSelectedCountryName());
            tvMadeDate.setText(etMadeDate.getText().toString());
            tvColor.setText(spnColor.getSelectedItem().toString());
            tvIdentitySign.setText(etIdentitySign.getText().toString());
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvSPThana.setText(spnSPThana.getSelectedItem().toString());
            tvAddressDetails.setText(etAddressDetails.getText().toString());
            tvVehicleDate.setText(etVehicleDate.getText().toString());
            tvVehicleTime.setText(etVehicleTime.getText().toString());
            //ivrPhoto.setImageResource(ivVehicleAttachment.getSourceLayoutResId());
            Log.i("reportinfo", "engineno: " + etEngineNo.getText().toString() + ",chesisno: " + etChesisNo.getText().toString() + ",date: " + etMadeDate.getText().toString() + "");
        } catch (Exception e) {
            Log.e("reportinfo", "btnNext4: ", e);
        }

    }

    @OnClick(R.id.Edit)
    public void Edit() {
        llInput.setVisibility(View.VISIBLE);
        mcvReport.setVisibility(View.GONE);
    }

    @OnClick(R.id.Submit)
    public void Submit() {
        //submitToServer();
        Intent intent = new Intent(VehicleEntryActivity.this, CodeGenerateActivity.class);
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
        gdInformationModel.setGdDate(etVehicleDate.getText().toString());
        gdInformationModel.setIdentityNo("3453453");
        gdInformationModel.setGDTypeId(Constants.ENTRY_TYPE_ID);
        gdInformationModel.setProductTypeId(Constants.PRODUCT_TYPE_ID);

        gdInformationModel.setDocumentTypeId(SELECTED_DOCUMENT_ID);
        gdInformationModel.setDocumentDescription("");

        //Vehicle Information

        gdInformationModel.setVehicleTypeId(SELECTED_VEHICLETYPE_ID);
        gdInformationModel.setMadeBy(SELECTED_VEHICLEMODEL_Name);
        gdInformationModel.setModelNo(etModel.getText().toString());
        gdInformationModel.setRegNoFirstPart(SELECTED_REGNO_1);
        gdInformationModel.setRegNoSecondPart(SELECTED_REGNO_1);
        gdInformationModel.setRegNoThiredPart(etRegNoName.getText().toString());
        gdInformationModel.setEngineNo(etEngineNo.getText().toString());
        gdInformationModel.setChasisNo(etChesisNo.getText().toString());
        gdInformationModel.setCcNo(etCCNo.getText().toString());
        gdInformationModel.setMadeIn(ccp.getSelectedCountryName());
        gdInformationModel.setModelDate(etMadeDate.getText().toString());

        //Identity Info
        gdInformationModel.setColorsId(SELECTED_COLOR_ID);
        gdInformationModel.setIdentifySign(etIdentitySign.getText().toString());

        //Place And Time
        gdInformationModel.setDivisionId(SELECTED_DIVISION_ID);
        gdInformationModel.setDistrictId(SELECTED_DISTRICT_ID);
        gdInformationModel.setThanaId(SELECTED_THANA_ID);
        gdInformationModel.setPlaceDetails(etAddressDetails.getText().toString());
        gdInformationModel.setPlaceDetails(etAddressDetails.getText().toString());
        gdInformationModel.setLafDate(etVehicleDate.getText().toString());
        gdInformationModel.setLafTime(etVehicleTime.getText().toString());


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.SaveGDInformation(token, gdInformationModel);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce");

                        Toast.makeText(VehicleEntryActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VehicleEntryActivity.this, CodeGenerateActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(VehicleEntryActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(VehicleEntryActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());

                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getVehicleMasterData() {

        progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<VehicleMasterModel> registrationRequest = retrofitService.GetVehicleMasterData();
        registrationRequest.enqueue(new Callback<VehicleMasterModel>() {
            @Override
            public void onResponse(Call<VehicleMasterModel> call, Response<VehicleMasterModel> response) {

                if (response.body() != null) {
                    progress.dismiss();
                    addVehicleTypeNamePresentSpinnerData(response.body().getVehicleTypes());
                    addVehicleMadyBySpinnerData(response.body().getVehicleModels());
                    addDistrictSpinnerData(response.body().getDistricts());
                    addColorSpinnerData(response.body().getColors());
                    addMetroAreaSpinnerData(response.body().getMetropolitanAreas());
                    addRegistrationLevelModelSpinnerData(response.body().getRegistrationLevels());
                    addThanaSpinnerData(response.body().getThanas());

                } else {
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<VehicleMasterModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getAllDocument() {


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<DocumentType>> registrationRequest = retrofitService.GetAllDocumentType();
        registrationRequest.enqueue(new Callback<List<DocumentType>>() {
            @Override
            public void onResponse(Call<List<DocumentType>> call, Response<List<DocumentType>> response) {

                if (response.body() != null) {

                    documentTypeArrayList.clear();
                    documentTypeArrayList.addAll(response.body());

                    for (int i = 0; i < response.body().size(); i++) {

                    }

                    addDocumentTypeNamePresentSpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DocumentType>> call, Throwable t) {
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void addDocumentTypeNamePresentSpinnerData(final List<DocumentType> body) {
        List<String> documentList = new ArrayList<>();
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                documentList.add( body.get(i).getDocumentTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                documentList.add(body.get(i).getDocumentTypeNameBn());
            }
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, documentList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDocumentType.setAdapter(dataAdapter2);
        spnDocumentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_DOCUMENT_ID = body.get(i).getId();

                } else {
                    SELECTED_DOCUMENT_ID = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addVehicleTypeNamePresentSpinnerData(final List<VehicleType> body) {
        List<String> vehicleList = new ArrayList<>();
        vehicleList.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                vehicleList.add(i + 1, body.get(i).getVehicleTypeName());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                vehicleList.add(i + 1, body.get(i).getVehicleTypeNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, vehicleList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnVehicleType.setAdapter(dataAdapter2);
        spnVehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_VEHICLETYPE_ID = body.get(i).getId();
                    //  getAllVehicleModel(body.get(i-1).getId());
                } else {
                    SELECTED_VEHICLETYPE_ID = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addVehicleMadyBySpinnerData(final List<VehicleModel> body) {
        List<String> vehicleMadyBy = new ArrayList<>();
        List<String> vehicleIcon = new ArrayList<>();
        vehicleMadyBy.add(0, selectOne);
        vehicleIcon.add(0, "");
        for (int i = 0; i < body.size(); i++) {
            vehicleMadyBy.add(i + 1, body.get(i).getModelName());
            vehicleIcon.add(i + 1, body.get(i).getImagePath());
        }
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), vehicleIcon, vehicleMadyBy);
        spnMadeBy.setAdapter(customAdapter);
        spnMadeBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_VEHICLEMODEL_Name = body.get(i).getModelName();
                } else {
                    SELECTED_VEHICLEMODEL_Name = "";
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                colorList.add(i + 1, body.get(i).getColorName());
                colorCode.add(i + 1, body.get(i).getColorCode());
            }
        }else {
            for (int i = 0; i < body.size(); i++) {
                colorList.add(i + 1, body.get(i).getColorNameBn());
                colorCode.add(i + 1, body.get(i).getColorCode());
            }
        }



        CustomColorAdapter customAdapter = new CustomColorAdapter(getApplicationContext(), colorList, colorCode);
        spnColor.setAdapter(customAdapter);
        spnColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_COLOR_ID = body.get(i).getId();
                } else {
                    SELECTED_COLOR_ID = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addMetroAreaSpinnerData(final List<MetropolitanArea> body) {
        List<String> colorList = new ArrayList<>();
        //colorList.add(0,selectOne);
        for (int i = 0; i < body.size(); i++) {
            colorList.add(body.get(i).getAreaName());
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, colorList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRegNoName1.setAdapter(dataAdapter2);
        spnRegNoName1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_REGNO_1 = body.get(i).getAreaName();
                } else {
                    SELECTED_REGNO_1 = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
/*    public void getAllVehicleType() {

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<VehicleType>> vehicleTypes = retrofitService.GetVehicleTypes();
        vehicleTypes.enqueue(new Callback<List<VehicleType>>() {
            @Override
            public void onResponse(Call<List<VehicleType>> call, Response<List<VehicleType>> response) {

                if (response.body() != null) {

                    vehicleTypeArrayList.clear();
                    vehicleTypeArrayList.addAll(response.body());

                    addVehicleTypeNamePresentSpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<VehicleType>> call, Throwable t) {
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    public void getAllVehicleModel(int id) {

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<VehicleModel>> vehicleModels = retrofitService.GetVehicleModelByVehicleId(id);
        vehicleModels.enqueue(new Callback<List<VehicleModel>>() {
            @Override
            public void onResponse(Call<List<VehicleModel>> call, Response<List<VehicleModel>> response) {

                if (response.body() != null) {

                    VehicleModelArrayList.clear();
                    VehicleModelArrayList.addAll(response.body());
                    addVehicleMadyBySpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<VehicleModel>> call, Throwable t) {
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void getAllColor() {


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<Colors>> colors = retrofitService.GetColors();
        colors.enqueue(new Callback<List<Colors>>() {
            @Override
            public void onResponse(Call<List<Colors>> call, Response<List<Colors>> response) {

                if (response.body() != null) {

                    colorArrayList.clear();
                    colorArrayList.addAll(response.body());

                    addColorSpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Colors>> call, Throwable t) {
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }*/





   /* public void GetAllMetropolitanArea() {


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<MetroAreaModel>> colors = retrofitService.GetAllMetropolitanArea();
        colors.enqueue(new Callback<List<MetroAreaModel>>() {
            @Override
            public void onResponse(Call<List<MetroAreaModel>> call, Response<List<MetroAreaModel>> response) {

                if (response.body() != null) {

                    metroAreaModelArrayList.clear();
                    metroAreaModelArrayList.addAll(response.body());

                    addMetroAreaSpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MetroAreaModel>> call, Throwable t) {
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }*/




    /*public void GetAllRegistrationLevel() {


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<RegistrationLevelModel>> colors = retrofitService.GetAllRegistrationLevel();
        colors.enqueue(new Callback<List<RegistrationLevelModel>>() {
            @Override
            public void onResponse(Call<List<RegistrationLevelModel>> call, Response<List<RegistrationLevelModel>> response) {

                if (response.body() != null) {

                    registrationLevelModels.clear();
                    registrationLevelModels.addAll(response.body());

                    addRegistrationLevelModelSpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<RegistrationLevelModel>> call, Throwable t) {
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }*/

    public void addRegistrationLevelModelSpinnerData(final List<RegistrationLevel> body) {

        List<String> colorList = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            colorList.add(body.get(i).getLevelName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, colorList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRegNoName2.setAdapter(dataAdapter2);
        spnRegNoName2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_REGNO_2 = body.get(i).getLevelName();
                } else {
                    SELECTED_REGNO_2 = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


  /*  public void getDistrict( ) {


            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<District>> divisions = retrofitService.getAllDistricts();
            divisions.enqueue(new Callback<List<District>>() {
                @Override
                public void onResponse(Call<List<District>> call, Response<List<District>> response) {

                    if (response.body() != null) {

                        districtArrayList.clear();
                        districtArrayList.addAll(response.body());

                        addDistrictSpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<District>> call, Throwable t) {
                    Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

    }*/

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
                        //getAllThana(body.get(i).getId());
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

   /* public void getAllThana(int id) {

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
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }*/


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
//Date Picker

    private void initializeVariables() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        backYear=calendar.get(Calendar.YEAR)-5;

        formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        etVehicleDate.setText(formatter.format(calendar.getTime()));
        Date myDate = null;
        try {
            myDate = formatter.parse(formatter.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(myDate);
        calendar.add(Calendar.YEAR, -5);
        Date newDate = calendar.getTime();
        String date = formatter.format(newDate);
        etMadeDate.setText(date);
        etVehicleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(mYear, mMonth, mDay, R.style.DatePickerSpinner);
            }
        });

        etMadeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(backYear, mMonth, mDay, R.style.DatePickerSpinner);
            }
        });

        mHour = calendar.get(Calendar.HOUR);
        mMin = calendar.get(Calendar.MINUTE);
        mSec = calendar.get(Calendar.SECOND);
        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
        etVehicleTime.setText(formatter1.format(calendar.getTime()));


    }


    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback(VehicleEntryActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .showTitle(true)
                .build()
                .show();
    }

    @OnClick(R.id.etVehicleTime)
    public void etVehicleTime() {
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
                etVehicleTime.setText(selectedHour + ":" + selectedMinute + " " + status);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        etVehicleDate.setText(formatter.format(calendar.getTime()));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
