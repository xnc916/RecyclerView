package com.feicuiedu.recyclerviewdemo_20170516.view01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.feicuiedu.recyclerviewdemo_20170516.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LinearActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<String> mData;
    private LinearAdapter mLinearAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        ButterKnife.bind(this);

        initView();
        initData();

    }

    // 视图初始化工作
    private void initView() {                  // 10:30 开始。

        /** 1. 设置布局管理器:展示的是什么样式的
         * 提供三种：StaggeredGridLayoutManager 瀑布流
         *          LinearLayoutManager 类似ListView
         *          GridLayoutManager  网格样式
         */
        // LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        // GridLayoutManager
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));

        // StaggeredGridLayoutManager
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));

        // 2. 如果想要添加item的添加和删除的动画,提供了一个可以直接使用的动画：DefaultItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 3. 如果想要设置，可以设置分割线,默认提供的：DividerItemDecoration，可以自定义，可以item布局里面自己去设置。
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));

        // 4. 设置适配器
        mLinearAdapter = new LinearAdapter();
        mRecyclerView.setAdapter(mLinearAdapter);

        // 5. 关于item的事件:点击、长按，需要自己去实现
        mLinearAdapter.setOnItemClickListener(new LinearAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(LinearActivity.this, "click:"+position, Toast.LENGTH_SHORT).show();
            }
        });
        mLinearAdapter.setOnItemLongClickListener(new LinearAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(LinearActivity.this, "LongClick："+position, Toast.LENGTH_SHORT).show();
                // 长按的时候删除数据
//                mLinearAdapter.removeData(position);
            }
        });

        // 6. 关于拖动和滑动的处理：借助一个类ItemTouchHelper来完成
//        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
//
//            /**
//             * 方向：
//             * dragdirs：拖动的方向：UP、DOWN、START、END、LEFT、RIGHT
//             * swipedirs:滑动的方向：UP、DOWN、START、END、LEFT、RIGHT
//             * 设置为0的时候，表示没有此项功能。
//             */
//
//            // 拿到设置的移动方向：滑动的方向、拖动的方向
//            @Override
//            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//
//                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//                int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
//
//                return makeMovementFlags(dragFlags,swipeFlags);
//            }
//
//            // 拖动的事件处理
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            // 滑动的事件处理
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//
//            }
//        };
        /**
         * simplecallback简易处理了一下callback的getMovementFlags,需要在构造方法的参数里面传入相应的拖拽和滑动的方向。
         */
        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT) {

            // 拖动事件处理
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                // 拿到拖动的ViewHolder的postion
                int fromPosition = viewHolder.getAdapterPosition();
                // 得到目标的ViewHolder的position
                int toPostion = target.getAdapterPosition();

                // 向下移动
                if (fromPosition<toPostion){
                    // 分别把中间的这些item进行调换
                    for (int i=fromPosition;i<toPostion;i++){
                        // 数据的交换：通过集合的工具类Collections
                        Collections.swap(mData,i,i+1);
                    }
                }else {
                    // 向上移动
                    for (int i=fromPosition;i<toPostion;i++){
                        Collections.swap(mData,i,i-1);
                    }
                }

                // 刷新一下适配器
                mLinearAdapter.itemMoved(fromPosition,toPostion,mData);

                // 表示被消费，执行了拖动
                return true;
            }

            // 滑动事件处理
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // 滑动的时候删除
                int position = viewHolder.getAdapterPosition();
                mLinearAdapter.removeData(position);
            }
        };
        // 创建
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        // 作用给RecyclerView
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @OnClick(R.id.btnAdd)
    public void addData(){
        // 添加一条数据
        mLinearAdapter.addData(1);
    }

    // 数据的获取
    private void initData() {
        mData = new ArrayList<>();

        for (int i='A';i<'z';i++){
            mData.add(""+(char)i);
        }
        mLinearAdapter.setData(mData);
    }
}
