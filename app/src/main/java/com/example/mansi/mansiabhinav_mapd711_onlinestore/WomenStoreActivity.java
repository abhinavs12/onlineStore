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


public class WomenStoreActivity extends AppCompatActivity {

    DatabaseManager mydb;
     ImageView image1, image2, image3, image4, image5;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_store);

        mydb = new DatabaseManager(this);
        image1 = (ImageView) findViewById(R.id.imageView3);
        image2 = (ImageView) findViewById(R.id.imageView6);
        image3 = (ImageView) findViewById(R.id.imageView7);
        image4 = (ImageView) findViewById(R.id.imageView8);
        image5 = (ImageView) findViewById(R.id.imageView9);
        username = getIntent().getStringExtra("email");
        final Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("user", getIntent().getStringExtra("user"));


        image1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String currentTime = Calendar.getInstance().getTime().toGMTString();
                mydb.insertData_table1(getIntent().getStringExtra("user"),"Cropped Swing Spot Sweat","Processing",currentTime,"35");
                startActivity(intent);

            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String currentTime = Calendar.getInstance().getTime().toGMTString();
                mydb.insertData_table1(getIntent().getStringExtra("user"),"Oversized Jumper","Processing",currentTime,"23");
                startActivity(intent);

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String currentTime = Calendar.getInstance().getTime().toGMTString();
                mydb.insertData_table1(getIntent().getStringExtra("user"),"One Shoulder Floral Dress","Processing",currentTime,"34");
                startActivity(intent);

            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String currentTime = Calendar.getInstance().getTime().toGMTString();
                mydb.insertData_table1(getIntent().getStringExtra("user"),"Denim White Top","Processing",currentTime,"21");
                startActivity(intent);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String currentTime = Calendar.getInstance().getTime().toGMTString();
                mydb.insertData_table1(getIntent().getStringExtra("user"),"Asymmetric Polka Dots Dress","Processing",currentTime,"23");
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

        Intent intent = new Intent(WomenStoreActivity.this, SignInActivity.class);
        Intent intent1 = new Intent(WomenStoreActivity.this, OrderActivity.class);
        Intent intent2 = new Intent(WomenStoreActivity.this, StoreActivity.class);


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
