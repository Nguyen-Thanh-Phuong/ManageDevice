package com.danang.managedevice.Controller.Holder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danang.managedevice.Controller.NhomDT.NhomDT_Holder;
import com.danang.managedevice.Controller.NhomTB.NhomTB_Holder;
import com.danang.managedevice.R;
import com.danang.managedevice.ui.DM_NhomTB_Fragment;

import org.jetbrains.annotations.NotNull;

public abstract class Holder_Template extends RecyclerView.ViewHolder {
    public final static int DM_NHOM_DT = R.layout.nhom_dt_item;
    public final static int DM_NHOM_TB = R.layout.nhom_tb_item;
    private View root;
    protected Context context;
    protected Object obj_View;
    public Holder_Template(@NonNull View itemView,Object obj_View,Context context) {
        super(itemView);
        root = itemView;
        this.context = context;
        this.obj_View = obj_View;
    }
    public static Holder_Template create(int type_Create, @NotNull View itemView,Object obj_View,Context context)
    {
        switch (type_Create)
        {
            case DM_NHOM_DT:
                if (obj_View instanceof DM_NhomTB_Fragment) {
                    return new NhomDT_Holder(itemView,obj_View,context,true);
                }
                return new NhomDT_Holder(itemView,obj_View,context);
            case DM_NHOM_TB:
                return new NhomTB_Holder(itemView,obj_View,context);
            default:
                return null;
        }

    }
    public abstract void bindData(Object object,int position);
}
