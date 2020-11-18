package com.danang.managedevice.Model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.danang.managedevice.Controller.Json_Handle;
import com.danang.managedevice.Controller.Send_To_View;
import com.danang.managedevice.Object.DMNhomDT;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

public class DM_NhomDT_TB_model implements ComunicationData {
    //public static DM_NhomDT_TB_model instances = new DM_NhomDT_TB_model();
    private DMNhomDT nhomDT;
    private DM_NhomDT_TB_model.Status stt;
    private SendData send =null;
    private static Send_To_View send_Refresh;
    private Context context;

    public static Send_To_View getSend_Refresh() {
        return send_Refresh;
    }

    public static void setSend_Refresh(Send_To_View send_) {
        send_Refresh = send_;
    }

    public DM_NhomDT_TB_model(DMNhomDT nhomDT, DM_NhomDT_TB_model.Status stt, Context context) {
        this.nhomDT = nhomDT;
        this.stt = stt;
        this.context = context;
        exec();
    }
    public DM_NhomDT_TB_model(DM_NhomDT_TB_model.Status stt, SendData send) {
        this.stt = stt;
        this.send = send;
        exec();
    }

    public DM_NhomDT_TB_model(DMNhomDT nhomDT, DM_NhomDT_TB_model.Status stt, SendData send) {
        this.nhomDT = nhomDT;
        this.stt = stt;
        this.send = send;
        exec();
    }

    public SendData getSend() {
        return send;
    }

    public void setSend(SendData send) {
        this.send = send;
    }




    @Override
    public boolean fillData(@NotNull SoapObject soapObject) {

        switch (stt)
        {
            case DELETE:
                return fillMaNhom(soapObject);
            case INSERT:
            case UPDATE:
                return insert(soapObject);
            case GETWITHCODE:
                return fillMaNhom(soapObject);
            case GETALL:
                return true;
        }
        return false;
    }
    private void exec()
    {
        String METHOD_NAME="";
        switch (stt)
        {
            case INSERT:
                METHOD_NAME = "DM_NhomDT_insert";
                break;
            case UPDATE:
                METHOD_NAME = "DM_NhomDT_update";
                break;
            case GETALL:
                METHOD_NAME = "DM_NhomDT_getAll";
                break;
            case DELETE:
                METHOD_NAME = "DM_NhomDT_delete";
                break;
            case GETWITHCODE:
                METHOD_NAME ="DM_NhomDT_getWithCode";
                break;
        }

        Database db = new Database(this,METHOD_NAME);
        db.execute();
    }



    @Override
    public void getData(@NotNull String result) {
        Object obj = null;
        switch (stt)
        {
            case INSERT:
            case DELETE:
            case UPDATE:
                Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                if(send_Refresh!=null)
                    send_Refresh.refersh();
                return;
            case GETALL:
                try
                {
                    obj  = Json_Handle.to_Object(result,new TypeToken<ArrayList<DMNhomDT>>(){}.getType());
                }catch (Exception er)
                {
                    Log.d("ERROR",er.toString());
                }
                break;
            case GETWITHCODE:
                break;
        }
        if(send!=null)
            send.sendData(obj);
    }

    public enum Status
    {
        INSERT,
        UPDATE,
        DELETE,
        GETWITHCODE,
        GETALL
    }


    private boolean insert(SoapObject soap)
    {
        return (fillMaNhom(soap) && fillTenNhom(soap));
    }


    private boolean fillMaNhom(SoapObject soap)
    {
        try
        {
            PropertyInfo info = new PropertyInfo();

            info.setName("manhomDT");
            info.setType(String.class);
            info.setValue(nhomDT.getMaNhomdt());

            soap.addProperty(info);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    private boolean fillTenNhom(SoapObject soap)
    {
        try
        {
            PropertyInfo info = new PropertyInfo();

            info.setName("tennhomDT");
            info.setType(String.class);
            info.setValue(nhomDT.getTenDt());

            soap.addProperty(info);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}

