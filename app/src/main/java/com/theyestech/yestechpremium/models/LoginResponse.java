package com.theyestech.yestechpremium.models;

public class LoginResponse {

    private String access_token;
    private String token_type;
    private String expires_in;
    private String userName;
    private String issued;
    private String expires;

    public LoginResponse(String access_token, String token_type, String expires_in, String userName, String issued, String expires) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.userName = userName;
        this.issued = issued;
        this.expires = expires;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }
}
