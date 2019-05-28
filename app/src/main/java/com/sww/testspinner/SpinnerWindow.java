package com.sww.testspinner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import java.util.ArrayList;

public class SpinnerWindow extends PopupWindow {

    private View rootView;
    private Context context;
    private RecyclerView recyclerWindow;
    private ArrayList<String> arrayList;
    private SpinnerAdapter adapter;

    public SpinnerWindow(Context context, ArrayList<String> datas) {
        super(context);
        this.context = context;
        this.arrayList = datas;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            rootView = inflater.inflate(R.layout.spinner_list, null);
        }
        recyclerWindow = rootView.findViewById(R.id.recycler_window);
        adapter=new SpinnerAdapter(arrayList);
        recyclerWindow.setAdapter(adapter);
        //设置可获取焦点
        setFocusable(true);
        setOutsideTouchable(false);//外部可触摸
        ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
        setBackgroundDrawable(colorDrawable);

    }

}
