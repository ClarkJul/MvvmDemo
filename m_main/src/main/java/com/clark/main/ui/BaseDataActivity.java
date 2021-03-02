package com.clark.main.ui;

import android.app.AppComponentFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BaseDataActivity extends AppCompatActivity {
    protected List<Integer> mDrawableList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        for (int i=0;i<=2;i++) {
            int drawable = getResources().getIdentifier("guide$i", "drawable", getPackageName());
            mDrawableList.add(drawable);
        }
    }
}
