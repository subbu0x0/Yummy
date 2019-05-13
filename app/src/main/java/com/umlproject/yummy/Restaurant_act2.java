package com.umlproject.yummy;

import android.content.Intent;
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

public class Restaurant_act2 extends AppCompatActivity {
    TextView iname,rname;
    String item,uname,res;
    Button b,ho;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_act2);
        Bundle bun = getIntent().getExtras();
        iname = findViewById(R.id.item_name2);
        rname = findViewById(R.id.res_name2);
        ho = findViewById(R.id.home1);
        b = (Button) findViewById(R.id.rcart1);
        item=bun.getString("itemn");
        uname = bun.getString("user");
        res = bun.getString("rest");
        iname.setText(res);
        rname.setText(item);
        ref = FirebaseDatabase.getInstance().getReference();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref1 = ref.child("registration").child(uname).child("cart").child(item);
                ref1.removeValue();
                Toast.makeText(getApplicationContext(),"successfully removed from cart",Toast.LENGTH_LONG).show();
            }
        });
        ho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                Bundle bun1 = new Bundle();
                bun1.putString("name",uname);
                i.putExtras(bun1);
                startActivity(i);
            }
        });
    }

}
