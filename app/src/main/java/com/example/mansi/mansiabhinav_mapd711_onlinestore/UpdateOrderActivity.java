package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateOrderActivity extends AppCompatActivity {
    DatabaseManager mydb;
    TextView textView;
    Button show;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);

        mydb = new DatabaseManager(this);

        textView = (TextView) findViewById(R.id.textView40);
        show = (Button) findViewById(R.id.button8);
        editText = (EditText) findViewById(R.id.editText9);

        show.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String username="", productName="", status="", date="", price="";
                Cursor res = mydb.getDataByID_table1(editText.getText().toString());
                if(res.getCount() == 0 ){
                    Toast.makeText(UpdateOrderActivity.this, "No Data Found", Toast.LENGTH_LONG).show();

                    return;
                }
                while ((res.moveToNext())) {
                    username = res.getString(1);
                    productName = res.getString(2);
                    status = "Out for Delivery";
                    date = res.getString(4);
                    price = res.getString(5);
                }




               boolean update = mydb.updateOrder(editText.getText().toString(),
                       username, productName, status, date, price);

                if (update){
                    textView.setText("Order Updated");
                }
                else {
                    Toast.makeText(UpdateOrderActivity.this, "Error Occured Please Try Again", Toast.LENGTH_LONG).show();

                }




            }


        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){

        Intent intent = new Intent(UpdateOrderActivity.this, SignInActivity.class);



        switch(menuItem.getItemId()){


            case R.id.Logout:
                startActivity(intent);
                break;



        }


        return true;

    }
}
