package com.danang.managedevice.Model.Account

import com.danang.managedevice.Model.ComunicationData
import com.danang.managedevice.Model.Database
import com.danang.managedevice.Object.Account
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import java.lang.Exception

class AccountModel: ComunicationData {
    var login: Login?=null;
    var mode: Model =
        Model.NONE;
    var account: Account?=null;
    companion object
    {
        val instance = AccountModel();
    }
    private constructor(){}
    fun exec()
    {
        // i am sory .. Database class cannot using singeton pattern
        var db: Database =
            Database(this,when(mode)
            {
                Model.CHECK -> "login";
                else -> ""
            });
        //implement Database Web Serices
        db.execute();
    }
    enum class Model
    {
        NONE,
        INSERT,
        UPDATE,
        DELETE,
        CHECK
    }
    private fun fillUsername(soapObject: SoapObject): Boolean {
        try {
            soapObject.addProperty(PropertyInfo().apply {
                setName("username");
                setType(String.javaClass)
                value = account!!.userName
            })
            return true;
        }catch (ex:Exception)
        {
            return false;
        }
    }
    private fun fillPassword(soapObject: SoapObject): Boolean {
        try {
            soapObject.addProperty(PropertyInfo().apply {
                setName("pass");
                setType(String.javaClass)
                value = account!!.password
            })
            return true;
        }catch (ex:Exception)
        {
            return false;
        }
    }
    private fun fillLogin(soapObject: SoapObject): Boolean {
        //Add parameter of Web Services Method
        if(!fillPassword(soapObject) || !fillUsername(soapObject))return false
        return true;
    }
    override fun fillData(soapObject: SoapObject): Boolean {
        when(mode)
        {
            Model.CHECK ->
                if(!fillLogin(soapObject))return false
        }
        return true;
    }

    override fun getData(result: String) {
            if(mode== Model.CHECK)
                login?.isLogin(result);
    }

}