package com.danang.managedevice.Model.QL_TB_CT

import android.util.Log
import com.danang.managedevice.Model.ComunicationData
import com.danang.managedevice.Model.Database
import com.danang.managedevice.Object.QL_TBi_CT
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import java.lang.Exception

class QL_TB_CT_Model :ComunicationData{
    private lateinit var state:State
    private lateinit var qlTbiCt: QL_TBi_CT;
    private lateinit var listResult:MutableList<QL_TBi_CT>
    private var sendData: SendDT_CTTB? =null;
    companion object
    {
        val instances = QL_TB_CT_Model();
    }
    fun exec(state: State,qlTbiCt: QL_TBi_CT,sendData:SendDT_CTTB?)
    {
        this.sendData = sendData;
        this.qlTbiCt = qlTbiCt;
        this.state = state;
        val db = Database(this,when(state)
        {
            State.GET_WITH_CODE->"search_CTTB_With_maql";
            else -> ""
        });
        db.execute();
    }

    private constructor()
    enum class State
    {
        NONE,
        INSERT,
        UPDATE,
        DELETE,
        GET_WITH_CODE
    }

    private fun fill_MaQL(soapObject: SoapObject): Boolean {
        try {
            soapObject.addProperty(PropertyInfo().apply {
                setName("maql")
                setType(String.javaClass);
                value = qlTbiCt.maql;
            })
            return true;
        }catch (ex:Exception)
        {
            return false;
        }
    }

    override fun fillData(soapObject: SoapObject): Boolean {
        when(state)
        {
            State.GET_WITH_CODE->
                return fill_MaQL(soapObject);
        }
        return true;
    }

    private fun fillListCTTBResult(result: String)
    {
        val gson = Gson();
        val arrayTypeResult = object : TypeToken<MutableList<QL_TBi_CT>>(){}.type
        listResult = gson.fromJson(result,arrayTypeResult);
        sendData?.sendData(listResult);
    }
    override fun getData(result: String) {
        when(state)
        {
            State.GET_WITH_CODE ->fillListCTTBResult(result);
        }
    }
}