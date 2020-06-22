package com.opus_bd.lostandfound.Activity.ENRTY;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.card.MaterialCardView;
import com.hbb20.CountryCodePicker;
import com.opus_bd.lostandfound.Activity.DASHBOARD.DashboardActivity;
import com.opus_bd.lostandfound.Adapter.CustomColorAdapter;
import com.opus_bd.lostandfound.Adapter.Extra.AddressListAdapter;
import com.opus_bd.lostandfound.Adapter.Extra.HabitListAdapter;
import com.opus_bd.lostandfound.Adapter.Extra.SpeechListAdapter;
import com.opus_bd.lostandfound.Adapter.GalleryAdapter;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.Gender;
import com.opus_bd.lostandfound.Model.Documentaion.Habit;
import com.opus_bd.lostandfound.Model.Documentaion.MDPersonalInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.MaritalStatus;
import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;
import com.opus_bd.lostandfound.Model.Documentaion.NumberType;
import com.opus_bd.lostandfound.Model.Documentaion.Occupation;
import com.opus_bd.lostandfound.Model.Documentaion.RelationType;
import com.opus_bd.lostandfound.Model.Documentaion.Religion;
import com.opus_bd.lostandfound.Model.Documentaion.Speech;
import com.opus_bd.lostandfound.Model.DressInfo.InTheBody;
import com.opus_bd.lostandfound.Model.DressInfo.InTheEye;
import com.opus_bd.lostandfound.Model.DressInfo.InTheHead;
import com.opus_bd.lostandfound.Model.DressInfo.InTheLeg;
import com.opus_bd.lostandfound.Model.DressInfo.InTheThroat;
import com.opus_bd.lostandfound.Model.DressInfo.InTheWaist;
import com.opus_bd.lostandfound.Model.DressInfo.MDDressInformationModel;
import com.opus_bd.lostandfound.Model.ExtraModel.AdreessList;
import com.opus_bd.lostandfound.Model.GDInfoModel.DNAProfileViewModel;
import com.opus_bd.lostandfound.Model.GDInfoModel.GDManInformationViewModel;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.Model.PhysicalInfo.AgePeriod;
import com.opus_bd.lostandfound.Model.PhysicalInfo.BeardType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.BodyChinType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.BodyColor;
import com.opus_bd.lostandfound.Model.PhysicalInfo.BodyStructure;
import com.opus_bd.lostandfound.Model.PhysicalInfo.EarType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.EyeType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.FaceShapeType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.ForeHeadType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.HairType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.MDPhysicalInformationModel;
import com.opus_bd.lostandfound.Model.PhysicalInfo.MoustacheType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.NeckType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.NoseType;
import com.opus_bd.lostandfound.Model.PhysicalInfo.SpecialBirthMarkBodyPart;
import com.opus_bd.lostandfound.Model.PhysicalInfo.SpecialBirthMarkBodyPartPosition;
import com.opus_bd.lostandfound.Model.PhysicalInfo.SpecialBirthMarkType;
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

public class UnknownManActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.llInput)
    LinearLayout llInput;
    @BindView(R.id.llReport)
    LinearLayout llReport;
    @BindView(R.id.mcvReport)
    MaterialCardView mcvReport;
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

    public Integer SELECTED_GENDER_ID, SELECTED_DOC_TYPE_ID, SELECTED_NUMBER_TYPE_ID;

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
    @BindView(R.id.spnGender)
    Spinner spnGender;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.spnBloodGroup)
    Spinner spnBloodGroup;
    @BindView(R.id.tvBloodGroup)
    TextView tvBloodGroup;
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

//    @BindView(R.id.tvHeight)
//    TextView tvHeight;
//    @BindView(R.id.tvColor)
//    TextView tvColor;

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
    public Integer SELECTED_AGE_PERIOD_ID, SELECTED_EYE_TYPE_ID, SELECTED_NOSE_TYPE_ID, SELECTED_HAIR_TYPE_ID, SELECTED_FOREHEAD_TYPE_ID, SELECTED_BEARD_TYPE_ID,
            SELECTED_PHYSICAL_CONDITION_ID, SELECTED_FACE_SHAPE_ID, SELECTED_BODY_CHIN_ID, SELECTED_BODY_COLOR_ID, SELECTED_MOUSTACE_ID, SELECTED_EAR_ID,
            SELECTED_NECK_ID, SELECTED_TEETH_ID, SELECTED_BODY_STRUCTURE_ID, SELECTED_BIRTH_MARK_ID, SELECTED_BIRTH_MARK_BODY_PART_ID, SELECTED_BIRTH_MARK_BODY_PART_POSITION_ID;
    public Integer SELECTED_INBODY_ID, SELECTED_INHEAD_ID, SELECTED_INEYE_ID, SELECTED_INTHROAT_ID, SELECTED_INWAIST_ID, SELECTED_INLEGS_ID,
            SELECTED_DBODY_COLOR_ID, SELECTED_DHEAD_COLOR_ID, SELECTED_DEYE_COLOR_ID, SELECTED_DTHROAT_COLOR_ID, SELECTED_DWAIST_COLOR_ID, SELECTED_DLEG_COLOR_ID;
    public Integer SELECTED_DISTRICT_ID;
    @BindView(R.id.etMotherName)
    EditText etMotherName;

    @BindView(R.id.spnDHead)
    Spinner spnDHead;
    @BindView(R.id.spnBirtMarkType)
    Spinner spnBirtMarkType;
    @BindView(R.id.spnBirtMartBodyPart)
    Spinner spnBirtMartBodyPart;

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
    @BindView(R.id.spnBirthMarkBodyPosition)
    Spinner spnBirthMarkBodyPosition;

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
    public Integer SELECTED_DISTRICT_ID_LP;
    public Integer SELECTED_THANA_ID;
    public Integer SELECTED_THANA_ID_LP;
    public Integer SELECTED_OCCUPATION_ID;
    public Integer SELECTED_RELATION_TYPE_ID;
    public Integer SELECTED_RELIGION_ID;
    public Integer SELECTED_MARITAL_STATUS_ID;
    public Integer SELECTED_HABIT_ID;
    public Integer SELECTED_SPEECH_ID;
    @BindView(R.id.etDLegMes)
    EditText etDLegMes;
    //DNA Profile
    @BindView(R.id.tvD3S1358)
    TextView tvD3S1358;
    @BindView(R.id.etD3S13581)
    EditText etD3S13581;
    @BindView(R.id.etD3S13582)
    EditText etD3S13582;
    @BindView(R.id.tvvWA)
    TextView tvvWA;
    @BindView(R.id.etvWA1)
    EditText etvWA1;
    @BindView(R.id.etvWA2)
    EditText etvWA2;
    @BindView(R.id.tvD16S539)
    TextView tvD16S539;
    @BindView(R.id.etD16S5391)
    EditText etD16S5391;
    @BindView(R.id.etD16S5392)
    EditText etD16S5392;
    @BindView(R.id.tvCSF1PO)
    TextView tvCSF1PO;
    @BindView(R.id.etCSF1PO)
    EditText etCSF1PO;
    @BindView(R.id.tvTPOX)
    TextView tvTPOX;
    @BindView(R.id.etTPOX1)
    EditText etTPOX1;
    @BindView(R.id.etTPOX2)
    EditText etTPOX2;
    @BindView(R.id.tvYindel)
    TextView tvYindel;
    @BindView(R.id.etYindel)
    EditText etYindel;
    @BindView(R.id.tvAmelogenin)
    TextView tvAmelogenin;
    @BindView(R.id.etAmelogenin1)
    EditText etAmelogenin1;
    @BindView(R.id.etAmelogenin2)
    EditText etAmelogenin2;
    @BindView(R.id.tvD8S1179)
    TextView tvD8S1179;
    @BindView(R.id.etD8S11791)
    EditText etD8S11791;
    @BindView(R.id.etD8S11792)
    EditText etD8S11792;
    @BindView(R.id.tvD21S11)
    TextView tvD21S11;
    @BindView(R.id.etD21S111)
    EditText etD21S111;
    @BindView(R.id.etD21S112)
    EditText etD21S112;
    @BindView(R.id.tvD18S51)
    TextView tvD18S51;
    @BindView(R.id.etD18S511)
    EditText etD18S511;
    @BindView(R.id.etD18S512)
    EditText etD18S512;
    @BindView(R.id.tvDYS391)
    TextView tvDYS391;
    @BindView(R.id.etDYS391)
    EditText etDYS391;
    @BindView(R.id.tvD2S441)
    TextView tvD2S441;
    @BindView(R.id.etD2S4411)
    EditText etD2S4411;
    @BindView(R.id.etD2S4412)
    EditText etD2S4412;
    @BindView(R.id.tvD19S433)
    TextView tvD19S433;
    @BindView(R.id.etD19S4331)
    EditText etD19S4331;
    @BindView(R.id.etD19S4332)
    EditText etD19S4332;
    @BindView(R.id.tvTH01)
    TextView tvTH01;
    @BindView(R.id.etTH011)
    EditText etTH011;
    @BindView(R.id.etTH012)
    EditText etTH012;
    @BindView(R.id.tvFGA)
    TextView tvFGA;
    @BindView(R.id.etFGA1)
    EditText etFGA1;
    @BindView(R.id.etFGA2)
    EditText etFGA2;
    @BindView(R.id.tvD22S1045)
    TextView tvD22S1045;
    @BindView(R.id.etD22S10451)
    EditText etD22S10451;

    //date picker
    SimpleDateFormat formatter;
    int mYear, mMonth, mDay;
    Calendar calendar = Calendar.getInstance();

    //time picker
    int mHour, mMin, mSec;

    //Color List

    List<String> ColorName;
    List<String> ColorCode;
    ArrayList<Thana> thanaArrayList = new ArrayList<>();
    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    @BindView(R.id.etD22S10452)
    EditText etD22S10452;
    @BindView(R.id.tvD5S818)
    TextView tvD5S818;
    @BindView(R.id.etD5S8181)
    EditText etD5S8181;
    @BindView(R.id.etD5S8182)
    EditText etD5S8182;
    @BindView(R.id.tvD13S317)
    TextView tvD13S317;
    @BindView(R.id.etD13S3171)
    EditText etD13S3171;
    @BindView(R.id.etD13S3172)
    EditText etD13S3172;
    @BindView(R.id.tvD7S820)
    TextView tvD7S820;
    @BindView(R.id.etD7S8201)
    EditText etD7S8201;
    @BindView(R.id.etD7S8202)
    EditText etD7S8202;
    @BindView(R.id.spnAgeperiod)
    Spinner spnAgeperiod;
    @BindView(R.id.etVisibleTattoos)
    EditText etVisibleTattoos;
    @BindView(R.id.spnDLegMT)
    Spinner spnDLegMT;
    String selectOne;
    ArrayList<Colors> colorArrayList = new ArrayList<>();


    //Address List
    ArrayList<AdreessList> addressListArrayList = new ArrayList<>();
    AddressListAdapter addressListAdapter;
    SpeechListAdapter speechListAdapter;
    HabitListAdapter habitListAdapter;

    //String[] lstHabit,lstSpeech;
    ArrayList<String> lstHabit = new ArrayList<String>();
    ArrayList<String> lstSpeech = new ArrayList<String>();
    ArrayList<String> lstDocument = new ArrayList<String>();
    ArrayList<DNAProfileViewModel> dnaProfileViewModels = new ArrayList<DNAProfileViewModel>();

    @BindView(R.id.rvAddressList)
    RecyclerView rvAddressList;
    @BindView(R.id.ivImage)
    ImageView ivImage;@BindView(R.id.gv)
    GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    ProgressDialog progress;
    public String Language,english,bangla;
    public  int counterHabit,counterSpeech=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unknown_man);
        ButterKnife.bind(this);
        Boolean languageStatus = getSharedPrefValue();
        english=getResources().getString(R.string.english);
        bangla=getResources().getString(R.string.bangla);
        if (languageStatus) {
            Language=english;
        } else {
            Language=bangla;
        }
        //setProgress();
        mcvReport.setVisibility(View.GONE);
        llPersonInfromation.setVisibility(View.GONE);
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

//        getMDPersonalInfo();
//        getMDDressInfo();
//        getMDGlobalInfo();
//        getMDPhysicalInfo();

        spnDLeg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem!=selectOne){
                    tvDLegSize.setText(selectedItem+" "+ getResources().getString(R.string.in_leg_text));
                }

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        ccp.setDefaultCountryUsingNameCode("BD");
        ccp.resetToDefaultCountry();

    }

    @OnClick(R.id.btnAddHabit)
    public void btnAddHabit() {
        counterHabit++;
        String habit=spnHabits.getSelectedItem().toString();
        try {
            lstHabit.add(counterHabit+". "+ habit);
            //addressListAdapter.notifyItemInserted(addressListAdapter.getItemCount()+1);

            habitListAdapter.notifyDataSetChanged();

            for (int i = 0; i < lstHabit.size(); i++) {
                Utilities.showLogcatMessage("notifyDataSetChanged " + lstHabit.get(i));}
        } catch (Exception e) {
            Utilities.showLogcatMessage("notifyDataSetChanged " + e.toString());
        }

    }

    @OnClick(R.id.btnAddSpeech)
    public void btnAddSpeech() {
        counterSpeech++;
        String habit=spnSpeech.getSelectedItem().toString();

        try {
            lstSpeech.add(counterSpeech+". "+ habit);
            //addressListAdapter.notifyItemInserted(addressListAdapter.getItemCount()+1);

            speechListAdapter.notifyDataSetChanged();

            for(int i=0;i<lstSpeech.size();i++){
                Utilities.showLogcatMessage("notifyDataSetChanged " + lstSpeech.get(i));}
        } catch (Exception e) {
            Utilities.showLogcatMessage("notifyDataSetChanged " + e.toString());
        }


        //lvSpeech_list.setAdapter(speechListAdapter);

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
            adreessList.setItemId(addressListAdapter.getItemCount()+1);
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

    @OnClick(R.id.ivTPersonInfromation)
    public void ivTPersonInfromation() {
        if (isllPersonInfromationChecked) {
            // show password
            llPersonInfromation.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonInfromation);
            isllPersonInfromationChecked = false;
        } else {
            // hide password
            llPersonInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonInfromation);
            isllPersonInfromationChecked = true;
        }

    }


    @OnClick({R.id.ivTPersonIdentification, R.id.btnNext1})
    public void ivTPersonIdentification() {
        if (isllPersonIdentificationChecked) {
            // show password
            llPersonInfromation.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonInfromation);
            isllPersonInfromationChecked = true;

            mcvPersonIdendityInformation.setVisibility(View.VISIBLE);
            llPersonIdentification.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonIdentification);
            isllPersonIdentificationChecked = false;

        } else {
            // hide password
            llPersonIdentification.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonIdentification);
            isllPersonIdentificationChecked = true;
        }

    }


    @OnClick({R.id.ivTPersonAddress, R.id.btnNext2})
    public void ivTPersonAddress() {
        if (isllPersonAddressChecked) {
            // show password
            llPersonIdentification.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonIdentification);
            isllPersonIdentificationChecked = true;

            mcvPersonAddress.setVisibility(View.VISIBLE);
            llPersonAddress.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonAddress);
            isllPersonAddressChecked = false;

        } else {
            // hide password
            llPersonAddress.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonAddress);
            isllPersonAddressChecked = true;
        }

    }


    @OnClick({R.id.ivTPersonPhysical, R.id.btnNext3})
    public void ivTPersonPhysical() {
        if (isllPersonPhysicalChecked) {
            // show password
            llPersonAddress.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonAddress);
            isllPersonAddressChecked = true;

            mcvPersonPhysical.setVisibility(View.VISIBLE);
            llPersonPhysical.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonPhysical);
            isllPersonPhysicalChecked = false;

        } else {
            // hide password
            llPersonPhysical.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonPhysical);
            isllPersonPhysicalChecked = true;
        }

    }


    @OnClick({R.id.ivDressDescription, R.id.btnNext4})
    public void ivDressDescription() {
        if (isllDressDescriptionChecked) {
            // show password
            llPersonPhysical.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonPhysical);
            isllPersonPhysicalChecked = true;

            mcvDressDescription.setVisibility(View.VISIBLE);
            llDressDescription.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivDressDescription);
            isllDressDescriptionChecked = false;

        } else {
            // hide password
            llDressDescription.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDressDescription);
            isllDressDescriptionChecked = true;
        }

    }

    @OnClick({R.id.ivPersonPhotoes, R.id.btnNextDressDes})
    public void ivPersonPhotoes() {
        if (isllllPersonPhotoesChecked) {
            // show password
            llDressDescription.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDressDescription);
            isllDressDescriptionChecked = true;

            mcvPersonPhotoes.setVisibility(View.VISIBLE);
            llPersonPhotoes.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivPersonPhotoes);
            isllllPersonPhotoesChecked = false;

        } else {
            // hide password
            llPersonPhotoes.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivPersonPhotoes);
            isllllPersonPhotoesChecked = true;
        }

    }

    @OnClick({R.id.ivPersonLostPlace, R.id.btnNextPhotoes})
    public void ivTPersonLostPlace() {
        if (isllPersonLostPlaceChecked) {
            // show password
            llPersonPhotoes.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivPersonPhotoes);
            isllllPersonPhotoesChecked = true;

            mcvPersonLostPlace.setVisibility(View.VISIBLE);
            llPersonLostPlace.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivTPersonLostPlace);
            isllPersonLostPlaceChecked = false;

        } else {
            // hide password
            llPersonLostPlace.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonLostPlace);
            isllPersonLostPlaceChecked = true;
        }

    }
    @OnClick({R.id.ivDNAProfile, R.id.btnNext5})
    public void ivDNAProfile() {
        if (isllDNAProfileChecked) {
            // show password
            llPersonLostPlace.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivTPersonLostPlace);
            isllPersonLostPlaceChecked = true;

            mcvDNAProfile.setVisibility(View.VISIBLE);
            llDNAProfile.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.ic_drop_up).into(ivDNAProfile);
            isllDNAProfileChecked = false;

        } else {
            // hide password
            llDNAProfile.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.ic_drop_down).into(ivDNAProfile);
            isllDNAProfileChecked = true;
        }

    }

    public void setProgress() {
        progress = new ProgressDialog(this);
        progress.setMessage("Loading.... ");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
    }

    public void getMDPersonalInfo() {

        //progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<MDPersonalInformationModel> mdPersonalInformationModelCall = retrofitService.GetPersonalInformationMasterData();
        mdPersonalInformationModelCall.enqueue(new Callback<MDPersonalInformationModel>() {
            @Override
            public void onResponse(Call<MDPersonalInformationModel> call, Response<MDPersonalInformationModel> response) {

                if (response.body() != null) {
                    //progress.dismiss();
                    addOccupationSpinnerData(response.body().getOccupations());
                    addRelationTypeSpinnerData(response.body().getRelationTypes());
                    addReligionSpinnerData(response.body().getReligions());
                    addMaritalStatusSpinnerData(response.body().getMaritalStatuses());
                    addHabitSpinnerData(response.body().getHabits());
                    addSpeechSpinnerData(response.body().getSpeeches());
                    addGenderSpinnerData(response.body().getGenders());
                    addNumberTypeSpinnerData(response.body().getNumberTypes());
                } else {
                    //progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MDPersonalInformationModel> call, Throwable t) {
                //progress.dismiss();
                Toast.makeText(UnknownManActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getMDGlobalInfo() {

        //progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<VehicleMasterModel> registrationRequest = retrofitService.GetVehicleMasterData();
        registrationRequest.enqueue(new Callback<VehicleMasterModel>() {
            @Override
            public void onResponse(Call<VehicleMasterModel> call, Response<VehicleMasterModel> response) {

                if (response.body() != null) {
                    //progress.dismiss();

                    addDistrictSpinnerData(response.body().getDistricts());
                    addDistrictSpinnerDataLP(response.body().getDistricts());
                    addColorSpinnerData(response.body().getColors());
                    addNationalIdentitySpinnerData(response.body().getNationalIdentityTypesModels());


                } else {
                    //progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<VehicleMasterModel> call, Throwable t) {
                //progress.dismiss();
                Toast.makeText(UnknownManActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getMDPhysicalInfo() {
        //progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<MDPhysicalInformationModel> mdPersonalInformationModelCall = retrofitService.GetPhysicalInformationMasterData();
        mdPersonalInformationModelCall.enqueue(new Callback<MDPhysicalInformationModel>() {
            @Override
            public void onResponse(Call<MDPhysicalInformationModel> call, Response<MDPhysicalInformationModel> response) {

                if (response.body() != null) {
                    //progress.dismiss();
                    addAgeSpinnerData(response.body().getAgePeriods());
                    addEyeSpinnerData(response.body().getEyeTypes());
                    addNoseSpinnerData(response.body().getNoseTypes());
                    addHairSpinnerData(response.body().getHairTypes());
                    addForeHeadSpinnerData(response.body().getForeHeadTypes());
                    addBeardSpinnerData(response.body().getBeardTypes());
                    addBodyStuctureSpinnerData(response.body().getBodyStructures());
                    addFaceShapeSpinnerData(response.body().getFaceShapeTypes());
                    addBodyChinSpinnerData(response.body().getBodyChinTypes());
                    addBodyColorSpinnerData(response.body().getBodyColors());
                    addMustaceSpinnerData(response.body().getMoustacheTypes());
                    addEarsSpinnerData(response.body().getEarTypes());
                    addNeckSpinnerData(response.body().getNeckTypes());
                    addTeethSpinnerData(response.body().getTeethTypes());
                    addSpecialBodyConditionSpinnerData(response.body().getSpecialBodyConditions());
                    addBirthMarkTypeSpinnerData(response.body().getSpecialBirthMarkTypes());
                    addBirthMarkBodyPartSpinnerData(response.body().getSpecialBirthMarkBodyParts());
                    addBirthMarkBodyPositionSpinnerData(response.body().getSpecialBirthMarkBodyPartPositions());
                } else {
                    //progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MDPhysicalInformationModel> call, Throwable t) {
                //progress.dismiss();
                Toast.makeText(UnknownManActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getMDDressInfo() {
        //progress.show();
        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<MDDressInformationModel> mdPersonalInformationModelCall = retrofitService.GetDressInformationMasterData();
        mdPersonalInformationModelCall.enqueue(new Callback<MDDressInformationModel>() {
            @Override
            public void onResponse(Call<MDDressInformationModel> call, Response<MDDressInformationModel> response) {

                if (response.body() != null) {
                    //progress.dismiss();
                    addInHeadSpinnerData(response.body().getInTheHeads());
                    addInEyeSpinnerData(response.body().getInTheEyes());
                    addInBodySpinnerData(response.body().getInTheBodies());
                    addInThroadSpinnerData(response.body().getInTheThroats());
                    addInLegsSpinnerData(response.body().getInTheLegs());
                    addInWestSpinnerData(response.body().getInTheWaists());

                } else {
                    //progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<MDDressInformationModel> call, Throwable t) {
                //progress.dismiss();
                Toast.makeText(UnknownManActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addNationalIdentitySpinnerData(final List<NationalIdentityTypesModel> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getNationalIdentityName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getNationalIdentityNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDocType.setAdapter(dataAdapter2);
        spnDocType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_DOC_TYPE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_DOC_TYPE_ID = null;
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

    public void addNumberTypeSpinnerData(final List<NumberType> body) {
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
        spnNumberType.setAdapter(dataAdapter2);
        spnNumberType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_NUMBER_TYPE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_NUMBER_TYPE_ID = null;
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

    public void addGenderSpinnerData(final List<Gender> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getGenderName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getGenderNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGender.setAdapter(dataAdapter2);
        spnGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_GENDER_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_GENDER_ID = 0;
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

    public void addAgeSpinnerData(final List<AgePeriod> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getPeriodName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getPeriodNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAgeperiod.setAdapter(dataAdapter2);
        spnAgeperiod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_AGE_PERIOD_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_AGE_PERIOD_ID = null;
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

    public void addEyeSpinnerData(final List<EyeType> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
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
                        SELECTED_EYE_TYPE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_EYE_TYPE_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
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
                        SELECTED_NOSE_TYPE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_NOSE_TYPE_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
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
                        SELECTED_HAIR_TYPE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_HAIR_TYPE_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
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
                        SELECTED_FOREHEAD_TYPE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_FOREHEAD_TYPE_ID = null;
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

    public void addBodyStuctureSpinnerData(final List<BodyStructure> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getStructureName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getStructureNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBodyStucture.setAdapter(dataAdapter2);
        spnBodyStucture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_BODY_STRUCTURE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_BODY_STRUCTURE_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
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
                        SELECTED_BEARD_TYPE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_BEARD_TYPE_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
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
                        SELECTED_FACE_SHAPE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_FACE_SHAPE_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getChinTypeName());
            }
        }else {
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
                        SELECTED_BODY_CHIN_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_BODY_CHIN_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getColorName());
            }
        }else {
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
                        SELECTED_BODY_COLOR_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_BODY_COLOR_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
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
                        SELECTED_MOUSTACE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_MOUSTACE_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
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
                        SELECTED_EAR_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_EAR_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
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
                        SELECTED_NECK_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_NECK_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getTypeName());
            }
        }else {
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
                        SELECTED_TEETH_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_TEETH_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getConditionName());
            }
        }else {
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
                        SELECTED_PHYSICAL_CONDITION_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_PHYSICAL_CONDITION_ID = null;
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

    public void addBirthMarkTypeSpinnerData(final List<SpecialBirthMarkType> body) {
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
        spnBirtMarkType.setAdapter(dataAdapter2);
        spnBirtMarkType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_BIRTH_MARK_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_BIRTH_MARK_ID = null;
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

    public void addBirthMarkBodyPartSpinnerData(final List<SpecialBirthMarkBodyPart> body) {
        List<String> lstData = new ArrayList<>();
        lstData.add(0, selectOne);
        if (Language == english) {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getBodyName());
            }
        } else {
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getBodyNameBn());
            }
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lstData);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBirtMartBodyPart.setAdapter(dataAdapter2);
        spnBirtMartBodyPart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_BIRTH_MARK_BODY_PART_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_BIRTH_MARK_BODY_PART_ID = null;
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

    public void addBirthMarkBodyPositionSpinnerData(final List<SpecialBirthMarkBodyPartPosition> body) {
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
        spnBirthMarkBodyPosition.setAdapter(dataAdapter2);
        spnBirthMarkBodyPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_BIRTH_MARK_BODY_PART_POSITION_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_BIRTH_MARK_BODY_PART_POSITION_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
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
                        SELECTED_INBODY_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_INBODY_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
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
                        SELECTED_INHEAD_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_INHEAD_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
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
                        SELECTED_INTHROAT_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_INTHROAT_ID = null;
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

    public void addInEyeSpinnerData(final List<InTheEye> body) {
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
        spnDEye.setAdapter(dataAdapter2);
        spnDEye.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_INEYE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_INEYE_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
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
                        SELECTED_INWAIST_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_INWAIST_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
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
                        SELECTED_INLEGS_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_INLEGS_ID = null;
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
                    SELECTED_DEYE_COLOR_ID = body.get(i - 1).getId();
                } else {
                    SELECTED_DEYE_COLOR_ID = null;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnDEyeColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_DEYE_COLOR_ID = body.get(i - 1).getId();
                } else {
                    SELECTED_DEYE_COLOR_ID = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnDHeadColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_DHEAD_COLOR_ID = body.get(i - 1).getId();
                } else {
                    SELECTED_DHEAD_COLOR_ID = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnDWaistColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_DWAIST_COLOR_ID = body.get(i - 1).getId();
                } else {
                    SELECTED_DWAIST_COLOR_ID = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnDThroatColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_DTHROAT_COLOR_ID = body.get(i - 1).getId();
                } else {
                    SELECTED_DTHROAT_COLOR_ID = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnDLegColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 1) {
                    SELECTED_DLEG_COLOR_ID = body.get(i - 1).getId();
                } else {
                    SELECTED_DLEG_COLOR_ID = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void customDialog() {
        final Dialog dialog = new Dialog(UnknownManActivity.this);
        dialog.setContentView(R.layout.dialog_custom_code_generator);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        // set the custom dialog components - text, image and button
        Button btnThanks = (Button) dialog.findViewById(R.id.btnThanks);


        btnThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Intent intent = new Intent(UnknownManActivity.this, DashboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Toast.makeText(dialog, "Please install browser to continue", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialog.show();
    }

    @OnClick(R.id.btnDNA)
    public void ReportShown() {
        llInput.setVisibility(View.GONE);
        mcvReport.setVisibility(View.VISIBLE);
        try {
            tvName.setText(etName.getText().toString());
            tvFathersName.setText(etFathersName.getText().toString());
            tvSpouseName.setText(etSpouseName.getText().toString());
            tvAge.setText(etAge.getText().toString());
            tvNidNum.setText(etNidNum.getText().toString());
            tvDocType.setText(spnDocType.getSelectedItem().toString());
            tvNumberType.setText(spnNumberType.getSelectedItem().toString());
            tvNum.setText(etNum.getText().toString());

            tvReligion.setText(spnReligion.getSelectedItem().toString());
            tvGender.setText(spnGender.getSelectedItem().toString());
            tvBloodGroup.setText(spnBloodGroup.getSelectedItem().toString());
            tvMaritalStatus.setText(spnMaritalStatus.getSelectedItem().toString());
            tvOcupation.setText(spnOcupation.getSelectedItem().toString());
            tvHabits.setText(spnHabits.getSelectedItem().toString());
            tvSpeech.setText(spnSpeech.getSelectedItem().toString());

            tvccp.setText(ccp.getSelectedCountryName());
            tvSPDistrict.setText(spnSPDistrict.getSelectedItem().toString());
            tvSPThana.setText(spnSPThana.getSelectedItem().toString());
            tvVillage.setText(etVillage.getText().toString());
            tvAddressDetails.setText(etAddressDetails.getText().toString());
            tvAddressType.setText(spnAddressType.getSelectedItem().toString());
            tvOneLineAddress.setText(etOneLineAddress.getText().toString());

            tvEye.setText(spnEye.getSelectedItem().toString());
            tvNose.setText(spnNose.getSelectedItem().toString());
            tvHair.setText(spnHair.getSelectedItem().toString());
            tvForhead.setText(spnForhead.getSelectedItem().toString());
            tvBeard.setText(spnBeard.getSelectedItem().toString());
            tvWeight.setText(etWeight.getText().toString());
            tvBodyStucture.setText(spnBodyStucture.getSelectedItem().toString());
            tvFaceShape.setText(spnFaceShape.getSelectedItem().toString());
            tvChin.setText(spnChin.getSelectedItem().toString());
            tvSkinColor.setText(spnSkinColor.getSelectedItem().toString());
            tvMustache.setText(spnMustache.getSelectedItem().toString());
            tvMustache.setText(spnEar.getSelectedItem().toString());
            tvNeck.setText(spnNeck.getSelectedItem().toString());
            tvHeight_feet.setText(etHeight_feet.getText().toString());
            tvHeight_Inch.setText(etHeight_Inch.getText().toString());
            tvIdentityficationMark.setText(etIdentityficationMark.getText().toString());
            tvTeeth.setText(spndescription_of_teeth.getSelectedItem().toString());
            tvSpecial_physical_description.setText(spnspecial_physical_description.getSelectedItem().toString());
            //tvHeight.setText(spnHeight_feet.getSelectedItem().toString());
            //tvColor.setText(etColor.getText().toString());

            tvDHead.setText(spnDHead.getSelectedItem().toString());
            tvDHeadColor.setText(spnDHeadColor.getSelectedItem().toString());
            tvDEye.setText(tvDEye.getText().toString());
            tvDEyeColor.setText(spnDEyeColor.getSelectedItem().toString());
            tvDThroat.setText(spnDThroat.getSelectedItem().toString());
            tvDThroatColor.setText(spnDThroatColor.getSelectedItem().toString());
            tvDBody.setText(spnDBody.getSelectedItem().toString());
            tvDBodyColor.setText(spnDBodyColor.getSelectedItem().toString());
            tvDWaist.setText(spnDWaist.getSelectedItem().toString());
            tvDWaistColor.setText(spnDWaistColor.getSelectedItem().toString());
          /*  tvPhotesType.setText(spnPhotesType.getSelectedItem().toString());
            tvPhotoesName.setText(etPhotoesName.getText().toString());*/

            tvLostistrict.setText(spnLostistrict.getSelectedItem().toString());
            tvLostistrict.setText(spnLostThana.getSelectedItem().toString());
            tvLostVillage.setText(etLostVillage.getText().toString());
            tvLostAddressDetails.setText(etLostAddressDetails.getText().toString());
            tvVehicleDate.setText(etVehicleDate.getText().toString());
            tvVehicleTime.setText(etVehicleTime.getText().toString());

            DNAProfileViewModel model = new DNAProfileViewModel();
            model.setLocous(tvD3S1358.getText().toString());
            model.setGenotype1(etD3S13581.getText().toString());
            model.setGenotype2(etD3S13582.getText().toString());

            dnaProfileViewModels.add(model);


            DNAProfileViewModel model1 = new DNAProfileViewModel();
            model.setLocous(tvvWA.getText().toString());
            model.setGenotype1(etvWA1.getText().toString());
            model.setGenotype2(etvWA2.getText().toString());

            dnaProfileViewModels.add(model1);


            DNAProfileViewModel model2 = new DNAProfileViewModel();
            model.setLocous(tvD16S539.getText().toString());
            model.setGenotype1(etD16S5391.getText().toString());
            model.setGenotype2(etD16S5392.getText().toString());

            dnaProfileViewModels.add(model2);


            DNAProfileViewModel model3 = new DNAProfileViewModel();
            model.setLocous(tvCSF1PO.getText().toString());
            model.setGenotype1(etCSF1PO.getText().toString());
            model.setGenotype2("");

            dnaProfileViewModels.add(model3);


            DNAProfileViewModel model4 = new DNAProfileViewModel();
            model.setLocous(tvTPOX.getText().toString());
            model.setGenotype1(etTPOX1.getText().toString());
            model.setGenotype2(etTPOX2.getText().toString());
            if (etTPOX1.getText().toString() != "") {
                dnaProfileViewModels.add(model4);
            }

            DNAProfileViewModel model5 = new DNAProfileViewModel();
            model.setLocous(tvYindel.getText().toString());
            model.setGenotype1(etYindel.getText().toString());
            model.setGenotype2("");
            if (tvYindel.getText().toString() != "") {
                dnaProfileViewModels.add(model5);
            }

            DNAProfileViewModel model6 = new DNAProfileViewModel();
            model.setLocous(tvAmelogenin.getText().toString());
            model.setGenotype1(etAmelogenin1.getText().toString());
            model.setGenotype2(etAmelogenin2.getText().toString());
            if (etAmelogenin1.getText().toString() != "") {
                dnaProfileViewModels.add(model6);
            }

            DNAProfileViewModel model7 = new DNAProfileViewModel();
            model.setLocous(tvD8S1179.getText().toString());
            model.setGenotype1(etD8S11791.getText().toString());
            model.setGenotype2(etD8S11792.getText().toString());
            if (etD8S11791.getText().toString() != "") {
                dnaProfileViewModels.add(model7);
            }

            DNAProfileViewModel model8 = new DNAProfileViewModel();
            model.setLocous(tvD21S11.getText().toString());
            model.setGenotype1(etD21S111.getText().toString());
            model.setGenotype2(etD21S112.getText().toString());
            if (etD21S111.getText().toString() != "") {
                dnaProfileViewModels.add(model8);
            }

            DNAProfileViewModel model9 = new DNAProfileViewModel();
            model.setLocous(tvD18S51.getText().toString());
            model.setGenotype1(etD18S511.getText().toString());
            model.setGenotype2(etD18S512.getText().toString());
            if (etD18S511.getText().toString() != "") {
                dnaProfileViewModels.add(model9);
            }

            DNAProfileViewModel model10 = new DNAProfileViewModel();
            model.setLocous(tvDYS391.getText().toString());
            model.setGenotype1(etDYS391.getText().toString());
            model.setGenotype2("");
            if (etDYS391.getText().toString() != "") {
                dnaProfileViewModels.add(model10);
            }

            DNAProfileViewModel model11 = new DNAProfileViewModel();
            model.setLocous(tvD2S441.getText().toString());
            model.setGenotype1(etD2S4411.getText().toString());
            model.setGenotype2(etD2S4412.getText().toString());
            if (etD2S4411.getText().toString() != "") {
                dnaProfileViewModels.add(model11);
            }

            DNAProfileViewModel model12 = new DNAProfileViewModel();
            model.setLocous(tvD19S433.getText().toString());
            model.setGenotype1(etD19S4331.getText().toString());
            model.setGenotype2(etD19S4332.getText().toString());
            if (etD19S4331.getText().toString() != "") {
                dnaProfileViewModels.add(model12);
            }

            DNAProfileViewModel model13 = new DNAProfileViewModel();
            model.setLocous(tvTH01.getText().toString());
            model.setGenotype1(etTH011.getText().toString());
            model.setGenotype2(etTH012.getText().toString());
            if (etTH011.getText().toString() != "") {
                dnaProfileViewModels.add(model13);
            }

            DNAProfileViewModel model14 = new DNAProfileViewModel();
            model.setLocous(tvFGA.getText().toString());
            model.setGenotype1(etFGA1.getText().toString());
            model.setGenotype2(etFGA2.getText().toString());
            if (etFGA1.getText().toString() != "") {
                dnaProfileViewModels.add(model14);
            }

            DNAProfileViewModel model15 = new DNAProfileViewModel();
            model.setLocous(tvD22S1045.getText().toString());
            model.setGenotype1(etD22S10451.getText().toString());
            model.setGenotype2(etD22S10452.getText().toString());
            if (etD22S10451.getText().toString() != "") {
                dnaProfileViewModels.add(model15);
            }

            DNAProfileViewModel model16 = new DNAProfileViewModel();
            model.setLocous(tvD5S818.getText().toString());
            model.setGenotype1(etD5S8181.getText().toString());
            model.setGenotype2(etD5S8182.getText().toString());
            if (etD5S8181.getText().toString() != "") {
                dnaProfileViewModels.add(model16);
            }

            DNAProfileViewModel model17 = new DNAProfileViewModel();
            model.setLocous(tvD13S317.getText().toString());
            model.setGenotype1(etD13S3171.getText().toString());
            model.setGenotype2(etD13S3172.getText().toString());
            if (etD13S3171.getText().toString() != "") {
                dnaProfileViewModels.add(model17);
            }

            DNAProfileViewModel model18 = new DNAProfileViewModel();
            model.setLocous(tvD7S820.getText().toString());
            model.setGenotype1(etD7S8201.getText().toString());
            model.setGenotype2(etD7S8202.getText().toString());
            if (etD7S8201.getText().toString() != "") {
                dnaProfileViewModels.add(model18);
            }


        } catch (Exception e) {

        }



       /* Intent intent = new Intent(UnknownManActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @OnClick(R.id.Edit)
    public void Edit() {
        llInput.setVisibility(View.VISIBLE);
        mcvReport.setVisibility(View.GONE);
       /* tvName.setText(etName.getText().toString());
        tvFathersName.setText(etFathersName.getText().toString());
        tvSpouseName.setText(etSpouseName.getText().toString());
        tvNidNum.setText(etNidNum.getText().toString());
        tvReligion.setText(spnReligion.getSelectedItem().toString());*/


       /* Intent intent = new Intent(UnknownManActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    @OnClick(R.id.Submit)
    public void Submit() {
        submitToServer();
      /*  Intent intent = new Intent(UnknownManActivity.this, CodeGenerateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }

    private void submitToServer() {

        String token = SharedPrefManager.getInstance(this).getToken();
        String UserName = SharedPrefManager.getInstance(this).getUser();

        final GDManInformationViewModel model = new GDManInformationViewModel();

        //GD Information
        model.setUserName(UserName);
        model.setGdFor(Constants.GDFOR);
        //model.setGdDate("2020-04-14");
        model.setIdentityNo("3453453");
        model.setGDTypeId(Constants.ENTRY_TYPE_ID);
        model.setProductTypeId(Constants.PRODUCT_TYPE_ID);
        model.setDocumentDescription("");
        //Man Information
        model.setRelationTypeId(SELECTED_RELATION_TYPE_ID);
        model.setName(tvName.getText().toString());
        model.setAproxAge(tvAge.getText().toString());
        model.setAgePeriodId(SELECTED_AGE_PERIOD_ID);
        model.setGenderId(SELECTED_GENDER_ID);
        model.setIsHealthDisabled(1);
        model.setFatherName(tvFathersName.getText().toString());
        model.setMotherName(etMotherName.getText().toString());
        model.setSpouseName(tvSpouseName.getText().toString());
        model.setNationalIdentityTypeId(SELECTED_DOC_TYPE_ID);
        model.setManIdentityNo(tvNidNum.getText().toString());
        model.setNumberTypeId(SELECTED_NUMBER_TYPE_ID);
        model.setNumber(tvNum.getText().toString());

        model.setDescriptionCircumcisionId(1);
        model.setReligionId(SELECTED_RELIGION_ID);
        model.setBloodGroup(tvBloodGroup.getText().toString());
        model.setOccupationId(SELECTED_OCCUPATION_ID);
        model.setMaritalStatusId(SELECTED_MARITAL_STATUS_ID);

        //Address
        model.setManDistrictId(SELECTED_DISTRICT_ID_LP);
        model.setManThanaId(SELECTED_THANA_ID_LP);
        model.setPostOffice(tvVillage.getText().toString());
        model.setAddressDetails(tvAddressDetails.getText().toString());
        model.setAddressType(tvAddressType.getText().toString());
        model.setOneLineAddress(tvOneLineAddress.getText().toString());
        //Physical
        model.setEyeTypeId(SELECTED_EYE_TYPE_ID);
        model.setNoseTypeId(SELECTED_NOSE_TYPE_ID);
        model.setHairTypeId(SELECTED_HAIR_TYPE_ID);
        model.setForeHeadTypeId(SELECTED_FOREHEAD_TYPE_ID);
        model.setBeardTypeId(SELECTED_BEARD_TYPE_ID);
        model.setWeight(Double.parseDouble(tvWeight.getText().toString()));
        model.setBodyStructureId(SELECTED_BODY_STRUCTURE_ID);
        model.setFaceShapeTypeId(SELECTED_FACE_SHAPE_ID);
        model.setBodyChinTypeId(SELECTED_BODY_CHIN_ID);
        model.setBodyColorId(SELECTED_BODY_COLOR_ID);
        model.setMoustacheTypeId(SELECTED_MOUSTACE_ID);
        model.setEarTypeId(SELECTED_EAR_ID);
        model.setNeckTypeId(SELECTED_NECK_ID);
        model.setHeightFeet(Double.parseDouble(tvHeight_feet.getText().toString()));
        model.setHeightInch(Double.parseDouble(tvHeight_Inch.getText().toString()));
        model.setSpecialBirthMarkTypeId(SELECTED_BIRTH_MARK_ID);
        model.setspecialBirthMarkBodyPartId(SELECTED_BIRTH_MARK_BODY_PART_ID);
        model.setSpecialBirthMarkBodyPartPositionId(SELECTED_BIRTH_MARK_BODY_PART_POSITION_ID);
        model.setVisibleTatto(etVisibleTattoos.getText().toString());
        model.setOtherIdentityfyMark(tvIdentityficationMark.getText().toString());
        model.setTeethTypeId(SELECTED_TEETH_ID);
        model.setSpecialBodyConditionId(SELECTED_PHYSICAL_CONDITION_ID);
        //Dress
        model.setInTheHeadId(SELECTED_INHEAD_ID);
        model.setInTheHeadColorId(SELECTED_DHEAD_COLOR_ID);
        model.setInTheBodyId(SELECTED_INBODY_ID);
        model.setInTheBodyColorId(SELECTED_DBODY_COLOR_ID);
        model.setInTheThroatId(SELECTED_INTHROAT_ID);
        model.setInTheThroatColorId(SELECTED_DTHROAT_COLOR_ID);
        model.setInTheWaistId(SELECTED_INWAIST_ID);
        model.setInTheWaistColorId(SELECTED_DWAIST_COLOR_ID);
        model.setInTheLegsId(SELECTED_INLEGS_ID);
        model.setInTheLegsColorId(SELECTED_DLEG_COLOR_ID);
        model.setInTheEyeId(SELECTED_INEYE_ID);
        model.setInTheEyeColorId(SELECTED_DEYE_COLOR_ID);
        model.setShoesSize(etDLegMes.getText().toString());
        model.setShoesSizeType(spnDLegMT.getSelectedItem().toString());
        //space and time
        model.setDistrictId(SELECTED_DISTRICT_ID);
        model.setThanaId(SELECTED_THANA_ID);
        model.setVillage(tvVillage.getText().toString());
        model.setAddressDetails(tvAddressDetails.getText().toString());
        model.setLafDate("2020-06-01");
        model.setLafTime("tvVehicleTime.getText().toString()");
        model.setDNAProfileViewModels(dnaProfileViewModels);

        RetrofitService retrofitService = RetrofitClientInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<String> registrationRequest = retrofitService.SaveGDManInformation(SharedPrefManager.BEARER + token, model);
        registrationRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response.body() != null) {
                        Toast.makeText(UnknownManActivity.this, "Successfully Done!", Toast.LENGTH_SHORT).show();
                        CodeGenerateActivity.code = response.body().toString();
                        Intent intent = new Intent(UnknownManActivity.this, CodeGenerateActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(UnknownManActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(UnknownManActivity.this, "Something went Wrong! Please try again later", Toast.LENGTH_SHORT).show();
                }
                //            showProgressBar(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utilities.showLogcatMessage("Fail to connect " + t.toString());
                // Utilities.hideProgress(LoginActivity.this);
                Toast.makeText(UnknownManActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();

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
                        getAllThana(body.get(i - 1).getId());
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
                Toast.makeText(UnknownManActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                    SELECTED_THANA_ID = null;
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
        spnLostistrict.setAdapter(dataAdapter2);
        spnLostistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    if (i >= 1) {
                        SELECTED_DISTRICT_ID_LP = body.get(i).getId();
                        getAllThanaLP(body.get(i - 1).getId());
                    } else {
                        SELECTED_DISTRICT_ID_LP = null;
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
                Toast.makeText(UnknownManActivity.this, "Fail to connect " + t.toString(), Toast.LENGTH_SHORT).show();
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
                    SELECTED_THANA_ID_LP = body.get(i - 1).getId();
                } else {
                    SELECTED_THANA_ID_LP = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                relatinoTypeList.add(i + 1, body.get(i).getRelationName());
            }
        }else {
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
                        SELECTED_RELATION_TYPE_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_RELATION_TYPE_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                occupationList.add(i + 1, body.get(i).getName());
            }
        }else {
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
                        SELECTED_OCCUPATION_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_OCCUPATION_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getReligionName());
            }
        }else {
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
                        SELECTED_RELIGION_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_RELIGION_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getName());
            }
        }else {
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
                        SELECTED_MARITAL_STATUS_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_MARITAL_STATUS_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getHabitName());
            }
        }else {
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
                        SELECTED_HABIT_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_HABIT_ID = null;
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
        if(Language==english){
            for (int i = 0; i < body.size(); i++) {
                lstData.add(i + 1, body.get(i).getSpeechName());
            }
        }else {
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
                        SELECTED_SPEECH_ID = body.get(i - 1).getId();
                    } else {
                        SELECTED_SPEECH_ID = null;
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
                .callback(UnknownManActivity.this)
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
        }
        catch (Exception e)
        {
            Utilities.showLogcatMessage("onActivityResult "+e.toString());
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

    @OnClick(R.id.btnAddPhotoes)
    public void ImageAdd(){
        CropImage.activity().start(UnknownManActivity.this);
    }


    //ImagePicker

    public void ImagePicker(){
        ImagePicker.Companion.with(this)
                .galleryOnly()
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

}
