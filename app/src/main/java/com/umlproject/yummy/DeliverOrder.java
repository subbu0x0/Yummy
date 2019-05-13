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

public class DeliverOrder extends AppCompatActivity {
    String id,dname;
    DatabaseReference dbref,ref,ref1;
    TextView key,res,price,location,status,m,name;
    Button select;
    Order o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_order);
        key = findViewById(R.id.order_id5);
        res = findViewById(R.id.res_name5);
        price = findViewById(R.id.order_price5);
        location = findViewById(R.id.del_loc5);
        status = findViewById(R.id.del_status5);
        m = findViewById(R.id.pay_mode5);
        select = findViewById(R.id.sod);
        name = findViewById(R.id.c_name);
        Bundle bun = getIntent().getExtras();
        id = bun.getString("key");
        dname = bun.getString("name");
        dbref= FirebaseDatabase.getInstance().getReference();
        ref = dbref.child("orders");
        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                o = dataSnapshot.getValue(Order.class);
              //  System.out.println(dataSnapshot.getValue(Order.class));
                key.setText(id);
                res.setText(o.getRest());
                price.setText(o.getPrice());
                location.setText(o.getLocation());
                status.setText(o.getStatus());
                m.setText(o.getPaymode());
                name.setText(o.getUname());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(id).child("status").setValue("ORDER_SELECTED_FOR_DELIVERY");
                ref1 = dbref.child("delivery_executive").child(dname).child("selected_orders").child(id);
                ref1.setValue(id);
                Toast.makeText(getApplicationContext(),"order selected",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
