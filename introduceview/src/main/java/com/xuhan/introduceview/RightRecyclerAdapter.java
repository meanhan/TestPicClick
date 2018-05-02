package com.xuhan.introduceview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 18-4-27.
 */

public class RightRecyclerAdapter extends RecyclerView.Adapter<RightRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mDataList;
    private int mLayoutId;
    private OnItemClickListener mItemClickListener;



    public RightRecyclerAdapter(Context context, int layoutId, List<String> datalist) {
        mContext = context;
        mLayoutId = layoutId;
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = datalist;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setDataList(List<String> dataList) {
        this.mDataList = dataList;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public RightRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(final RightRecyclerAdapter.MyViewHolder holder, final int position) {
        holder.mTextView.setText(mDataList.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.item_tv);
        }
    }
}
