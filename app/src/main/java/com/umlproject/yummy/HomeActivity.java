package com.umlproject.yummy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    String rn;
    long ip;
    TextView w;
    Button sea,ca,ord,il;
    EditText s;
    String item,text;
    int count=0;
    FirebaseDatabase ref;
    DatabaseReference ref1;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        w = (TextView)findViewById(R.id.welcome);
        s = (EditText)findViewById(R.id.usersearch);
        sea = (Button)findViewById(R.id.entersearch);
        il = (Button) findViewById(R.id.list_of_items);
        ca = findViewById(R.id.cart);
       ord = findViewById(R.id.ord);
        ref = FirebaseDatabase.getInstance();
        ref1 = ref.getReference("items");
        Bundle bun=new Bundle();
        bun=getIntent().getExtras();
        assert bun != null;
        text=bun.getString("name");
        w.setText(getResources().getString(R.string.welcome, text));
        sea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = s.getText().toString();
                if(!TextUtils.isEmpty(item)){
                search();
                }
                else{
                    Toast.makeText(getApplicationContext(),"enter item to search",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent car = new Intent(getApplicationContext(),CartActivity.class);
                Bundle bun = new Bundle();
                bun.putString("un",text);
                car.putExtras(bun);
                startActivity(car);

            }
        });
       ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent or = new Intent(getApplicationContext(),OrdersActivity.class);
                Bundle bun = new Bundle();
                bun.putString("un",text);
                or.putExtras(bun);
                startActivity(or);

            }
        });
        il.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent items = new Intent(getApplicationContext(),ItemList.class);
                Bundle bun = new Bundle();
                bun.putString("un",text);
                items.putExtras(bun);
                startActivity(items);
            }
        });
    }
    public void search() {
        final DatabaseReference itemsRef = ref1.child(item);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                   Toast.makeText(getApplicationContext(), "item not found"+item, Toast.LENGTH_SHORT)
                            .show();
                }
                else {

                    Bundle bun = new Bundle();
                    Intent i1 = new Intent(getApplicationContext(),Restaurant_act.class);
                    bun.putString("itemn", item);
                    bun.putString("user",text);
                    i1.putExtras(bun);
                    startActivity(i1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
               };
        itemsRef.addListenerForSingleValueEvent(eventListener);
    }

    }
