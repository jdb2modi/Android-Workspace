package com.example.databasedemo;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DatabaseMainActivity extends Activity {
	
	MyDatabase myDb;
	TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_main);
        tvData = (TextView)findViewById(R.id.TextViewData);
        
        myDb  = new MyDatabase(this);
        
       
       myDb.addContact("Mitesh", "9999999999");
       myDb.addContact("A", "9999999991");
       myDb.addContact("B", "9999999992");
       myDb.addContact("C", "9999999993");
       myDb.addContact("D", "9999999994");
       myDb.addContact("E", "9999999995");
       
        //db.deleteContact("Mitesh");
       
       Cursor c = myDb.getDb().rawQuery("select * from "+MyDatabase.TABLE_CONTACTS, null);
       
       if(c.moveToFirst()){
    	   do {
    			int id = c.getInt(c.getColumnIndex(MyDatabase.KEY_ID));
    		   String strName = c.getString(c.getColumnIndex(MyDatabase.KEY_NAME));
    			String strPhone = c.getString(c.getColumnIndex(MyDatabase.KEY_PH_NO));
    			
    			tvData.append("id: "+id +"  Name: "+strName +"  Phone: "+strPhone +"\n\n");
    			
    		} while (c.moveToNext());
       }
       
      
	 
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	myDb.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.database_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
