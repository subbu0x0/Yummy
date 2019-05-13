package com.umlproject.yummy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OdetailsRes extends AppCompatActivity {
    String id;
    DatabaseReference dbref;
    TextView key,uname,price,location,status,m;
    Order o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odetails_res);
        key = findViewById(R.id.order_id1);
        uname = findViewById(R.id.user_name1);
        price = findViewById(R.id.order_price1);
        location = findViewById(R.id.del_loc1);
        status = findViewById(R.id.del_status1);
        m = findViewById(R.id.pay_mode1);
        Bundle bun = getIntent().getExtras();
        id = bun.getString("key");
        dbref= FirebaseDatabase.getInstance().getReference("orders");
        dbref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                o = dataSnapshot.getValue(Order.class);
                key.setText(id);
                uname.setText(o.getUname());
                price.setText(o.getPrice());
                location.setText(o.getLocation());
                status.setText(o.getStatus());
                m.setText(o.getPaymode());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
