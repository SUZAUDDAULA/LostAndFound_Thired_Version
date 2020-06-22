package com.opus_bd.lostandfound.Activity.OtherItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.opus_bd.lostandfound.Activity.DashboardActivity;
import com.opus_bd.lostandfound.Activity.InformationEntryActivity;
import com.opus_bd.lostandfound.Adapter.CustomColorAdapter;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElectronicsActivity extends AppCompatActivity {
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;
    // Information View
    @BindView(R.id.mcvCardInformation)
    MaterialCardView mcvCardInformation;
    @BindView(R.id.mcvCardtIdendityInformation)
    MaterialCardView mcvCardtIdendityInformation;
    @BindView(R.id.mcvLostPlaceTimeInformation)
    MaterialCardView mcvLostPlaceTimeInformation;

    @BindView(R.id.spnColor)
    Spinner spnColor;

    @BindView(R.id.tvPrice)
    TextView tvPrice;
    /*  Product  Idendity Information*/


    @BindView(R.id.etProductIdentitySign)
    EditText etProductIdentitySign;

    @BindView(R.id.tvImage)
    TextView tvImage;

    /*Product place and time Information*/
    @BindView(R.id.spnDistrict)
    Spinner spnDistrict;

    @BindView(R.id.spnThana)
    Spinner spnThana;

    @BindView(R.id.spnVilage)
    Spinner spnVilage;

    @BindView(R.id.etAddressDetails)
    EditText etAddressDetails;

    @BindView(R.id.etProductDate)
    EditText etProductDate;

    @BindView(R.id.etProductTime)
    EditText etProductTime;

    boolean isllCardInformationChecked = true;
    @BindView(R.id.llCardInformation)
    LinearLayout llCardInformation;

    @BindView(R.id.ivCardInformation)
    ImageView ivCardInformation;

    boolean isllCardtIdendityInformationChecked = true;
    @BindView(R.id.llCardtIdendityInformation)
    LinearLayout llCardtIdendityInformation;

    @BindView(R.id.ivCardtIdendityInformation)
    ImageView ivCardtIdendityInformation;

    boolean isllLostPlaceTimeInformationChecked = true;
    @BindView(R.id.llLostPlaceTimeInformation)
    LinearLayout llLostPlaceTimeInformation;

    @BindView(R.id.ivLostPlaceTimeInformation)
    ImageView ivLostPlaceTimeInformation;

    //Address List

    ArrayList<District> districtArrayList = new ArrayList<>();
    ArrayList<Thana> thanaArrayList = new ArrayList<>();
    ArrayList<Colors> colorArrayList = new ArrayList<>();

    public int SELECTED_COLOR_ID;
    public int SELECTED_DISTRICT_ID;
    public int SELECTED_THANA_ID;

    //data
    @BindView(R.id.spnElectronicType)
    Spinner spnElectronicType;

    @BindView(R.id.spnElectronicName)
    Spinner spnElectronicName;

    String[] electronicsApp;

    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();

    //time picker
    int mHour, mMin, mSec;

    int PICK_IMAGE_MULTIPLE = 27;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery, gvGallery1;
    private GalleryAdapter galleryAdapter;
    String selectOne,ServiceTag,EMCProductID,ProductNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);
        ButterKnife.bind(this);

        mcvCardInformation.setVisibility(View.VISIBLE);
        mcvCardtIdendityInformation.setVisibility(View.GONE);
        mcvLostPlaceTimeInformation.setVisibility(View.GONE);
        mcvReport.setVisibility(View.GONE);
        selectOne = getResources().getString(R.string.select_option);

        getAllColor();
        getDistrict();
        //date picker
        initializeVariables();
        spnElectronicType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();

                if(selectedItem.equals("Home Appearance"))
                {
                    electronicsApp=getResources().getStringArray(R.array.array_home_appearnce_type);
                }else if(selectedItem.equals("Kitchen appearance")){
                    electronicsApp=getResources().getStringArray(R.array.array_kitchen_appearnce_type);
                }else if(selectedItem.equals("Kitchen appearance")){
                    electronicsApp=getResources().getStringArray(R.array.array_kitchen_appearnce_type);
                }else if(selectedItem.equals("Small Appearance")){
                    electronicsApp=getResources().getStringArray(R.array.array_small_appearnce_type);
                }else if(selectedItem.equals("Self Appearance")){
                    electronicsApp=getResources().getStringArray(R.array.array_self_appearnce_type);
                }else {
                    electronicsApp=getResources().getStringArray(R.array.array_home_appearnce_type);
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,electronicsApp);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                spnElectronicName.setAdapter(spinnerArrayAdapter);
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

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

    //Multiple image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {

            super.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            Utilities.showLogcatMessage("onActivityResult " + e.toString());
        }
       /* Utilities.showLogcatMessage("requestCode "+requestCode);
        Utilities.showLogcatMessage("resultCode "+resultCode);
        Utilities.showLogcatMessage("data "+data);*/
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {

                    Uri mImageUri = data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();

                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                    gvGallery.setAdapter(galleryAdapter);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);


                    gvGallery1.setAdapter(galleryAdapter);
                    gvGallery1.setVerticalSpacing(gvGallery1.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp1 = (ViewGroup.MarginLayoutParams) gvGallery1
                            .getLayoutParams();
                    mlp1.setMargins(0, gvGallery1.getHorizontalSpacing(), 0, 0);
                    //  tvPhoto.setText("Selected Images " + mArrayUri.size());

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                            galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                            gvGallery.setAdapter(galleryAdapter);
                            gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);


                            gvGallery1.setAdapter(galleryAdapter);
                            gvGallery1.setVerticalSpacing(gvGallery1.getHorizontalSpacing());
                            ViewGroup.MarginLayoutParams mlp1 = (ViewGroup.MarginLayoutParams) gvGallery1
                                    .getLayoutParams();
                            mlp1.setMargins(0, gvGallery1.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                        //  tvPhoto.setText("Selected Images " + mArrayUri.size());
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG)
                    .show();
            Utilities.showLogcatMessage(" " + e.getLocalizedMessage());
        }


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
                Toast.makeText(ElectronicsActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                    SELECTED_COLOR_ID = 0;
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
                Toast.makeText(ElectronicsActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.ivCardInformation)
    public void ivCardInformation() {
        if (isllCardInformationChecked) {
            // show password
            llCardInformation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivCardInformation);
            isllCardInformationChecked = false;
        } else {
            // hide password
            llCardInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivCardInformation);
            isllCardInformationChecked = true;
        }

    }

    @OnClick({R.id.ivCardtIdendityInformation, R.id.btnNext1})
    public void ivCardtIdendityInformation() {

        if (isllCardtIdendityInformationChecked) {
            // show password
            llCardInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivCardInformation);
            isllCardInformationChecked = true;

            mcvCardtIdendityInformation.setVisibility(View.VISIBLE);
            llCardtIdendityInformation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivCardtIdendityInformation);
            isllCardtIdendityInformationChecked = false;

        } else {
            // hide password
            llCardtIdendityInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivCardtIdendityInformation);
            isllCardtIdendityInformationChecked = true;
        }
    }



    @OnClick({R.id.ivLostPlaceTimeInformation, R.id.btnNext2})
    public void ivLostPlaceTimeInformation() {
        if (isllLostPlaceTimeInformationChecked) {
            // show password
            llCardtIdendityInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivCardtIdendityInformation);
            isllCardtIdendityInformationChecked = true;

            mcvLostPlaceTimeInformation.setVisibility(View.VISIBLE);
            llLostPlaceTimeInformation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivLostPlaceTimeInformation);
            isllLostPlaceTimeInformationChecked = false;

        } else {
            // hide password
            llLostPlaceTimeInformation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivLostPlaceTimeInformation);
            isllLostPlaceTimeInformationChecked = true;
        }

    }


    @OnClick(R.id.btnNext3)
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
                Toast.makeText(ElectronicsActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
        formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        etProductDate.setText(formatter.format(calendar.getTime()));
        etProductDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDate(mYear, mMonth, mDay, R.style.DatePickerSpinner);
            }
        });


        mHour = calendar.get(Calendar.HOUR);
        mMin = calendar.get(Calendar.MINUTE);
        mSec = calendar.get(Calendar.SECOND);
        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
        etProductTime.setText(formatter1.format(calendar.getTime()));


    }


//    @VisibleForTesting
//    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
//        new SpinnerDatePickerDialogBuilder()
//                .context(this)
//                .callback(CardsActivity.this)
//                .spinnerTheme(spinnerTheme)
//                .defaultDate(year, monthOfYear, dayOfMonth)
//                .showTitle(true)
//                .build()
//                .show();
//    }

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


//    @Override
//    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
//        etProductDate.setText(formatter.format(calendar.getTime()));
//    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
