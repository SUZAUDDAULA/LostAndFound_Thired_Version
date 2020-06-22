package com.opus_bd.lostandfound;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.opus_bd.lostandfound.Activity.DashboardActivity;
import com.opus_bd.lostandfound.Activity.LoginActivity;
import com.opus_bd.lostandfound.Activity.UnknownManActivity;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleType;
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

public class LostInformationEntryActivity extends AppCompatActivity {
    @BindView(R.id.llVOwn)
    LinearLayout llVOwn;

    @BindView(R.id.llVOthers)
    LinearLayout llVOthers;

    @BindView(R.id.LLInputForOthers)
    LinearLayout LLInputForOthers;
    @BindView(R.id.llplaceDocument)
    LinearLayout llplaceDocument;
    @BindView(R.id.etModel)
    EditText etModel;
    @BindView(R.id.etRegNoName)
    EditText etRegNoName;

    @BindView(R.id.etEngineNo)
    EditText etEngineNo;

    @BindView(R.id.cvItems)
    CardView cvItems;
    @BindView(R.id.LLItems)
    LinearLayout LLItems;
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.llEntry)
    LinearLayout llEntry; @BindView(R.id.vehicleDocument)
    LinearLayout vehicleDocument;
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

    @BindView(R.id.etNumber)
    EditText etNumber;
    @BindView(R.id.cvInput)
    CardView cvInput;
    @BindView(R.id.fabOwn)
    FloatingActionButton fabOwn;
    @BindView(R.id.fabVihecal)
    FloatingActionButton fabVihecal;

    @BindView(R.id.fabOther)
    FloatingActionButton fabOther;
    //Spinner
    @BindView(R.id.spnDocumentType)
    Spinner spnDocumentType;
    @BindView(R.id.spnVehicleType)
    AppCompatSpinner spnVehicleType;

    @BindView(R.id.spnMadeBy)
    AppCompatSpinner spnMadeBy;
    ArrayList<DocumentType> documentTypeArrayList = new ArrayList<>();
    public int SELECTED_DOCUMENT_ID;
    ArrayList<VehicleType> vehicleTypeArrayList = new ArrayList<>();
    ArrayList<VehicleModel> VehicleModelArrayList = new ArrayList<>();

    public int SELECTED_VEHICLETYPE_ID;
    public String SELECTED_VEHICLEMODEL_Name;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_information_entry);
        ButterKnife.bind(this);
        LLItems.setVisibility(View.GONE);
        LLInputForOthers.setVisibility(View.GONE);
        llEntry.setVisibility(View.GONE);
        vehicleDocument.setVisibility(View.GONE);
        llplaceDocument.setVisibility(View.GONE);

        iv1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));


        //Spinner
        getAllDocument();
        getAllVehicleType();
        //addItemsOnSpinner2();
    }
    protected void attachBaseContext(Context base) {
        SharedPreferences tprefs = base.getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, MODE_PRIVATE);
        boolean language = tprefs.getBoolean(SharedPrefManager.KEY_State, true);
        if (language)
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.ENGLISH));
        else
            super.attachBaseContext(LocaleHelper.setLocale(base, Constants.BANGLA));
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVOwn, R.id.fabOwn})
    public void LlVOwn() {
        Constants.GDFOR=Constants.OWN;
        fabOwn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimaryDark));
        fabOther.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.grey_40));
        LLInputForOthers.setVisibility(View.GONE);
        cvInput.setVisibility(View.GONE);
        LLItems.setVisibility(View.VISIBLE);
        cvItems.setVisibility(View.VISIBLE);
        iv4.setVisibility(View.GONE);
        v3.setVisibility(View.GONE);
        iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVOthers, R.id.fabOther})
    public void llVOthers() {
        Constants.GDFOR=Constants.OTHERS;
        fabOwn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.grey_40));
        fabOther.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimaryDark));
        LLInputForOthers.setVisibility(View.VISIBLE);
        cvInput.setVisibility(View.GONE);
        LLItems.setVisibility(View.GONE);
        cvItems.setVisibility(View.GONE);
        iv2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.btnOthersInput)
    public void btnOthersInput() {
        LLInputForOthers.setVisibility(View.GONE);
        // llEntry.setVisibility(View.GONE);
        cvItems.setVisibility(View.VISIBLE);
        LLItems.setVisibility(View.VISIBLE);
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.llVVihecal,R.id.fabVihecal})
    public void llVVihecal() {
        Constants.PRODUCT_TYPE_ID=Constants.VEHICLE;
        fabVihecal.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimaryDark));
        LLInputForOthers.setVisibility(View.GONE);
        llInput.setVisibility(View.GONE);
        llEntry.setVisibility(View.VISIBLE);
        LLItems.setVisibility(View.VISIBLE);
        cvItems.setVisibility(View.GONE);
        iv4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        iv3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
        v2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorAccent));
    }

    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(LostInformationEntryActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @OnClick(R.id.btnEntryInput)
    public void btnEntryInput() {
        llEntry.setVisibility(View.GONE);
        vehicleDocument.setVisibility(View.VISIBLE);

    }
 @OnClick({R.id.llVMan,R.id.fabMan})
    public void llVMan() {
        // submitToServer();
     Constants.PRODUCT_TYPE_ID=Constants.MAN;
        Intent intent = new Intent(LostInformationEntryActivity.this, UnknownManActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @OnClick(R.id.btnEntryDocument)
    public void btnEntryDocument() {
        vehicleDocument.setVisibility(View.GONE);
        llplaceDocument.setVisibility(View.VISIBLE);

        // submitToServer();

        // vehicleDocument.setVisibility(View.VISIBLE);
       /* Intent intent = new Intent(LostInformationEntryActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/

    } @OnClick(R.id.btnPlaceDocument)
    public void btnPlaceDocument() {
        llplaceDocument.setVisibility(View.VISIBLE);
        customDialog();

        // submitToServer();

        // vehicleDocument.setVisibility(View.VISIBLE);
       /* Intent intent = new Intent(LostInformationEntryActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/

    }

    public void customDialog() {
        final Dialog dialog = new Dialog(LostInformationEntryActivity.this);
        dialog.setContentView(R.layout.dialog_custom_code_generator);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        Button btnThanks = (Button) dialog.findViewById(R.id.btnThanks);
        btnThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(LostInformationEntryActivity.this, DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
       /* // set the custom dialog components - text, image and button
        ImageView ivLiveChat = (ImageView) dialog.findViewById(R.id.ivLiveChat);
        ImageView ivEmail = (ImageView) dialog.findViewById(R.id.ivEmail);
        ImageView ivCall = (ImageView) dialog.findViewById(R.id.ivCall);


        CardView cvLiveChat = (CardView) dialog.findViewById(R.id.cvLiveChat);
        CardView cvEmail = (CardView) dialog.findViewById(R.id.cvEmail);
      */

       /* ivLiveChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.police.gov.bd/en/criminal_investigation_department"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cvLiveChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.police.gov.bd/en/criminal_investigation_department"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01737366028"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });ivEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, "Juelrananatore@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Support");
                    startActivity(Intent.createChooser(intent, "Send Email"));*//*
                    intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                    startActivity(Intent.createChooser(intent, "Send Email"));*//*
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });cvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, "Juelrananatore@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Support");
                    startActivity(Intent.createChooser(intent, "Send Email"));*//*
                    intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

                 *//*
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }*/
        /* });*/



        dialog.show();
    }
    private void submitToServer() {

        String token = SharedPrefManager.getInstance(this).getToken();
        final GDInformationModel gdInformationModel = new GDInformationModel();

        ////  gdInformationModel.setUserName(etUserName.toString());
        gdInformationModel.setGdFor("2");
        gdInformationModel.setGdDate("2020-04-14");
        gdInformationModel.setIdentityNo(etNumber.getText().toString());
        gdInformationModel.setGDTypeId(1);
        gdInformationModel.setProductTypeId(1);

        gdInformationModel.setDocumentTypeId(SELECTED_DOCUMENT_ID);
        gdInformationModel.setDocumentDescription("");

        gdInformationModel.setVehicleTypeId(SELECTED_VEHICLETYPE_ID);
        gdInformationModel.setModelNo(etModel.getText().toString());
        gdInformationModel.setEngineNo(etEngineNo.getText().toString());
        gdInformationModel.setMadeBy(SELECTED_VEHICLEMODEL_Name);
        // registrationModel.setOtpCode(etNidNum.getText().toString());


        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.SaveGDInformation(token, gdInformationModel);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Utilities.showLogcatMessage("responce");


                     /*   try{
                            String auth=response.body().getJwt().replace("{\"auth_token\":\"","");
                            String auth1=auth.replace("\"}","");
                            Utilities.showLogcatMessage(" "+auth1);
                            SharedPrefManager.getInstance(context).saveToken(auth1);
                            SharedPrefManager.getInstance(context).saveotp(response.body().getOtpCode());
                            SharedPrefManager.getInstance(context).saveotp(response.body().getUserInfo().getUserName());
                        }
                        catch (Exception e) {
                            Utilities.showLogcatMessage("Exception 1"+e.toString());
                            Toast.makeText(context, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                        }*/

                        Toast.makeText(LostInformationEntryActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LostInformationEntryActivity.this, DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LostInformationEntryActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Utilities.showLogcatMessage("Exception 2" + e.toString());
                    Toast.makeText(LostInformationEntryActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(LostInformationEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    //Spinner

    public void getAllDocument() {

        String token = SharedPrefManager.getInstance(this).getToken();

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<DocumentType>> registrationRequest = retrofitService.GetAllDocumentType();
        registrationRequest.enqueue(new Callback<List<DocumentType>>() {
            @Override
            public void onResponse(Call<List<DocumentType>> call, Response<List<DocumentType>> response) {

                if (response.body() != null) {

                    documentTypeArrayList.clear();
                    documentTypeArrayList.addAll(response.body());
                    Utilities.showLogcatMessage(" Div Size " + response.body().size());
                    for (int i = 0; i < response.body().size(); i++) {
                        Utilities.showLogcatMessage(" Div ID" + response.body().get(i).getId());
                    }

                    addDocumentTypeNamePresentSpinnerData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DocumentType>> call, Throwable t) {
                Toast.makeText(LostInformationEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
     /*   } else {
            Toast.makeText(this, "Not registered! Please sign in to continue", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }*/
    }


    public void addDocumentTypeNamePresentSpinnerData(final List<DocumentType> body) {
        List<String> documentList = new ArrayList<>();

        documentList.add(getResources().getString(R.string.doc_type));
        for (int i = 0; i < body.size(); i++) {
            documentList.add(body.get(i).getDocumentTypeName());
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
    } public void getAllVehicleType() {

        String token = SharedPrefManager.getInstance(this).getToken();
        if (token != null) {
            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<VehicleType>> vehicleTypes = retrofitService.GetVehicleTypes();
            vehicleTypes.enqueue(new Callback<List<VehicleType>>() {
                @Override
                public void onResponse(Call<List<VehicleType>> call, Response<List<VehicleType>> response) {

                    if (response.body() != null) {

                        vehicleTypeArrayList.clear();
                        vehicleTypeArrayList.addAll(response.body());
                        Utilities.showLogcatMessage(" Div Size " + response.body().size());
                        for (int i = 0; i < response.body().size(); i++) {
                            Utilities.showLogcatMessage(" Div ID" + response.body().get(i).getId());
                        }

                        addVehicleTypeNamePresentSpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<VehicleType>> call, Throwable t) {
                    Toast.makeText(LostInformationEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Not registered! Please sign in to continue", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void addVehicleTypeNamePresentSpinnerData(final List<VehicleType> body) {
        List<String> vehicleList = new ArrayList<>();
        vehicleList.add("যানবাহনের ধরণ");
        for (int i = 0; i < body.size(); i++) {
            vehicleList.add(body.get(i).getVehicleTypeName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, vehicleList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnVehicleType.setAdapter(dataAdapter2);
        spnVehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    SELECTED_VEHICLETYPE_ID = body.get(i).getId();
                    getAllVehicleModel(body.get(i).getId());
                } else {
                    SELECTED_VEHICLETYPE_ID = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getAllVehicleModel(int id) {

        String token = SharedPrefManager.getInstance(this).getToken();
        if (token != null) {
            RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
            Call<List<VehicleModel>> vehicleModels = retrofitService.GetVehicleModelByVehicleId(id);
            vehicleModels.enqueue(new Callback<List<VehicleModel>>() {
                @Override
                public void onResponse(Call<List<VehicleModel>> call, Response<List<VehicleModel>> response) {

                    if (response.body() != null) {

                        VehicleModelArrayList.clear();
                        VehicleModelArrayList.addAll(response.body());
                        Utilities.showLogcatMessage(" Div Size " + response.body().size());
                        for (int i = 0; i < response.body().size(); i++) {
                            Utilities.showLogcatMessage(" Div ID" + response.body().get(i).getId());
                        }

                        addVehicleMadyBySpinnerData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<VehicleModel>> call, Throwable t) {
                    Toast.makeText(LostInformationEntryActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Not registered! Please sign in to continue", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void addVehicleMadyBySpinnerData(final List<VehicleModel> body) {
        List<String> vehicleMadyBy = new ArrayList<>();
//        vehicleMadyBy.add("্রান্ডের নাম");
        for (int i = 0; i < body.size(); i++) {
            vehicleMadyBy.add(body.get(i).getModelName());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, vehicleMadyBy);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMadeBy.setAdapter(dataAdapter2);
        spnMadeBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
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

    // add items into spinner dynamically
//    public void addItemsOnSpinner2() {
//
//        spinner2 = (Spinner) findViewById(R.id.spinner2);
//        List<String> list = new ArrayList<String>();
//        list.add("list 1");
//        list.add("list 2");
//        list.add("list 3");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner2.setAdapter(dataAdapter);
//    }


}
