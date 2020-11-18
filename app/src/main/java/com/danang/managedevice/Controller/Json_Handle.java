package com.danang.managedevice.Controller;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class Json_Handle {

    public static Object to_Object(String json_String, Type type)
    {
        return new Gson().fromJson(json_String,type);
    }
}
