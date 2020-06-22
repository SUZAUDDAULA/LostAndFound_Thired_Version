package com.opus_bd.lostandfound.Activity.OtherItem;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;
import com.opus_bd.lostandfound.Activity.ENRTY.CodeGenerateActivity;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.Model.OthersItem.ComputerInfo;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;
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

public class MobilePhoneActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
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

    @BindView(R.id.etIMENumber)
    EditText etIMENumber;

    @BindView(R.id.etMobileModel)
    EditText etMobileModel;

    @BindView(R.id.etMACAddress)
    EditText etMACAddress;

    @BindView(R.id.etSerialNumber)
    EditText etSerialNumber;

    @BindView(R.id.etScreenSize)
    EditText etScreenSize;

    @BindView(R.id.etbattery)
    EditText etbattery;

    @BindView(R.id.etRAM)
    EditText etRAM;

    @BindView(R.id.etROM)
    EditText etROM;

    @BindView(R.id.etAddressDetails)
    EditText etAddressDetails;

    @BindView(R.id.etProductDate)
    EditText etProductDate;

    @BindView(R.id.etProductTime)
    EditText etProductTime;

    @BindView(R.id.etWatchQuantity)
    EditText etWatchQuantity;

    @BindView(R.id.etWatchAttachment)
    EditText etWatchAttachment;

    @BindView(R.id.tvPrice)
    TextView tvPrice;

    @BindView(R.id.etPrice)
    TextView etPrice;

    @BindView(R.id.trPhoneAccessories)
    TableRow trPhoneAccessories;

    @BindView(R.id.rom)
    Spinner rom;

    @BindView(R.id.ram)
    Spinner ram;

    @BindView(R.id.spnPhoneType)
    Spinner spnPhoneType;

    @BindView(R.id.spnPhoneAccessoriesType)
    Spinner spnPhoneAccessoriesType;

    @BindView(R.id.spnPhoneBrand)
    Spinner spnPhoneBrand;

    @BindView(R.id.spnPhoneOS)
    Spinner spnPhoneOS;

    @BindView(R.id.spnPhoneColor)
    Spinner spnPhoneColor;

    @BindView(R.id.spnDistrict)
    Spinner spnDistrict;

    @BindView(R.id.spnThana)
    Spinner spnThana;

    @BindView(R.id.spnVilage)
    EditText spnVilage;

    String selectOne;

    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();

    //time picker
    int mHour, mMin, mSec;

    ArrayList<District> districtArrayList = new ArrayList<>();
    ArrayList<Thana> thanaArrayList = new ArrayList<>();
    ArrayList<Colors> colorArrayList = new ArrayList<>();

    public Integer SELECTED_COLOR_ID;
    public Integer SELECTED_DISTRICT_ID;
    public Integer SELECTED_THANA_ID;

    public String Language,english,bangla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_phone);
        ButterKnife.bind(this);

        mcvCardInformation.setVisibility(View.VISIBLE);
        mcvCardtIdendityInformation.setVisibility(View.GONE);
        mcvLostPlaceTimeInformation.setVisibility(View.GONE);
        mcvReport.setVisibility(View.GONE);
        selectOne = getResources().getString(R.string.select_option);

        trPhoneAccessories.setVisibility(View.GONE);

        Boolean languageStatus = getSharedPrefValue();
        english=getResources().getString(R.string.english);
        bangla=getResources().getString(R.string.bangla);
        if (languageStatus) {
            Language=english;
        } else {
            Language=bangla;
        }

        getDistrict();

        initializeVariables();

        spnPhoneType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();

                if(selectedItem.equals("Phone Accessories"))
                {
                    trPhoneAccessories.setVisibility(View.VISIBLE);
                }else {
                    trPhoneAccessories.setVisibility(View.GONE);
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        etIMENumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // if user is typing string one character at a time
                if (count == 1) {
                    // auto insert dashes while user typing their ssn
                    if (start == 2 || start == 6|| start == 10|| start == 14) {
                        etIMENumber.setText(etIMENumber.getText() + "-");
                        etIMENumber.setSelection(etIMENumber.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrencyPicker picker = CurrencyPicker.newInstance("Select Currency");  // dialog title

                picker.setListener(new CurrencyPickerListener() {
                    @Override
                    public void onSelectCurrency(String name, String code, String symbol, int flagDrawableResID) {
                        // Implement your code here
                        tvPrice.setText(code);
                        picker.dismiss();
                    }
                });
                picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");
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
    public void Submit() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        final ComputerInfo model = new ComputerInfo();

        //GD Information
        model.setUserName(UserName);
        model.setGdFor(Constants.GDFOR);
        model.setGDTypeId(Constants.ENTRY_TYPE_ID);
        model.setProductTypeId(Constants.PRODUCT_TYPE_ID);
        model.setDocumentDescription("");

        // mobile type
        // accessories
        model.setBrandTypeId(spnPhoneBrand.getSelectedItemPosition());
        model.setModel(etMobileModel.getText().toString());
       //operation system
        model.setColorId(spnPhoneColor.getSelectedItemPosition());
        // IME model.(etIMENumber.getText().toString());
       // Mac Address model.(etMACAddress.getText().toString());
        model.setSerialNo(etSerialNumber.getText().toString());
       // Screen Size model.(etScreenSize.getText().toString());
       // Battery model.(etbattery.getText().toString());

        // Ram size model.(etRAM.getText().toString());
        // Ram dropdown model.(ram.getSelectedItemPosition());

        // Rom size model.(etROM.getText().toString());
        // Rom dropdown model.(rom.getSelectedItemPosition());

        model.setPrice(Double.valueOf(etPrice.getText().toString()));
        // currency
        //Quantity
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
                        Toast.makeText(MobilePhoneActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        CodeGenerateActivity.code = response.body().toString();
                        Intent intent = new Intent(MobilePhoneActivity.this, CodeGenerateActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MobilePhoneActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MobilePhoneActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(MobilePhoneActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    // Date Time
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
                .callback(MobilePhoneActivity.this)
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
                Toast.makeText(MobilePhoneActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                        SELECTED_DISTRICT_ID = body.get(i - 1).getId();
                        getAllThana(body.get(i-1).getId());
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
                Toast.makeText(MobilePhoneActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                    SELECTED_THANA_ID = body.get(i - 1).getId();
                } else {
                    SELECTED_THANA_ID = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean getSharedPrefValue() {
        SharedPreferences tprefs = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return tprefs.getBoolean(KEY_State, true);
    }

}
