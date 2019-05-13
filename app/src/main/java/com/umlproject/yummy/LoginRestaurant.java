package com.umlproject.yummy;

import android.content.Intent;
import android.support.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginRestaurant extends AppCompatActivity {
    EditText password, name;
    Button login;
    TextView clogin, reg;
    DatabaseReference registration;
    Spinner spinner;
    String r;
    String[] role = new String[]{"restaurant", "delivery_executive"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_restaurant);
        registration = FirebaseDatabase.getInstance().getReference();
        password = (EditText) findViewById(R.id.resp);
        login = (Button) findViewById(R.id.restaurantlogin);
        name = (EditText) findViewById(R.id.resn);
        spinner = findViewById(R.id.roles);
        clogin = findViewById(R.id.customer);
        reg = findViewById(R.id.regrest);

        reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegRestaurant.class);
                startActivity(i);
            }
        });

        clogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(), Retrieve.class);
                startActivity(i1);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "enter details", Toast.LENGTH_SHORT).show();
                } else {
                    login1();
                }
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginRestaurant.this, android.R.layout.simple_spinner_item, role);
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

    private void login1() {
        final String pwd = password.getText().toString();
        final String n = name.getText().toString();
        DatabaseReference itemsRef = registration.child(r).child(n);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    Toast.makeText(getApplicationContext(), "user not found", Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                    Restaurant res = dataSnapshot.getValue(Restaurant.class);
                    String pw = res.getPwd();
                    if(pw.equals(pwd)){
                        if(r.equals("restaurant")){
                        Intent i = new Intent(getApplicationContext(),ResHome.class);
                        Bundle bun= new Bundle();
                        bun.putString("name",n);
                        i.putExtras(bun);
                        startActivity(i);
                    }
                    else if(r.equals("delivery_executive")){
                            Intent i = new Intent(getApplicationContext(),DelHome.class);
                            Bundle bun= new Bundle();
                            bun.putString("name",n);
                            i.putExtras(bun);
                            startActivity(i);
                    }
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

