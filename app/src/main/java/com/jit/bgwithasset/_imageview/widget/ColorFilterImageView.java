package com.jit.bgwithasset._imageview.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * @author crazyZhangxl on 2019/3/9.
 * Describe:
 */
public class ColorFilterImageView extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener {
    public ColorFilterImageView(Context context) {
        this(context,null);
    }

    public ColorFilterImageView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ColorFilterImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            // 按下时图像变灰
            case MotionEvent.ACTION_DOWN:
                Log.e("事件","摁下");
                setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                break;
            // 手指离开或取消操作时恢复原色
            case MotionEvent.ACTION_UP:
                Log.e("事件","抬起");
                setColorFilter(Color.TRANSPARENT);
            case MotionEvent.ACTION_CANCEL:
                Log.e("事件","取消");
                setColorFilter(Color.TRANSPARENT);
                break;
            default:
                break;
        }
        return false;
    }
}
