package com.jit.bgwithasset.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jit.bgwithasset.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author crazyZhangxl on 2018-12-28 11:07:26.
 * Describe: todo 安卓文件流操作 ------
 */

public class JsonActivity extends AppCompatActivity {
    private static final String TAG = "json";
    private static final String FILE_NAME = "improve.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        findViewById(R.id.btnWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        writeToFile(FILE_NAME);
                    }
                }).start();
            }
        });

        findViewById(R.id.btnRead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,  isFileIsExits(FILE_NAME)+"");
            }
        });
    }

    private Person<ShowBean> parseSampleJson(){
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(getAssets().open("json/person.json"), "UTF-8");
            return new Gson().fromJson(inputStreamReader, new TypeToken<Person<ShowBean>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != inputStreamReader){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 判断assets先是否存在某一文件
     * @param fileNmae
     * @return
     */
    private boolean isFileIsExits(String fileNmae){
        File streamPath = getFileStreamPath(fileNmae);
        return streamPath.exists();
    }

    //以字符流的形式打开文件并向里面写内容 然后读取

    private void writeToFile(String file){
        FileOutputStream fileOutputStream = null;
        String msg ="大家好,我是谁,我喜欢你？";
        try {
            fileOutputStream = openFileOutput(file, MODE_PRIVATE);
            fileOutputStream.write(msg.getBytes());
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (null != fileOutputStream){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
