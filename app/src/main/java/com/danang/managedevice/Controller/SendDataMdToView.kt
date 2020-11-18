package com.danang.managedevice.Controller

import com.danang.managedevice.Object.QLTB

interface SendDataMdToView {
    fun sendData(qltb: QLTB)
}