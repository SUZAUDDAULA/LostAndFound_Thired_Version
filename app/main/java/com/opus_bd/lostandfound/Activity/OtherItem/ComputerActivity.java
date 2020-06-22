package com.opus_bd.lostandfound.Activity.OtherItem;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.card.MaterialCardView;
import com.hbb20.CountryCodePicker;
import com.mynameismidori.currencypicker.CurrencyPicker;
import com.mynameismidori.currencypicker.CurrencyPickerListener;
import com.opus_bd.lostandfound.Activity.CodeGenerateActivity;
import com.opus_bd.lostandfound.Activity.DashboardActivity;
import com.opus_bd.lostandfound.Activity.InformationEntryActivity;
import com.opus_bd.lostandfound.Activity.OthersItemEntryActivity;
import com.opus_bd.lostandfound.Adapter.CustomAdapter;
import com.opus_bd.lostandfound.Adapter.CustomColorAdapter;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.Documentaion.MetroAreaModel;
import com.opus_bd.lostandfound.Model.Documentaion.RegistrationLevelModel;
import com.opus_bd.lostandfound.Model.Documentaion.ProductType;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Division;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
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

public class ComputerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    // Layout
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;

    // Information View
    @BindView(R.id.mcvProductInformation)
    MaterialCardView mcvProductInformation;
    @BindView(R.id.mcvProductIdendityInformation)
    MaterialCardView mcvProductIdendityInformation;

    @BindView(R.id.mcvProductPlaceTimeInformation)
    MaterialCardView mcvProductPlaceTimeInformation;


    /* input*/
    /*  Product Information*/
    @BindView(R.id.spnProductBrand)
    Spinner spnProductBrand;

    @BindView(R.id.etProductModel)
    EditText etProductModel;

    @BindView(R.id.etServiceTag)
    EditText etServiceTag;

    @BindView(R.id.etEMCProductID)
    EditText etEMCProductID;

    @BindView(R.id.etProductNumber)
    EditText etProductNumber;
    @BindView(R.id.spnColor)
    Spinner spnColor;

    @BindView(R.id.tvPrice)
    TextView tvPrice;

    /*  Product  Idendity Information*/


    @BindView(R.id.etProductIdentitySign)
    EditText etProductIdentitySign;



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


    boolean isllProductEntryChecked = true;
    @BindView(R.id.llProductInfromation)
    LinearLayout llProductInfromation;

    @BindView(R.id.ivProductInformation)
    ImageView ivProductInformation;

    boolean isllProductIdentificationChecked = true;
    @BindView(R.id.llProductIdentityInfo)
    LinearLayout llProductIdentityInfo;

    @BindView(R.id.ivProductIdentityInfo)
    ImageView ivProductIdentityInfo;

    boolean isllVPATChecked = true;
    @BindView(R.id.llProductPlaceTime)
    LinearLayout llProductPlaceTime;

    @BindView(R.id.ivProductPlaceTime)
    ImageView ivProductPlaceTime;    @BindView(R.id.rowComAssesories)
    TableRow rowComAssesories;

   

   
    //Address List
    
    ArrayList<District> districtArrayList = new ArrayList<>();
    ArrayList<Thana> thanaArrayList = new ArrayList<>();
    ArrayList<Colors> colorArrayList = new ArrayList<>();

    public int SELECTED_COLOR_ID;
    public int SELECTED_DISTRICT_ID;
    public int SELECTED_THANA_ID;


    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();

    //time picker
    int mHour, mMin, mSec;


    //Image

    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    @BindView(R.id.gv)
    GridView gvGallery;
    private GalleryAdapter galleryAdapter;

//Report view textView

   /* @BindView(R.id.tvModel)
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

    @BindView(R.id.tvProductDate)
    TextView tvProductDate;

    @BindView(R.id.tvProductTime)
    TextView tvProductTime;

//    @BindView(R.id.tvSPDivision)
//    TextView tvSPDivision;

    @BindView(R.id.tvSPDistrict)
    TextView tvSPDistrict;

    @BindView(R.id.tvSPThana)
    TextView tvSPThana;

    @BindView(R.id.tvPhoto)
    TextView tvPhoto;


    @BindView(R.id.tvProductType)
    TextView tvProductType;

    @BindView(R.id.tvMadeBy)
    TextView tvMadeBy;
    @BindView(R.id.tvColor)
    TextView tvColor;
    @BindView(R.id.tvBlueBook)
    TextView tvBlueBook;*/


    String selectOne,ServiceTag,EMCProductID,ProductNumber;
    //Spinner

    char[] ServiceTagArray, EMCProductIDArray, ProductNumberArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer);
        ButterKnife.bind(this);

        if(Constants.COMPUTER_TYPE_ID==Constants.COMACCESORIES){
            rowComAssesories.setVisibility(View.VISIBLE);
        }
        mcvProductInformation.setVisibility(View.VISIBLE);
        mcvProductIdendityInformation.setVisibility(View.GONE);
        mcvProductPlaceTimeInformation.setVisibility(View.GONE);
        mcvReport.setVisibility(View.GONE);
        selectOne = getResources().getString(R.string.select_option);
        //Spinner
        getAllColor();
        getDistrict();


        //date picker
        initializeVariables();
       
        etServiceTag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (etServiceTag.getText().length() == 3 || etServiceTag.getText().length() == 6 || etServiceTag.getText().length() == 9 || etServiceTag.getText().length() == 12 || etServiceTag.getText().length() == 15 || etServiceTag.getText().length() == 18 || etServiceTag.getText().length() == 21 || etServiceTag.getText().length() == 24 || etServiceTag.getText().length() == 27) {
                    ServiceTag = etServiceTag.getText().toString().toUpperCase() + "-";
                    char c = ServiceTag.charAt(ServiceTag.length() - 2);

                    if (c != '-') {
                        ServiceTagArray = ServiceTag.toCharArray();
                        ServiceTagArray[ServiceTag.length() - 2] = ServiceTagArray[ServiceTag.length() - 1];
                        ServiceTagArray[ServiceTag.length() - 1] = c;

                        //code to convert charArray back to String..
                        ServiceTag = new String(ServiceTagArray);
                        etServiceTag.setText(ServiceTag);
                        etServiceTag.setSelection(ServiceTag.length());
                        ServiceTag = null;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etEMCProductID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (etEMCProductID.getText().length() == 3 || etEMCProductID.getText().length() == 6 || etEMCProductID.getText().length() == 9 || etEMCProductID.getText().length() == 12 || etEMCProductID.getText().length() == 15 || etEMCProductID.getText().length() == 18 || etEMCProductID.getText().length() == 21 || etEMCProductID.getText().length() == 24 || etEMCProductID.getText().length() == 27) {
                    EMCProductID = etEMCProductID.getText().toString().toUpperCase() + "-";
                    char c = EMCProductID.charAt(EMCProductID.length() - 2);

                    if (c != '-') {
                        EMCProductIDArray = EMCProductID.toCharArray();
                        EMCProductIDArray[EMCProductID.length() - 2] = EMCProductIDArray[EMCProductID.length() - 1];
                        EMCProductIDArray[EMCProductID.length() - 1] = c;

                        //code to convert charArray back to String..
                        EMCProductID = new String(EMCProductIDArray);
                        etEMCProductID.setText(EMCProductID);
                        etEMCProductID.setSelection(EMCProductID.length());
                        EMCProductID = null;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        etProductNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (etEMCProductID.getText().length() == 3 || etEMCProductID.getText().length() == 6 || etEMCProductID.getText().length() == 9 || etEMCProductID.getText().length() == 12 || etEMCProductID.getText().length() == 15 || etEMCProductID.getText().length() == 18 || etEMCProductID.getText().length() == 21 || etEMCProductID.getText().length() == 24 || etEMCProductID.getText().length() == 27) {
                    ProductNumber = etEMCProductID.getText().toString().toUpperCase() + "-";
                    char c = ProductNumber.charAt(ProductNumber.length() - 2);

                    if (c != '-') {
                        ProductNumberArray = ProductNumber.toCharArray();
                        ProductNumberArray[ProductNumber.length() - 2] = ProductNumberArray[ProductNumber.length() - 1];
                        ProductNumberArray[ProductNumber.length() - 1] = c;

                        //code to convert charArray back to String..
                        ProductNumber = new String(EMCProductIDArray);
                        etEMCProductID.setText(ProductNumber);
                        etEMCProductID.setSelection(ProductNumber.length());
                        ProductNumber = null;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//Multiple image



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

    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }



    @OnClick(R.id.ivProductInformation)
    public void ivProductInformation() {
        if (isllProductEntryChecked) {
            // show password
            llProductInfromation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivProductInformation);
            isllProductEntryChecked = false;
        } else {
            // hide password
            llProductInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivProductInformation);
            isllProductEntryChecked = true;
        }

    }

    @OnClick({R.id.ivProductIdentityInfo, R.id.btnNext1})
    public void ivProductIdentityInfo() {

        if (isllProductIdentificationChecked) {
            // show password
            llProductInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivProductInformation);
            isllProductEntryChecked = true;

            mcvProductIdendityInformation.setVisibility(View.VISIBLE);
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
    public void ReportShown() {
     /*   llInput.setVisibility(View.GONE);
        mcvReport.setVisibility(View.VISIBLE);*/
        Intent intent=new Intent(this, OthersItemEntryActivity.class);
        startActivity(intent);
        Toast.makeText(this, R.string.toast_succss,Toast.LENGTH_SHORT).show();
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

 /*   @OnClick(R.id.Edit)
    public void Edit() {
        llInput.setVisibility(View.VISIBLE);
        mcvReport.setVisibility(View.GONE);
    }

    @OnClick(R.id.Submit)
    public void Submit() {
        //submitToServer();
        Intent intent = new Intent(ComputerActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }*/

/*
    private void submitToServer() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        final GDInformationModel gdInformationModel = new GDInformationModel();

        //GD Information
        gdInformationModel.setUserName(UserName);
        gdInformationModel.setGdFor(Constants.GDFOR);
        gdInformationModel.setGdDate(etProductDate.getText().toString());
        gdInformationModel.setIdentityNo("3453453");
        gdInformationModel.setGDTypeId(Constants.ENTRY_TYPE_ID);
        gdInformationModel.setProductTypeId(Constants.PRODUCT_TYPE_ID);

        gdInformationModel.setDocumentTypeId(SELECTED_DOCUMENT_ID);
        gdInformationModel.setDocumentDescription("");

        //Product Information

        gdInformationModel.setProductTypeId(SELECTED_ProductTYPE_ID);
        gdInformationModel.setMadeBy(SELECTED_ProductMODEL_Name);
        gdInformationModel.setModelNo(etModel.getText().toString());
        gdInformationModel.setRegNoFirstPart(SELECTED_REGNO_1);
        gdInformationModel.setRegNoSecondPart(SELECTED_REGNO_1);
        gdInformationModel.setRegNoThiredPart(etRegNoName.getText().toString());
        gdInformationModel.setServiceTag(etServiceTag.getText().toString());
        gdInformationModel.setChasisNo(etEMCProductID.getText().toString());
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
        gdInformationModel.setLafDate(etProductDate.getText().toString());
        gdInformationModel.setLafTime(etProductTime.getText().toString());


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.SaveGDInformation(token, gdInformationModel);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce");

                        Toast.makeText(ComputerActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ComputerActivity.this, CodeGenerateActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(ComputerActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(ComputerActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());

                Toast.makeText(ComputerActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
*/


    
/*
    public void getAllProductType() {

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<ProductType>> ProductTypes = retrofitService.GetProductTypes();
        ProductTypes.enqueue(new Callback<List<ProductType>>() {
            @Override
            public void onResponse(Call<List<ProductType>> call, Response<List<ProductType>> response) {

                if (response.body() != null) {

                    ProductTypeArrayList.clear();
                    ProductTypeArrayList.addAll(response.body());

                    addProductTypeNamePresentSpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ProductType>> call, Throwable t) {
                Toast.makeText(ComputerActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void addProductTypeNamePresentSpinnerData(final List<ProductType> body) {
        List<String> ProductList = new ArrayList<>();
        ProductList.add(0, selectOne);
        for (int i = 0; i < body.size(); i++) {
            ProductList.add(i + 1, body.get(i).getProductTypeName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ProductList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProductType.setAdapter(dataAdapter2);
        spnProductType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_ProductTYPE_ID = body.get(i).getId();
                    getAllProductModel(body.get(i - 1).getId());
                } else {
                    SELECTED_ProductTYPE_ID = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getAllProductModel(int id) {

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<ProductModel>> ProductModels = retrofitService.GetProductModelByProductId(id);
        ProductModels.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {

                if (response.body() != null) {

                    ProductModelArrayList.clear();
                    ProductModelArrayList.addAll(response.body());
                    addProductMadyBySpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Toast.makeText(ComputerActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addProductMadyBySpinnerData(final List<ProductModel> body) {
        List<String> ProductMadyBy = new ArrayList<>();
        List<String> ProductIcon = new ArrayList<>();
        ProductMadyBy.add(0, selectOne);
        ProductIcon.add(0, "");
        for (int i = 0; i < body.size(); i++) {
            ProductMadyBy.add(i + 1, body.get(i).getModelName());
            ProductIcon.add(i + 1, body.get(i).getImagePath());
        }
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), ProductIcon, ProductMadyBy);
        spnMadeBy.setAdapter(customAdapter);
        spnMadeBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_ProductMODEL_Name = body.get(i).getModelName();
                } else {
                    SELECTED_ProductMODEL_Name = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }*/

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
                Toast.makeText(ComputerActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ComputerActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ComputerActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                .callback(ComputerActivity.this)
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
