package com.jit.bgwithasset.background;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jit.bgwithasset.R;

/**
 * @author crazyZhangxl on 2018-12-25 15:15:29.
 * Describe:
 */

public class BackgroundActivity extends AppCompatActivity {
    private TextView mTvText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        mTvText = findViewById(R.id.tvText);
        setLineBg();
    }



    private void measureLocation() {
    }

    private void setLineBg(){
        // GradientDrawable相当于drawable中自定义shape的布局文件所生成的Drawable，所以它可以设置corners、solid、stroke
        GradientDrawable background = new GradientDrawable();
        background.setCornerRadius(10);
        background.setStroke(4, Color.RED);
        background.setStroke(2,Color.GREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mTvText.setBackground(background);
        }else {
            mTvText.setBackgroundDrawable(background);
        }
    }
}
