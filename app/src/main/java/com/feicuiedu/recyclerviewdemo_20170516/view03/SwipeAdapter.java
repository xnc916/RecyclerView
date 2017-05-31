package com.feicuiedu.recyclerviewdemo_20170516.view03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.feicuiedu.recyclerviewdemo_20170516.R;

/**
 * Created by gqq on 2017/5/16.
 */

public class SwipeAdapter extends BaseSwipeAdapter{

    private Context mContext;

    public SwipeAdapter(Context context) {
        mContext = context;
    }

    //---------------------BaseSwipeAdapter里面的方法------------------------
    // 返回item的SwipeLayout的布局的id
    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    // 视图的初始化和操作
    @Override
    public View generateView(int position, ViewGroup parent) {
        // item的布局填充
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_swipe,null);
        // 分别找到item布局里面的控件，点击等处理
        final SwipeLayout swipeLayout = (SwipeLayout) view.findViewById(getSwipeLayoutResourceId(position));
        // 设置模式
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // 设置监听：可以直接new SwipeListener接口，也可以直接new SimpleSwipeListener，选择性的重写里面的方法
        swipeLayout.addSwipeListener(new SimpleSwipeListener(){
            @Override
            public void onClose(SwipeLayout layout) {
                super.onClose(layout);
                Toast.makeText(mContext, "关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                super.onOpen(layout);
                Toast.makeText(mContext, "打开", Toast.LENGTH_SHORT).show();
            }
        });

        // 设置双击
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(mContext, "双击", Toast.LENGTH_SHORT).show();
            }
        });

        // 分别找到BottomView的控件，并处理
        view.findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "展示", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void fillValues(int position, View convertView) {
        TextView textView = (TextView) convertView.findViewById(R.id.tvShow);
        textView.setText("展示的数据："+position);
    }

    //-----------------------------------
    @Override
    public int getCount() {
        return 40;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
