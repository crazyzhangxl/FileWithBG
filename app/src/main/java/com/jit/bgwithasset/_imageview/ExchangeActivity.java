package com.jit.bgwithasset._imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.jit.bgwithasset.R;

/**
 * @author crazyZhangxl on 2019-3-9 21:09:46.
 * Describe:Bitmap以及Drawable切换
 */

public class ExchangeActivity extends AppCompatActivity {
    private ImageView ivOne,ivTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        initViews();
        bitmapToDrawable();
        drawableToBitmap();
    }

    private void initViews() {
        ivOne = findViewById(R.id.ivOne);
        ivTwo = findViewById(R.id.ivTwo);
    }

    private void  bitmapToDrawable(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.beauty);
        //BitmapDrawable 继承自 bitmap
        BitmapDrawable drawable= new BitmapDrawable(getResources(), bitmap);
        ivOne.setImageDrawable(drawable);
    }

    private void drawableToBitmap(){
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.beauty);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bm= bd.getBitmap();
        ivTwo.setImageBitmap(bm);
    }
}
