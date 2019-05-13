package com.umlproject.yummy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Restaurant_act4 extends AppCompatActivity {
    TextView iname,price;
    String item;
    long i;
    Button b;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_act4);
        ref = FirebaseDatabase.getInstance().getReference();
        b = (Button) findViewById(R.id.ritem);
        iname =  (TextView) findViewById(R.id.iname3);
        price = (TextView)findViewById(R.id.iprice3);
        Bundle bun=getIntent().getExtras();
        assert bun != null;
        item=bun.getString("itemn");
        iname.setText(item);
        setItem();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref1 = ref.child("items").child(item);
                ref1.removeValue();
                Toast.makeText(getApplicationContext(),"successfully added to cart",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void setItem(){
        DatabaseReference pr = ref.child("items")
                .child(item)
                .child("price");
        pr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                i = dataSnapshot.getValue(Long.class);
                price.setText(String.valueOf(i));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
