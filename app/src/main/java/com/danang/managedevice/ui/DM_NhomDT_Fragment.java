package com.danang.managedevice.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.danang.managedevice.Controller.Animation_View;
import com.danang.managedevice.Controller.EdtLayout_Handle;
import com.danang.managedevice.Controller.Adapter_Recycler;
import com.danang.managedevice.Controller.Send_To_View;
import com.danang.managedevice.Model.DM_NhomDT_model;
import com.danang.managedevice.Model.SendData;
import com.danang.managedevice.Object.DMNhomDT;
import com.danang.managedevice.R;

import java.util.ArrayList;

public class DM_NhomDT_Fragment extends Fragment implements SendData, Send_To_View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String SUBMIT = "SUBMIT";
    private static final String EDIT = "UPDATE";


    private String mParam1;
    private String mParam2;

    Animation_View an_recycler;
    Adapter_Recycler adapter;

    View root;
    RecyclerView recyclerView;
    EditText edt_code,edt_name;
    Button btn_Submit;
    ImageView imgView;

    public DM_NhomDT_Fragment() {
    }
    public static DM_NhomDT_Fragment newInstance(String param1, String param2) {
        DM_NhomDT_Fragment fragment = new DM_NhomDT_Fragment();
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        root = inflater.inflate(R.layout.fragment_dm__nhom_dt, container, false);
        edt_code = EdtLayout_Handle.Companion.getIns().setText_Hint(root.findViewById(R.id.code_panel),"Nhập Mã Đối Tượng","Input Object Code");
        edt_name = EdtLayout_Handle.Companion.getIns().setText_Hint(root.findViewById(R.id.name_panel),"Nhập Tên Đối Tượng","Input Object Name");
        btn_Submit = root.findViewById(R.id.btn);
        recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        imgView = root.findViewById(R.id.img_up_down);
        an_recycler = new Animation_View(recyclerView,imgView);

        /*
        * Database*/
        DM_NhomDT_model.setSend_Refresh(this);
        /*
            SetDataListBox
         */
            new DM_NhomDT_model(DM_NhomDT_model.Status.GETALL,this::sendData);

        /*
            SetData button
        */
        btn_Submit.setText(SUBMIT);
        btn_Submit.setOnClickListener(view -> {
            DMNhomDT data = new DMNhomDT(edt_code.getText()+"",edt_name.getText()+"");
            if(btn_Submit.getText().toString().equals(SUBMIT))
                new DM_NhomDT_model(data, DM_NhomDT_model.Status.INSERT,getContext());
            else
                new DM_NhomDT_model(data, DM_NhomDT_model.Status.UPDATE,getContext());
        });
        return root;
    }

    @Override
    public void sendData(Object obj) {
        ArrayList<Object> list = (ArrayList<Object>) obj;
        if(adapter==null)
        {
            adapter = new Adapter_Recycler(getContext(),list,R.layout.nhom_dt_item,this);
            recyclerView.setAdapter(adapter);
        }
        else
        {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }

//        Swipe_Controller swipe = new Swipe_Controller();
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipe);
//        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onResume() {
        btn_Submit.setText(SUBMIT);
        edt_code.setEnabled(true);
        super.onResume();
    }

    @Override
    public void sendDataToView(Object obj) {
        btn_Submit.setText(EDIT);
        edt_code.setEnabled(false);
        DMNhomDT nhom_dt = (DMNhomDT) obj;
        an_recycler.Click();
        edt_code.setText(nhom_dt.getMaNhomdt());
        edt_name.setText(nhom_dt.getTenDt());

    }

    @Override
    public void refersh() {
        new DM_NhomDT_model(DM_NhomDT_model.Status.GETALL,this::sendData);
    }
}