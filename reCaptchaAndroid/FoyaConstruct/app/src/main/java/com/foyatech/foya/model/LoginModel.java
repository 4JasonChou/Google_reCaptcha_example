package com.foyatech.foya.model;

/**
 * Created by Admin on 2017/2/24.
 */

public class LoginModel {
    public String Account;
    public String Password;
    public LoginModel()
    {
        Account = "";
        Password = "";
    }
    public LoginModel(String acc, String pwd)
    {
        Account = acc;
        Password = pwd;
    }
}
