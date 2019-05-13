package com.umlproject.yummy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText password,email,name;
    TextView nu;
    Button submit,login;
    DatabaseReference registration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registration = FirebaseDatabase.getInstance().getReference("registration");
        submit = (Button) findViewById(R.id.userreg);
        password = (EditText) findViewById(R.id.userpassword);
        email = (EditText) findViewById(R.id.useremail);
        login = (Button) findViewById(R.id.userlogin);
        name = (EditText) findViewById(R.id.username);
        nu = findViewById(R.id.notuser1);
        nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),RegRestaurant.class);
                startActivity(i1);
            }
        });
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                register();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(),Retrieve.class);
                startActivity(i);
            }
        });

    }
    private void register(){
        String mail = email.getText().toString();
        String pwd = password.getText().toString();
        String n = name.getText().toString();
        if(!TextUtils.isEmpty(mail)){
           //String id = registration.push().getKey();
            User user = new User(n,mail,pwd);
            registration.child(n).setValue(user);
           Toast.makeText(this,"successful registration",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"enter details",Toast.LENGTH_LONG).show();
        }
    }
}
