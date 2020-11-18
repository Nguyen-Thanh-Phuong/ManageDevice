package com.danang.managedevice.Controller.NhomTB;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.danang.managedevice.Controller.Holder.Holder_Template;
import com.danang.managedevice.Controller.Other;
import com.danang.managedevice.Controller.Send_To_View;
import com.danang.managedevice.Model.DM_NhomTB_model;
import com.danang.managedevice.Object.DMNhomDT;
import com.danang.managedevice.Object.DMNhomTB;
import com.danang.managedevice.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

public class NhomTB_Holder extends Holder_Template {
    private static final String DVT = "Đơn Vị Tính : ";
    private static final String TYPE = "Loại : ";
    private static final String MAKT = "Mã kiểm tra : ";
    private static final String MANHOMDT = "Nhóm Đt : ";
    
    private TextView txv_title,txv_type,txv_dvt,txv_makt,txvNhomDT;
    private TextView txv_content;
    private ImageView viewEdit,viewDel;
    private DMNhomTB nhom_tb;
    private ListView list;
    private int position;

    public NhomTB_Holder(@NonNull View itemView, Object obj_View, Context context) {
        super(itemView,obj_View,context);
        txv_title = itemView.findViewById(R.id.txv_nhomdt_title);
        txv_content = itemView.findViewById(R.id.txv_nhomdt_content);
        txv_dvt = itemView.findViewById(R.id.txv_dvt);
        txv_type = itemView.findViewById(R.id.txv_Type);
        txv_makt = itemView.findViewById(R.id.txv_makt);
        list = itemView.findViewById(R.id.list);
        txvNhomDT = itemView.findViewById(R.id.textView4);

        viewEdit = itemView.findViewById(R.id.imgEdit);
        viewDel = itemView.findViewById(R.id.imgDel);
        viewEdit.setOnClickListener(v ->
        {
            if(nhom_tb!=null)
            {
                Send_To_View sendData = (Send_To_View)obj_View;
                sendData.sendDataToView(nhom_tb);
            }
        });
        viewDel.setOnClickListener(v ->
        {
            new AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Do you want Delete \" " + nhom_tb.getTenNhomtbi() +"\" ?")
                    .setIcon(R.drawable.close)
                    .setPositiveButton(android.R.string.yes,(dialogInterface, i) ->
                    {
                        new DM_NhomTB_model(nhom_tb, DM_NhomTB_model.Status.DELETE,context);
                    })
                    .setNegativeButton(android.R.string.no,null).show();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void bindData(Object object,int position) {
        DMNhomTB data = (DMNhomTB) object;
        this.position = position;
        this.nhom_tb = data;
        txv_title.setText(data.getManhom());
        txv_content.setText(data.getTenNhomtbi());
        txv_dvt.setText(DVT + data.getDvt());
        txv_type.setText(TYPE + data.getLoai().toString());
        txv_makt.setText(MAKT + data.getMakt());
        String nhomdt="";
        List<DMNhomDT> nhomdt_list = data.getDMNhomDT();
        if(nhomdt_list!=null)
            if(nhomdt_list.size()==1)
                txvNhomDT.setText(MANHOMDT + nhomdt_list.get(0).getTenDt());
            else
            {
                list.setVisibility(View.VISIBLE);
                ArrayList<String> lsSub = new ArrayList<String>();
                try
                {
                    for(DMNhomDT dt : nhomdt_list)
                    {
                        lsSub.add(dt.getTenDt());
                    }
                    ArrayAdapter<String> mListViewAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,lsSub);
                    Other.setHeightDynamicLsView(mListViewAdapter,list);
                    list.setAdapter(mListViewAdapter);
                }catch (Exception ex)
                {
                    Log.d("MESS_ERROR",ex.getMessage());
                }
            }

        else
        {
            txvNhomDT.setText(MANHOMDT + "Khác");
            list.setVisibility(View.GONE);
        }
//        txv_manhomDT.setText(MANHOMDT);
    }
}
