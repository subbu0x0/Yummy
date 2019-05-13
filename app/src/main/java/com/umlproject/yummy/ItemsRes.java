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

public class ItemsRes extends AppCompatActivity {
    ListView listView;
    DatabaseReference dbref;
    ArrayList<String> l;
    ArrayAdapter<String> ld;
    FirebaseDatabase fbdb;
    Items it;
    String rname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_res);
        listView = (ListView)findViewById(R.id.lview3);
        listView.setClickable(true);
        fbdb = FirebaseDatabase.getInstance();
        dbref = fbdb.getReference("items");
        Bundle bun=getIntent().getExtras();
        if(bun != null) {
            rname = bun.getString("name");
        }
        l = new ArrayList<>();
        ld  = new ArrayAdapter<>(this,R.layout.item_info,R.id.item,l);
        it = new Items();
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    it = ds.getValue(Items.class);
                    if(rname.equals(it.getRestaurant())){
                        l.add(it.getName());
                }
                }
                if(l.isEmpty()){
                    Toast.makeText(getApplicationContext(),"no items",Toast.LENGTH_SHORT)
                            .show();
                }
                // System.out.println(l);
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
                Intent i1 = new Intent(getApplicationContext(),Restaurant_act4.class);
                String item = ((TextView) view.findViewById(R.id.item)).getText().toString();
                bun.putString("itemn", item);
                i1.putExtras(bun);
                startActivity(i1);
            }
        });
    }
}
