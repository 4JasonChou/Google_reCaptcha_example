package com.foyatech.foyalibrary.model;

/**
 * Created by Admin on 2017/3/1.
 */

public class ApiRestResult {
    public int HttpCode ;
    public String HttpMsg ;

    public ApiRestResult()
    {
        HttpCode = 550;
        HttpMsg = null;
    }

    public ApiRestResult(int code,String msg)
    {
        HttpCode = code;
        HttpMsg = msg;
    }
}
