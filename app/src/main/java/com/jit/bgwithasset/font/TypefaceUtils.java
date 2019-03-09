package com.jit.bgwithasset.font;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.jit.bgwithasset.MyApp;

/**
 * @author crazyZhangxl on 2018/12/25.
 * Describe:
 */
public class TypefaceUtils {
    private static Typeface getTypeface() {
        Context context = MyApp.getmContext();
        return Typeface.createFromAsset(context.getAssets(),
                "fonts/MTfin-Regular3.0.ttf");
    }

    public static void setTypeface(TextView tv) {
        tv.setTypeface(getTypeface());
    }
}
