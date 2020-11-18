package com.danang.managedevice.Model

import android.os.AsyncTask
import android.util.Log
import org.ksoap2.SoapEnvelope
import org.ksoap2.SoapFault
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import java.lang.Exception

class Database(private var commData:ComunicationData?=null, private var METHOD_WEB_SERVICES:String ="") :AsyncTask<String,Void,String>(){
    var resultString:String=""
    companion object{
        final val networkTimeOut = 60*1000;
        //if you using Localhost . you must using 10.0.2.2 address ip v4
        private val URL = "http://10.0.2.2/Manage/Services.asmx?WSDL";
        private val NAMESPACE = "http://tempuri.org/";
    }
    override fun doInBackground(vararg p0: String?): String {
        if(METHOD_WEB_SERVICES.isEmpty() || commData ==null)return "";
        val SOAP_ACTION = NAMESPACE + METHOD_WEB_SERVICES;
        var requestObj = SoapObject(NAMESPACE,METHOD_WEB_SERVICES);

        if(!commData!!.fillData(requestObj))return "";

        val evelone = SoapSerializationEnvelope(SoapEnvelope.VER11)
        evelone.dotNet =true;
        evelone.setOutputSoapObject(requestObj);
        val httpTransportSE = HttpTransportSE(URL, networkTimeOut);
        try {
            httpTransportSE.call(SOAP_ACTION,evelone);
            val obj = evelone.bodyIn as SoapObject
            resultString = obj.getProperty(0).toString();
        }catch (ex:Exception)
        {
            resultString="";
        }
        return  resultString;
    }

    override fun onPostExecute(result: String?) {
        commData?.getData(resultString);
        super.onPostExecute(result)
    }
}