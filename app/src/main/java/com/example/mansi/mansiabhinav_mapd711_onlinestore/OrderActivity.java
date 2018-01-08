package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    DatabaseManager mydb;
    RelativeLayout relativelayout;
    String username, user;
    TextView noData, products, price, status, textView, textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mydb = new DatabaseManager(this);
        relativelayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        username = getIntent().getStringExtra("username");
        user = getIntent().getStringExtra("user");
        noData = (TextView)  findViewById(R.id.textView44);
        textView = (TextView)  findViewById(R.id.textView30);
        textView1 = (TextView)  findViewById(R.id.textView31);
        textView2 = (TextView)  findViewById(R.id.textView32);
        products = (TextView)  findViewById(R.id.textView41);
        price = (TextView)  findViewById(R.id.textView42);
        status = (TextView)  findViewById(R.id.textView43);

        Cursor res = mydb.getDataByUsername_table1(user);
        if(res.getCount() == 0 ){
            noData.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
            return;
        }
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        while ((res.moveToNext())){

            buffer.append(res.getString(2) +"\n");
            buffer2.append(res.getString(3) +"\n");
            buffer1.append(res.getString(5) +"\n");
            buffer.append("\n");
            buffer.append("\n");
            buffer1.append("\n");
            buffer1.append("\n");

            buffer2.append("\n");
            buffer2.append("\n");


            if (res.getString(2).length() > 20){
                buffer1.append("\n");

                buffer2.append("\n");

            }


        }


        products.setText(buffer);
        price.setText(buffer1);
        status.setText(buffer2);



    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){

        Intent intent = new Intent(OrderActivity.this, SignInActivity.class);
        Intent intent1 = new Intent(OrderActivity.this, OrderActivity.class);
        Intent intent2 = new Intent(OrderActivity.this, StoreActivity.class);


        switch(menuItem.getItemId()){
            case R.id.Orders:

                intent1.putExtra("username", getIntent().getStringExtra("username"));
                intent1.putExtra("user", getIntent().getStringExtra("user"));
                startActivity(intent1);

                break;

            case R.id.Logout:
                startActivity(intent);
                break;

            case R.id.Home:
                intent2.putExtra("username", getIntent().getStringExtra("username"));
                intent2.putExtra("user", getIntent().getStringExtra("user"));
                startActivity(intent2);
                break;

        }


        return true;

    }


}
