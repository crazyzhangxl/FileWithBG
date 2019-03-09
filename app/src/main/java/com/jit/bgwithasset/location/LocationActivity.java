package com.jit.bgwithasset.location;

import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.jit.bgwithasset.R;

/**
 * @author crazyZhangxl on 2018-12-25 16:14:55.
 * Describe:
 */

public class LocationActivity extends AppCompatActivity {
    private static final String TAG = "位置";
    private Button mBtnLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initViews();
        getWindowHeight();
    }

    private void initViews() {
        mBtnLocation = findViewById(R.id.btnLocation);
        getButtonLocation();
        getMeasureHeight();
    }

    private void getMeasureHeight() {
        mBtnLocation.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: "+ mBtnLocation.getMeasuredHeight());
                Log.e(TAG, "run: "+mBtnLocation.getMeasuredWidth());
                // output width = 208 height = 96
            }
        });
    }

    private void getButtonLocation(){

        mBtnLocation.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 一个控件在整个屏幕中的坐标位置 --(状态栏48px)
                int[] location1 = new int[2];
                mBtnLocation.getLocationOnScreen(location1);
                Log.e(TAG, "getLocationOnScreen : x = "+location1[0]+" y = "+location1[1] );
                // output  x = 40 , y = 200
                int[] location2 = new int[2];
                mBtnLocation.getLocationInWindow(location2);
                Log.e(TAG, "getLocationInWindow : x = "+location2[0]+" y = "+location2[1] );
                // output x = 40 , y = 200

                Rect rect = new Rect();
                mBtnLocation.getWindowVisibleDisplayFrame(rect);
                Log.e("getWindowVisible","矩形左 = "+rect.left+"  矩形上 = "+rect.top +
                        " 矩形右 = "+rect.right +"  矩形下 = "+rect.bottom);
                // output (0,48,720,1280)

                // 移除树构建测量 防止内存泄漏-------
                mBtnLocation.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });
    }

    private void getWindowHeight() {
        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        int height = getWindowManager().getDefaultDisplay().getHeight();
        Log.e(TAG, "heightPixels = "+heightPixels);
        // output = 1280
        Log.e(TAG, "height = "+height );
        // output = 1280
    }
}
