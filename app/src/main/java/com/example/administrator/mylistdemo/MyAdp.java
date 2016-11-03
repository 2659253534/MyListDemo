package com.example.administrator.mylistdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FanGuangjie on 2016/11/2.
 */
public class MyAdp extends BaseAdapter {
    private List<Data> list = new ArrayList<>();
    private List<Data> listDa;
    private Context context;
    private List<Data> listDel;
    private boolean b;

    public MyAdp(Context context, List<Data> list) {
        this.context = context;
        this.listDa = list;
        list.addAll(listDa);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setHide(boolean b) {

        this.b = b;

    }
    public void xin(){


        listDel = new ArrayList<>();
        if (b) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isChecked) {
                    listDel.add(list.get(i));
                }
            }

            for (int i = 0; i < listDel.size(); i++) {
                list.remove(listDel.get(i));
            }

        } else {
            list.clear();
            list.addAll(listDa);
        }
        //通知适配器
        super.notifyDataSetChanged();

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        final Data data = list.get(i);
        if (view == null) {
            view = View.inflate(context, R.layout.list_item, null);
            vh = new ViewHolder();
            vh.tv = (TextView) view.findViewById(R.id.textView);
            vh.ck = (CheckBox) view.findViewById(R.id.checkBox);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        //赋值
        vh.tv.setText(data.name);
        vh.ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                data.isChecked = b;
            }
        });
        vh.ck.setChecked(data.isChecked);

        return view;
    }

    class ViewHolder {
        TextView tv;
        CheckBox ck;

    }
}
