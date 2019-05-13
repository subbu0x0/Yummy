package com.umlproject.yummy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Retrieve extends AppCompatActivity {
    EditText pwd,name;
    Button submit;
    TextView reg,nu;
    DatabaseReference reference;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_act);
        reference = FirebaseDatabase.getInstance().getReference("registration");
        submit = (Button) findViewById(R.id.usersubmit);
        pwd = (EditText) findViewById(R.id.userp);
        name = (EditText) findViewById(R.id.usern);
        reg = (TextView)findViewById(R.id.userregister);
        nu = findViewById(R.id.notuser);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(pwd.getText().toString())||TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(getApplicationContext(),"enter details",Toast.LENGTH_SHORT).show();
                }
                else{
                login();
            }
            }
        });
        nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),LoginRestaurant.class);
                startActivity(i1);
            }
        });
    }
    public void login(){
        final String n = name.getText().toString().trim();
        final String p = pwd.getText().toString().trim();
        final DatabaseReference itemsRef = reference.child(n);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    Toast.makeText(getApplicationContext(), "user not found", Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                    User user = dataSnapshot.getValue(User.class);
                    String pw = user.getPwd();
                    if(pw.equals(p)){
                        Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                        Bundle bun= new Bundle();
                        bun.putString("name",n);
                        i.putExtras(bun);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        itemsRef.addListenerForSingleValueEvent(eventListener);

    }
}
