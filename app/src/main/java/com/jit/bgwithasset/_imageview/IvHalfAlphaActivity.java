package com.jit.bgwithasset._imageview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.jit.bgwithasset.R;

/**
 * @author crazyZhangxl on 2019-3-8 22:21:00.
 * Describe: 图片半透明效果
 *
 * 利用 matrix 实现平移到中心点及中心点缩放
 * https://blog.csdn.net/ducklikejava/article/details/80930706
 */

public class IvHalfAlphaActivity extends AppCompatActivity {
    private ImageView ivSrc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_alpha);
        ivSrc = findViewById(R.id.ivOrigin);
        ivSrc.setColorFilter(Color.parseColor("#80FFFFFF"));
    }
}
