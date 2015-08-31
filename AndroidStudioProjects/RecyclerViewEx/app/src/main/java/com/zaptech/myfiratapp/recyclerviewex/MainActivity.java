package com.zaptech.myfiratapp.recyclerviewex;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends Activity {

    private RecyclerView myRecyclerView;

    private MyAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<String> input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }
        myRecyclerView.setAdapter(mAdapter);
        mAdapter.remove("Test1");
    }

    private void findViews() {


        mLayoutManager = new LinearLayoutManager(this);

        myRecyclerView.setLayoutManager(mLayoutManager);

        input = new ArrayList<String>();

        mAdapter = new MyAdapter(input);
    }
}