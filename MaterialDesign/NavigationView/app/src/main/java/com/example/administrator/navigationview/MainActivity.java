package com.example.administrator.navigationview;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.id_drawer);
        final TextView textView = (TextView) findViewById(R.id.id_tv);

        //将状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);

            //android 4.4版本特殊处理
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //将侧边栏顶部延伸至status bar
                drawerLayout.setFitsSystemWindows(true);
                drawerLayout.setClipToPadding(false);
            }
        }

        //设置Toolbar
        toolbar.setTitle("主题栏");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(toolbar); //设置toolbar支持actionbar

        //使用ActionBarDrawerToggle,配合DrawerLayout和ActionBar,以实现推荐的抽屉功能
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.id_navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.id_navigation_home:
                        textView.setText("This is Home!");
                        break;

                    case R.id.id_navigation_favorite:
                        textView.setText("This is Favorite!");
                        break;

                    case R.id.id_navigation_download:
                        textView.setText("This is Download!");
                        break;

                    case R.id.id_navigation_more:
                        textView.setText("This is More!");
                        break;

                    case R.id.id_navigation_settings:
                        textView.setText("This is Settings!");
                        break;
                }

                item.setChecked(true); //点击了把它设为选中状态
                drawerLayout.closeDrawers(); //关闭抽屉
                return true;
            }
        });
    }
}
