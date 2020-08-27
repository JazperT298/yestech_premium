package com.theyestech.yestechpremium.intefaces;

import com.theyestech.yestechpremium.models.LoginRequest;
import com.theyestech.yestechpremium.models.LoginResponse;
import com.theyestech.yestechpremium.models.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("auth/Token")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
