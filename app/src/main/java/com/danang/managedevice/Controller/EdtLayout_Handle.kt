package com.danang.managedevice.Controller

import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.danang.managedevice.R

class EdtLayout_Handle {
    companion object
    {
        val ins = EdtLayout_Handle();
    }

    fun setText_Hint(view:View,nameTxv:String,nameHint:String):EditText
    {
        view.findViewById<TextView>(R.id.txv).text = nameTxv;
        val edt = view.findViewById<EditText>(R.id.edt);
        edt.hint = nameHint;
        edt.isEnabled = true;
        return edt;
    }

}