package com.opus_bd.lostandfound.Activity.PoliceInterface;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.card.MaterialCardView;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
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


public class SorothalReportActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public int btnImgCount;
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.llReport)
    LinearLayout llReport;
    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;
    boolean isllCaseDescriptionChecked = true;
    @BindView(R.id.mcvCaseDescription)
    MaterialCardView mcvCaseDescription;
    @BindView(R.id.llCaseDescription)
    LinearLayout llCaseDescription;
    @BindView(R.id.ivCaseDescription)
    ImageView ivCaseDescription;
    boolean isllDeathDescriptionChecked = true;
    @BindView(R.id.mcvDeathDescription)
    MaterialCardView mcvDeathDescription;
    @BindView(R.id.llDeathDescription)
    LinearLayout llDeathDescription;
    @BindView(R.id.ivDeathDescription)
    ImageView ivDeathDescription;
    boolean isllDeathIdendityInformationChecked = true;
    @BindView(R.id.mcvDeathIdendityInformation)
    MaterialCardView mcvDeathIdendityInformation;
    @BindView(R.id.llDeathIdendityInformation)
    LinearLayout llDeathIdendityInformation;
    @BindView(R.id.ivDeathIdendityInformation)
    ImageView ivDeathIdendityInformation;
    boolean isllDeathPersonPhotoesChecked = true;
    @BindView(R.id.mcvDeathPersonPhotoes)
    MaterialCardView mcvDeathPersonPhotoes;
    @BindView(R.id.llDeathPersonPhotoes)
    LinearLayout llDeathPersonPhotoes;

    @BindView(R.id.spnDistrict)
    Spinner spnDistrict;
    @BindView(R.id.spnThana)
    Spinner spnThana;
    @BindView(R.id.ivDeathPersonPhotoes)
    ImageView ivDeathPersonPhotoes;
    @BindView(R.id.etVehicleDate)
    EditText etVehicleDate;
    @BindView(R.id.etVehicleTime)
    EditText etVehicleTime;
    @BindView(R.id.btnAddPhotoes)
    Button btnAddPhotoes;
    @BindView(R.id.ivBackHead)
    ImageView ivBackHead;
    @BindView(R.id.ivFrontHead)
    ImageView ivFrontHead;
    @BindView(R.id.ivFacePic)
    ImageView ivFacePic;
    @BindView(R.id.ivFrontThroat)
    ImageView ivFrontThroat;
    @BindView(R.id.ivBehindNeck)
    ImageView ivBehindNeck;
    @BindView(R.id.ivChestPic)
    ImageView ivChestPic;
    @BindView(R.id.ivBackPic)
    ImageView ivBackPic;

    public int SELECTED_DISTRICT_ID;
    public int SELECTED_THANA_ID;

    ArrayList<District> districtArrayList = new ArrayList<>();
    ArrayList<Thana> thanaArrayList = new ArrayList<>();
    @BindView(R.id.ivEarPic)
    ImageView ivEarPic;
    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();
    //time picker
    int mHour, mMin, mSec;
    //Image Picker
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.gvGallery)
    GridView gvGallery;


    public String Language,english,bangla;
    String selectOne;
    private GalleryAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorothal_report);
        ButterKnife.bind(this);
        mcvReport.setVisibility(View.GONE);
        llCaseDescription.setVisibility(View.VISIBLE);
        Glide.with(this).load(R.drawable.ic_drop_up).into(ivCaseDescription);
        mcvDeathDescription.setVisibility(View.GONE);
        mcvDeathIdendityInformation.setVisibility(View.GONE);
        mcvDeathPersonPhotoes.setVisibility(View.GONE);
        this.setTitle(getResources().getText(R.string.sorothal));
        initializeVariables();
        selectOne = getResources().getString(R.string.select_option);
        Boolean languageStatus = getSharedPrefValue();
        getAllDistrict();
    }

    @OnClick(R.id.ivCaseDescription)
    public void ivCaseDescription() {
        if (isllCaseDescriptionChecked) {
            // show password
            llCaseDescription.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivCaseDescription);
            isllCaseDescriptionChecked = false;
        } else {
            // hide password
            llCaseDescription.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivCaseDescription);
            isllCaseDescriptionChecked = true;
        }

    }

    @OnClick({R.id.ivDeathDescription, R.id.btnNext1})
    public void ivDeathDescription() {

        if (isllDeathDescriptionChecked) {
            // show password
            llCaseDescription.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivCaseDescription);
            isllCaseDescriptionChecked = true;

            mcvDeathDescription.setVisibility(View.VISIBLE);
            llDeathDescription.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivDeathDescription);
            isllDeathDescriptionChecked = false;

        } else {
            // hide password
            llDeathDescription.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDeathDescription);
            isllDeathDescriptionChecked = true;
        }
    }


    @OnClick({R.id.ivDeathIdendityInformation, R.id.btnNext2})
    public void ivDeathIdendityInformation() {
        if (isllDeathIdendityInformationChecked) {
            // show password
            llDeathDescription.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDeathDescription);
            isllDeathDescriptionChecked = true;

            mcvDeathIdendityInformation.setVisibility(View.VISIBLE);
            llDeathIdendityInformation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivDeathIdendityInformation);
            isllDeathIdendityInformationChecked = false;

        } else {
            // hide password
            llDeathIdendityInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDeathIdendityInformation);
            isllDeathIdendityInformationChecked = true;
        }

    }

    @OnClick({R.id.ivDeathPersonPhotoes, R.id.btnNext3})
    public void ivDeathPersonPhotoes() {
        if (isllDeathPersonPhotoesChecked) {
            // show password
            llDeathIdendityInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDeathIdendityInformation);
            isllDeathIdendityInformationChecked = true;

            mcvDeathPersonPhotoes.setVisibility(View.VISIBLE);
            llDeathPersonPhotoes.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivDeathPersonPhotoes);
            isllDeathPersonPhotoesChecked = false;

        } else {
            // hide password
            llDeathPersonPhotoes.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDeathPersonPhotoes);
            isllDeathPersonPhotoesChecked = true;
        }

    }


    @OnClick(R.id.btnNextPhotoes)
    public void ReportShown() {
        llInput.setVisibility(View.GONE);
        mcvReport.setVisibility(View.VISIBLE);
        try {
       /*     tvProductType.setText(spnProductType.getSelectedItem().toString());
            tvProductDate.setText(etProductDate.getText().toString());
            tvModel.setText(etModel.getText().toString());
            //tvMadeBy.setText(spnMadeBy.getSelectedItem().toString());
            tvMadeIn.setText(ccp.getSelectedCountryName());
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvAddressDetails.setText(etAddressDetails.getText().toString());
            tvRegNoName.setText(spnRegNoName1.getSelectedItem().toString() + " " + spnRegNoName2.getSelectedItem().toString()
                    + " " + etRegNoName.getText().toString());
            tvEngineNo.setText(etServiceTag.getText().toString());
            tvChesisNo.setText(etEMCProductID.getText().toString());
            tvCCNo.setText(etCCNo.getText().toString());
            tvMadeIn.setText(ccp.getSelectedCountryName());
            tvMadeDate.setText(etMadeDate.getText().toString());
            tvColor.setText(spnColor.getSelectedItem().toString());
            tvIdentitySign.setText(etIdentitySign.getText().toString());
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvSPThana.setText(spnSPThana.getSelectedItem().toString());
            tvAddressDetails.setText(etAddressDetails.getText().toString());
            tvProductDate.setText(etProductDate.getText().toString());
            tvProductTime.setText(etProductTime.getText().toString());*/
            //ivrPhoto.setImageResource(ivProductAttachment.getSourceLayoutResId());
            //Log.i("reportinfo", "engineno: " + etServiceTag.getText().toString() + ",chesisno: " + etEMCProductID.getText().toString() + ",date: " + etMadeDate.getText().toString() + "");
        } catch (Exception e) {
            Log.e("reportinfo", "btnNext4: ", e);
        }

    }

    private void initializeVariables() {
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        etVehicleDate.setText(formatter.format(calendar.getTime()));

        etVehicleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(mYear, mMonth, mDay, R.style.DatePickerSpinner);
            }
        });


        mHour = calendar.get(Calendar.HOUR);
        mMin = calendar.get(Calendar.MINUTE);
        mSec = calendar.get(Calendar.SECOND);
        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
        etVehicleTime.setText(formatter1.format(calendar.getTime()));


    }

    private boolean getSharedPrefValue() {
        SharedPreferences tprefs = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return tprefs.getBoolean(KEY_State, true);
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
                Toast.makeText(SorothalReportActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
        spnThana.setAdapter(dataAdapter2);
        spnThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    public void getAllDistrict() {

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<District>> listCall = retrofitService.getAllDistricts();
        listCall.enqueue(new Callback<List<District>>() {
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
                Toast.makeText(SorothalReportActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

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
        spnDistrict.setAdapter(dataAdapter2);
        spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_DISTRICT_ID = body.get(i).getId();
                        getAllThana(body.get(i - 1).getId());
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


    //    Photo add
    @OnClick(R.id.btnAddPhotoes)
    public void ImageAdd() {
        //ImagePicker();
        btnImgCount = 1;
        CropImage.activity().start(SorothalReportActivity.this);
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
                if (btnImgCount == 1) {
                    mArrayUri.add(resultUri);
                    galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                } else if (btnImgCount == 2) {
                    ivFrontHead.setImageURI(resultUri);
                } else if (btnImgCount == 3) {
                    ivBackHead.setImageURI(resultUri);
                } else if (btnImgCount == 4) {
                    ivFacePic.setImageURI(resultUri);
                } else if (btnImgCount == 5) {
                    ivFrontThroat.setImageURI(resultUri);
                } else if (btnImgCount == 6) {
                    ivBehindNeck.setImageURI(resultUri);
                } else if (btnImgCount == 7) {
                    ivChestPic.setImageURI(resultUri);
                } else if (btnImgCount == 8) {
                    ivBackPic.setImageURI(resultUri);
                } else if (btnImgCount == 9) {
                    ivEarPic.setImageURI(resultUri);
                } else {
                    mArrayUri.add(resultUri);
                    galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                }


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Utilities.showLogcatMessage("ierror" + result.getError().getMessage());
                Exception error = result.getError();
            }
        }
    }


    @OnClick(R.id.ivFrontHead)
    public void ivFrontHead() {
        btnImgCount = 2;
        CropImage.activity().start(SorothalReportActivity.this);
    }

    @OnClick(R.id.ivBackHead)
    public void ivBackHead() {
        btnImgCount = 3;
        CropImage.activity().start(SorothalReportActivity.this);
    }

    @OnClick(R.id.ivFacePic)
    public void ivFacePic() {
        btnImgCount = 4;
        CropImage.activity().start(SorothalReportActivity.this);
    }

    @OnClick(R.id.ivFrontThroat)
    public void ivFrontThroat() {
        btnImgCount = 5;
        CropImage.activity().start(SorothalReportActivity.this);
    }

    @OnClick(R.id.ivBehindNeck)
    public void ivBehindNeck() {
        btnImgCount = 6;
        CropImage.activity().start(SorothalReportActivity.this);
    }

    @OnClick(R.id.ivChestPic)
    public void ivChestPic() {
        btnImgCount = 7;
        CropImage.activity().start(SorothalReportActivity.this);
    }

    @OnClick(R.id.ivBackPic)
    public void ivBackPic() {
        btnImgCount = 8;
        CropImage.activity().start(SorothalReportActivity.this);
    }

    @OnClick(R.id.ivEarPic)
    public void ivEarPic() {
        btnImgCount = 9;
        CropImage.activity().start(SorothalReportActivity.this);
    }


    public void ImagePicker() {
        ImagePicker.Companion.with(this)
                .galleryOnly()
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    //TIME AND dATE
    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback(SorothalReportActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .showTitle(true)
                .build()
                .show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        etVehicleDate.setText(formatter.format(calendar.getTime()));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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


}