package com.opus_bd.lostandfound.Activity.SEARCH;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.hbb20.CountryCodePicker;
import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.opus_bd.lostandfound.Adapter.CustomColorAdapter;
import com.opus_bd.lostandfound.Adapter.Extra.AddressListAdapter;
import com.opus_bd.lostandfound.Adapter.Extra.HabitListAdapter;
import com.opus_bd.lostandfound.Adapter.Extra.SpeechListAdapter;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.Habit;
import com.opus_bd.lostandfound.Model.Documentaion.MDPersonalInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.MaritalStatus;
import com.opus_bd.lostandfound.Model.Documentaion.Occupation;
import com.opus_bd.lostandfound.Model.Documentaion.RelationType;
import com.opus_bd.lostandfound.Model.Documentaion.Religion;
import com.opus_bd.lostandfound.Model.Documentaion.Speech;
import com.opus_bd.lostandfound.Model.DressInfo.InTheBody;
import com.opus_bd.lostandfound.Model.DressInfo.InTheHead;
import com.opus_bd.lostandfound.Model.DressInfo.InTheLeg;
import com.opus_bd.lostandfound.Model.DressInfo.InTheThroat;
import com.opus_bd.lostandfound.Model.DressInfo.InTheWaist;
import com.opus_bd.lostandfound.Model.DressInfo.MDDressInformationModel;
import com.opus_bd.lostandfound.Model.ExtraModel.AdreessList;
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

public class MenSearchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    public int SELECTED_DISTRICT_ID;
    public int SELECTED_DISTRICT_ID_LP;
    public int SELECTED_THANA_ID;
    public int SELECTED_THANA_ID_LP;
    public int SELECTED_OCCUPATION_ID;
    public int SELECTED_RELATION_TYPE_ID;
    public int SELECTED_RELIGION_ID;
    public int SELECTED_MARITAL_STATUS_ID;
    public int SELECTED_HABIT_ID;
    public int SELECTED_SPEECH_ID;
    public int SELECTED_EYE_TYPE_ID, SELECTED_NOSE_TYPE_ID, SELECTED_HAIR_TYPE_ID, SELECTED_FOREHEAD_TYPE_ID, SELECTED_BEARD_TYPE_ID,
            SELECTED_PHYSICAL_CONDITION_ID, SELECTED_FACE_SHAPE_ID, SELECTED_BODY_CHIN_ID, SELECTED_BODY_COLOR_ID, SELECTED_MOUSTACE_ID, SELECTED_EAR_ID,
            SELECTED_NECK_ID, SELECTED_TEETH_ID;
    public int SELECTED_INBODY_ID, SELECTED_INHEAD_ID, SELECTED_INTHROAT_ID, SELECTED_INWAIST_ID, SELECTED_INLEGS_ID;
    public String Language, english, bangla;
    public int counterHabit, counterSpeech = 0;
    @BindView(R.id.llInput)
    LinearLayout llInput;
    boolean isllPersonInfromationChecked = true;
    @BindView(R.id.llPersonInfromation)
    LinearLayout llPersonInfromation;
    @BindView(R.id.ivTPersonInfromation)
    ImageView ivTPersonInfromation;
    boolean isllPersonIdentificationChecked = true;
    @BindView(R.id.mcvPersonIdendityInformation)
    MaterialCardView mcvPersonIdendityInformation;
    @BindView(R.id.llPersonIdentification)
    LinearLayout llPersonIdentification;
    @BindView(R.id.ivTPersonIdentification)
    ImageView ivTPersonIdentification;
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
    boolean isllPersonLostPlaceChecked = true;
    @BindView(R.id.mcvPersonLostPlace)
    MaterialCardView mcvPersonLostPlace;
    @BindView(R.id.llPersonLostPlace)
    LinearLayout llPersonLostPlace;
    @BindView(R.id.ivPersonLostPlace)
    ImageView ivTPersonLostPlace;
    boolean isllDressDescriptionChecked = true;
    @BindView(R.id.mcvDressDescription)
    MaterialCardView mcvDressDescription;
    @BindView(R.id.llDressDescription)
    LinearLayout llDressDescription;
    @BindView(R.id.ivDressDescription)
    ImageView ivDressDescription;
    boolean isllllPersonPhotoesChecked = true;
    @BindView(R.id.mcvPersonPhotoes)
    MaterialCardView mcvPersonPhotoes;
    @BindView(R.id.llPersonPhotoes)
    LinearLayout llPersonPhotoes;
    @BindView(R.id.ivPersonPhotoes)
    ImageView ivPersonPhotoes;
    boolean isllDNAProfileChecked = true;
    @BindView(R.id.mcvDNAProfile)
    MaterialCardView mcvDNAProfile;
    @BindView(R.id.llDNAProfile)
    LinearLayout llDNAProfile;
    @BindView(R.id.ivDNAProfile)
    ImageView ivDNAProfile;
    //input field
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.etFathersName)
    EditText etFathersName;
    @BindView(R.id.tvFathersName)
    TextView tvFathersName;
    @BindView(R.id.etSpouseName)
    EditText etSpouseName;
    @BindView(R.id.tvSpouseName)
    TextView tvSpouseName;
    @BindView(R.id.etNidNum)
    EditText etNidNum;
    @BindView(R.id.tvNidNum)
    TextView tvNidNum;
    @BindView(R.id.spnDocType)
    Spinner spnDocType;
    @BindView(R.id.tvDocType)
    TextView tvDocType;
    @BindView(R.id.spnNumberType)
    Spinner spnNumberType;
    @BindView(R.id.tvNumberType)
    TextView tvNumberType;
    @BindView(R.id.etNum)
    EditText etNum;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.spnReligion)
    Spinner spnReligion;
    @BindView(R.id.tvReligion)
    TextView tvReligion;

    @BindView(R.id.spnOcupation)
    Spinner spnOcupation;
    @BindView(R.id.spnRelationWith)
    Spinner spnRelationWith;
    @BindView(R.id.tvOcupation)
    TextView tvOcupation;
    @BindView(R.id.spnMaritalStatus)
    Spinner spnMaritalStatus;
    @BindView(R.id.tvMaritalStatus)
    TextView tvMaritalStatus;
    @BindView(R.id.spnHabits)
    Spinner spnHabits;
    @BindView(R.id.tvHabits)
    TextView tvHabits;
    @BindView(R.id.spnSpeech)
    Spinner spnSpeech;
    @BindView(R.id.tvSpeech)
    TextView tvSpeech;
    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    @BindView(R.id.tvccp)
    TextView tvccp;
    @BindView(R.id.spnSPDistrict)
    Spinner spnSPDistrict;
    @BindView(R.id.tvSPDistrict)
    TextView tvSPDistrict;
    @BindView(R.id.spnSPThana)
    Spinner spnSPThana;
    @BindView(R.id.tvSPThana)
    TextView tvSPThana;
    @BindView(R.id.etVillage)
    EditText etVillage;
    @BindView(R.id.tvVillage)
    TextView tvVillage;
    @BindView(R.id.etAddressDetails)
    EditText etAddressDetails;
    @BindView(R.id.tvAddressDetails)
    TextView tvAddressDetails;
    @BindView(R.id.spnAddressType)
    Spinner spnAddressType;
    @BindView(R.id.tvAddressType)
    TextView tvAddressType;
    @BindView(R.id.etOneLineAddress)
    EditText etOneLineAddress;
    @BindView(R.id.tvOneLineAddress)
    TextView tvOneLineAddress;
    @BindView(R.id.spnColor1)
    Spinner spnColor1;
    @BindView(R.id.spnEye)
    Spinner spnEye;
    @BindView(R.id.tvEye)
    TextView tvEye;
    @BindView(R.id.spnNose)
    Spinner spnNose;
    @BindView(R.id.tvNose)
    TextView tvNose;
    @BindView(R.id.spnHair)
    Spinner spnHair;
    @BindView(R.id.tvHair)
    TextView tvHair;
    @BindView(R.id.spnForhead)
    Spinner spnForhead;
    @BindView(R.id.tvForhead)
    TextView tvForhead;
    @BindView(R.id.spnBeard)
    Spinner spnBeard;
    @BindView(R.id.tvBeard)
    TextView tvBeard;
    @BindView(R.id.etWeight)
    EditText etWeight;
    @BindView(R.id.tvWeight)
    TextView tvWeight;
    @BindView(R.id.spnBodyStucture)
    Spinner spnBodyStucture;
    @BindView(R.id.tvBodyStucture)
    TextView tvBodyStucture;
    @BindView(R.id.spnFaceShape)
    Spinner spnFaceShape;
    @BindView(R.id.tvFaceShape)
    TextView tvFaceShape;
    @BindView(R.id.spnChin)
    Spinner spnChin;
    @BindView(R.id.tvChin)
    TextView tvChin;
    @BindView(R.id.spnSkinColor)
    Spinner spnSkinColor;
    @BindView(R.id.tvSkinColor)
    TextView tvSkinColor;
    @BindView(R.id.spnMustache)
    Spinner spnMustache;
    @BindView(R.id.tvMustache)
    TextView tvMustache;
    @BindView(R.id.spnEar)
    Spinner spnEar;
    @BindView(R.id.tvEar)
    TextView tvEar;
    @BindView(R.id.spnNeck)
    Spinner spnNeck;
    @BindView(R.id.tvNeck)
    TextView tvNeck;
    @BindView(R.id.etHeight_feet)
    EditText etHeight_feet;
    @BindView(R.id.tvHeight_feet)
    TextView tvHeight_feet;
    @BindView(R.id.etHeight_Inch)
    EditText etHeight_Inch;
    @BindView(R.id.tvHeight_Inch)
    TextView tvHeight_Inch;
    @BindView(R.id.etIdentityficationMark)
    EditText etIdentityficationMark;
    @BindView(R.id.tvIdentityficationMark)
    TextView tvIdentityficationMark;
    @BindView(R.id.spndescription_of_teeth)
    Spinner spndescription_of_teeth;
    @BindView(R.id.tvTeeth)
    TextView tvTeeth;
    @BindView(R.id.spnspecial_physical_description)
    Spinner spnspecial_physical_description;
    @BindView(R.id.tvSpecial_physical_description)
    TextView tvSpecial_physical_description;
    @BindView(R.id.spnDHead)
    Spinner spnDHead;
    @BindView(R.id.tvDHead)
    TextView tvDHead;
    @BindView(R.id.spnDHeadColor)
    Spinner spnDHeadColor;
    @BindView(R.id.tvDHeadColor)
    TextView tvDHeadColor;
    @BindView(R.id.spnDEye)
    Spinner spnDEye;
    @BindView(R.id.tvDEye)
    TextView tvDEye;
    @BindView(R.id.spnDEyeColor)
    Spinner spnDEyeColor;
    @BindView(R.id.tvDEyeColor)
    TextView tvDEyeColor;
    @BindView(R.id.spnDThroat)
    Spinner spnDThroat;
    @BindView(R.id.tvDThroat)
    TextView tvDThroat;
    @BindView(R.id.spnDThroatColor)
    Spinner spnDThroatColor;
    @BindView(R.id.tvDThroatColor)
    TextView tvDThroatColor;
    @BindView(R.id.spnDBody)
    Spinner spnDBody;
    @BindView(R.id.tvDBody)
    TextView tvDBody;
    @BindView(R.id.spnDBodyColor)
    Spinner spnDBodyColor;
    @BindView(R.id.tvDBodyColor)
    TextView tvDBodyColor;
    @BindView(R.id.spnDWaist)
    Spinner spnDWaist;
    @BindView(R.id.tvDWaist)
    TextView tvDWaist;
    @BindView(R.id.spnDWaistColor)
    Spinner spnDWaistColor;
    @BindView(R.id.spnDLeg)
    Spinner spnDLeg;
    @BindView(R.id.spnDLegColor)
    Spinner spnDLegColor;
    @BindView(R.id.tvDWaistColor)
    TextView tvDWaistColor;
    @BindView(R.id.tvPhotesType)
    TextView tvPhotesType;
    @BindView(R.id.tvPhotoesName)
    TextView tvPhotoesName;
    @BindView(R.id.spnSPDivision)
    Spinner spnSPDivision;
    @BindView(R.id.tvSPDivision)
    TextView tvSPDivision;
    @BindView(R.id.spnLostistrict)
    Spinner spnLostistrict;
    @BindView(R.id.tvLostistrict)
    TextView tvLostistrict;
    @BindView(R.id.spnLostThana)
    Spinner spnLostThana;
    @BindView(R.id.tvLostThana)
    TextView tvLostThana;
    @BindView(R.id.etLostVillage)
    EditText etLostVillage;
    @BindView(R.id.tvLostVillage)
    TextView tvLostVillage;
    @BindView(R.id.etLostAddressDetails)
    EditText etLostAddressDetails;

    //Color List
    @BindView(R.id.tvLostAddressDetails)
    TextView tvLostAddressDetails;
    @BindView(R.id.etVehicleDate)
    EditText etVehicleDate;
    @BindView(R.id.tvVehicleDate)
    TextView tvVehicleDate;
    @BindView(R.id.etVehicleTime)
    EditText etVehicleTime;
    @BindView(R.id.tvVehicleTime)
    TextView tvVehicleTime;
    @BindView(R.id.tvDLegSize)
    TextView tvDLegSize;
    @BindView(R.id.btnAddHabit)
    Button btnAddHabit;
    @BindView(R.id.lvHabit_list)
    RecyclerView lvHabit_list;
    @BindView(R.id.btnAddSpeech)
    Button btnAddSpeech;
    @BindView(R.id.lvSpeech_list)
    RecyclerView lvSpeech_list;
    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();
    //time picker
    int mHour, mMin, mSec;

    ArrayList<Thana> thanaArrayList = new ArrayList<>();

    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    String selectOne;
    //Address List
    ArrayList<AdreessList> addressListArrayList = new ArrayList<>();
    AddressListAdapter addressListAdapter;
    SpeechListAdapter speechListAdapter;
    HabitListAdapter habitListAdapter;
    //String[] lstHabit,lstSpeech;
    ArrayList<String> lstHabit = new ArrayList<String>();
    ArrayList<String> lstSpeech = new ArrayList<String>();
    @BindView(R.id.rvAddressList)
    RecyclerView rvAddressList;
    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.gv)
    GridView gvGallery;
    ProgressDialog progress;
    private GalleryAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_search);
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
        llPersonInfromation.setVisibility(View.VISIBLE);
        Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonInfromation);
        mcvPersonIdendityInformation.setVisibility(View.GONE);
        mcvPersonPhysical.setVisibility(View.GONE);
        mcvPersonAddress.setVisibility(View.GONE);
        mcvPersonLostPlace.setVisibility(View.GONE);
        mcvDressDescription.setVisibility(View.GONE);
        mcvPersonPhotoes.setVisibility(View.GONE);
        mcvDNAProfile.setVisibility(View.GONE);
        //date picker
        initializeVariables();
        selectOne = getResources().getString(R.string.select_option);
        intRecyclerView();
        this.setTitle(getResources().getText(R.string.missingpersonlevel));
        getMDPersonalInfo();
        getMDDressInfo();
        getMDGlobalInfo();
        getMDPhysicalInfo();

        spnDLeg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem != selectOne) {
                    tvDLegSize.setText(selectedItem + " " + getResources().getString(R.string.in_leg_text));
                }

            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ccp.setDefaultCountryUsingNameCode("BD");
        ccp.resetToDefaultCountry();

    }

    @OnClick(R.id.btnAddHabit)
    public void btnAddHabit() {
        counterHabit++;
        String habit = spnHabits.getSelectedItem().toString();
        try {
            lstHabit.add(counterHabit + ". " + habit);
            habitListAdapter.notifyDataSetChanged();

            for (int i = 0; i < lstSpeech.size(); i++) {
                Utilities.showLogcatMessage("notifyDataSetChanged " + lstHabit.get(i));
            }
        } catch (Exception e) {
            Utilities.showLogcatMessage("notifyDataSetChanged " + e.toString());
        }

    }

    @OnClick(R.id.btnAddSpeech)
    public void btnAddSpeech() {
        counterSpeech++;
        String habit = spnSpeech.getSelectedItem().toString();

        try {
            lstSpeech.add(counterSpeech + ". " + habit);
            speechListAdapter.notifyDataSetChanged();

            for (int i = 0; i < lstSpeech.size(); i++) {
                Utilities.showLogcatMessage("notifyDataSetChanged " + lstSpeech.get(i));
            }
        } catch (Exception e) {
            Utilities.showLogcatMessage("notifyDataSetChanged " + e.toString());
        }
    }

    public void intRecyclerView() {
        addressListAdapter = new AddressListAdapter(addressListArrayList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvAddressList.setLayoutManager(layoutManager);
        rvAddressList.setAdapter(addressListAdapter);


        speechListAdapter = new SpeechListAdapter(lstSpeech, this);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        lvSpeech_list.setLayoutManager(layoutManager1);
        lvSpeech_list.setAdapter(speechListAdapter);


        habitListAdapter = new HabitListAdapter(lstHabit, this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        lvHabit_list.setLayoutManager(layoutManager2);
        lvHabit_list.setAdapter(habitListAdapter);
    }

    @OnClick(R.id.btnAddAddress)
    public void AddressSave() {
        AdreessList adreessList = new AdreessList();
        try {
            // adreessList.setItemId(addressListArrayList.size()+1);
            adreessList.setItemId(addressListAdapter.getItemCount() + 1);
            adreessList.setCountryName("" + ccp.getSelectedCountryName());
            adreessList.setDistrictName("" + spnSPDistrict.getSelectedItem().toString());
            adreessList.setVillageName("" + spnSPThana.getSelectedItem().toString());
            adreessList.setVillageName("" + etVillage.getText().toString());
            adreessList.setAddressName("" + etAddressDetails.getText().toString());
            adreessList.setAddressType("" + spnAddressType.getSelectedItem().toString());
            adreessList.setOneLineAddress("" + etOneLineAddress.getText().toString());
        } catch (Exception e) {
            Utilities.showLogcatMessage("adreessList " + e.toString());
        }

        try {
            addressListArrayList.add(adreessList);
            //addressListAdapter.notifyItemInserted(addressListAdapter.getItemCount()+1);
            addressListAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Utilities.showLogcatMessage("notifyDataSetChanged " + e.toString());
        }

    }


//Date Picker

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

    public void getMDPersonalInfo() {

        progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<MDPersonalInformationModel> mdPersonalInformationModelCall = retrofitService.GetPersonalInformationMasterData();
        mdPersonalInformationModelCall.enqueue(new Callback<MDPersonalInformationModel>() {
            @Override
            public void onResponse(Call<MDPersonalInformationModel> call, Response<MDPersonalInformationModel> response) {

                if (response.body() != null) {
                    progress.dismiss();
                    addOccupationSpinnerData(response.body().getOccupations());
                    addRelationTypeSpinnerData(response.body().getRelationTypes());
                    addReligionSpinnerData(response.body().getReligions());
                    addMaritalStatusSpinnerData(response.body().getMaritalStatuses());
                    addHabitSpinnerData(response.body().getHabits());
                    addSpeechSpinnerData(response.body().getSpeeches());
                } else {
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MDPersonalInformationModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(MenSearchActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MenSearchActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MenSearchActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MenSearchActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addEyeSpinnerData(final List<EyeType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getChinTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getColorName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getConditionName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getConditionNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnspecial_physical_description.setAdapter(dataAdapter2);
        spnspecial_physical_description.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        } else {
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

    //InformationEntryActivity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    //DashBoard

    @OnClick(R.id.fab)
    public void fab() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();

    }


    public void addDistrictSpinnerData(final List<District> body) {
        List<String> districtList = new ArrayList<>();
        districtList.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                districtList.add(i + 1, body.get(i).getDistrictName());
            }
        } else {
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
                Toast.makeText(MenSearchActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void addThanaSpinnerData(final List<Thana> body) {
        List<String> thanaList = new ArrayList<>();
        thanaList.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                thanaList.add(i + 1, body.get(i).getThanaName());
            }
        } else {
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
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                districtList.add(i + 1, body.get(i).getDistrictName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                districtList.add(i + 1, body.get(i).getDistrictNameBn());
            }
        }
//        for (int i = 0; i < body.size(); i++) {
//            districtList.add(i + 1, body.get(i).getDistrictName());
//        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, districtList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLostistrict.setAdapter(dataAdapter2);
        spnLostistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_DISTRICT_ID_LP = body.get(i).getId();
                        getAllThanaLP(body.get(i - 1).getId());
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
                Toast.makeText(MenSearchActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addThanaSpinnerDataLP(final List<Thana> body) {
        List<String> thanaList = new ArrayList<>();
        thanaList.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                thanaList.add(i + 1, body.get(i).getThanaName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                thanaList.add(i + 1, body.get(i).getThanaNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, thanaList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLostThana.setAdapter(dataAdapter2);
        spnLostThana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    public void addRelationTypeSpinnerData(final List<RelationType> body) {
        List<String> relatinoTypeList = new ArrayList<>();
        relatinoTypeList.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                relatinoTypeList.add(i + 1, body.get(i).getRelationName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                relatinoTypeList.add(i + 1, body.get(i).getRelationNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, relatinoTypeList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRelationWith.setAdapter(dataAdapter2);
        spnRelationWith.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_RELATION_TYPE_ID = body.get(i).getId();
                    } else {
                        SELECTED_RELATION_TYPE_ID = 0;
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

    public void addOccupationSpinnerData(final List<Occupation> body) {
        List<String> occupationList = new ArrayList<>();
        occupationList.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                occupationList.add(i + 1, body.get(i).getName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                occupationList.add(i + 1, body.get(i).getNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, occupationList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOcupation.setAdapter(dataAdapter2);
        spnOcupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_OCCUPATION_ID = body.get(i).getId();
                    } else {
                        SELECTED_OCCUPATION_ID = 0;
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

    public void addReligionSpinnerData(final List<Religion> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getReligionName());
            }
        } else {
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

    public void addMaritalStatusSpinnerData(final List<MaritalStatus> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaritalStatus.setAdapter(dataAdapter2);
        spnMaritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_MARITAL_STATUS_ID = body.get(i).getId();
                    } else {
                        SELECTED_MARITAL_STATUS_ID = 0;
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

    public void addHabitSpinnerData(final List<Habit> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getHabitName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getHabitNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHabits.setAdapter(dataAdapter2);
        spnHabits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_HABIT_ID = body.get(i).getId();
                    } else {
                        SELECTED_HABIT_ID = 0;
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

    public void addSpeechSpinnerData(final List<Speech> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getSpeechName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getSpeechNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSpeech.setAdapter(dataAdapter2);
        spnSpeech.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_SPEECH_ID = body.get(i).getId();
                    } else {
                        SELECTED_SPEECH_ID = 0;
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


    //TIME AND dATE
    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback(MenSearchActivity.this)
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
                mArrayUri.add(resultUri);
                galleryAdapter = new GalleryAdapter(getApplicationContext(), mArrayUri);
                gvGallery.setAdapter(galleryAdapter);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


}
