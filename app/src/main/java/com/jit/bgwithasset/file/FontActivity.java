package com.jit.bgwithasset.file;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import com.jit.bgwithasset.R;
import com.jit.bgwithasset.font.TypefaceUtils;

/**
 * @author crazyZhangxl on 2018-12-25 17:17:38.
 * Describe:
 */

public class FontActivity extends AppCompatActivity {
    private TextView mtvText1,mTvText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        mtvText1 = findViewById(R.id.tvOne);
        mTvText2 = findViewById(R.id.tvTwo);
        setStyle();

    }

    private void setStyle() {
        //设置字体
        TypefaceUtils.setTypeface(mtvText1);
    }
}
