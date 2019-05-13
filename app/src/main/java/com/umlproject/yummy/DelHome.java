package com.umlproject.yummy;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DelHome extends Activity {
    TextView tv;
    Button slist,olist;
    String name;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_home);
        tv = findViewById(R.id.wel1);
        slist = findViewById(R.id.so);
        olist = findViewById(R.id.ol1);
        Bundle bun = getIntent().getExtras();
        name = bun.getString("name");
        tv.setText(getResources().getString(R.string.welcome,name));
        ref = FirebaseDatabase.getInstance().getReference();
        slist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),SelectedOrders.class);
                Bundle bun = new Bundle();
                bun.putString("name",name);
                i1.putExtras(bun);
                startActivity(i1);
            }
        });
        olist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),OrdersList.class);
                Bundle bun = new Bundle();
                bun.putString("name",name);
                i1.putExtras(bun);
                startActivity(i1);
            }
        });
    }

}
