package com.example.asus.threemodel.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.threemodel.R;

public class CollectActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView shoucang_clear;
    private ImageView my_shoucang_fanhui;
    private GridView shouchang_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initview();

}

    private void initview() {
        shoucang_clear = findViewById(R.id.shoucang_clear);
        my_shoucang_fanhui = findViewById(R.id.my_shoucang_fanhui);
        shouchang_view = findViewById(R.id.shouchang_view);
        shoucang_clear.setOnClickListener(this);
        my_shoucang_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shoucang_clear:
                Toast.makeText(this, "清楚", Toast.LENGTH_LONG).show();
                break;
            case R.id.my_shoucang_fanhui:
                finish();
                break;

        }
    }
}
