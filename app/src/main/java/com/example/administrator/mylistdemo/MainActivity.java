package com.example.administrator.mylistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private ListView lv;
    private List<Data> list;
    private MyAdp myAdp;
    private boolean b=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        //添加数据
        addData();
        //设置适配器
        myAdp = new MyAdp(this,list);
        lv.setAdapter(myAdp);
        myAdp.setHide(false);
        myAdp.xin();


    }

    private void addData() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Data("sb" + i, i + "",false));
        }
    }

    private void initUI() {
        btn1 = (Button) findViewById(R.id.button);
        btn3 = (Button) findViewById(R.id.button3);
        btn2 = (Button) findViewById(R.id.button2);
        lv = (ListView) findViewById(R.id.listView);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                xuanze(true);
                break;
            case R.id.button2:
                xuanze(false);
                break;
            case R.id.button3:
                if(b==true){
                    b=false;
                }else{
                    b=true;
                }
                hide(b);
                myAdp.setHide(b);
                myAdp.xin();
                break;
        }
    }

    private void hide(boolean b) {
        if(b)
            btn3.setText("取消隐藏");
        else
            btn3.setText("隐藏已选");
    }

    private void xuanze(boolean b) {
        if(b){
            for (int i = 0; i < list.size(); i++) {
                list.get(i).isChecked=true;
            }
        }else{

            for (int i = 0; i < list.size(); i++) {
                list.get(i).isChecked=false;
            }
        }

        myAdp.notifyDataSetChanged();



    }
}
