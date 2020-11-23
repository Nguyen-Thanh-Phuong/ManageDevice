package com.danang.managedevice.ui;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.danang.managedevice.Controller.Adapter_Recycler;
import com.danang.managedevice.Controller.Animation_View;
import com.danang.managedevice.Controller.EdtLayout_Handle;
import com.danang.managedevice.Controller.NhomDT.SendCheckedData;
import com.danang.managedevice.Controller.Send_To_View;
import com.danang.managedevice.MainActivity;
import com.danang.managedevice.Model.DM_NhomDT_model;
import com.danang.managedevice.Model.DM_NhomTB_model;
import com.danang.managedevice.Model.SendData;
import com.danang.managedevice.Object.DMNhomDT;
import com.danang.managedevice.Object.DMNhomTB;
import com.danang.managedevice.R;

import java.util.ArrayList;
import java.util.List;

public class DM_NhomTB_Fragment extends Fragment implements Send_To_View, SendData, SendCheckedData {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private static final String SUBMIT = "SUBMIT";
    private static final String EDIT = "UPDATE";

    Animation_View an_TB,an_DT;
    Adapter_Recycler adapterTB,adapterDT;

    List<DMNhomDT> listCheckDT = new ArrayList<DMNhomDT>();
    View root;
    RecyclerView recyclerTB,recyclerDT;
    EditText edt_code,edt_name,edt_dvt,edt_type,edt_makt;
    Button btn_Submit;
    ImageView imgView;

    public DM_NhomTB_Fragment() {
    }
    public static DM_NhomTB_Fragment newInstance(String param1, String param2) {
        DM_NhomTB_Fragment fragment = new DM_NhomTB_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        MainActivity activity = (MainActivity) getActivity();
//        activity.sendData(this);
        root = inflater.inflate(R.layout.fragment_d_m__nhom_tb, container, false);
        edt_code = EdtLayout_Handle.Companion.getIns().setText_Hint(root.findViewById(R.id.code_panel),"Nhập Mã Đối Tượng","Input Devices Code");
        edt_name = EdtLayout_Handle.Companion.getIns().setText_Hint(root.findViewById(R.id.name_panel),"Nhập Tên Đối Tượng","Input Devices Name");
        edt_dvt = EdtLayout_Handle.Companion.getIns().setText_Hint(root.findViewById(R.id.dvt_panel),"Nhập Đơn vị tính","Input Devices Dvt");
        edt_type = EdtLayout_Handle.Companion.getIns().setText_Hint(root.findViewById(R.id.type_panel),"Nhập Loại","Input Devices Type");
        edt_makt = EdtLayout_Handle.Companion.getIns().setText_Hint(root.findViewById(R.id.makt_panel),"Nhập Mã Kt","Input Devices Makt");
        btn_Submit = root.findViewById(R.id.btn);

        recyclerTB = root.findViewById(R.id.recycler);
        recyclerTB.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerDT = root.findViewById(R.id.rcc_NhomDT);
        recyclerDT.setLayoutManager(new LinearLayoutManager(getContext()));
//
        imgView = root.findViewById(R.id.img_up_down);

        /*
         * Database*/
        DM_NhomTB_model.setSend_Refresh(this);
        /*
            SetDataListBox
         */

//        DMNhomTB dt = new DMNhomTB("CDM",edt_name.getText()+"",edt_dvt.getText()+"",0,
//                edt_makt.getText().toString(),edt_code.getText().toString());
//        new DM_NhomTB_model(dt,DM_NhomTB_model.Status.GETWITHCODE,this);
        new DM_NhomTB_model(DM_NhomTB_model.Status.GETALL,this::sendData);
        new DM_NhomDT_model(DM_NhomDT_model.Status.GETALL,this::sendData);
//        /*
//            SetData button
//        */
        btn_Submit.setText(SUBMIT);
        btn_Submit.setOnClickListener(view -> {
            DMNhomTB data = new DMNhomTB(edt_code.getText()+"",edt_name.getText()+"",edt_dvt.getText()+"",0,
                    edt_makt.getText().toString(), listCheckDT);
            if(btn_Submit.getText().toString().equals(SUBMIT))
                new DM_NhomTB_model(data, DM_NhomTB_model.Status.INSERT,getContext());
            else
                new DM_NhomTB_model(data, DM_NhomTB_model.Status.UPDATE,getContext());
        });
        return root;
    }

    @Override
    public void sendDataToView(Object obj) {
        btn_Submit.setText(EDIT);
        edt_code.setEnabled(false);
        DMNhomTB nhom_tb = (DMNhomTB) obj;
        an_TB.Click();
        edt_code.setText(nhom_tb.getManhom());
        edt_name.setText(nhom_tb.getTenNhomtbi());
        edt_dvt.setText(nhom_tb.getDvt());
        edt_type.setText(nhom_tb.getLoai()+"");
        edt_makt.setText(nhom_tb.getMakt());
    }


    @Override
    public void refersh() {
        new DM_NhomTB_model(DM_NhomTB_model.Status.GETALL,this::sendData);
    }

    @Override
    public void sendData(Object obj){
        ArrayList<Object> list = (ArrayList<Object>) obj;
        if(list.size()>0)
        {
            if(list.get(0) instanceof DMNhomTB)
            {
                if(adapterTB ==null)
                {
                    adapterTB = new Adapter_Recycler(getContext(),list,R.layout.nhom_tb_item,this);
                    recyclerTB.setAdapter(adapterTB);
                    an_TB = new Animation_View(recyclerTB,imgView);
                }
                else
                {
                    adapterTB.setList(list);
                    adapterTB.notifyDataSetChanged();
                }
            }
            else
            {
                if(adapterDT ==null)
                {
                    adapterDT = new Adapter_Recycler(getContext(),list,R.layout.nhom_dt_item,this);
                    recyclerDT.setAdapter(adapterDT);
                    an_DT = new Animation_View(recyclerDT,root.findViewById(R.id.imgDT));
                }
                else
                {
                    adapterDT.setList(list);
                    adapterDT.notifyDataSetChanged();
                }
            }
        }
    }
    public boolean hide()
    {
        return (an_DT.hide() || an_TB.hide());
    }
    @Override
    public void sendCheckedObj(Object obj, boolean checked) {
        if(checked)
            listCheckDT.add((DMNhomDT) obj);
        else
            listCheckDT.remove(obj);
    }
}