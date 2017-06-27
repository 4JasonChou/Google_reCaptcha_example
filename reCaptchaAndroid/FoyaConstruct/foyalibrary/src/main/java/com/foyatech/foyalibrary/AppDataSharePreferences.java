package com.foyatech.foyalibrary;


import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

public class AppDataSharePreferences {

    private SharedPreferences settingData;
    private Activity mActivity;
    public AppDataSharePreferences(Activity activity)
    {
        mActivity = activity;

        // 第二個參數表示這些暫存資料皆為私人使用
        settingData = mActivity.getSharedPreferences ("PackageName", 0);
    }

    public void SetAppData(String key,Object data)
    {
        SharedPreferences.Editor PE = settingData.edit();
        if(data.getClass().equals(String.class))
        { PE.putString(key,(String)data); }
        else if (data.getClass().equals(int.class))
        { PE.putInt(key,(int)data); }
        else if (data.getClass().equals(Long.class))
        { PE.putLong(key,(Long)data); }
        else if (data.getClass().equals(Float.class))
        { PE.putFloat(key,(Float)data); }
        else if (data.getClass().equals(Boolean.class))
        { PE.putBoolean(key,(Boolean) data);}
        else
        { Log.e("AppDataSharePreferences","Method is wrong use ! , Object Type didn't accept. (only String,Int,Long,Float,Boolean"); }
        PE.commit();
    }


    public String GetAppData(String key,String defaultData)
    {
        return settingData.getString(key,defaultData);
    }

    public int GetAppData(String key,int defaultData)
    {
        return settingData.getInt(key,defaultData);
    }

    public Boolean GetAppData(String key,Boolean defaultData)
    {
        return settingData.getBoolean(key,defaultData);
    }

    public Float GetAppData(String key,Float defaultData)
    {
        return settingData.getFloat(key,defaultData);
    }

    public Long GetAppData(String key,Long defaultData)
    {
        return settingData.getLong(key,defaultData);
    }

}
