package com.umlproject.yummy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity{
    ListView listView;
    DatabaseReference dbref,ref;
    ArrayList <String> l;
    ArrayAdapter <String> ld;
    long tp;
    Cart ca;
    String uname,rest;
    Button ord;
    TextView co;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listView = (ListView)findViewById(R.id.cart_items);
        listView.setClickable(true);
        co = (TextView) findViewById(R.id.cost);
        ord = findViewById(R.id.order);
        Bundle bun = getIntent().getExtras();
        uname = bun.getString("un");
        dbref = FirebaseDatabase.getInstance().getReference();
        ref = dbref.child("registration").child(uname).child("cart");
        l = new ArrayList<>();
        ld  = new ArrayAdapter<>(this,R.layout.item_info,R.id.item,l);
        ca = new Cart();
        getcart();
        ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tp!=0) {
                    Intent place_order = new Intent(getApplicationContext(), Porder.class);
                    Bundle bun = new Bundle();
                    bun.putString("rest", rest);
                    bun.putString("user", uname);
                    bun.putString("tc", String.valueOf(tp));
                    place_order.putExtras(bun);
                    startActivity(place_order);
                }
                else{
                    Toast.makeText(getApplicationContext(),"add items to cart",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bun = new Bundle();
                Intent i1 = new Intent(getApplicationContext(),Restaurant_act2.class);
                String item1 = ((TextView) view.findViewById(R.id.item)).getText().toString();
                String[] item = item1.split(":");
                bun.putString("itemn", item[0]);
                bun.putString("user",uname);
                bun.putString("rest",rest);
                i1.putExtras(bun);
                startActivity(i1);
            }
        });
    }
    public void getcart(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    ca = dataSnapshot1.getValue(Cart.class);
                    //System.out.println(dataSnapshot1.getValue());
                    tp = tp + ca.getPrice();
                    assert ca != null;
                    l.add(ca.getName()+":"+ String.valueOf(ca.getPrice()));
                }
                listView.setAdapter(ld);
                co.setText(String.valueOf(tp));
                rest = ca.getRestaurant();
                System.out.println(rest);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
