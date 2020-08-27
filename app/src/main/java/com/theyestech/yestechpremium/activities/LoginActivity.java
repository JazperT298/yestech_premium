package com.theyestech.yestechpremium.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.theyestech.yestechpremium.HttpProvider;
import com.theyestech.yestechpremium.MainActivity;
import com.theyestech.yestechpremium.R;
import com.theyestech.yestechpremium.models.LoginRequest;
import com.theyestech.yestechpremium.models.LoginResponse;
import com.theyestech.yestechpremium.models.UserModel;
import com.theyestech.yestechpremium.utils.ApiClient;
import com.theyestech.yestechpremium.utils.Debugger;
import com.theyestech.yestechpremium.utils.Utility;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private View view;
    private Context context;

    private EditText et_LoginEmail,et_LoginPassword;
    private FloatingActionButton fab_LoginSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeUI();
    }

    private void initializeUI(){
        et_LoginEmail = findViewById(R.id.et_LoginEmail);
        et_LoginPassword = findViewById(R.id.et_LoginPassword);
        fab_LoginSignIn = findViewById(R.id.fab_LoginSignIn);
        fab_LoginSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_LoginEmail.getText().toString().isEmpty() || et_LoginPassword.getText().toString().isEmpty()){
                    Toasty.warning(context,"Please input all fields").show();
                }else {
//                    Intent intent = new Intent(context, MainActivity.class);
//                    intent.putExtra("USERTYPE", et_LoginEmail.getText().toString());
//                    startActivity(intent);
//                    finish();
                    //loginUser();
                    //login();
                    JSONObject jsonParams = new JSONObject();

                    try {
                        jsonParams.put("grant_type", "password");
                        jsonParams.put("username", et_LoginEmail.getText().toString());
                        jsonParams.put("password", et_LoginPassword.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    StringEntity entity = null;
                    try {
                        entity = new StringEntity(jsonParams.toString());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    if(!Utility.haveNetworkConnection(context)){
                        et_LoginEmail.setError("Internet connection is required");
                        et_LoginEmail.requestFocus();
                    }else {
                        Debugger.printO(jsonParams.toString());
                        doLogin(entity);
                    }
                }
//                if(et_LoginEmail.getText().toString().equals("admin")){
//                    Intent intent = new Intent(context, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else if(et_LoginEmail.getText().toString().equals("educator")){
//
//                }else if(et_LoginEmail.getText().toString().equals("student")){
//
//                }else{
//                    Toasty.warning(context, "Invalid Input!").show();
//                }
            }
        });
    }

    private void loginUser(){
        RequestParams params = new RequestParams();
        params.put("grant_type","password");
        params.put("username", et_LoginEmail.getText().toString());
        params.put("password", et_LoginPassword.getText().toString());

        HttpProvider.postLogin(context, "auth/Token", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody, StandardCharsets.UTF_8);
                Debugger.logD("str " + str);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Debugger.logD("FUCN " + error.getMessage());
            }
        });
    }

    private void doLogin(StringEntity stringEntity){

        HttpProvider.post(context, "auth/Token", stringEntity,"application/x-www-form-urlencoded", new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Debugger.logD("wtf " + response.toString());
                try {
                    boolean isSuccess = response.getBoolean("success");
                    if(isSuccess) {

                        Debugger.logD("wtf " + response.toString());

                    } else{
                    }

                }catch (Exception err){
                    Debugger.logD("login Error: "+err);
                }

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toasty.error(context, throwable.toString()).show();
                Debugger.logD("shit1 " + throwable.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toasty.error(context, errorResponse.toString()).show();
                Debugger.logD("shit2 " + errorResponse.toString());
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Toasty.error(context, throwable.toString()).show();
                Debugger.logD("shit3 " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }
        });
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setGrant_type("password");
        loginRequest.setUsername(et_LoginEmail.getText().toString());
        loginRequest.setPassword(et_LoginPassword.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Debugger.logD("Success " + response.toString());
                }else{
                    Debugger.logD("Failed " + response.toString());
                }
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                Debugger.logD("Error " + t.getLocalizedMessage());
            }
        });
    }

}
