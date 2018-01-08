package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText username, password;
    DatabaseManager mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mydb = new DatabaseManager(this);

        Button signInStore = (Button) findViewById(R.id.signinstore);

        final Intent intent = new Intent(this, StoreActivity.class);
        final Intent intentAdmin = new Intent(this, AdminActivity.class);
        username = (EditText) findViewById(R.id.editText6);
        password = (EditText) findViewById(R.id.editText8);



        signInStore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean signedIn = false;
                boolean admin = false;

                String wrongUsernameAndPassword = getString(R.string.wrongUsernameAndPassword);
                Cursor res = mydb.getAllData();

                while ((res.moveToNext())){

                    if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                        admin = true;
                        startActivity(intentAdmin);
                        break;
                    }

                    if (username.getText().toString().equals(res.getString(3)) && password.getText().toString().equals(res.getString(4))){

                        intent.putExtra("username",res.getString(1));
                        intent.putExtra("user",res.getString(3));

                        signedIn = true;
                        break;

                    }
                }
                    if (signedIn){
                        startActivity(intent);
                    }
                    else if (!admin ){
                        Toast.makeText(SignInActivity.this, wrongUsernameAndPassword, Toast.LENGTH_LONG).show();
                    }






            }
        });
    }
}
