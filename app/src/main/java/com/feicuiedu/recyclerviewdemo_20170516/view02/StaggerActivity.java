package com.feicuiedu.recyclerviewdemo_20170516.view02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.feicuiedu.recyclerviewdemo_20170516.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StaggerActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private StaggerAdapter mStaggerAdapter;
    private List<String> mData;
    private List<Integer> mHeights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger);

        ButterKnife.bind(this);

        initView();
        initData();

    }

    // 数据的填充
    private void initData() {
        mData = new ArrayList<>();
        mHeights = new ArrayList<>();
        for (int i='A';i<'z';i++){
            mData.add(""+(char)i);
            // 高度：随机，Math.random() 0--1
            mHeights.add((int) (Math.random()*400+200));
        }
        mStaggerAdapter.setData(mData, mHeights);
    }

    // 视图的初始化
    private void initView() {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        // 设置适配器
        mStaggerAdapter = new StaggerAdapter();
        mRecyclerView.setAdapter(mStaggerAdapter);

        mStaggerAdapter.setOnItemClickListener(new StaggerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(StaggerActivity.this, "click:"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
