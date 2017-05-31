package com.feicuiedu.recyclerviewdemo_20170516.view01;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feicuiedu.recyclerviewdemo_20170516.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gqq on 2017/5/16.
 */
// 适配器
public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.MyViewHolder>{

    // 数据
    private List<String> mData= new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    // 数据填充的方法
    public void setData(List<String> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    // 添加某条数据
    public void addData(int position) {
        mData.add(position,"insert one");
        // 刷新
        notifyItemInserted(position);
        notifyItemRangeChanged(position,mData.size()-position);

    }

    // 移除某一条数据
    public void removeData(int position) {
        mData.remove(position);
        // 刷新
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mData.size()-position);
    }

    // item移动的刷新
    public void itemMoved(int fromPosition, int toPostion, List<String> data) {
        mData.clear();
        mData.addAll(data);
        // 刷新
        notifyItemMoved(fromPosition,toPostion);
        notifyItemRangeChanged(fromPosition,mData.size()-fromPosition);
    }

    // 创建ViewHolder视图
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recycler,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    // 视图和数据进行绑定
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTvItem.setText(mData.get(position));// 完成数据的填充

        // 处理点击和长按的事件
        if (mOnItemClickListener!=null){
            holder.mTvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 可以直接在这里完成item的点击事件，不方便处理，接口回调
                    mOnItemClickListener.onItemClick(position);
                }
            });
        }
        if (mOnItemLongClickListener!=null){
            holder.mTvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    mOnItemLongClickListener.onItemLongClick(position);

                    // 返回true，表示消费掉
                    return true;
                }
            });
        }
    }

    // item的数量
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 创建ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder{

        // 使用Butterknife绑定视图
        @BindView(R.id.item_text)
        TextView mTvItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    // 利用接口回调，自己去定义两个接口：点击和长按，提供两个点击和长按的方法
    // 最终的接口让调用者实现，实现里面监听方法的具体实现

    // 设置监听的方法：实现接口的初始化
    // 设置点击监听
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    // 设置长按监听
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    // 点击监听
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    // 长按的监听
    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }
}
