package com.umlproject.yummy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResHome extends AppCompatActivity {
    TextView tv;
    EditText ip,in;
    Button ilist,olist,additem;
    String res_name,in1;
    long ip1;
    DatabaseReference ref,ref1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_home);
        tv = findViewById(R.id.wel);
        ip = findViewById(R.id.iprice);
        in = findViewById(R.id.iname);
        ilist = findViewById(R.id.il);
        olist = findViewById(R.id.ol);
        additem = findViewById(R.id.aitem);
        Bundle bun = getIntent().getExtras();
        res_name = bun.getString("name");
        tv.setText(getResources().getString(R.string.welcome,res_name));
        ref = FirebaseDatabase.getInstance().getReference();
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in1 = in.getText().toString();
                if(TextUtils.isEmpty(in1) || TextUtils.isEmpty(ip.getText().toString())){
                    Toast.makeText(getApplicationContext(), "enter item details", Toast.LENGTH_SHORT).show();
                }
                else{
                    ip1 = Integer.parseInt(ip.getText().toString());
                    aditem();
                }
            }
        });
        ilist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ItemsRes.class);
                Bundle bun= new Bundle();
                bun.putString("name",res_name);
                i.putExtras(bun);
                startActivity(i);
            }
        });
        olist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),OrdersRes.class);
                Bundle bun= new Bundle();
                bun.putString("name",res_name);
                i.putExtras(bun);
                startActivity(i);
            }
        });
    }
    public void aditem(){
        ref1 = ref.child("items").child(in1);
        Items it = new Items(res_name,in1,ip1);
        ref1.setValue(it);
        Toast.makeText(this,"item added successfully",Toast.LENGTH_LONG).show();
    }
}
