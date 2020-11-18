package com.danang.managedevice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.danang.managedevice.Model.SendData
import com.danang.managedevice.Object.QL_TBi_CT
import com.danang.managedevice.ui.DM_NhomTB_Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ramotion.foldingcell.FoldingCell

class MainActivity : AppCompatActivity(),SendData {
    private lateinit var navController:NavController;
    private var frag_DMNhomTB:DM_NhomTB_Fragment?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_root)
        val appbarConfiguration = AppBarConfiguration(setOf(
            R.id.home_fragment,R.id.search_fragment,R.id.profile_fragment,R.id.result_Search_QRFragment
        ))
        setupActionBarWithNavController(navController,appbarConfiguration);

        findViewById<BottomNavigationView>(R.id.menu_nav).setupWithNavController(navController)
        supportActionBar?.hide()
    }

    override fun onBackPressed() {
        val id = navController.currentDestination?.id;
            Log.d("MESS_D","hihi");
        if(id == R.id.DM_NhomTB_Fragment)
            if(frag_DMNhomTB?.hide()!!)
                return;
        super.onBackPressed()
    }

    override fun sendData(obj: Any?) {
        if(obj is DM_NhomTB_Fragment)
        {
            frag_DMNhomTB = obj
        }
    }
}