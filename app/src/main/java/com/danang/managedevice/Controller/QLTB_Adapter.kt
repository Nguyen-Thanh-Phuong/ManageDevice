package com.danang.managedevice.Controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.danang.managedevice.Object.QLTB
import com.danang.managedevice.R
import java.util.*

class QLTB_Adapter(var context: Context,var listName:MutableList<String>,var listValue:MutableList<String>,var listResImg:MutableList<Int>) : BaseAdapter() {
    private lateinit var dataList:QLTB


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_qltb,null);
        view.findViewById<TextView>(R.id.textView2).text = listName[p0];
        view.findViewById<TextView>(R.id.textView3).text = listValue[p0]
        view.findViewById<ImageView>(R.id.imageView7).setImageResource(listResImg[p0])
        return view;
    }

    override fun getItem(p0: Int): Any {
        return listValue[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0;
    }

    override fun getCount(): Int {
        return listValue.size
    }
    companion object
    {
        fun getAdapter(qltb: QLTB,context: Context):QLTB_Adapter
        {
            val listName = mutableListOf<String>()
            val listValue = mutableListOf<String>()
            val listResimg = mutableListOf<Int>()

            listName.add("Tên đơn vị :");
            listValue.add(if(qltb.tendonvi.isNotEmpty())qltb.tendonvi else "(Chưa Nhập)");
            listResimg.add(R.drawable.customer);

            listName.add("Người sử dụng :");
            listValue.add(if(qltb.nguoisd.isNotEmpty())qltb.nguoisd!! else "(Chưa Nhập)");
            listResimg.add(R.drawable.admin_with_cogwheels);

            listName.add("Loại Máy :");
            listValue.add(if(qltb.loaimay.isNotEmpty())qltb.loaimay else "(Trống)");
            listResimg.add(R.drawable.conveyor_belt);

            listName.add("Ngày Cấp :");
            listValue.add(if(qltb.ngaycap!!.isNotEmpty())qltb.ngaycap else "(Trống)");
            listResimg.add(R.drawable.admin_with_cogwheels);

            listName.add("Nơi Cấp :");
            listValue.add(if(qltb.noicap.isNotEmpty())qltb.noicap else "(Trống)");
            listResimg.add(R.drawable.placeholder);

            listName.add("Nơi Mua :");
            listValue.add(if(qltb.nguoinhap!!.trim().isNotEmpty()) qltb.nguoinhap else "(Trống)");
            listResimg.add(R.drawable.admin_with_cogwheels);

            if(qltb.manhom.isNotEmpty())
            {
                listName.add("Nhóm TB : ");
                listValue.add(if(qltb.manhom.isNotEmpty()) qltb.manhom else "(Chưa Nhập)")
                listResimg.add(R.drawable.teamwork_devices);

                if (qltb.manhom.toLowerCase(Locale.ROOT).equals("computer"))
                {
                    //ad
                    listName.add("AD : ");
                    listValue.add(qltb.ad);
                    listResimg.add(R.drawable.admin_with_cogwheels);
                    //Ban quyen
                    listName.add("Bản Quyền : ");
                    listValue.add(qltb.banquyen.toString());
                    listResimg.add(R.drawable.source);
                    //Virus
                    listName.add("Virut :");
                    listValue.add(if(qltb.virut)"Có" else "Không");
                    listResimg.add(R.drawable.protection);
                    //Domain
                    listName.add("Domain :");
                    listValue.add(if(qltb.domain)"Có" else "Không");
                    listResimg.add(R.drawable.click);
                    //Ten May
                    listName.add("Tên Máy : ");
                    listValue.add(qltb.tenmay);
                    listResimg.add(R.drawable.devices);
                }
            }

            listName.add("Ngày Mua :");
            listValue.add(if(qltb.ngaymua!!.isEmpty())qltb.ngaymua else "(Trống)");
            listResimg.add(R.drawable.placeholder);

            listName.add("Mục Đích :");
            listValue.add(if(qltb.mucdich.isNotEmpty())qltb.mucdich else "(Trống)");
            listResimg.add(R.drawable.target);

            listName.add("Ngày Mua :");
            listValue.add(if(qltb.ngaymua.isNotEmpty())qltb.mucdich else "(Trống)");
            listResimg.add(R.drawable.writing);

            listName.add("Mã Loại :");
            listValue.add(if(qltb.maloai.isNotEmpty())qltb.maloai else "(Trống)");
            listResimg.add(R.drawable.type);

            if(qltb.maTsan!=null && !qltb.maTsan!!.trim().isEmpty())
            {
                listName.add("Mã Tài Sản :");
                listValue.add(qltb.maTsan!!);
                listResimg.add(R.drawable.gold_bars);
            }
            listName.add("Nhóm Đối Tượng :");
            listValue.add(if(qltb.maNhomdt.isEmpty())qltb.maNhomdt else "Khác");
            listResimg.add(R.drawable.teamwork_devices);

            return QLTB_Adapter(context,listName,listValue,listResimg)
        }
    }
}