package com.jit.bgwithasset.asset;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jit.bgwithasset.MainActivity;
import com.jit.bgwithasset.PareDateAttributeManager;
import com.jit.bgwithasset.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author crazyZhangxl on 2018-12-25 15:15:12.
 * Describe:
 */

public class AssetEasyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_easy);
        findViewById(R.id.btnAsset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAssetList("zxl");
            }
        });

        findViewById(R.id.btnSax).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ParseDataManager.getInstance().parseData(MainActivity.this);
                PareDateAttributeManager.getInstance().init(AssetEasyActivity.this);
            }
        });
    }

    private void showAssetList(String fileAssetName){
        AssetManager assetManager = getResources().getAssets();
        try {
            String[] lists = assetManager.list(fileAssetName);
            for (String fileName:lists){
                if (fileName.contains(".")){
                    // 文件
                    Log.e("asset","文件名路径  = "+fileAssetName+ File.separator+fileName+"\n");
                    showContent(fileAssetName+File.separator+fileName);
                }else {
                    Log.e("asset","文件夹路径  = "+fileAssetName+File.separator+fileName+"\n");
                    showAssetList(fileAssetName+File.separator+fileName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showContent(String fileName) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String line = null;
        AssetManager assetManager = getResources().getAssets();
        try {
            inputStream = assetManager.open(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                Log.e("数据打印", line+"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                    inputStream = null;
                }
            }

            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    bufferedReader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                    bufferedReader = null;
                }
            }
        }
    }
}
