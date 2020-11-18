package com.danang.managedevice.Controller.NhomDT;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.danang.managedevice.Controller.Holder.Holder_Template;
import com.danang.managedevice.Controller.Send_To_View;
import com.danang.managedevice.Model.DM_NhomDT_model;
import com.danang.managedevice.Object.DMNhomDT;
import com.danang.managedevice.R;

public class NhomDT_Holder extends Holder_Template {
    private TextView txv_title;
    private TextView txv_content;
    private ImageView viewEdit,viewDel;
    private DMNhomDT nhom_dt;
    private CheckBox ck;
    private int position;
    private boolean checkMenu=false;
    public NhomDT_Holder(@NonNull View itemView, Object obj_View, Context context) {
        super(itemView,obj_View,context);
        setGenerateData(itemView);
        setDataNhomDT();
    }
    public NhomDT_Holder(@NonNull View itemView, Object obj_View, Context context,boolean checkMenu) {
        super(itemView,obj_View,context);
        this.checkMenu = checkMenu;
        setGenerateData(itemView);
        if(checkMenu)
            setDataCheckMenu();
        else setDataNhomDT();
    }
    private void setGenerateData(@NonNull View itemView)
    {
        txv_title = itemView.findViewById(R.id.txv_nhomdt_title);
        txv_content = itemView.findViewById(R.id.txv_nhomdt_content);
        viewEdit = itemView.findViewById(R.id.imgEdit);
        viewDel = itemView.findViewById(R.id.imgDel);
        ck = itemView.findViewById(R.id.checkBox);
    }
    private void setDataCheckMenu()
    {
        viewDel.setVisibility(View.GONE);
        viewEdit.setVisibility(View.GONE);
        ck.setOnCheckedChangeListener((compoundButton, b) -> {
            SendCheckedData send = (SendCheckedData)obj_View;
            send.sendCheckedObj(nhom_dt,ck.isChecked());
        });

    }
    private void setDataNhomDT()
    {
        ck.setVisibility(View.GONE);
        viewEdit.setOnClickListener(v ->
        {
            if(nhom_dt!=null)
            {
                Send_To_View sendData = (Send_To_View)obj_View;
                sendData.sendDataToView(nhom_dt);
            }
        });
        viewDel.setOnClickListener(v ->
        {
            new AlertDialog.Builder(context)
                    .setTitle("Delete")
                    .setMessage("Do you want Delete \" " + nhom_dt.getTenDt() +"\" ?")
                    .setIcon(R.drawable.close)
                    .setPositiveButton(android.R.string.yes,(dialogInterface, i) ->
                    {
                        new DM_NhomDT_model(nhom_dt, DM_NhomDT_model.Status.DELETE,context);
                    })
                    .setNegativeButton(android.R.string.no,null).show();
        });
    }

    @Override
    public void bindData(Object object,int position) {
        this.position = position;
        DMNhomDT data = (DMNhomDT) object;
        this.nhom_dt = data;
        txv_title.setText(data.getMaNhomdt());
        txv_content.setText(data.getTenDt());
    }
}
