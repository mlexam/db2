package com.example.dboperation2;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText rollno, name, email;
    Button update_btn, delete_btn, select_btn,insert_btn;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollno= findViewById(R.id.rollno);
        name= findViewById(R.id.name);
        email= findViewById(R.id.email);
        insert_btn= findViewById(R.id.insert_btn);
        select_btn= findViewById(R.id.select_btn);
        update_btn= findViewById(R.id.update_btn);
        delete_btn= findViewById(R.id.delete_btn);
        db= new DBHelper(getApplicationContext());

        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rollno_num= Integer.parseInt(rollno.getText().toString());
                String name_txt= name.getText().toString();
                String email_txt= email.getText().toString();

                boolean insert_result= db.insertToDB(rollno_num, name_txt, email_txt);
                if(insert_result){
                    Toast.makeText(getApplicationContext(), "Inserted successfully.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Insertion failed !!", Toast.LENGTH_LONG).show();
                }

            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rollno_num= Integer.parseInt(rollno.getText().toString());
                String name_txt= name.getText().toString();
                String email_txt= email.getText().toString();

                DBHelper db= new DBHelper(getApplicationContext());

                boolean update_result= db.updateToDB(rollno_num, name_txt, email_txt);
                if(update_result){
                    Toast.makeText(getApplicationContext(), "Updated successfully.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Updation failed !!", Toast.LENGTH_LONG).show();
                }

            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rollno_num= Integer.parseInt(rollno.getText().toString());
                DBHelper db= new DBHelper(getApplicationContext());

                boolean update_result= db.deleteFromDB(rollno_num);
                if(update_result){
                    Toast.makeText(getApplicationContext(), "Deleted successfully.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Deletion failed !!", Toast.LENGTH_LONG).show();
                }
            }
        });
        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.selectFromDB();
                if (res.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "No entry Exist", Toast.LENGTH_LONG).show();
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("id : " + res.getString(0) + "\n");
                        buffer.append("Name : " + res.getString(1) + "\n");
                        buffer.append("email : " + res.getString(2) + "\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("User Entries");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });




    }
}