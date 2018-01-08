package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);


        TextView name = (TextView) findViewById(R.id.name);

        String username = getIntent().getStringExtra("username");

        name.setText("Hi " +username +" Start your Shopping:");

        ImageView men = (ImageView) findViewById(R.id.men);
        ImageView women = (ImageView) findViewById(R.id.imageView);
        ImageView kid = (ImageView) findViewById(R.id.kids);

        final Intent intentMen = new Intent(this, MenStoreActivity.class);
        final Intent intentWomen = new Intent(this, WomenStoreActivity.class);
        final Intent intentKids = new Intent(this, KidsStoreActivity.class);

        men.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentMen.putExtra("email",getIntent().getStringExtra("username"));
                intentMen.putExtra("user",getIntent().getStringExtra("user"));
                startActivity(intentMen);


            }
        });

        women.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentWomen.putExtra("email",getIntent().getStringExtra("username"));
                intentWomen.putExtra("user",getIntent().getStringExtra("user"));
                startActivity(intentWomen);


            }
        });

        kid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentKids.putExtra("email",getIntent().getStringExtra("username"));
                intentKids.putExtra("user",getIntent().getStringExtra("user"));
                startActivity(intentKids);


            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){

        Intent intent = new Intent(StoreActivity.this, SignInActivity.class);
        Intent intent1 = new Intent(StoreActivity.this, OrderActivity.class);
        Intent intent2 = new Intent(StoreActivity.this, StoreActivity.class);


        switch(menuItem.getItemId()){
            case R.id.Orders:
                intent1.putExtra("username",getIntent().getStringExtra("username"));
                intent1.putExtra("user",getIntent().getStringExtra("user"));
                startActivity(intent1);

                break;

            case R.id.Logout:
                startActivity(intent);
                break;

            case R.id.Home:
                intent2.putExtra("username",getIntent().getStringExtra("username"));
                intent2.putExtra("user",getIntent().getStringExtra("user"));
                startActivity(intent2);
                break;

        }


        return true;

    }
}
