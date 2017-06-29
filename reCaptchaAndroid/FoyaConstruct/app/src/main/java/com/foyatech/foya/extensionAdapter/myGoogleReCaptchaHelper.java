package com.foyatech.foya.extensionAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.foyatech.foya.view.Fragment_reCaptcha;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

/**
 * Created by Admin on 2017/6/29.
 */

public class myGoogleReCaptchaHelper implements GoogleApiClient.ConnectionCallbacks,  GoogleApiClient.OnConnectionFailedListener {

    private String TAG = "myGoogleReCaptchaHelper";
    private Activity mActivity;
    private GoogleApiClient mGoogleApiClient;
    private String mSiteKey ;
    private String mSecretKey ;

    public myGoogleReCaptchaHelper(Activity activity,String siteKey ,String secretKey)
    {
        mActivity = activity;
        mSecretKey = secretKey;
        mSiteKey = siteKey;


    }

    public void ConnectApiService()
    {
        mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();
    }

    public View.OnClickListener ImNotBotButtonEvent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SafetyNet.SafetyNetApi.verifyWithRecaptcha(mGoogleApiClient, mSiteKey)
                    .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                        @Override
                        public void onResult(SafetyNetApi.RecaptchaTokenResult result) {
                            Status status = result.getStatus();
                            if ((status != null) && status.isSuccess()) {
                                // Indicates communication with reCAPTCHA service was
                                // successful. Use result.getTokenResult() to get the
                                // user response token if the user has completed
                                // the CAPTCHA.
                                Log.d(TAG,"Verify Scuess.");
                                if (!result.getTokenResult().isEmpty()) {
                                    // User response token must be validated using the
                                    // reCAPTCHA site verify API.
                                    Log.d(TAG,"Verify Scuess and token :" + result.getTokenResult() );
                                }
                            } else {
                                Log.d("GOTORECAPTCHA", "Error occurred when communicating with the reCAPTCHA service." );
                            }
                        }
                    });
        }
    };


    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG,"Connect Scuess.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG,"Connect Failed.");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG,"Connect Suspended on " + String.valueOf(i) );
    }
}
