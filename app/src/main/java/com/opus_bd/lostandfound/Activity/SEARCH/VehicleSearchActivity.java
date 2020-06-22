package com.opus_bd.lostandfound.Activity.SEARCH;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.opus_bd.lostandfound.Activity.PoliceInterface.PoliceMainActivity;
import com.opus_bd.lostandfound.Adapter.CustomAdapter;
import com.opus_bd.lostandfound.Adapter.CustomColorAdapter;
import com.opus_bd.lostandfound.Adapter.VehicleSearchInformationAdapter;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleType;
import com.opus_bd.lostandfound.Model.Vehichel.MetropolitanArea;
import com.opus_bd.lostandfound.Model.Vehichel.RegistrationLevel;
import com.opus_bd.lostandfound.Model.Vehichel.VehicleMasterModel;
import com.opus_bd.lostandfound.Model.VehicleSearch.VehicleSearchListModel;
import com.opus_bd.lostandfound.R;
import com.opus_bd.lostandfound.RetrofitService.RetrofitClientInstance;
import com.opus_bd.lostandfound.RetrofitService.RetrofitService;
import com.opus_bd.lostandfound.Utils.Constants;
import com.opus_bd.lostandfound.Utils.LocaleHelper;
import com.opus_bd.lostandfound.Utils.Utilities;
import com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.KEY_State;
import static com.opus_bd.lostandfound.sharedPrefManager.SharedPrefManager.SHARED_PREF_NAME;


public class VehicleSearchActivity extends AppCompatActivity {
    public int SELECTED_VEHICLETYPE_ID, SELECTED_VEHICLE_BRAND_TYPE_NAME, SELECTED_COLOR_ID;

    public String SELECTED_REGNO_1, SELECTED_REGNO_2;
    char[] engNoArray, chesisNoArray;
    String selectOne, engNoString, chesisNoString;
    public String Language, english, bangla;

    ProgressDialog progress;


    @BindView(R.id.spnVehicleType)
    Spinner spnVehicleType;
    @BindView(R.id.spnVehicleBrandType)
    Spinner spnVehicleBrandType;
    @BindView(R.id.actvModelName)
    AutoCompleteTextView actvModelName;
    @BindView(R.id.spnRegNoName1)
    Spinner spnRegNoName1;
    @BindView(R.id.spnRegNoName2)
    Spinner spnRegNoName2;
    @BindView(R.id.etRegNoName)
    EditText etRegNoName;
    @BindView(R.id.etEngineNo)
    EditText etEngineNo;
    @BindView(R.id.etChesisNo)
    EditText etChesisNo;
    @BindView(R.id.etCCNo)
    EditText etCCNo;
    @BindView(R.id.spnColor)
    Spinner spnColor;
    @BindView(R.id.tvNoDataFound)
    TextView tvNoDataFound;

    @BindView(R.id.cvSearch)
    CardView cvSearch;

    @BindView(R.id.rvGDInfo)
    RecyclerView rvGDInfo;
    VehicleSearchInformationAdapter vehicleSearchInformationAdapter;
    ArrayList<VehicleSearchListModel> vehicleModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_search);
        ButterKnife.bind(this);
        Boolean languageStatus = getSharedPrefValue();
        english = getResources().getString(R.string.english);
        bangla = getResources().getString(R.string.bangla);
        if (languageStatus) {
            Language = english;
        } else {
            Language = bangla;
        }
        setProgress();
        selectOne = getResources().getString(R.string.select_option);
        getVehicleMasterData();
        textUpperCase();

        intRecyclerView();
    }

    public void intRecyclerView() {
        vehicleSearchInformationAdapter = new VehicleSearchInformationAdapter(vehicleModelArrayList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        rvGDInfo.setLayoutManager(layoutManager);
        rvGDInfo.setAdapter(vehicleSearchInformationAdapter);
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
    }

    public void textUpperCase() {
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
                    try {
                        addVehicleTypeNamePresentSpinnerData(response.body().getVehicleTypes());
                    } catch (Exception e) {
                        Utilities.showExceptionMessage("" + e.toString());
                    }
                    try {
                        addVehicleBrandBySpinnerData(response.body().getVehicleModels());

                    } catch (Exception e) {
                        Utilities.showExceptionMessage("" + e.toString());
                    }

                    try {
                        addColorSpinnerData(response.body().getColors());

                    } catch (Exception e) {
                        Utilities.showExceptionMessage("" + e.toString());
                    }
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


                } else {
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<VehicleMasterModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(VehicleSearchActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                    SELECTED_VEHICLETYPE_ID = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addVehicleBrandBySpinnerData(final List<VehicleModel> body) {
        List<String> vehicleMadyBy = new ArrayList<>();
        List<String> vehicleIcon = new ArrayList<>();
        vehicleMadyBy.add(0, selectOne);
        vehicleIcon.add(0, "");
        for (int i = 0; i < body.size(); i++) {
            vehicleMadyBy.add(i + 1, body.get(i).getModelName());
            vehicleIcon.add(i + 1, body.get(i).getImagePath());
        }
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), vehicleIcon, vehicleMadyBy);
        spnVehicleBrandType.setAdapter(customAdapter);
        spnVehicleBrandType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_VEHICLE_BRAND_TYPE_NAME = body.get(i - 1).getId();
                } else {
                    SELECTED_VEHICLE_BRAND_TYPE_NAME = 0;
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                colorList.add(i + 1, body.get(i).getColorName());
                colorCode.add(i + 1, body.get(i).getColorCode());
            }
        } else {
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
                    SELECTED_COLOR_ID = body.get(i - 1).getId();
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

    @OnClick(R.id.btnSearch)
    public void Search() {

        SearchItems();
    }

    private void SearchItems() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();
        String modelNo = actvModelName.getText().toString();
        if (modelNo.equals("")) {
            modelNo = "NA";
        }
        String regiNo = SELECTED_REGNO_1 + " " + SELECTED_REGNO_2 + " " + etRegNoName.getText().toString();
        if (etRegNoName.getText().toString().equals("")) {
            regiNo = "NA";
        }
        String engineNo = etEngineNo.getText().toString();
        if (engineNo.equals("")) {
            engineNo = "NA";
        }
        String chesisNo = etChesisNo.getText().toString();
        if (chesisNo.equals("")) {
            chesisNo = "NA";
        }
        String cc = etCCNo.getText().toString();
        if (cc.equals("")) {
            cc = "NA";
        }
        int colorId = SELECTED_COLOR_ID;


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<VehicleSearchListModel>> registrationRequest = retrofitService.GetVehicleInformationBySearching(SharedPrefManager.BEARER + token,
                SELECTED_VEHICLETYPE_ID, SELECTED_VEHICLE_BRAND_TYPE_NAME, modelNo, regiNo, engineNo, chesisNo, cc, colorId, UserName);
       /* Call<List<VehicleSearchListModel>> registrationRequest = retrofitService.GetVehicleInformationBySearching(SharedPrefManager.BEARER + token,
                4, 1, "bdhddh", "Dhaka Metro E 95-5965", "BS-BD-DB-SD-B",
                "BS-BD-BD-HH-D", "6464646465", 5);*/
        Utilities.showLogcatMessage("SELECTED_VEHICLETYPE_ID=" + SELECTED_VEHICLETYPE_ID + "-SELECTED_VEHICLE_BRAND_TYPE_NAME=" + SELECTED_VEHICLE_BRAND_TYPE_NAME + "-" + "modelNo=" + modelNo + "-" + "regiNo=" + regiNo + "-" + "engineNo=" + engineNo + "-" + "chesisNo=" + chesisNo + "-" + "cc=" + cc + "-" + "colorId=" + colorId);

        registrationRequest.enqueue(new Callback<List<VehicleSearchListModel>>() {
            @Override
            public void onResponse(Call<List<VehicleSearchListModel>> call, Response<List<VehicleSearchListModel>> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce" + response.body().size());
                        if (response.body().size() == 0) {
                            tvNoDataFound.setVisibility(View.VISIBLE);
                            cvSearch.setVisibility(View.GONE);
                        } else {
                            tvNoDataFound.setVisibility(View.GONE);
                            cvSearch.setVisibility(View.VISIBLE);
                            vehicleModelArrayList.clear();
                            vehicleModelArrayList.addAll(response.body());
                            vehicleSearchInformationAdapter.notifyDataSetChanged();
                        }

                        //  Toast.makeText(VehicleSearchActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(VehicleSearchActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(VehicleSearchActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<List<VehicleSearchListModel>> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());

                Toast.makeText(VehicleSearchActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    //onBackPressed
    @Override
    public void onBackPressed() {
        if (Constants.isAdmin == 1) {
            Intent intent = new Intent(this, PoliceMainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
        super.onBackPressed();
    }
//DashBoard

    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();

    }
}
