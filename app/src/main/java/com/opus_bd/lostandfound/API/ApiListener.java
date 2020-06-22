package com.opus_bd.lostandfound.API;

import com.opus_bd.lostandfound.Model.User.UserAuthModel;

public class ApiListener {
    public interface LoginUserListener {
        void onUserLoginSuccess(UserAuthModel status);

        void onUserLoginFailed(String msg);
    }
}
