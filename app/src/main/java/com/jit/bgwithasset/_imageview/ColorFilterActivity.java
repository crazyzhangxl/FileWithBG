package com.jit.bgwithasset._imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jit.bgwithasset.R;
import com.jit.bgwithasset._imageview.widget.ColorFilterImageView;

/**
 * @author crazyZhangxl on 2019-3-9 19:44:09.
 * Describe: 触摸灰度的活动
 *
 *
 */

public class ColorFilterActivity extends AppCompatActivity {
    private ColorFilterImageView ivFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_filter);

        initViews();

        // 不设置点击事件为什么只响应 down__说明被消费了
        ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initViews() {
        ivFilter = findViewById(R.id.ivFilter);
    }
}
