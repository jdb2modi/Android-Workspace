package com.zaptech.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    Button mbtnClickMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        mbtnClickMe=(Button)findViewById(R.id.btnClick);
        mbtnClickMe.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this,"Button Clicked",Toast.LENGTH_LONG).show();
    }
}
