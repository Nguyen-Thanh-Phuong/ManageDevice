package com.danang.managedevice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.danang.managedevice.MainActivity
import com.danang.managedevice.Model.Account.AccountModel
import com.danang.managedevice.Model.Account.Login
import com.danang.managedevice.Object.Account
import com.danang.managedevice.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),
    Login {
    val SUCCESS ="Sucess";
    val Failer = "Fail"
    var instances = AccountModel.instance;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnSubmitLogin.setOnClickListener {
            if(edtUser.text.toString().isEmpty() || edtPass.text.toString().isEmpty())
            {
                Toast.makeText(this, "Please input Username of Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }
            instances.apply {
                account  =Account(null,null,null,edtPass.text.toString(),null,edtUser.text.toString());
                mode = AccountModel.Model.CHECK;
                login=this@LoginActivity;
            }
            instances.exec();
        }
        supportActionBar?.hide();
    }

    override fun isLogin(msg:String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        if(msg==SUCCESS)
            startActivity(Intent(this,MainActivity::class.java))
    }


}