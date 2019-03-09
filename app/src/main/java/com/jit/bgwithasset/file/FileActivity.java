package com.jit.bgwithasset.file;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jit.bgwithasset.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author crazyZhangxl on 2018-12-26 21:44:34.
 * Describe:
 * https://blog.csdn.net/zhe_ge_sha_shou/article/details/74834235
 *
 * https://www.jianshu.com/p/3340bbdc4819
 */

public class FileActivity extends AppCompatActivity {
    private static final String TAG = "FileActivity";
    private final static String APP_CONFIG = "config2";
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        mContext = this;
        Log.e(TAG, getCacheDir().getAbsolutePath());
        //  /data/user/0/com.jit.bgwithasset/cache
        Log.e(TAG, Environment.getExternalStorageDirectory().getAbsolutePath());
        //  /storage/emulated/0

        File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
        Log.e(TAG, dirConf.getPath() );
        // /data/user/0/com.jit.bgwithasset/app_config

        putValue();
        dodDeleteFile();

    }

    private void dodDeleteFile() {

    }

    private  void putValue(){
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            // 读取app_config目录下的config
            // (文件名,模式)
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            File file = new File(dirConf.getPath(),APP_CONFIG);
            if (!file.exists()){
                file.createNewFile();
            }
            // ok好的 不回文件操作我们就好好学习 ---------
            fis = new FileInputStream(file.getPath());
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        props.setProperty("name","zxl");
        props.setProperty("age","18");


        FileOutputStream fos = null;
        try {
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            File conf = new File(dirConf, APP_CONFIG);
            fos = new FileOutputStream(conf);
            props.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
