package student.medicalapp1.msrit.mohith.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Health5 extends AppCompatActivity {
    EditText e1,e2,e3;

    EditText others,others1;
    Button next,back;

    Spinner edema;
    ArrayAdapter<CharSequence> adapter;

    Spinner rach;
    ArrayAdapter<CharSequence> adapter1;

    String edema1,rach1;
    String a,b,c;
    String other,other1;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health5);

        e1=(EditText)findViewById(R.id.editText23);
        e2=(EditText)findViewById(R.id.editText24);
        e3=(EditText)findViewById(R.id.editText25);

        edema=(Spinner)findViewById(R.id.Edema);
       rach=(Spinner)findViewById(R.id.Rachiticchanges);
        others=(EditText)findViewById(R.id.editText21);
        others1=(EditText)findViewById(R.id.editText22);
        next=(Button)findViewById(R.id.button8);
        back=(Button)findViewById(R.id.button18);

        db = FirebaseFirestore.getInstance();

        back.setVisibility(View.INVISIBLE);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        adapter= ArrayAdapter.createFromResource(Health5.this,R.array.Edema,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edema.setAdapter(adapter);

        edema.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edema1=parent.getItemAtPosition(position).toString();

                if(edema1=="others")
                {
                    others.setVisibility(View.VISIBLE);
                    other=others.getText().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter1= ArrayAdapter.createFromResource(Health5.this,R.array.Rachiticchanges,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rach.setAdapter(adapter1);

        rach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rach1=parent.getItemAtPosition(position).toString();

                if(rach1=="others")
                {
                    others1.setVisibility(View.VISIBLE);
                    other1=others1.getText().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                other=others.getText().toString();
                other1=others1.getText().toString();

                a=e1.getText().toString();
                b=e2.getText().toString();
                c=e3.getText().toString();
                UpdateData(a,b,c);
                Intent i=new Intent(Health5.this,Health6.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Health5.this,Health4.class);
                startActivity(i);
            }
        });

    }
    private void UpdateData(String a,String b,String c ) {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Health").document("Choices");
        contact.update("Edema:", edema1);
        contact.update("Rachitic changes:", rach1);
        contact.update("CVS:", a);
        contact.update("RS:", b);
        contact.update("Abd:", c)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        if(other!=null && edema1=="others")
        {
            contact.update("Edema:", other);
        }
        if(other1!=null && rach1=="others")
        {
            contact.update("Rachitic changes:", other1);
        }

    }

//    @Override
//    public void onBackPressed()
//    {
//        Toast.makeText(getApplicationContext(),"Invalid action",Toast.LENGTH_SHORT).show();
//
//    }
}
