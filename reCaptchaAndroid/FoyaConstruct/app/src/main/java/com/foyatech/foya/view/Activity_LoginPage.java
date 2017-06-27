package com.foyatech.foya.view;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.foyatech.foya.R;
import com.foyatech.foyalibrary.*;
import com.foyatech.foya.presenter.presenterInterface.ILoginPresenter;
import com.foyatech.foya.presenter.LoginPresenterCompl;
import com.foyatech.foya.view.viewInterface.ILoginView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.safetynet.SafetyNet;

import me.leolin.shortcutbadger.ShortcutBadger;

public class Activity_LoginPage extends AppCompatActivity implements ILoginView {

    private String Tag = "Activity_LoginPage";
    private View mView;
    private Activity mActivity;

    private Button mLoginButton;
    private EditText mAccountEditText;
    private EditText mPasswordEditText;

    private AppDataSharePreferences mAppData;
    private ILoginPresenter LoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(com.foyatech.foya.R.layout.activity_login_page);
        mActivity = this;
        mView = this.findViewById(android.R.id.content);

        //Find View
        mLoginButton = (Button)findViewById(R.id.loginPage_loginButton);
        mAccountEditText = (EditText)findViewById(R.id.loginPage_accEdit);
        mPasswordEditText = (EditText)findViewById(R.id.loginPage_passwordEdit);

        //Set OnclickEven ...
        mLoginButton.setOnClickListener(OnClick_Login);

        //Init Logic
        mAppData = new AppDataSharePreferences(this);
        LoginPresenter = new LoginPresenterCompl(this,this);


    }

    //region OnClick Funcation
    Button.OnClickListener OnClick_Login = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            LoginPresenter.DoLogin(mAccountEditText.getText().toString(),mPasswordEditText.getText().toString());
        }
    };
    //endregion

    @Override
    public void onLoginResult(Boolean result)
    {
        if (result){
            mAppData.SetAppData("Name","Jason");
            Intent loginSys = new Intent(Activity_LoginPage.this,Activity_MainPage.class);
            startActivity(loginSys);
        }
        else {

            /* 以下是 推波通知與Icon通知系統 , 每種類型的ICon通知都不一樣 ! */

            /* ICon通知 */
            int badgeCount = 10;
            ShortcutBadger.applyCount(mActivity, badgeCount);; //for 1.1.3

            Intent resultIntent = new Intent(mActivity, Activity_LoginPage.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(
                    mActivity,
                    0,
                    resultIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            /* 推波通知 */
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mActivity);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle("My notification");
            mBuilder.setContentText("Hello World!");
            mBuilder.setContentIntent(resultPendingIntent);

            int mNotificationId = 0;
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(mNotificationId, mBuilder.build());

            Snackbar.make(mView, "Login fail", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //ShortcutBadger.removeCount(mActivity);
    }
}
