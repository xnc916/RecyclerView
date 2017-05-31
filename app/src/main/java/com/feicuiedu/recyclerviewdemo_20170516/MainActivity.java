package com.feicuiedu.recyclerviewdemo_20170516;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.feicuiedu.recyclerviewdemo_20170516.view01.LinearActivity;
import com.feicuiedu.recyclerviewdemo_20170516.view02.StaggerActivity;
import com.feicuiedu.recyclerviewdemo_20170516.view03.SwipeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnNormal,R.id.btnStagger,R.id.btnSwipe})
    public void click(View view){
        switch (view.getId()){
            case R.id.btnNormal:
                startActivity(new Intent(MainActivity.this, LinearActivity.class));
                break;
            case R.id.btnStagger:
                startActivity(new Intent(MainActivity.this, StaggerActivity.class));
                break;
            case R.id.btnSwipe:
                startActivity(new Intent(MainActivity.this, SwipeActivity.class));
                break;
        }
    }
}
