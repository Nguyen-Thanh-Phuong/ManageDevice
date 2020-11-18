package com.danang.managedevice.Controller.CTTB

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.danang.managedevice.Object.QL_TBi_CT
import com.danang.managedevice.R
import com.ramotion.foldingcell.FoldingCell

class CTTB_Adapter (var context: Context, var list: MutableList<QL_TBi_CT>) : BaseAdapter() {
    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_list_scan_normal,null);
        //Fill Data
        //View Controller
        val fc =view as FoldingCell
        fc.setOnClickListener {
            fc.toggle(false);
        }
        val data_item = list[p0];
//        val btn = view.findViewById<Button>(R.id.btn_fix);
        //View Normal
        view.findViewById<TextView>(R.id.txv_Devices_Code).text = data_item.matb;
        view.findViewById<TextView>(R.id.txvType).text = data_item.loai;
        view.findViewById<TextView>(R.id.txv_desciption).text = data_item.ghichu;
        //View Clicked
        view.findViewById<TextView>(R.id.txvSo_Che_Tao).text = data_item.sochetao
        view.findViewById<TextView>(R.id.txv_parameter).text = data_item.thongso
        view.findViewById<TextView>(R.id.txv_time_shied).text = data_item.thoihanbh
        view.findViewById<TextView>(R.id.txv_where_map).text = data_item.noisx
        view.findViewById<TextView>(R.id.txv_user_input).text = "";
        view.findViewById<TextView>(R.id.txv_date_input).text = data_item.ngaynhap

        return view
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0;
    }

    override fun getCount(): Int {
        return list.size;
    }
}