package com.example.asus.threemodel.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.threemodel.R;

public class FitActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView my_shezhi_fanhui;
    private RelativeLayout my_shezhi_tuijian;
    private RelativeLayout my_shezhi_qingli;
    private RelativeLayout my_shezhi_tui_guanyu;
    private RelativeLayout my_shezhi_jianyi;
    private TextView text_guanbi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit);
        initview();
    }
    private void initview() {
        my_shezhi_fanhui = findViewById(R.id.my_shezhi_fanhui);
        my_shezhi_tuijian = findViewById(R.id.my_shezhi_tuijian);
        my_shezhi_qingli = findViewById(R.id.my_shezhi_qingli);
        my_shezhi_tui_guanyu = findViewById(R.id.my_shezhi_tui_guanyu);
        my_shezhi_jianyi = findViewById(R.id.my_shezhi_jianyi);
        my_shezhi_fanhui.setOnClickListener(this);
        my_shezhi_tuijian.setOnClickListener(this);
        my_shezhi_qingli.setOnClickListener(this);
        my_shezhi_tui_guanyu.setOnClickListener(this);
        my_shezhi_jianyi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.my_shezhi_fanhui :
                finish();
                break;
            case R.id.my_shezhi_tuijian :
                tuijian();
                break;
            case R.id.my_shezhi_qingli :
                //点击清理缓存
Toast.makeText(this, "清理缓存",Toast.LENGTH_LONG).show();
                break;
            case R.id.my_shezhi_tui_guanyu :
                //点击跳出dilog

guanyu();
                break;
            case R.id.my_shezhi_jianyi :
                //建议反馈
                jianyi();
                break;
        }
    }
/**
 * 点击的时候弹出一个自定义delog
 * 然后把自己的建议发出去，关闭dilog
 *
 * */
    private void jianyi() {

    }

    private void guanyu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.shezhi_guanyu, null);
              builder.setView(view);
        text_guanbi = view.findViewById(R.id.text_guanbi);
        text_guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(FitActivity.this, "关于",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = builder.create();
                alertDialog.show();

    }

    private void tuijian() {
        new  AlertDialog.Builder(this)
                .setTitle("发现一个看片神器" )
                .setMessage("aaaaaaaaaaaa" )
                .setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("复制", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(FitActivity.this, "已复制到粘贴板", Toast.LENGTH_LONG).show();

                    }
                })
                .show();
    }
}
