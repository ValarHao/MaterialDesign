package com.example.administrator.recyclerview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mRecyclerLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<String> mItemDatas;
    private int mItemNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView部分
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recycler_view);
        //设置固定大小
        mRecyclerView.setHasFixedSize(true);
        //创建线性布局
        mRecyclerLayoutManager = new LinearLayoutManager(this);
        //垂直方向
        mRecyclerLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        mRecyclerView.setLayoutManager(mRecyclerLayoutManager);
        //创建适配器
        mAdapter = new MyRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        //SwipeRefreshLayout部分
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mItemDatas.add(mItemNum, "item" + (mItemNum+1));
                        mItemNum++;

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(0);
                    }
                }).start();
            }
        });
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            swipeRefreshLayout.setRefreshing(false);
            mAdapter.notifyDataSetChanged();
        }
    };

    class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

        private LayoutInflater inflater;

        public MyRecyclerAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            mItemNum = 5;
            mItemDatas = new ArrayList<String>();
            for (int i=0; i<mItemNum; i++) {
                mItemDatas.add(i, "item" + (i+1));
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.recycler_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemTv.setText(mItemDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mItemDatas.size();
        }

        //自定义的ViewHolder，持有每个Item的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {
            public CardView itemCV;
            public TextView itemTv;

            public ViewHolder(View view) {
                super(view);
                itemCV = (CardView) view.findViewById(R.id.id_card_view);
                itemTv = (TextView) view.findViewById(R.id.id_item_tv);
            }
        }
    }
}
