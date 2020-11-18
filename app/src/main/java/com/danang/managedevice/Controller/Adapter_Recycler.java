package com.danang.managedevice.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.danang.managedevice.Controller.Holder.Holder_Template;

import java.util.ArrayList;

public class Adapter_Recycler extends RecyclerView.Adapter<Holder_Template> {
    private Context context;
    private ArrayList<Object> list;
    private int resID_Layout;
    private Object obj_View;

    public Adapter_Recycler(Context context, ArrayList<Object> list,int resID_Layout,Object obj_View) {
        this.context = context;
        this.list = list;
        this.resID_Layout = resID_Layout;
        this.obj_View = obj_View;
    }

    @NonNull
    @Override
    public Holder_Template onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resID_Layout,parent,false);
        Holder_Template holder = Holder_Template.create(resID_Layout,view,obj_View,context);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_Template holder, int position) {
            holder.bindData(list.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }
}
