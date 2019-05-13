package com.umlproject.yummy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectedOrders extends AppCompatActivity {
    ListView listView;
    DatabaseReference dbref;
    ArrayList<String> l;
    ArrayAdapter<String> ld;
    String dname;
    FirebaseDatabase fbdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_orders);
        listView = (ListView)findViewById(R.id.lv7);
        listView.setClickable(true);
        fbdb = FirebaseDatabase.getInstance();
        Bundle bun = getIntent().getExtras();
        dname = bun.getString("name");
        dbref = fbdb.getReference().child("delivery_executive").child(dname).child("selected_orders");
        l = new ArrayList<>();
        ld  = new ArrayAdapter<>(this,R.layout.item_info,R.id.item,l);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String ord = ds.getValue(String.class);
                        l.add(ord);
                }
                if(l.isEmpty()){
                    Toast.makeText(getApplicationContext(),"no selected orders",Toast.LENGTH_SHORT).show();
                }
                listView.setAdapter(ld);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bun = new Bundle();
                Intent i1 = new Intent(getApplicationContext(),Deliver.class);
                String id1 = ((TextView) view.findViewById(R.id.item)).getText().toString();
                bun.putString("key", id1);
                bun.putString("name",dname);
                i1.putExtras(bun);
                startActivity(i1);
            }
        });
    }
}
