package com.example.abardatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText e1, e2, e3;
    Button b1;
    DatabaseReference reff;
    ListView listview;
    List<Information>informationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.edittext1);
        e2 = (EditText) findViewById(R.id.edittext2);
        e3 = (EditText) findViewById(R.id.edittext3);
        b1 = (Button) findViewById(R.id.button);
        informationList=new ArrayList<>();
        listview=(ListView)findViewById(R.id.listview);

        reff= FirebaseDatabase.getInstance().getReference().child("Information");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot informationsnap:dataSnapshot.getChildren())
                {

                    Information information=informationsnap.getValue(Information.class);
                    informationList.add(information);
                }
                InformationList adapter=new InformationList(MainActivity.this,informationList);
                listview.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void AddData() {
        String name = e1.getText().toString().trim();
        String email = e2.getText().toString().trim();
        String address = e3.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(address)) {
            String id = reff.push().getKey();
            Information information = new Information(id,name,email,address);
            reff.child(id).setValue(information);
            Toast.makeText(MainActivity.this, "Information Added", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(MainActivity.this, "Check something is missing ", Toast.LENGTH_LONG).show();
        }

    }
}
