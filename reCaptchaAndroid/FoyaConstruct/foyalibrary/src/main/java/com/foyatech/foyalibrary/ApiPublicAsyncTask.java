package com.foyatech.foyalibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.foyatech.foyalibrary.model.ApiRestResult;
import java.util.HashMap;

public class ApiPublicAsyncTask {

    /*
            提供給 Restful Api 使用 :
            1. URL
            2. Mehod
            3. Request Json Object
            4. Return Json Object
       */

    private ApiAsyncTask mApiAsyncTask;
    private boolean mPauseUI;
    private String  mPauseMsg;

    private Context mContext;

    private String mUrl;
    private HashMap mHeader;
    private String mMethod;
    private Object mJsonObj;

    public  ApiPublicAsyncTask(Context context)
    {
        mContext = context;
        mApiAsyncTask = null;
        mPauseUI = false;
        mPauseMsg = "Please wait";
    }

    public ApiPublicAsyncTask(Context context, boolean pauseUI , String pauseMsg )
    {
        mContext = context;
        mApiAsyncTask = null;
        mPauseUI = pauseUI;
        mPauseMsg = pauseMsg ;
    }

    public void Set(String url, HashMap header, String method, Object body)
    {
        mUrl = url;
        mHeader = header;
        mMethod = method;
        mJsonObj = body;
    }

    public void Start(){

        GsonJson jsonTool = new GsonJson();
        String dataStr = jsonTool.toJsonString(mJsonObj);
        if(mApiAsyncTask==null) {
            mApiAsyncTask = new ApiAsyncTask(dataStr);
            mApiAsyncTask.execute();
        }
    }

    public void ProcessFinish(ApiRestResult result)
    {
        //Need to override the finish thing need to be execute
    }

    public class ApiAsyncTask extends AsyncTask<Void, Void, ApiRestResult> {

        private ProgressDialog Dialog;
        private String JsonData;

        ApiAsyncTask(String jsonData)
        {
            JsonData = jsonData;
        }

        @Override
        protected void onPreExecute() {
            //發送前
            if(mPauseUI) {
                Dialog = new ProgressDialog(mContext);
                Dialog.setMessage(mPauseMsg);
                Dialog.setCancelable(false);
                Dialog.show();
            }
        }

        @Override
        protected ApiRestResult doInBackground(Void... params) {
            ApiRestResult result;
            try{
                result = new WebApiUtil(mUrl,mHeader,mMethod,JsonData).DoWebApi();
            }catch(Exception ex){
                return new ApiRestResult(0,ex.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(ApiRestResult apiRestResult) {
            if(mPauseUI) Dialog.dismiss();
            mApiAsyncTask = null;
            ProcessFinish(apiRestResult);
        }

        @Override
        protected void onCancelled() {
            if(mPauseUI) Dialog.dismiss();
            mApiAsyncTask = null;
        }
    }
}
