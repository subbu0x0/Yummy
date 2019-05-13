package com.umlproject.yummy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegRestaurant extends AppCompatActivity {
    EditText password,email,name;
    Button submit,login;
    DatabaseReference registration;
    Spinner spinner;
    String r;
    String[] role =new String[] {"restaurant","delivery_executive"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_restaurant);
        registration = FirebaseDatabase.getInstance().getReference();
        submit = (Button) findViewById(R.id.resreg);
        password = (EditText) findViewById(R.id.respassword);
        email = (EditText) findViewById(R.id.resemail);
        login = (Button) findViewById(R.id.reslogin);
        name = (EditText) findViewById(R.id.resname);
        spinner = findViewById(R.id.spinner3);
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                register();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(),LoginRestaurant.class);
                startActivity(i);
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegRestaurant.this,android.R.layout.simple_spinner_item,role);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                r = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void register(){
        String mail = email.getText().toString();
        String pwd = password.getText().toString();
        String n = name.getText().toString();
        if(!TextUtils.isEmpty(mail) &&!TextUtils.isEmpty(pwd) &&!TextUtils.isEmpty(n) ){
            Restaurant restaurant = new Restaurant(n,pwd,mail);
            registration.child(r).child(n).setValue(restaurant);
            //String id = registration.push().getKey();
            Toast.makeText(this,"successful registration",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"enter details",Toast.LENGTH_LONG).show();
        }
    }
}
