package com.jit.bgwithasset._imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.jit.bgwithasset.R;

/**
 * @author crazyZhangxl on 2019-1-26 9:04:43.
 * Describe: 加载图片
 * Android超大图加载
 * https://www.jianshu.com/p/b0e2be9e0f8c
 */

public class LoadImageActivity extends AppCompatActivity {
    private ImageView mIvOne,mIvTwo;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        initViews();
        loadPic();
        fuyongPic();

    }

    /**
     * 复用已经存在的bitmap
     */
    private void fuyongPic() {
        BitmapFactory.Options options =new  BitmapFactory.Options();
        options.inBitmap = mBitmap;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flower, options);
        mIvTwo.setImageBitmap(bitmap);
    }

    /**
     * 加载压缩图片---
     */
    private void loadPic() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),R.drawable.flower,options);

        options.inSampleSize = calculateSampleSize(160,160,options.outWidth,options.outHeight);
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flower, options);
        mIvOne.setImageBitmap(mBitmap);
    }

    /**
     *
     * @param targetWidth   原始图片的宽
     * @param targetHeight  原始图片的高
     * @param nowWidth      原始图片的宽度
     * @param nowHeight     原始图片的高度
     * @return 返回图片压缩的比率
     */
    private int calculateSampleSize(int targetWidth,int targetHeight,int nowWidth,int nowHeight){
        Log.e("压缩", "未压缩前 宽 = "+nowWidth+",未压缩钱高 = "+nowHeight);
        int result = 1;
        int widthSize = nowWidth%targetWidth == 0? nowWidth/targetWidth:nowWidth/targetWidth+1;
        int heightSize = nowHeight%targetHeight == 0? nowHeight%targetHeight:nowHeight/targetHeight+1;
        int maxSize = Math.max(widthSize,heightSize);
        if (maxSize >= 1){
            result = maxSize;
        }

        if (nowHeight > targetHeight || nowWidth > targetWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) nowHeight / (float) targetHeight);
            final int widthRatio = Math.round((float) nowWidth / (float) targetWidth);
            // 选择宽和高比率中最小的作为inSampleSize的值，这样可以保证最终生成图片的宽和高
            // 一定都会大于等于目标的宽和高。
            result = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        Log.e("压缩", "比率 = "+result);
        return result;
    }


    private void initViews() {
        mIvOne = findViewById(R.id.firstImage);
        mIvTwo = findViewById(R.id.secondImage);
    }
}
