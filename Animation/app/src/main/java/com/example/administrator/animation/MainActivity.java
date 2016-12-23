package com.example.administrator.animation;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.ripple);

        /**
         * createCircularReveal(View view, int centerX, int centerY, float startRadius, float endRadius)
         * view => 动画的View
         * centerX,centerY => 动画开始的中心点XY坐标
         * startRadius,endRadius => 动画开始半径和结束的半径
         */
        setContentView(R.layout.circular_reveal);
        final ImageView ovalView = (ImageView) findViewById(R.id.id_oval);
        ovalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animator animator = ViewAnimationUtils.createCircularReveal(
                        ovalView, ovalView.getWidth()/2, ovalView.getHeight()/2, ovalView.getWidth(), 0
                );
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(2000);
                animator.start();
            }
        });
        final ImageView rectView = (ImageView) findViewById(R.id.id_rect);
        rectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animator animator = ViewAnimationUtils.createCircularReveal(
                        rectView, 0, 0, 0, (float)Math.hypot(rectView.getWidth(), rectView.getHeight())
                );
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(2000);
                animator.start();
            }
        });
    }
}
