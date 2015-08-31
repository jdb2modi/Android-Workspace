package com.zaptech.storeandretriveimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private ImageView mimg1;
    private Button mbtnStore;
    private Button mbtnRetrtive;
    private DatabaseHelper mDatabase;
    private byte[] img=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        imageToBitmap();
    }

    public void init() {
        mimg1 = (ImageView) findViewById(R.id.img1);
        mimg1.setImageResource(0);
        mbtnStore = (Button) findViewById(R.id.btnStore);
        mbtnStore.setOnClickListener(this);
        mbtnRetrtive = (Button) findViewById(R.id.btnRetrive);
        mbtnRetrtive.setOnClickListener(this);
        mDatabase=new DatabaseHelper(MainActivity.this);
        mDatabase.getWritableDatabase();
    }

    public void imageToBitmap(){
        Bitmap b= BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG,100,bos);
        img=bos.toByteArray();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStore:
             //   mDatabase.insertImage(img);
                Toast.makeText(MainActivity.this, "Image Saved Successfully", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnRetrive:
                Toast.makeText(MainActivity.this, "Retriving...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
