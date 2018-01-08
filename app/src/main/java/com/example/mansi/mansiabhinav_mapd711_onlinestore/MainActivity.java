package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signIn = (Button) findViewById(R.id.signin);
        Button join = (Button) findViewById(R.id.join);
        final Intent joinIntent = new Intent(this, JoinActivity.class);
        final Intent signinIntent = new Intent(this, SignInActivity.class);

        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            startActivity(signinIntent);


            }
        });


        join.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            startActivity(joinIntent);



            }
        });

    }
}
