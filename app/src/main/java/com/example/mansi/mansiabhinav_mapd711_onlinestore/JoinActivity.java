package com.example.mansi.mansiabhinav_mapd711_onlinestore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    DatabaseManager mydb;
    EditText editFirstName, editLastName, editEmail, editPassword, editDOB;
    RadioGroup radioGroup;
    String gender = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mydb = new DatabaseManager(this);

        Button joinStore = (Button) findViewById(R.id.joinStore);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        editFirstName = (EditText) findViewById(R.id.editText2);
        editLastName = (EditText) findViewById(R.id.editText5);
        editEmail = (EditText) findViewById(R.id.editText3);
        editPassword = (EditText) findViewById(R.id.editText4);
        editDOB = (EditText) findViewById(R.id.editText7);


        final Intent intent = new Intent(this, SignInActivity.class);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton male = (RadioButton) findViewById(R.id.radioButton2);
                RadioButton female = (RadioButton) findViewById(R.id.radioButton);
                if (male.isChecked()) {
                    gender = male.getText().toString();
                } else if (female.isChecked()) {
                    gender= female.getText().toString();
                }

            }
        });

        joinStore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String usernameReg = getString(R.string.usernameReg);
                String ucontinue = getString(R.string.ucontinue);
                String tryAgain = getString(R.string.tryAgain);

                boolean registered = false;
                Cursor res = mydb.getAllData();

                while ((res.moveToNext())){
                    if (editEmail.getText().toString().equals(res.getString(3))){
                        registered = true;
                    }

                }

                if(registered){
                    Toast.makeText(JoinActivity.this, usernameReg, Toast.LENGTH_LONG).show();
                } else{
                    boolean isInserted =   mydb.insertCustomer(editFirstName.getText().toString(),
                            editLastName.getText().toString(),
                            editEmail.getText().toString(),
                            editPassword.getText().toString(),
                            gender,
                            editDOB.getText().toString());
                    if(isInserted){
                        Toast.makeText(JoinActivity.this,ucontinue , Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(JoinActivity.this, tryAgain, Toast.LENGTH_LONG).show();

                    }
                }




            }
        });

    }


}
