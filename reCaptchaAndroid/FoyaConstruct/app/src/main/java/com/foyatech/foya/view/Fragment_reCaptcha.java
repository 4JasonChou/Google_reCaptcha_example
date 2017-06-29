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
import com.foyatech.foya.extensionAdapter.myGoogleReCaptchaHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class Fragment_reCaptcha extends Fragment {

    private View mView;
    private Activity mActivity;

    private myGoogleReCaptchaHelper mGoogleReCaptchaHelper;

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

        mGoogleReCaptchaHelper = new myGoogleReCaptchaHelper(mActivity ,mSiteKey,mSecretKey);
        mGoogleReCaptchaHelper.ConnectApiService();
        mButton.setOnClickListener(mGoogleReCaptchaHelper.ImNotBotButtonEvent);

        return mView;
    }

}
