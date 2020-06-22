package com.opus_bd.lostandfound.RetrofitService;


import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentCategory;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.Documentaion.MDPersonalInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.MetroAreaModel;
import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;
import com.opus_bd.lostandfound.Model.Documentaion.Occupation;
import com.opus_bd.lostandfound.Model.Documentaion.RegistrationLevelModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleType;
import com.opus_bd.lostandfound.Model.DressInfo.MDDressInformationModel;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Division;
import com.opus_bd.lostandfound.Model.GlobalData.MDAddressInforamtionModel;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.Model.PhysicalInfo.MDPhysicalInformationModel;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserLoginModel;
import com.opus_bd.lostandfound.Model.Vehichel.VehicleMasterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    @POST("AppAPI/AccountInfo/LogIn")
    Call<UserAuthModel> login(@Body UserLoginModel userLoginModel);


    @POST("AppAPI/AccountInfo/Register")
    Call<UserAuthModel> Register(@Body RegistrationModel registrationModel);

    @POST("AppAPI/AccountInfo/OTPVarified")
    Call<String> OTPVarified(@Body RegistrationModel registrationModel);

    //Documentation
    @GET("AppAPI/DocumentMaster/GetNationalIdentityTypes")
    Call<List<NationalIdentityTypesModel>> GetNationalIdentityTypes();

    @GET("AppAPI/DocumentMaster/GetAllDocumentType")
    Call<List<DocumentType>> GetAllDocumentType();

    @GET("AppAPI/DocumentMaster/GetDocumentCategoryByTypeId/{id}")
    Call<List<DocumentCategory>> GetDocumentCategoryByTypeId(@Path("id") int id);


    //Dashboard
    @POST("AppAPI/LostFound/SaveGDInformation")
    Call<String> SaveGDInformation(@Header("Authorization") String token, @Body GDInformationModel gdInformationModel);

//Global data

    @GET("AppAPI/AddressMaster/GetDivisions")
    Call<List<Division>> GetDivisions();

    @GET("AppAPI/AddressMaster/GetAllDistrict")
    Call<List<District>> getAllDistricts();

    @GET("AppAPI/AddressMaster/GetThanaByDistrictId/{id}")
    Call<List<Thana>> GetThanaByDistrictId(@Path("id") int id);



    @GET("AppAPI/DocumentMaster/GetVehicleTypes")
    Call<List<VehicleType>> GetVehicleTypes();

    @GET("AppAPI/DocumentMaster/GetVehicleModelByVehicleId/{id}")
    Call<List<VehicleModel>> GetVehicleModelByVehicleId(@Path("id") int id);
//Global data


    @GET("AppAPI/LostFound/GetGDInformationByUser/{UserName}")
    Call<List<GDInformation>> GetGDInformationByUser(@Header("Authorization") String token,@Path("UserName") String userName);



    @GET("AppAPI/DocumentMaster/GetColors")
    Call<List<Colors>> GetColors();
    @GET("AppAPI/VehicleMaster/GetAllMetropolitanArea")
    Call<List<MetroAreaModel>> GetAllMetropolitanArea();

    @GET("AppAPI/VehicleMaster/GetAllRegistrationLevel")
    Call<List<RegistrationLevelModel>> GetAllRegistrationLevel();

    @GET("AppAPI/DocumentMaster/GetOccupationInfo")
    Call<List<Occupation>> GetOccupationInfo();


    @GET("AppAPI/DocumentMaster/GetVehicleMasterData")
    Call<VehicleMasterModel> GetVehicleMasterData();

    @GET("AppAPI/ExtendMaster/GetDressInformationMasterData")
    Call<MDDressInformationModel> GetDressInformationMasterData();

    @GET("AppAPI/ExtendMaster/GetPhysicalInformationMasterData")
    Call<MDPhysicalInformationModel> GetPhysicalInformationMasterData();

    @GET("AppAPI/ExtendMaster/GetPersonalInformationMasterData")
    Call<MDPersonalInformationModel> GetPersonalInformationMasterData();

    @GET("AppAPI/AddressMaster/GetAddressInformationMasterData")
    Call<MDAddressInforamtionModel> GetAddressInformationMasterData();

}
