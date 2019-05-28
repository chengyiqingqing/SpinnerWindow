package com.sww.testspinner;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private EditText et_input;
    private ImageButton ib_drapdown;
    private ListView listView;
    private ArrayList<String> datas;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        et_input = (EditText) findViewById(R.id.et_input);
        ib_drapdown = (ImageButton) findViewById(R.id.ib_drapdown);

        ib_drapdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow();
            }
        });
    }

    private void initPopupWindow() {
        initListView();
        //显示下拉选择框
        popupWindow = new PopupWindow(listView, et_input.getWidth(), 300);
        //设置可获取焦点
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);//外部可触摸
        popupWindow.setBackgroundDrawable(new BitmapDrawable());//设置空的背景，显示点击事件
        //显示在指定控件下方
        popupWindow.showAsDropDown(et_input, 0, -5);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initListView() {
        listView = new ListView(this);
        //创建一些假数据
        datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add((10000 + i) + "");
        }
        listView.setOnItemClickListener(this);
        listView.setAdapter(new MyAdapter());
        SpinnerWindow spinnerWindow = new SpinnerWindow(MainActivity.this,datas);
        spinnerWindow.showAsDropDown(et_input, 0, -5);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String string = datas.get(position);
        et_input.setText(string);
        popupWindow.dismiss();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = View.inflate(parent.getContext(), R.layout.item_main, null);
            } else {
                view = convertView;
            }
            TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
            ImageButton img_del = (ImageButton) view.findViewById(R.id.ib_del);
            img_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(position);
                    notifyDataSetChanged();

                    if (datas.size() == 0) {
                        //如果删除最后一条数据，就隐藏PopupWindow
                        popupWindow.dismiss();
                    }
                }
            });
            tv_number.setText(datas.get(position));
            return view;
        }
    }
}
