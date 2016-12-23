package com.example.administrator.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mListFragment;
    private List<String> mListTitle;

    private TabAdapter mTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.id_tab);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        //添加fragment
        mListFragment = new ArrayList<Fragment>();
        mListFragment.add(new Fragment1());
        mListFragment.add(new Fragment2());
        mListFragment.add(new Fragment3());
        mListFragment.add(new Fragment4());

        //添加标题
        mListTitle = new ArrayList<String>();
        mListTitle.add("标题1");
        mListTitle.add("标题2");
        mListTitle.add("标题3");
        mListTitle.add("标题4");

        /**
         * TabLayout的一些方法：
         * addTab() => 添加选项卡到layout中
         * newTab() => 新建个tab
         * setTabMode() => 设置Mode
         * getTabMode() => 获取tab的模式
         * getTabAt() => 获取选项卡
         * getTabCount() => 获取选项卡总个数
         * getTabGravity() => 获取tab的Gravity
         * getTabTextColors() => 获取tab中文本的颜色
         * removeAllTabs() => 移除所有tab
         * removeTab() => 移除指定的 tab
         * removeTabAt() => 移除指定位置的 tab
         * setOnTabSelectedListener() => 为每个tab增加选择监听器
         * setScrollPosition() => 设置滚动位置
         * setTabGravity() => 设置Gravity
         * setTabTextColors() => 设置tab中文本的颜色
         * setTabsFromPagerAdapter() => 设置PagerAdapter
         * setupWithViewPager() => 和ViewPager联动
         */
        //设置TabLayout的模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //给TabLayout添加tab名称
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(3)));

        mTabAdapter = new TabAdapter(getSupportFragmentManager(), mListFragment, mListTitle);

        //viewpager加载adapter
        mViewPager.setAdapter(mTabAdapter);
        //TabLayout加载viewpager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    class TabAdapter extends FragmentPagerAdapter {

        private List<Fragment> listFragment;
        private List<String> listTitle;

        public TabAdapter(FragmentManager fm, List<Fragment> listFragment, List<String> listTitle) {
            super(fm);
            this.listFragment = listFragment;
            this.listTitle = listTitle;
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listTitle.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return listTitle.get(position % listTitle.size());
        }
    }
}
