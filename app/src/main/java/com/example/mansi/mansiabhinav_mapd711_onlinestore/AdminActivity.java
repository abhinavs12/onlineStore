package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class AdminActivity extends AppCompatActivity {

    Button showOrders, showOrderByUser, updateOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        showOrders = (Button) findViewById(R.id.button4);
        showOrderByUser = (Button) findViewById(R.id.button5);
        updateOrder = (Button) findViewById(R.id.button6);

        final Intent orders = new Intent(this, ShowOrdersActivity.class);
        final Intent orderByUser = new Intent(this, ShowOrderByUserActivity.class);
        final Intent statusChange = new Intent(this, UpdateOrderActivity.class);

        showOrders.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            startActivity(orders);


            }
        });

        showOrderByUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(orderByUser);

            }
        });

        updateOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(statusChange);

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){

        Intent intent = new Intent(AdminActivity.this, SignInActivity.class);



        switch(menuItem.getItemId()){


            case R.id.Logout:
                startActivity(intent);
                break;



        }


        return true;

    }
}
