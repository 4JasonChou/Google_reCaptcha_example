package com.foyatech.foya.presenter;

import android.app.Activity;
import android.util.Log;

import com.foyatech.foya.model.*;
import com.foyatech.foya.presenter.presenterInterface.ILoginPresenter;
import com.foyatech.foya.view.viewInterface.ILoginView;
import com.foyatech.foyalibrary.ApiPublicAsyncTask;
import com.foyatech.foyalibrary.model.*;

import java.util.HashMap;


public class LoginPresenterCompl implements ILoginPresenter {

    public LoginPresenterCompl(ILoginView loginView) {
        this.mLoginView = loginView;
    }
    public LoginPresenterCompl(ILoginView loginView,Activity activity) {
        this.mLoginView = loginView;
        mActivity = activity;
    }

    private ILoginView mLoginView;
    private Activity mActivity;
    private ApiPublicAsyncTask mTask = null;

    @Override
    public void DoLogin(String account, String password) {
        Boolean isLoginSuccess = false;
        if(account.equals("123") && password.equals("123"))
            isLoginSuccess = true;

        final Boolean result = isLoginSuccess;
        mLoginView.onLoginResult(true);

    }

    /* Restful Api (Json) 使用說明 :
        *  此為 Async異地任務,透過OKHttp呼叫RestfulApi的簡易使用工具  ApiPublicAsyncTask
        *  1. 先使用 ApiPublicAsyncTask 宣告 任務
        *  2. ApiPublicAsyncTask( thisActivity , true/false , "等待時的文字提示" )
        *  3. 設定RestfulApi的呼叫資訊 ApiPublicAsyncTask.Set( URL , Header格式HashMap , "POST"/"GET"/... , JsonObject 接受可轉化為Json字串的物件  )
        *  4. Override ProcessFinish(...) 當任務完成後應該做的事情 , Json RestfulApi的結構 ApiRestResult.Code ( returnCode ) , ApiRestResult.Msg( Json字串 )
        */
    private void loginApi()
    {
        if (mTask == null) {
            ParkPayLogin value = new ParkPayLogin();
            value.MemberNo = "FOYA0001";
            value.Password = "bca062bea543bdfce4b5d30e8982de96";
            mTask = new ApiPublicAsyncTask(mActivity,true,"Waiting..."){
                @Override
                public void ProcessFinish(ApiRestResult result)
                {
                    Log.d("GOTO",String.valueOf(result.HttpCode));
                    Log.d("GOTO", result.HttpMsg);
                    mTask = null;
                }
            };
            HashMap header = new HashMap();
            header.put("Content-Type","application/json");
            mTask.Set("http://10.1.1.166/ParkPayWebApi/api/login",header,"POST",value);
            mTask.Start();
        }
    }

}
