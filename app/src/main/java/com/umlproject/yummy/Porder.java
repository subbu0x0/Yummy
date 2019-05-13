package com.umlproject.yummy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Porder extends AppCompatActivity {
    TextView username,res,pri,loc,pay;
    String res_n,un,p,mode,status,loca;
    String[] locations = new String[]{"Maddilapalem", "Kommadi", "Tagarapuvalasa"};
    Button ord,ho;
    RadioGroup pay_mode;
    Spinner spinner;
    DatabaseReference db,ref;
    FirebaseDatabase fb;
protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

        setContentView(R.layout.porder_act);
        fb = FirebaseDatabase.getInstance();
        Bundle bun = getIntent().getExtras();
        res_n = bun.getString("rest");
        un = bun.getString("user");
        p = bun.getString("tc");
        username = findViewById(R.id.user_name);
        res = findViewById(R.id.restaurant);
        pri = findViewById(R.id.price);
        loc = findViewById(R.id.loc);
        pay = findViewById(R.id.pay);
        spinner = findViewById(R.id.spinner1);
        pay_mode = findViewById(R.id.radioGroup);
        ho = findViewById(R.id.home);
        pay_mode.clearCheck();
        pay_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb){
                    mode = rb.getText().toString();

                }
            }
        });

    ArrayAdapter<String>adapter = new ArrayAdapter<String>(Porder.this,android.R.layout.simple_spinner_item,locations);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    username.setText(un);
    res.setText(res_n);
    pri.setText(p);
    loc.setText(getString(R.string.sl));
    pay.setText(getString(R.string.pm));
    status = "ORDER_PLACED";
   // Integer indexValue = spinner.getSelectedItemPosition();
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            loca = spinner.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
    ord = findViewById(R.id.orderfood);
    ord.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setorder();
        }
    });
    ho.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
            Bundle bun1 = new Bundle();
            bun1.putString("name",un);
            i.putExtras(bun1);
            startActivity(i);
        }
    });

}
public void setorder(){
    db = fb.getReference("orders");
    String key = db.push().getKey();
    Order order = new Order("delivery not started",key,res_n,un,p,mode,status,loca);
    db.child(key).setValue(order);
    Toast.makeText(this,"order successfully placed ",Toast.LENGTH_LONG).show();
    ref = fb.getReference("registration").child(un).child("cart");
    ref.removeValue();



}
}
