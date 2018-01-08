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
        Button btn = (Button) findViewById(R.id.button);
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
                boolean registered = false;
                Cursor res = mydb.getAllData();

                while ((res.moveToNext())){
                    if (editEmail.getText().toString().equals(res.getString(3))){
                        registered = true;
                    }

                }

                if(registered){
                    Toast.makeText(JoinActivity.this, "Email Address already Registered", Toast.LENGTH_LONG).show();
                } else{
                    boolean isInserted =   mydb.insertCustomer(editFirstName.getText().toString(),
                            editLastName.getText().toString(),
                            editEmail.getText().toString(),
                            editPassword.getText().toString(),
                            gender,
                            editDOB.getText().toString());
                    if(isInserted){
                        Toast.makeText(JoinActivity.this, "You are Signed in Continue Shopping with us", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(JoinActivity.this, "Please try again", Toast.LENGTH_LONG).show();

                    }
                }




            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // populateListview();
                Cursor res = mydb.getAllData();
                if(res.getCount() == 0 ){
                    showMessages("Error","NO Data Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while ((res.moveToNext())){
                    buffer.append("Id :" + res.getString(0) +"\n");
                    buffer.append("Name :" + res.getString(1) +"\n");
                    buffer.append("Subject :" + res.getString(2) +"\n");
                    buffer.append("Marks :" + res.getString(3) +"\n");
                    buffer.append("Marks :" + res.getString(4) +"\n");
                    buffer.append("Marks :" + res.getString(5) +"\n");

                    showMessages("DATa",buffer.toString());
                }


            }
        });


    }

    public void  showMessages(String title, String Messeges){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messeges);
        builder.show();
    }
}
