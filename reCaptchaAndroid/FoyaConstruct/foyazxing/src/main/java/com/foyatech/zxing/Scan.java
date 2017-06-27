package com.foyatech.zxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.foyatech.zxing.zxing.activity.CaptureActivity;
import com.foyatech.zxing.zxing.camera.CameraManager;
import com.foyatech.zxing.zxing.view.ViewfinderView;

/**
 * Created by Admin on 2017/3/6.
 */

public class Scan extends CaptureActivity implements SurfaceHolder.Callback ,View.OnClickListener {

    public static Intent createIntent(Context context) {
        return new Intent(context, Scan.class);
    }

    public Activity getActivity() {
        return this;
    }

    private Button mCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        init(this, (SurfaceView) findViewById(R.id.svCameraScan), (ViewfinderView) findViewById(R.id.vfvCameraScan));

        initView();
        initData();
        initEvent();

    }

    public void initView() {
        mCancelButton = (Button)findViewById(R.id.btn_cancel_scan);
        mCancelButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private boolean isOpen = false;

    private void switchLight(boolean open) {
        if (open == isOpen) {
            return;
        }
        isOpen = CameraManager.get().switchLight(open);
    }

    public void initData() {//必须调用

    }


    public void initEvent() {//必须调用

       // findViewById(R.id.ivCameraScanLight).setOnClickListener(this);
    }


    public void onReturnClick(View v) {
        finish();
    }
    public void onForwardClick(View v) {
       // CommonUtil.toActivity(context, QRCodeActivity.createIntent(context, 1));
    }

    //系统自带监听方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    @Override
    public void onClick(View v) {
        /*
        switch (v.getId()) {
            case : "light":
                switchLight(! isOpen);
                break;
            default:
                break;
        }*/
    }


    public boolean isAlive() {
        return false;
    }

    public boolean isRunning() {
        return false;
    }

}
