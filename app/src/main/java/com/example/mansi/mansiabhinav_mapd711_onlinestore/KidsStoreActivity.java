package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Calendar;

public class KidsStoreActivity extends AppCompatActivity {

    DatabaseManager mydb;
    ImageView image1, image2, image3, image4;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_store);

        mydb = new DatabaseManager(this);
       image1 = (ImageView) findViewById(R.id.imageView13);
        image2 = (ImageView) findViewById(R.id.imageView14);
        image3 = (ImageView) findViewById(R.id.imageView15);
        image4 = (ImageView) findViewById(R.id.imageView16);
        username = getIntent().getStringExtra("email");
        final Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("user", getIntent().getStringExtra("user"));


        image1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String product = getString(R.string.boysBlackBlazer);
                String status = getString(R.string.processing);

                String currentTime = Calendar.getInstance().getTime().toGMTString();
                mydb.insertData_table1(getIntent().getStringExtra("user"),product,status,currentTime,"25");
                startActivity(intent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String product = getString(R.string.whiteTShirt);
                String status = getString(R.string.processing);
                String currentTime = Calendar.getInstance().getTime().toGMTString();
                mydb.insertData_table1(getIntent().getStringExtra("user"),product,status,currentTime,"30");
                startActivity(intent);

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String product = getString(R.string.girlsWhiteTop);
                String status = getString(R.string.processing);
                String currentTime = Calendar.getInstance().getTime().toGMTString();
                mydb.insertData_table1(getIntent().getStringExtra("user"),product,status,currentTime,"40");
                startActivity(intent);

            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String product = getString(R.string.printedTop);
                String status = getString(R.string.processing);

                String currentTime = Calendar.getInstance().getTime().toGMTString();
                mydb.insertData_table1(getIntent().getStringExtra("user"),product,status,currentTime,"50");
                startActivity(intent);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){

        Intent intent = new Intent(KidsStoreActivity.this, SignInActivity.class);
        Intent intent1 = new Intent(KidsStoreActivity.this, OrderActivity.class);
        Intent intent2 = new Intent(KidsStoreActivity.this, StoreActivity.class);


        switch(menuItem.getItemId()){
            case R.id.Orders:
                intent1.putExtra("username", getIntent().getStringExtra("email"));
                intent1.putExtra("user", getIntent().getStringExtra("user"));
                startActivity(intent1);

                break;

            case R.id.Logout:
                startActivity(intent);
                break;

            case R.id.Home:
                intent2.putExtra("username", getIntent().getStringExtra("email"));
                intent2.putExtra("user", getIntent().getStringExtra("user"));
                startActivity(intent2);
                break;

        }


        return true;

    }

}
