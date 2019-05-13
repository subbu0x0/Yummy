package com.umlproject.yummy;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderDetails extends AppCompatActivity {
String id,tr;
DatabaseReference dbref;
TextView key,res,price,location,status,m,track_loc;
Button trac;
Order o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        key = findViewById(R.id.order_id);
        res = findViewById(R.id.res_name);
        track_loc = findViewById(R.id.tl);
        price = findViewById(R.id.order_price);
        location = findViewById(R.id.del_loc);
        status = findViewById(R.id.del_status);
        m = findViewById(R.id.pay_mode);
        trac = findViewById(R.id.track);
        Bundle bun = getIntent().getExtras();
        id = bun.getString("key");
        dbref= FirebaseDatabase.getInstance().getReference("orders");
        dbref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                o = dataSnapshot.getValue(Order.class);
                key.setText(id);
                res.setText(o.getRest());
                price.setText(o.getPrice());
                location.setText(o.getLocation());
                status.setText(o.getStatus());
                m.setText(o.getPaymode());
                tr = o.getTracking();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    trac.setOnClickListener(new View.OnClickListener() {
    @Override
        public void onClick(View v) {
        track_loc.setText(tr);
        track_loc.setVisibility(View.VISIBLE);
    }
});
    }
}
