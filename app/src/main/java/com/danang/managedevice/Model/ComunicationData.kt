package com.danang.managedevice.Model

import org.ksoap2.serialization.SoapObject

interface ComunicationData {
    fun fillData(soapObject: SoapObject):Boolean
    fun getData(result:String);
}