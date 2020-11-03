package com.danang.managedevice.Object


import com.google.gson.annotations.SerializedName

data class Account(
    var loai: Int?,
    var madv: String?,
    @SerializedName("MoTa")
    var moTa: String?,
    @SerializedName("Password")
    var password: String?,
    @SerializedName("Role")
    var role: String?,
    @SerializedName("UserName")
    var userName: String?
)