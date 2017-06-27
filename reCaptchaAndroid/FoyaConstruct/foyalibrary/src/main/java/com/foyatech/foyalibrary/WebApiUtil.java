package com.foyatech.foyalibrary;

import com.foyatech.foyalibrary.model.ApiRestResult;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Admin on 2017/3/1.
 */

public class WebApiUtil {

    private final String TAG = "WebApiUtil";
    private final String EMPTY_STRING = "";

    private String mUrl;
    private HashMap mHeader;
    private String mJsonStrBody;
    private String mMethod;

    public WebApiUtil(String url, HashMap header, String method, String body)
    {
        mUrl = url;
        mHeader = header;
        mMethod = method;
        mJsonStrBody = body;
    }

    public ApiRestResult DoWebApi()
    {
        ApiRestResult res = new ApiRestResult(0,"");
        try {

            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            //  URL
            Request.Builder requestBuild = new Request.Builder().url(mUrl);
            // HEADER
            for (Object key : mHeader.keySet()) {
                requestBuild.addHeader( key.toString() ,mHeader.get(key).toString() );
            }
            // BODY
            if( mJsonStrBody==null )
                mJsonStrBody = EMPTY_STRING  ;
            RequestBody body = RequestBody.create(JSON, mJsonStrBody );
            // METHOD
            switch (mMethod)
            {
                case "GET":
                    break;
                case "POST":
                    requestBuild.post(body);
                    break;
                case "PATCH":
                    requestBuild.patch(body);
                    break;
                case "PUT":
                    requestBuild.put(body);
                    break;
                case "DELETE":
                    requestBuild.delete(body);
                    break;
                default:
                    throw new Exception("Error Api Method.");
            }
            // SET REQUEST
            Request request = requestBuild.build();
            // SEND AND GET RESPONSE
            Response response = client.newCall(request).execute();

            res.HttpCode = response.code();
            res.HttpMsg = response.body().string();

        }
        catch (Exception ex)
        {
            res.HttpMsg = "Throw Exception: " + ex.toString();
        }

        return  res;
    }


}
