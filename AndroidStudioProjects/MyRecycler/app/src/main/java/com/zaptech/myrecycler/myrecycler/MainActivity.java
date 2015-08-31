package com.zaptech.myrecycler.myrecycler;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private RecyclerView myRecyclerView;

    private MyAdater mAdapter;

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
        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);

        myRecyclerView.setLayoutManager(mLayoutManager);

        input = new ArrayList<String>();

        mAdapter = new MyAdater(input);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
