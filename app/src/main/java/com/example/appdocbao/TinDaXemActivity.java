package com.example.appdocbao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TinDaXemActivity extends AppCompatActivity {
    ListView lvTinDaXem;
    TinDaXemAdapter adapter;
    SQLHelperTinTuc sqlHelperTinTuc;
    ImageView btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_da_xem);
        lvTinDaXem = findViewById(R.id.lvTinDaXem);

        sqlHelperTinTuc = new SQLHelperTinTuc(getBaseContext());
        ArrayList<ItemTinTuc> itemTinTucs = sqlHelperTinTuc.getAllNewAdvanced();
        adapter = new TinDaXemAdapter(getBaseContext(), itemTinTucs);
        lvTinDaXem.setAdapter(adapter);

    }
}
