package com.opus_bd.lostandfound.RetrofitService;


import com.opus_bd.lostandfound.Model.Dashboard.ApplicationUser;
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
import com.opus_bd.lostandfound.Model.GDInfoModel.GDViewModel;
import com.opus_bd.lostandfound.Model.GDInfoModel.NewsFeedViewModel;
import com.opus_bd.lostandfound.Model.GlobalData.District;
import com.opus_bd.lostandfound.Model.GlobalData.Division;
import com.opus_bd.lostandfound.Model.GlobalData.FileUpload;
import com.opus_bd.lostandfound.Model.GlobalData.MDAddressInforamtionModel;
import com.opus_bd.lostandfound.Model.GlobalData.Thana;
import com.opus_bd.lostandfound.Model.OthersItem.ComputerInfo;
import com.opus_bd.lostandfound.Model.PhysicalInfo.MDPhysicalInformationModel;
import com.opus_bd.lostandfound.Model.User.RegistrationModel;
import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserInfo;
import com.opus_bd.lostandfound.Model.User.UserLoginModel;
import com.opus_bd.lostandfound.Model.User.UserProfileModel;
import com.opus_bd.lostandfound.Model.Vehichel.Likes;
import com.opus_bd.lostandfound.Model.Vehichel.VehicleMasterModel;
import com.opus_bd.lostandfound.Model.Vehichel.VehiclePostModel;
import com.opus_bd.lostandfound.Model.VehicleSearch.VehicleSearchListModel;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitService {
    @POST("api/AccountInfo/LogIn")
    Call<UserAuthModel> login(@Body UserLoginModel userLoginModel);


    @POST("api/AccountInfo/Register")
    Call<UserAuthModel> Register(@Body RegistrationModel registrationModel);

    @POST("api/AccountInfo/ProfileUpdate")
    Call<RegistrationModel> ProfileUpdate(@Header("Authorization") String token,@Body UserProfileModel model);

    @GET("api/AccountInfo/GetUserInfo/{userName}")
    Call<RegistrationModel> GetUserInfo(@Header("Authorization") String token, @Path("userName") String userName);


    @GET("api/AddressCategory/SendSMSOTP/{mobile}/{message}/{token}")
    Call<String> SendSMSOTP(@Path("mobile") String mobile, @Path("message") String message, @Path("token") String token);

    @POST("api/AccountInfo/OTPVarified")
    Call<String> OTPVarified(@Body RegistrationModel registrationModel);

    //Documentation
    @GET("api/DocumentMaster/GetNationalIdentityTypes")
    Call<List<NationalIdentityTypesModel>> GetNationalIdentityTypes();

    @GET("api/DocumentMaster/GetAllDocumentType")
    Call<List<DocumentType>> GetAllDocumentType();

    @GET("api/DocumentMaster/GetDocumentCategoryByTypeId/{id}")
    Call<List<DocumentCategory>> GetDocumentCategoryByTypeId(@Path("id") int id);


    //Dashboard
    @POST("api/LostFound/SaveGDInformation")
    Call<String> SaveGDInformation(@Header("Authorization") String token, @Body GDInformationModel gdInformationModel);


    //Vehicle POst
    @Multipart
    @POST("api/LostFound/SaveGDInformation")
    Call<String> SaveVehicleLostInfromation(@Header("Authorization") String token, @Body VehiclePostModel vehiclePostModel, @Part MultipartBody.Part file);

    @Multipart
    @POST("api/LostFound/UploadImage")
    Call<String> UploadImage(@Part MultipartBody.Part file);

    @POST("api/LostFound/SaveVehicleWithImage")
    Call<String> SaveVehicleWithImage(@Header("Authorization") String token,@Body VehiclePostModel model);

    @POST("api/LostFound/UploadImageWithEncode")
    Call<String> UploadImageWithEncode(@Header("Authorization") String token,@Body FileUpload fileUpload);

    //Man POst
    @POST("api/LostFound/SaveGDManInformation")
    Call<String> SaveGDManInformation(@Header("Authorization") String token, @Body GDManInformationViewModel model);

    // Others

    @POST("api/OthersItem/SaveComputerInfo")
    Call<String> SaveComputerInfo(@Header("Authorization") String token, @Body ComputerInfo model);

    @POST("api/LostFound/SaveLikes")
    Call<String> SaveLikes(@Header("Authorization") String token, @Body Likes model);

//Global data

    @GET("api/AddressMaster/GetDivisions")
    Call<List<Division>> GetDivisions();

    @GET("api/AddressMaster/GetAllDistrict")
    Call<List<District>> getAllDistricts();

    @GET("api/AddressMaster/GetThanaByDistrictId/{id}")
    Call<List<Thana>> GetThanaByDistrictId(@Path("id") int id);


    @GET("api/DocumentMaster/GetVehicleTypes")
    Call<List<VehicleType>> GetVehicleTypes();

    @GET("api/DocumentMaster/GetVehicleModelByVehicleId/{id}")
    Call<List<VehicleModel>> GetVehicleModelByVehicleId(@Path("id") int id);


    @GET("api/DocumentMaster/GetDocumentCategoryBrandByDocumentTypeId/{id}")
    Call<List<DocumentCategoryBrand>> GetDocumentCategoryBrandByDocumentTypeId(@Path("id") int id);

    @GET("api/DocumentMaster/GetDocumentCategoryAccessoriesByDocumentTypeId/{id}")
    Call<List<DocumentCategoryAccessory>> GetDocumentCategoryAccessoriesByDocumentTypeId(@Path("id") int id);

    @GET("api/DocumentMaster/GetAllComputerAccessoriesBrand/{id}")
    Call<List<ComputerAccessoriesBrand>> GetAllComputerAccessoriesBrand();
//Global data

    @GET("api/LostFound/GetALLNewFeedsInfo/{UserName}/{gdTypeId}/{vehicleTypeId}")
    Call<List<NewsFeedViewModel>> GetALLNewFeedsInfo(@Header("Authorization") String token, @Path("UserName") String userName,@Path("gdTypeId") int gdTypeId,@Path("vehicleTypeId") int vehicleTypeId);


    @GET("api/LostFound/GetGDInformationByUser/{UserName}")
    Call<List<GDInformation>> GetGDInformationByUser(@Header("Authorization") String token, @Path("UserName") String userName);

    //Dashboard Data
    @GET("api/LostFound/GetCountGDInformationStatus/{UserName}")
    Call<StatusTypeViewModel> GetCountGDInformationStatus(@Header("Authorization") String token, @Path("UserName") String userName);

    @GET("api/LostFound/GetCountGDInformationByGDType/{UserName}/{statusId}")
    Call<GDTypeStatusModel> GetCountGDInformationByGDType(@Header("Authorization") String token, @Path("UserName") String userName, @Path("statusId") int statusId);

    @GET("api/LostFound/GetAllGDInformationByFiltering/{UserName}/{statusId}/{gdTypeId}")
    Call<List<GDInformation>> GetAllGDInformationByFiltering(@Header("Authorization") String token, @Path("UserName") String userName, @Path("statusId") int statusId, @Path("gdTypeId") int gdTypeId);


    @GET("api/DocumentMaster/GetColors")
    Call<List<Colors>> GetColors();

    @GET("api/VehicleMaster/GetAllMetropolitanArea")
    Call<List<MetroAreaModel>> GetAllMetropolitanArea();

    @GET("api/VehicleMaster/GetAllRegistrationLevel")
    Call<List<RegistrationLevelModel>> GetAllRegistrationLevel();

    @GET("api/DocumentMaster/GetOccupationInfo")
    Call<List<Occupation>> GetOccupationInfo();


    @GET("api/DocumentMaster/GetVehicleMasterData")
    Call<VehicleMasterModel> GetVehicleMasterData();

    @GET("api/ExtendMaster/GetDressInformationMasterData")
    Call<MDDressInformationModel> GetDressInformationMasterData();

    @GET("api/ExtendMaster/GetPhysicalInformationMasterData")
    Call<MDPhysicalInformationModel> GetPhysicalInformationMasterData();

    @GET("api/ExtendMaster/GetPersonalInformationMasterData")
    Call<MDPersonalInformationModel> GetPersonalInformationMasterData();

    @GET("api/AddressMaster/GetAddressInformationMasterData")
    Call<MDAddressInforamtionModel> GetAddressInformationMasterData();

    //Search
    @GET("api/LostFound/GetVehicleInformationBySearching/{vehicleTypeId}/{brandId}/{modelNo}/{regiNo}/{engineNo}/{chesisNo}/{cc}/{colorId}/{userName}")
    Call<List<VehicleSearchListModel>> GetVehicleInformationBySearching(@Header("Authorization") String token, @Path("vehicleTypeId") int vehicleTypeId,
                                                                        @Path("brandId") int brandId,
                                                                        @Path("modelNo") String modelNo,
                                                                        @Path("regiNo") String regiNo,
                                                                        @Path("engineNo") String engineNo,
                                                                        @Path("chesisNo") String chesisNo,
                                                                        @Path("cc") String cc,
                                                                        @Path("colorId") int colorId,
                                                                        @Path("userName") String userName
    );

    @GET("api/LostFound/GetVehicleDetailsInformationByGDId/{id}")
    Call<GDInformation> GetVehicleDetailsInformationByGDId();

    //Update
    @PUT("api/LostFound/UpdateGDInformationStatusById/{id}/{statusId}")
    Call<String> UpdateGDInformationStatusById(@Header("Authorization") String token, @Path("id") int id, @Path("statusId") int statusId);

}
