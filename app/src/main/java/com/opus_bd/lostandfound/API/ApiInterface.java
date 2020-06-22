package com.opus_bd.lostandfound.API;

import com.opus_bd.lostandfound.Model.Dashboard.GDInformation;
import com.opus_bd.lostandfound.Model.Dashboard.GDInformationModel;
import com.opus_bd.lostandfound.Model.Dashboard.GDTypeStatusModel;
import com.opus_bd.lostandfound.Model.Dashboard.StatusTypeViewModel;
import com.opus_bd.lostandfound.Model.Documentaion.Colors;
import com.opus_bd.lostandfound.Model.Documentaion.ComputerAccessoriesBrand;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentCategory;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentCategoryAccessory;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentCategoryBrand;
import com.opus_bd.lostandfound.Model.Documentaion.DocumentType;
import com.opus_bd.lostandfound.Model.Documentaion.MDPersonalInformationModel;
import com.opus_bd.lostandfound.Model.Documentaion.MetroAreaModel;
import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;
import com.opus_bd.lostandfound.Model.Documentaion.Occupation;
import com.opus_bd.lostandfound.Model.Documentaion.RegistrationLevelModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleModel;
import com.opus_bd.lostandfound.Model.Documentaion.VehicleType;
import com.opus_bd.lostandfound.Model.DressInfo.MDDressInformationModel;
import com.opus_bd.lostandfound.Model.GDInfoModel.GDManInformationViewModel;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Division;
import com.opus_bd.lostandfound.Model.GlobalData.MDAddressInforamtionModel;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.Model.OthersItem.ComputerInfo;
import com.opus_bd.lostandfound.Model.PhysicalInfo.MDPhysicalInformationModel;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserLoginModel;
import com.opus_bd.lostandfound.Model.Vehichel.VehicleMasterModel;
import com.opus_bd.lostandfound.Model.Vehichel.VehiclePostModel;
import com.opus_bd.lostandfound.Model.VehicleSearch.VehicleSearchListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("AppAPI/AccountInfo/LogIn")
    Call<UserAuthModel> login(@Body UserLoginModel userLoginModel);


    @POST("AppAPI/AccountInfo/Register")
    Call<UserAuthModel> Register(@Body RegistrationModel registrationModel);


    @GET("AppAPI/AddressCategory/SendSMSOTP/{mobile}/{message}/{token}")
    Call<String> SendSMSOTP(@Path("mobile") String mobile, @Path("message") String message, @Path("token") String token);

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


    //Vehicle POst
    @POST("AppAPI/LostFound/SaveGDInformation")
    Call<String> SaveVehicleLostInfromation(@Header("Authorization") String token, @Body VehiclePostModel vehiclePostModel);

    //Man POst
    @POST("AppAPI/LostFound/SaveGDManInformation")
    Call<String> SaveGDManInformation(@Header("Authorization") String token, @Body GDManInformationViewModel model);

    // Others

    @POST("AppAPI/OthersItem/SaveComputerInfo")
    Call<String> SaveComputerInfo(@Header("Authorization") String token, @Body ComputerInfo model);

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


    @GET("AppAPI/DocumentMaster/GetDocumentCategoryBrandByDocumentTypeId/{id}")
    Call<List<DocumentCategoryBrand>> GetDocumentCategoryBrandByDocumentTypeId(@Path("id") int id);

    @GET("AppAPI/DocumentMaster/GetDocumentCategoryAccessoriesByDocumentTypeId/{id}")
    Call<List<DocumentCategoryAccessory>> GetDocumentCategoryAccessoriesByDocumentTypeId(@Path("id") int id);

    @GET("AppAPI/DocumentMaster/GetAllComputerAccessoriesBrand/{id}")
    Call<List<ComputerAccessoriesBrand>> GetAllComputerAccessoriesBrand();
//Global data


    @GET("AppAPI/LostFound/GetGDInformationByUser/{UserName}")
    Call<List<GDInformation>> GetGDInformationByUser(@Header("Authorization") String token, @Path("UserName") String userName);

    //Dashboard Data
    @GET("AppAPI/LostFound/GetCountGDInformationStatus/{UserName}")
    Call<StatusTypeViewModel> GetCountGDInformationStatus(@Header("Authorization") String token, @Path("UserName") String userName);

    @GET("AppAPI/LostFound/GetCountGDInformationByGDType/{UserName}/{statusId}")
    Call<GDTypeStatusModel> GetCountGDInformationByGDType(@Header("Authorization") String token, @Path("UserName") String userName, @Path("statusId") int statusId);

    @GET("AppAPI/LostFound/GetAllGDInformationByFiltering/{UserName}/{statusId}/{gdTypeId}")
    Call<List<GDInformation>> GetAllGDInformationByFiltering(@Header("Authorization") String token, @Path("UserName") String userName, @Path("statusId") int statusId, @Path("gdTypeId") int gdTypeId);


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

    //Search
    @GET("AppAPI/LostFound/GetVehicleInformationBySearching/{vehicleTypeId}/{brandId}/{modelNo}/{regiNo}/{engineNo}/{chesisNo}/{cc}/{colorId}")
    Call<List<VehicleSearchListModel>> GetVehicleInformationBySearching(@Header("Authorization") String token, @Path("vehicleTypeId") int vehicleTypeId,
                                                                        @Path("brandId") int brandId,
                                                                        @Path("modelNo") String modelNo,
                                                                        @Path("regiNo") String regiNo,
                                                                        @Path("engineNo") String engineNo,
                                                                        @Path("chesisNo") String chesisNo,
                                                                        @Path("cc") String cc,
                                                                        @Path("colorId") int colorId
    );

    @GET("AppAPI/LostFound/GetVehicleDetailsInformationByGDId/{id}")
    Call<GDInformation> GetVehicleDetailsInformationByGDId();


}
