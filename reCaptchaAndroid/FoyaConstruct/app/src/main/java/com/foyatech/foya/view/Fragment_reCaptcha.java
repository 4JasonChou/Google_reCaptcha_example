package com.foyatech.foya.view;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.foyatech.foya.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class Fragment_reCaptcha extends Fragment  implements GoogleApiClient.ConnectionCallbacks,  GoogleApiClient.OnConnectionFailedListener {

    private View mView;
    private Activity mActivity;
    private GoogleApiClient mGoogleApiClient;

    final String mSiteKey = "6LejliYUAAAAAOCL9TrrBFJkTFHg2Sp9Mr0jj_mW";
    final String mSecretKey  = "6LejliYUAAAAAMFc7PCCbLmOK5RlQaZCKayxq24D";

    private TextView mResult;
    private Button mButton;

    public Fragment_reCaptcha() {
        // Required empty public constructor
    }

    public static Fragment_reCaptcha newInstance(String param1, String param2) {
        Fragment_reCaptcha fragment = new Fragment_reCaptcha();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_re_captcha, container, false);
        mActivity = this.getActivity();
        mResult = (TextView)mView.findViewById(R.id.recaptcha_result);
        mButton = (Button)mView.findViewById(R.id.recaptcha_button);

        mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(Fragment_reCaptcha.this)
                .addOnConnectionFailedListener(Fragment_reCaptcha.this)
                .build();

        mGoogleApiClient.connect();

        mButton.setOnClickListener(new View.OnClickListener() {
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
                                    mResult.setText("isScuess");
                                    if (!result.getTokenResult().isEmpty()) {
                                        // User response token must be validated using the
                                        // reCAPTCHA site verify API.
                                        mResult.setText("isScuess" + result.getTokenResult() );
                                    }
                                } else {
                                    Log.d("GOTORECAPTCHA", "Error occurred when communicating with the reCAPTCHA service." );
                                }
                            }
                        });
            }
        });

        return mView;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(mActivity, "onConnected()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(mActivity, "onConnectionFailed()", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(mActivity, "onConnectionSuspended" + String.valueOf(i) , Toast.LENGTH_LONG).show();
    }

}
