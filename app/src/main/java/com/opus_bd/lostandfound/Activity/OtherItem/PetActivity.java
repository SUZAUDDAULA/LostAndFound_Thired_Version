package com.opus_bd.lostandfound.Activity.OtherItem;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.card.MaterialCardView;
import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.opus_bd.lostandfound.Activity.ENRTY.CodeGenerateActivity;
import com.opus_bd.lostandfound.Activity.ENRTY.InformationEntryActivity;
import com.opus_bd.lostandfound.Activity.ENRTY.OthersItemEntryActivity;
import com.opus_bd.lostandfound.Adapter.CustomColorAdapter;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.Model.OthersItem.ComputerInfo;
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

public class PetActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    @BindView(R.id.gv)
    GridView gvGallery;
    private GalleryAdapter galleryAdapter;

    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();

    //time picker
    int mHour, mMin, mSec;
    //Address List

    ArrayList<District> districtArrayList = new ArrayList<>();
    ArrayList<Thana> thanaArrayList = new ArrayList<>();
    ArrayList<Colors> colorArrayList = new ArrayList<>();

    public Integer SELECTED_COLOR_ID;
    public Integer SELECTED_DISTRICT_ID;
    public Integer SELECTED_THANA_ID;

    // Layout
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;

    // Information View
    @BindView(R.id.mcvPrimaryInformation)
    MaterialCardView mcvPrimaryInformation;
    @BindView(R.id.mcvIdendityInformation)
    MaterialCardView mcvIdendityInformation;

    @BindView(R.id.mcvProductPlaceTimeInformation)
    MaterialCardView mcvProductPlaceTimeInformation;
    //Vew
    boolean isllProductEntryChecked = true;
    @BindView(R.id.llProductInfromation)
    LinearLayout llProductInfromation;

    @BindView(R.id.ivPrimaryInfromation)
    ImageView ivPrimaryInfromation;

    boolean isllProductIdentificationChecked = true;
    @BindView(R.id.llProductIdentityInfo)
    LinearLayout llProductIdentityInfo;

    @BindView(R.id.ivProductIdentityInfo)
    ImageView ivProductIdentityInfo;

    boolean isllVPATChecked = true;
    @BindView(R.id.llProductPlaceTime)
    LinearLayout llProductPlaceTime;

    @BindView(R.id.ivProductPlaceTime)
    ImageView ivProductPlaceTime;
    String selectOne;

    
    //Input

    @BindView(R.id.spnPetType)
    Spinner spnPetType;
    
    @BindView(R.id.spnColor)
    Spinner spnColor;
    

    @BindView(R.id.etSize)
    EditText etSize;

    @BindView(R.id.etAmmount)
    EditText etAmmount;

    /*  Product  Idendity Information*/


    @BindView(R.id.etProductIdentitySign)
    EditText etProductIdentitySign;



    /*Product place and time Information*/
    @BindView(R.id.spnDistrict)
    Spinner spnDistrict;

    @BindView(R.id.spnThana)
    Spinner spnThana;

    @BindView(R.id.spnVilage)
    EditText spnVilage;


    @BindView(R.id.etAddressDetails)
    EditText etAddressDetails;

    @BindView(R.id.etProductDate)
    EditText etProductDate;

    @BindView(R.id.etProductTime)
    EditText etProductTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        ButterKnife.bind(this);
        mcvPrimaryInformation.setVisibility(View.VISIBLE);
        mcvIdendityInformation.setVisibility(View.GONE);
        mcvProductPlaceTimeInformation.setVisibility(View.GONE);
        mcvReport.setVisibility(View.GONE);
        selectOne = getResources().getString(R.string.select_option);
        //Spinner
        getAllColor();
        getDistrict();
        //date picker
        initializeVariables();
    }
    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }



    @OnClick(R.id.ivPrimaryInfromation)
    public void ivPrimaryInfromation() {
        if (isllProductEntryChecked) {
            // show password
            llProductInfromation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivPrimaryInfromation);
            isllProductEntryChecked = false;
        } else {
            // hide password
            llProductInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivPrimaryInfromation);
            isllProductEntryChecked = true;
        }

    }

    @OnClick({R.id.ivProductIdentityInfo, R.id.btnNext1})
    public void ivProductIdentityInfo() {

        if (isllProductIdentificationChecked) {
            // show password
            llProductInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivPrimaryInfromation);
            isllProductEntryChecked = true;

            mcvIdendityInformation.setVisibility(View.VISIBLE);
            llProductIdentityInfo.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivProductIdentityInfo);
            isllProductIdentificationChecked = false;

        } else {
            // hide password
            llProductIdentityInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivProductIdentityInfo);
            isllProductIdentificationChecked = true;
        }
    }



    @OnClick({R.id.ivProductPlaceTime, R.id.btnNext2})
    public void ivProductPlaceTime() {
        if (isllVPATChecked) {
            // show password
            llProductIdentityInfo.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivProductIdentityInfo);
            isllProductIdentificationChecked = true;

            mcvProductPlaceTimeInformation.setVisibility(View.VISIBLE);
            llProductPlaceTime.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivProductPlaceTime);
            isllVPATChecked = false;

        } else {
            // hide password
            llProductPlaceTime.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivProductPlaceTime);
            isllVPATChecked = true;
        }

    }


    @OnClick(R.id.btnNext3)
    public void Submit(){

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        final ComputerInfo model = new ComputerInfo();

        //GD Information
        model.setUserName(UserName);
        model.setGdFor(Constants.GDFOR);
        model.setGDTypeId(Constants.ENTRY_TYPE_ID);
        model.setProductTypeId(Constants.PRODUCT_TYPE_ID);
        model.setDocumentDescription("");

        model.setProductTypeId(spnPetType.getSelectedItemPosition());
        model.setColorId(SELECTED_COLOR_ID);
        // size  model.(etSize.getText().toString());
        // Amount model.(etAmmount.getText().toString());
        model.setIdentificationMark(etProductIdentitySign.getText().toString());
        model.setDistrictId(SELECTED_DISTRICT_ID);
        model.setThanaId(SELECTED_THANA_ID);
        model.setVillage(spnVilage.getText().toString());

        model.setAddressDetails(etAddressDetails.getText().toString());
        model.setDate(etProductDate.getText().toString());
        model.setTime(etProductTime.getText().toString());

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);

        Call<String> registrationRequest = retrofitService.SaveComputerInfo(SharedPrefManager.BEARER+token, model);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Toast.makeText(PetActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        CodeGenerateActivity.code = response.body().toString();
                        Intent intent = new Intent(PetActivity.this, CodeGenerateActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(PetActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(PetActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(PetActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

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
                Toast.makeText(PetActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
        spnColor.setAdapter(customAdapter);
        spnColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_COLOR_ID = body.get(i).getId();
                } else {
                    SELECTED_COLOR_ID = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getDistrict() {


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
                Toast.makeText(PetActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addDistrictSpinnerData(final List<District> body) {
        List<String> districtList = new ArrayList<>();
        districtList.add(0, selectOne);
        for (int i = 0; i < body.size(); i++) {
            districtList.add(i + 1, body.get(i).getDistrictName());
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, districtList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDistrict.setAdapter(dataAdapter2);
        spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_DISTRICT_ID = body.get(i).getId();
                        getAllThana(body.get(i).getId());
                    } else {
                        SELECTED_DISTRICT_ID = null;
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
                Toast.makeText(PetActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addThanaSpinnerData(final List<Thana> body) {
        List<String> thanaList = new ArrayList<>();
        thanaList.add(0, selectOne);
        for (int i = 0; i < body.size(); i++) {
            thanaList.add(i + 1, body.get(i).getThanaName());
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, thanaList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnThana.setAdapter(dataAdapter2);
        spnThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_THANA_ID = body.get(i).getId();
                } else {
                    SELECTED_THANA_ID = null;
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
//Date Picker

    private void initializeVariables() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        etProductDate.setText(formatter.format(calendar.getTime()));
        etProductDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(mYear, mMonth, mDay, R.style.DatePickerSpinner);
            }
        });


        mHour = calendar.get(Calendar.HOUR);
        mMin = calendar.get(Calendar.MINUTE);
        mSec = calendar.get(Calendar.SECOND);
        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
        etProductTime.setText(formatter1.format(calendar.getTime()));


    }


    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback(PetActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .showTitle(true)
                .build()
                .show();
    }

    @OnClick(R.id.etProductTime)
    public void etProductTime() {
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
                etProductTime.setText(selectedHour + ":" + selectedMinute + " " + status);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        etProductDate.setText(formatter.format(calendar.getTime()));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @OnClick(R.id.btnAddPhotoes)
    public void ImageAdd(){
        ImagePicker();
    }

    //ImagePicker

    public void ImagePicker(){
        ImagePicker.Companion.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
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
}
