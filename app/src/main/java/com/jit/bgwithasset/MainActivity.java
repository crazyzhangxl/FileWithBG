package com.jit.bgwithasset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jit.bgwithasset._imageview.ColorFilterActivity;
import com.jit.bgwithasset._imageview.ExchangeActivity;
import com.jit.bgwithasset.asset.AssetEasyActivity;
import com.jit.bgwithasset.background.BackgroundActivity;
import com.jit.bgwithasset._imageview.IvHalfAlphaActivity;
import com.jit.bgwithasset._imageview.LoadImageActivity;
import com.jit.bgwithasset.demo.PerfectDemoActivity;
import com.jit.bgwithasset.file.FileActivity;
import com.jit.bgwithasset.file.FontActivity;
import com.jit.bgwithasset.icon.HomeIconActivity;
import com.jit.bgwithasset.json.JsonActivity;
import com.jit.bgwithasset.location.LocationActivity;

/**
 * @author crazyZhangxl on 2018-12-10 17:50:39.
 * Describe:
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnParse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AssetEasyActivity.class));
            }
        });

        findViewById(R.id.btnBg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BackgroundActivity.class));
            }
        });

        findViewById(R.id.location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LocationActivity.class));
            }
        });

        findViewById(R.id.font).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FontActivity.class));
            }
        });

        findViewById(R.id.demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PerfectDemoActivity.class));
            }
        });

        findViewById(R.id.btnFile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FileActivity.class));
            }
        });

        findViewById(R.id.btnJson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JsonActivity.class));
            }
        });

        findViewById(R.id.btnHomeIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeIconActivity.class));
            }
        });

        findViewById(R.id.btnLoadImageOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoadImageActivity.class));
            }
        });

        findViewById(R.id.btnAlpha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IvHalfAlphaActivity.class));
            }
        });

        findViewById(R.id.btnTouchGray).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ColorFilterActivity.class));
            }
        });

        findViewById(R.id.btnBpWithDw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExchangeActivity.class));
            }
        });

    }


}
