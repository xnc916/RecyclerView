package com.feicuiedu.recyclerviewdemo_20170516.view03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.feicuiedu.recyclerviewdemo_20170516.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwipeActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        ButterKnife.bind(this);

        // 设置适配器
        SwipeAdapter adapter = new SwipeAdapter(this);
        mListView.setAdapter(adapter);
    }
}
