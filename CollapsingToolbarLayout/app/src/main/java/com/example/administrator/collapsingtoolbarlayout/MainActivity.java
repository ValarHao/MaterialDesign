package com.example.administrator.collapsingtoolbarlayout;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar部分
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.id_collapsing_toolbar);
        mCollapsingToolbarLayout.setTitle("标题栏");
        //RecyclerView部分
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recycler);
        //设置固定大小
        mRecyclerView.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(this);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        //创建适配器
        RecyclerView.Adapter adapter = new MyRecyclerAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

        private LayoutInflater mInflater;
        private String[] mDatas = null;

        public MyRecyclerAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            mDatas = new String[20];
            for (int i=0; i<20; i++) {
                int index = i + 1;
                mDatas[i] = "item" + index;
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.recycler_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemTv.setText(mDatas[position]);
        }

        @Override
        public int getItemCount() {
            return mDatas.length;
        }

        //自定义的ViewHolder，持有每个Item的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView itemTv;

            public ViewHolder(View view) {
                super(view);
                itemTv = (TextView) view.findViewById(R.id.id_item_tv);
            }
        }
    }
}
