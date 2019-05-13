package com.umlproject.yummy;

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

public class Restaurant_act extends AppCompatActivity {
    TextView iname,rname,price;
    String item,price1,uname;
    long i;
    Button b;
    DatabaseReference ref;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_act);
        ref = FirebaseDatabase.getInstance().getReference();
        b = (Button) findViewById(R.id.acart);
        iname =  (TextView) findViewById(R.id.item_name);
        rname = (TextView)findViewById(R.id.res_name);
        price = (TextView)findViewById(R.id.item_price);
       Bundle bun=getIntent().getExtras();
        assert bun != null;
        item=bun.getString("itemn");
       uname = bun.getString("user");
       iname.setText(item);
        setItem();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref1 = ref.child("registration").child(uname).child("cart").child(item);
                Cart cart = new Cart(item,i,price1);
                        ref1.setValue(cart);
                Toast.makeText(getApplicationContext(),"successfully added to cart",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void setItem(){
        DatabaseReference m = ref.child("items")
                .child(item)
                .child("restaurant");
        m.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                price1 = dataSnapshot.getValue(String.class);
                rname.setText( price1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
