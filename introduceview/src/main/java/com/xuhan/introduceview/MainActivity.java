package com.xuhan.introduceview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mLeftRecyclerView;
    private RecyclerView mRightRecyclerView;
    private LeftRecyclerAdapter mLeftAdapter;
    private RightRecyclerAdapter mRightAdapter;
    private List<String> mLeftDataList;
    private List<String> mRightDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeftRecyclerView = findViewById(R.id.left_list);
        mRightRecyclerView = findViewById(R.id.right_list);
        initData();
        initView();
    }

    private void initData() {
        mLeftDataList  = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mLeftDataList.add("leftItem"+i);
        }
        mRightDataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mRightDataList.add("rightItem"+i);
        }
    }

    private void initView() {
        mLeftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLeftAdapter = new LeftRecyclerAdapter(this,R.layout.left_recycler_item,mLeftDataList);
        mLeftRecyclerView.setAdapter(mLeftAdapter);
        mRightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRightRecyclerView.addItemDecoration(new MyDividerItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {
                ColorDecoration decoration = new ColorDecoration();
                decoration.bottom = 2;
                decoration.decorationColor = Color.GRAY;
                return decoration;
            }
        });
        mRightAdapter = new RightRecyclerAdapter(this,R.layout.right_recycler_item,mRightDataList);
        mRightRecyclerView.setAdapter(mRightAdapter);

        mLeftAdapter.setItemClickListener(new LeftRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                refreshRightView(position);
            }
        });

        mRightAdapter.setItemClickListener(new RightRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    private void refreshRightView(int position){
        mRightDataList.clear();
        for (int i = 0; i < 10; i++) {
            mRightDataList.add("NEWrightItem:"+position+"-"+i);
        }
        mRightAdapter.setDataList(mRightDataList);
        mRightAdapter.notifyDataSetChanged();
    }
}
