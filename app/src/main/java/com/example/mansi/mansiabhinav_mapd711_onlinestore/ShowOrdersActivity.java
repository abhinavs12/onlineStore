package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ShowOrdersActivity extends AppCompatActivity {
    DatabaseManager mydb;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders);
        mydb = new DatabaseManager(this);

        textView = (TextView) findViewById(R.id.textView35);


        Cursor res = mydb.getAllData_table1();
        if(res.getCount() == 0 ){
            Toast.makeText(ShowOrdersActivity.this, "No Data Found", Toast.LENGTH_LONG).show();

            return;
        }
        StringBuffer buffer = new StringBuffer();
        while ((res.moveToNext())){
            buffer.append("Order ID :" + res.getString(0) +"\n");
            buffer.append("UserName :" + res.getString(1) +"\n");
            buffer.append("ProductName :" + res.getString(2) +"\n");
            buffer.append("Status :" + res.getString(3) +"\n");
            buffer.append("Date :" + res.getString(4) +"\n");
            buffer.append("Price :" + res.getString(5) +"\n");
            buffer.append("\n");

        }

        textView.setText(buffer);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){

        Intent intent = new Intent(ShowOrdersActivity.this, SignInActivity.class);



        switch(menuItem.getItemId()){


            case R.id.Logout:
                startActivity(intent);
                break;



        }


        return true;

    }
}
