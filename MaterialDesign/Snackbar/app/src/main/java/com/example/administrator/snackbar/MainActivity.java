package com.example.administrator.snackbar;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.id_tv);

        /**
         * 当父布局使用CoordinatorLayout时，可以被滑动手势划走
         * Snackbar的一些方法：
         * make() => 创建 Snackbar
         * setAction() => 对Snackbar设置单击事件
         * setDuration() => 设置Snackbar显示时长
         * getDuration() => 得到显示时长
         * setActionTextColor() => 设置Action颜色
         * setText() => 更新Snackbar中的文本
         * show() => 显示Snackbar
         * dismiss() => 消失Snackbar
         */
        findViewById(R.id.id_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(textView, "点击了FAB", Snackbar.LENGTH_SHORT)
                        .setAction("Dismiss", null)
                        .show();
            }
        });
    }
}
