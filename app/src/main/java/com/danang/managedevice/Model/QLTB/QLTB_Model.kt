package com.danang.managedevice.Model.QLTB

import com.danang.managedevice.Controller.SendDataMdToView
import com.danang.managedevice.Model.ComunicationData
import com.danang.managedevice.Model.Database
import com.danang.managedevice.Object.QLTB
import com.google.gson.Gson
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import java.lang.Exception

class QLTB_Model:ComunicationData {
    private lateinit var state:State
    private lateinit var qltb: QLTB;
    private lateinit var listResult:MutableList<QLTB>
    public lateinit var sendDataToView:SendDataMdToView;
    private lateinit var result:QLTB;
    companion object
    {
        val instances = QLTB_Model();
    }
    fun exec(state: State,qltb: QLTB)
    {
        this.qltb = qltb;
        this.state = state;
        val db = Database(this,when(state)
        {
            State.GET_WITH_CODE->"search_QLTB_With_maql";
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
                value = qltb.maql;
            })
            return true;
        }catch (ex: Exception)
        {
            return false;
        }
    }

    override fun fillData(soapObject: SoapObject): Boolean {
        when(state)
        {
            State.GET_WITH_CODE->
                if(!fill_MaQL(soapObject))return false;
        }
        return true;
    }

    private fun fillListCTTBResult(result: String)
    {
        val gson = Gson();
        this.result = gson.fromJson<QLTB>(result,QLTB::class.java);
    }
    override fun getData(result: String) {
        when(state)
        {
            State.GET_WITH_CODE ->fillListCTTBResult(result);
        }
        sendDataToView.sendData(this.result);
    }
}