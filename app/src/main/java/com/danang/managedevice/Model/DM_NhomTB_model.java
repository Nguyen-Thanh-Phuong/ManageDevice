package com.danang.managedevice.Model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.InspectableProperty;
import androidx.annotation.IntDef;

import com.danang.managedevice.Controller.Json_Handle;
import com.danang.managedevice.Controller.Send_To_View;
import com.danang.managedevice.Object.DMNhomDT;
import com.danang.managedevice.Object.DMNhomTB;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;

public class DM_NhomTB_model implements ComunicationData {
    //public static DM_NhomTB_model instances = new DM_NhomTB_model();
    private DMNhomTB nhomTB;
    private DMNhomDT nhomDT;
    private DM_NhomTB_model.Status stt;
    private SendData send =null;
    private static Send_To_View send_Refresh;
    private Context context;
    private static int count=0;

    private DM_NhomTB_model(Status stt,DMNhomDT nhomDT,DMNhomTB nhomTB) {
        this.nhomDT = nhomDT;
        this.stt = stt;
        this.nhomTB = nhomTB;
        exec();
    }

    public static Send_To_View getSend_Refresh() {
        return send_Refresh;
    }

    public static void setSend_Refresh(Send_To_View send_) {
        send_Refresh = send_;
    }

    public DM_NhomTB_model(DMNhomTB nhomTB, DM_NhomTB_model.Status stt, Context context) {
        this.nhomTB = nhomTB;
        this.stt = stt;
        this.context = context;
        exec();
    }
    public DM_NhomTB_model(DM_NhomTB_model.Status stt, SendData send) {
        this.stt = stt;
        this.send = send;
        exec();
    }

    public DM_NhomTB_model(DMNhomTB nhomTB, DM_NhomTB_model.Status stt, SendData send) {
        this.nhomTB = nhomTB;
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
            case INSERT_DT:
                return insertDS_TB_DT(soapObject);
        }
        return false;
    }
    private void exec()
    {
        String METHOD_NAME="";
        switch (stt)
        {
            case INSERT:
                METHOD_NAME = "DM_NhomTB_insert";
                break;
            case UPDATE:
                METHOD_NAME = "DM_nhomTB_update";
                break;
            case GETALL:
                METHOD_NAME = "DM_NhomTB_getAll";
                break;
            case DELETE:
                METHOD_NAME = "DM_NhomTB_delete";
                break;
            case GETWITHCODE:
                METHOD_NAME ="DM_NhomTB_getWithCode";
                break;
            case INSERT_DT:
                count=0;
                METHOD_NAME = "DS_NhomTB_DT_insert";
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
                refresh();
                for(DMNhomDT dt : nhomTB.getDMNhomDT())
                    new DM_NhomTB_model(Status.INSERT_DT,dt,nhomTB);
                return;
            case GETALL:
                try
                {
                    obj  = Json_Handle.to_Object(result,new TypeToken<ArrayList<DMNhomTB>>(){}.getType());
                }catch (Exception er)
                {
                    Log.d("ERROR",er.toString());
                }
                break;
            case GETWITHCODE:
                break;
            case INSERT_DT:
                ++count;
                refresh();
                return;
        }
        if(send!=null)
            send.sendData(obj);
    }
    private void refresh()
    {
        if(send_Refresh!=null && nhomTB.getDMNhomDT().size()==count)
            send_Refresh.refersh();
    }
    public enum Status
    {
        INSERT,
        UPDATE,
        DELETE,
        GETWITHCODE,
        GETALL,
        INSERT_DT
    }

    private boolean insertDS_TB_DT(SoapObject soap)
    {
        return (fillMaDT(soap) && fillMaNhom(soap));
    }
    private boolean insert(SoapObject soap)
    {
        return (fillMaNhom(soap) && fillTenNhom(soap) && fillDvt(soap) && fillLoai(soap) && fillMaKt(soap));
    }

    private boolean fillMaDT(SoapObject soap)
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
    private boolean fillMaNhom(SoapObject soap)
    {
        try
        {
            PropertyInfo info = new PropertyInfo();

            info.setName("manhomTB");
            info.setType(String.class);
            info.setValue(nhomTB.getManhom());

            soap.addProperty(info);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    private boolean fillMaNhomDT(SoapObject soap)
    {
        try
        {
            PropertyInfo info = new PropertyInfo();

            info.setName("manhomDT");
            info.setType(String.class);
            info.setValue(nhomTB.getManhom());

            soap.addProperty(info);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    private boolean fillMaKt(SoapObject soap)
    {
        try
        {
            PropertyInfo info = new PropertyInfo();

            info.setName("makt");
            info.setType(String.class);
            info.setValue(nhomTB.getMakt());

            soap.addProperty(info);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    private boolean fillLoai(SoapObject soap)
    {
        try
        {
            PropertyInfo info = new PropertyInfo();

            info.setName("loai");
            info.setType(Integer.class);
            info.setValue(nhomTB.getLoai());

            soap.addProperty(info);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    private boolean fillDvt(SoapObject soap)
    {
        try
        {
            PropertyInfo info = new PropertyInfo();

            info.setName("dvt");
            info.setType(String.class);
            info.setValue(nhomTB.getDvt());

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

            info.setName("ten");
            info.setType(String.class);
            info.setValue(nhomTB.getTenNhomtbi());

            soap.addProperty(info);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}
