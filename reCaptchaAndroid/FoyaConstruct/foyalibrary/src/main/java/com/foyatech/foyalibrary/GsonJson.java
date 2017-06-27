package com.foyatech.foyalibrary;

import com.google.gson.Gson;

public class GsonJson {
    private Gson gson = new Gson();
    public String toJsonString ( Object classModel )
    {
        String res = gson.toJson(classModel);
        return  res;
    }

    public Object toJsonObject ( String jsonStr  , Class typeClass)
    {
        Object res = gson.fromJson(jsonStr, typeClass);
        return res;
     }
}
