package com.feicuiedu.recyclerviewdemo_20170516.view02;

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

public class StaggerAdapter extends RecyclerView.Adapter<StaggerAdapter.myViewHolder> {

    /**
     * 数据
     * 高是不固定的，以随机数的形式给item设置高
     */
    // 数据
    private List<String> mData = new ArrayList<>();
    // 高的随机数据
    private List<Integer> mHeights = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;

    // 设置数据
    public void setData(List<String> list, List<Integer> heights) {
        mHeights.clear();
        mHeights.addAll(heights);
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recycler, parent, false);
        myViewHolder viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {

        // 设置Item的高度：高度是随机的一系列数
        // 拿到布局参数
        ViewGroup.LayoutParams layoutParams = holder.mTvText.getLayoutParams();
        // 更改其中的高
        layoutParams.height = mHeights.get(position);
        // 设置
        holder.mTvText.setLayoutParams(layoutParams);

        holder.mTvText.setText(mData.get(position));

        if (mOnItemClickListener!=null){
            holder.mTvText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_text)
        TextView mTvText;

        public myViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    // 设置监听
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    // 点击监听
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

}
