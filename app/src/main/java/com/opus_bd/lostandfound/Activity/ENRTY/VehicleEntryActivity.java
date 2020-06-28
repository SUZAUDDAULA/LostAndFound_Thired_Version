package com.opus_bd.lostandfound.Activity.ENRTY;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
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

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.card.MaterialCardView;
import com.hbb20.CountryCodePicker;
import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.opus_bd.lostandfound.Activity.DASHBOARD.ItemWiseNewsFeedActivity;
import com.opus_bd.lostandfound.Activity.DASHBOARD.UserProfileActivity;
import com.opus_bd.lostandfound.Adapter.CustomAdapter;
import com.opus_bd.lostandfound.Adapter.CustomColorAdapter;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleType;
import com.opus_bd.lostandfound.Model.GDInfoModel.GDViewModel;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Division;
import com.opus_bd.lostandfound.Model.GlobalData.FileUpload;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.Model.Vehichel.MetropolitanArea;
import com.opus_bd.lostandfound.Model.Vehichel.RegistrationLevel;
import com.opus_bd.lostandfound.Model.Vehichel.VehicleMasterModel;
import com.opus_bd.lostandfound.Model.Vehichel.VehiclePostModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;
import com.theartofdev.edmodo.cropper.CropImage;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.KEY_State;
import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.SHARED_PREF_NAME;

public class VehicleEntryActivity extends AppCompatActivity {
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

    @BindView(R.id.ivVehiclePhoto)
    ImageView ivVehiclePhoto;

    @BindView(R.id.iv_lineVP)
    ImageView iv_lineVP;

    @BindView(R.id.etDescription)
    EditText etDescription;

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
    public Integer SELECTED_DOCUMENT_ID;
    public Integer SELECTED_VEHICLETYPE_ID, SELECTED_VEHICLEBANDTYPE_ID;
    public String SELECTED_VEHICLEMODEL_Name;
    public String SELECTED_REGNO_1;
    public String SELECTED_REGNO_2;
    public Integer SELECTED_COLOR_ID;
    public Integer SELECTED_DIVISION_ID;
    public Integer SELECTED_DISTRICT_ID;
    public Integer SELECTED_THANA_ID;

    ProgressDialog progress;
    //date picker
    SimpleDateFormat formatter;
    public String Language, english, bangla;
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
    @BindView(R.id.tvTagPeople)
    TextView tvTagPeople;
    @BindView(R.id.tvVehicleInfromation)
    TextView tvVehicleInfromation;


//Multiple Image add

    private Button btn;
    int PICK_IMAGE_MULTIPLE = 27;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery, gvGallery1;
    private GalleryAdapter galleryAdapter;
    int mYear, mMonth, mDay, backYear;
    String selectOne, engNoString, chesisNoString,fileInfo,fileName,imagePath;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99 ;
    private static final int CAPTURE_REQUEST_CODE = 0;
    private static final int SELECT_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_entry);
        ButterKnife.bind(this);
        Boolean languageStatus = getSharedPrefValue();
        english = getResources().getString(R.string.english);
        bangla = getResources().getString(R.string.bangla);
        if (languageStatus) {
            Language = english;
        } else {
            Language = bangla;
        }
        ccp.setDefaultCountryUsingNameCode("JP");
        ccp.resetToDefaultCountry();
        tvTagPeople.setText(Constants.VEHICLE_TYPE_NAME+" Information");
        tvVehicleInfromation.setText(Constants.VEHICLE_TYPE_NAME+" Information");
        setProgress();
        mcvVehicleInformation.setVisibility(View.GONE);
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
        //getAllDocument();



        /*getAllColor();*/
        //getDivision();
       /* GetAllMetropolitanArea();
        getDistrict();
        GetAllRegistrationLevel();*/

        //date picker
        //initializeVariables();
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
        progress.setMessage("Data Processing.... ");
    }


    @OnClick(R.id.rlTagPeople)
    public  void  rlTagPeople(){
        llVehicleInfromation.setVisibility(View.VISIBLE);
        mcvVehicleInformation.setVisibility(View.VISIBLE);
        getVehicleMasterData();
    }
// @OnClick(R.id.btnPost)
//    public  void  btnPost(){
//     Intent intent = new Intent(VehicleEntryActivity.this, DashboardActivity.class);
//     startActivity(intent);
//     finish();
//    }

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


    @OnClick(R.id.Edit)
    public void Edit() {
        llInput.setVisibility(View.VISIBLE);
        mcvReport.setVisibility(View.GONE);
    }


    @OnClick(R.id.rlPhtotVideo)
    public void ImageAdd() {
        CropImage.activity().start(VehicleEntryActivity.this);
//        Intent intent=new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent,0);
    }


    //ImagePicker

    public void ImagePicker() {
        ImagePicker.Companion.with(this)
                .galleryOnly()
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            Utilities.showLogcatMessage("onActivityResult " + e.toString());
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                //Uri uri=data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                    fileInfo = Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
                    fileName = String.valueOf(Calendar.getInstance().getTimeInMillis());
                } catch (IOException e) {
                    Utilities.showLogcatMessage("IMAGE_BASE_64 " + e.toString());
                }

                //imagePath=getRealPathFromUri(uri);

                ivVehiclePhoto.setImageURI(resultUri);
                ivVehiclePhoto.setVisibility(View.VISIBLE);
                iv_lineVP.setVisibility(View.VISIBLE);
                etDescription.setHint("say something about this photo...");
                //mArrayUri.add(resultUri);
//                galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
//                gvGallery.setAdapter(galleryAdapter);


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void UploadBaseString(){
        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();
        Utilities.showLogcatMessage("FileInfo="+fileInfo);
        FileUpload fileUpload=new FileUpload(fileName,fileInfo);
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.UploadImageWithEncode(SharedPrefManager.BEARER + token,fileUpload);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce" + response.body());

                        Toast.makeText(VehicleEntryActivity.this, "Successfully Image Upload Done!", Toast.LENGTH_SHORT).show();


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

    private  String getRealPathFromUri(Uri uri){
        String [] projection={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getApplicationContext(),uri,projection,null,null,null);
        Cursor cursor=loader.loadInBackground();

        int column_indx=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String  result=cursor.getString(column_indx);
        cursor.close();
        return  result;

    }



    @OnClick(R.id.btnPost)
    public void Submit() {
        submitToServer();

           // UploadImage();
        //UploadBaseString();

    }

    private void submitToServer() {
        progress.show();
        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();
        String regNoName=etRegNoName.getText().toString();
        String regNo=SELECTED_REGNO_1+", "+SELECTED_REGNO_2+", "+regNoName;
        String engineNo=etEngineNo.getText().toString();
        String vehicleDescription=etDescription.getText().toString();
        String modelNo=etModel.getText().toString();
        Integer GDTYPEID=Constants.ENTRY_TYPE_ID;
        Integer VEHICLETYPEID=Constants.VEHICLE_TYPE_ID;

        final VehiclePostModel model = new VehiclePostModel(fileInfo,GDTYPEID,VEHICLETYPEID,SELECTED_VEHICLEBANDTYPE_ID,regNo,SELECTED_REGNO_1,SELECTED_REGNO_2,regNoName,modelNo,engineNo,vehicleDescription,UserName);

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.SaveVehicleWithImage(SharedPrefManager.BEARER + token, model);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce" + response.body());
                        progress.dismiss();
                        Toast.makeText(VehicleEntryActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(VehicleEntryActivity.this, ItemWiseNewsFeedActivity.class);

                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(VehicleEntryActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    progress.dismiss();
                    Toast.makeText(VehicleEntryActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                progress.dismiss();
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void UploadImage() {

        File file=new File(imagePath);
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("image",file.getName(),requestBody);

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.UploadImage(body);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce" + response.body());

                        Toast.makeText(VehicleEntryActivity.this, "Successfully Image Upload Done!", Toast.LENGTH_SHORT).show();


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

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<VehicleMasterModel> registrationRequest = retrofitService.GetVehicleMasterData();
        registrationRequest.enqueue(new Callback<VehicleMasterModel>() {
            @Override
            public void onResponse(Call<VehicleMasterModel> call, Response<VehicleMasterModel> response) {

                if (response.body() != null) {
                    try {
                        addVehicleTypeNamePresentSpinnerData(response.body().getVehicleTypes());
                    } catch (Exception e) {
                        Utilities.showExceptionMessage("" + e.toString());
                    }
                    try {
                        addVehicleMadyBySpinnerData(response.body().getVehicleModels());

                    } catch (Exception e) {
                        Utilities.showExceptionMessage("" + e.toString());
                    }
//                    try {
//                        addDistrictSpinnerData(response.body().getDistricts());
//
//                    } catch (Exception e) {
//                        Utilities.showExceptionMessage("" + e.toString());
//                    }
//                    try {
//                        addColorSpinnerData(response.body().getColors());
//
//                    } catch (Exception e) {
//                        Utilities.showExceptionMessage("" + e.toString());
//                    }
                    try {
                        addMetroAreaSpinnerData(response.body().getMetropolitanAreas());


                    } catch (Exception e) {
                        Utilities.showExceptionMessage("" + e.toString());
                    }
                    try {
                        addRegistrationLevelModelSpinnerData(response.body().getRegistrationLevels());


                    } catch (Exception e) {
                        Utilities.showExceptionMessage("" + e.toString());
                    }
//                    try {
//                        addThanaSpinnerData(response.body().getThanas());
//                    } catch (Exception e) {
//                        Utilities.showExceptionMessage("" + e.toString());
//                    }


                } else {
                    //progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<VehicleMasterModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(VehicleEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void addVehicleTypeNamePresentSpinnerData(final List<VehicleType> body) {
        List<String> vehicleList = new ArrayList<>();
        vehicleList.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                vehicleList.add(i + 1, body.get(i).getVehicleTypeName());
            }
        } else {
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
                    SELECTED_VEHICLETYPE_ID = body.get(i - 1).getId();
                    //  getAllVehicleModel(body.get(i-1).getId());
                } else {
                    SELECTED_VEHICLETYPE_ID = null;
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
                    SELECTED_VEHICLEMODEL_Name = body.get(i - 1).getModelName();
                    SELECTED_VEHICLEBANDTYPE_ID = body.get(i - 1).getId();
                } else {
                    SELECTED_VEHICLEMODEL_Name = "";
                    SELECTED_VEHICLEBANDTYPE_ID = null;
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


    @OnClick({R.id.profile_Name,R.id.user_prifile_pic})
    public void profile_Name() {
        Intent intent = new Intent(VehicleEntryActivity.this, UserProfileActivity.class);
        startActivity(intent);
        finish();
    }

    //InformationEntryActivity
    @Override
    public void onBackPressed() {
        Constants.ENTRY_TYPE_ID=Constants.ENTRY_TYPE_ID;
        Constants.ENTRY_TYPE_Name=Constants.ENTRY_TYPE_Name;
        Intent intent = new Intent(this, ItemWiseNewsFeedActivity.class);
        startActivity(intent);
        finish();
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
@OnClick(R.id.ivappLogo)
    public void ivappLogo() {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public boolean CheckPermission() {
        if (ContextCompat.checkSelfPermission(VehicleEntryActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(VehicleEntryActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(VehicleEntryActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(VehicleEntryActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(VehicleEntryActivity.this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(VehicleEntryActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(VehicleEntryActivity.this)
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(VehicleEntryActivity.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_LOCATION);


                                startActivity(new Intent(VehicleEntryActivity
                                        .this, VehicleEntryActivity.class));
                                VehicleEntryActivity.this.overridePendingTransition(0, 0);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(VehicleEntryActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;

        }
    }

}
