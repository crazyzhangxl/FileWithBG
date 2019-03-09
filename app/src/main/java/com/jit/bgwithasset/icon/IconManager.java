package com.jit.bgwithasset.icon;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;
import android.util.Xml;
import android.widget.ExpandableListView;

import com.jit.bgwithasset.MyApp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author crazyZhangxl on 2018/12/31.
 * Describe: 图标管理---
 *
 * https://www.cnblogs.com/huhx/p/useLruCache.html  LruCache原理运用
 */
public class IconManager {

    private static final String ICON_DIR ="home_icons/";
    private static final int CACHE_MAX_SIZE = 1024;
    private static final List<Entry> mEntryList = new ArrayList<>();
    private static final Map<String,Entry> mTextEntry = new HashMap<>();
    /**
     * 不是太熟悉----
     */
    private static LruCache<String, Bitmap> mDrawableCache;


    static {
        load(MyApp.getmContext(),ICON_DIR+"icons");

        mDrawableCache = new LruCache<String, Bitmap>(CACHE_MAX_SIZE) {
            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                if (oldValue != newValue) {
                    oldValue.recycle();
                }
            }
        };
    }

    private static void load(Context context, String iconPath) {
        new EntryHandler().load(context,iconPath);
        Log.e("icon", "load: "+mEntryList.size());
    }


    private static class EntryHandler extends DefaultHandler{

        public void load(Context context,String iconPath){
            InputStream is = null;
            try {
                is = context.getAssets().open(iconPath);
                Xml.parse(is,Xml.Encoding.UTF_8,this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (SAXException e) {
                e.printStackTrace();
            }finally {
                if (null !=  is){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("icon".equals(localName)){
                String des = attributes.getValue(uri, "title");
                String picPath = attributes.getValue(uri,"src");
                int sort = Integer.parseInt(attributes.getValue(uri,"sort"));
                mEntryList.add(new Entry(des,picPath,sort));
            }
        }
    }

    public static final Drawable getDrawable(Context context, String text) {
        Entry entry = mTextEntry.get(text);
        if (entry == null || TextUtils.isEmpty(entry.des)) {
            return null;
        }

        Bitmap cache = mDrawableCache.get(entry.picPath);
        if (cache == null) {
            cache = loadAssetBitmap(context, entry.picPath);
        }
        return new BitmapDrawable(context.getResources(), cache);
    }

    private static Bitmap loadAssetBitmap(Context context, String picPath) {
        InputStream inputStream = null;
        try {
            Resources resources = context.getResources();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDensity = DisplayMetrics.DENSITY_HIGH;
            options.inScreenDensity = resources.getDisplayMetrics().densityDpi;
            options.inTargetDensity = resources.getDisplayMetrics().densityDpi;
            inputStream =  context.getAssets().open(picPath);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, new Rect(), options);
            if (bitmap != null){
                // 文件路径,bitmap
                mDrawableCache.put(picPath,bitmap);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static class Entry{
        private String des;
        private String picPath;
        private int sort;

        public Entry(String des, String picPath, int sort) {
            this.des = des;
            this.picPath = picPath;
            this.sort = sort;
        }

        public int getSort() {
            return sort;
        }

        public String getDes() {
            return des;
        }

        public String getPicPath() {
            return picPath;
        }
    }
}
