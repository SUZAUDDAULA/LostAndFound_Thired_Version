package com.opus_bd.lostandfound.API;

import androidx.annotation.NonNull;

import com.opus_bd.lostandfound.Model.User.UserAuthModel;
import com.opus_bd.lostandfound.Model.User.UserLoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppAPI {
    public static String ERROR_MSG = "Network Error.Try again";
    private static AppAPI dataManager = null;

    public static AppAPI getInstance() {

        if (dataManager == null) {
            dataManager = new AppAPI();
        }

        return dataManager;
    }

    public void loginUser(UserLoginModel userLoginModel, final ApiListener.LoginUserListener loginUserListener) {

        ApiClient.getApiInterface().login(userLoginModel).enqueue(new Callback<UserAuthModel>() {
            @Override
            public void onResponse(@NonNull Call<UserAuthModel> call, @NonNull Response<UserAuthModel> response) {
                if (response != null) {
                    loginUserListener.onUserLoginSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<UserAuthModel> call, @NonNull Throwable t) {
                loginUserListener.onUserLoginFailed(t.getLocalizedMessage());
            }
        });
    }

}
