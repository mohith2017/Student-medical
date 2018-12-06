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

public class Health2 extends AppCompatActivity {
    EditText others,others1,others2;
    Button next,back;

    Spinner eye1;
    ArrayAdapter<CharSequence> adapter;

    Spinner eye2;
    ArrayAdapter<CharSequence> adapter1;

    Spinner lips;
    ArrayAdapter<CharSequence> adapter2;

    String eye11,eye21,lips1;
    String other1,other2,other3;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health2);

        eye1=(Spinner)findViewById(R.id.eye1);
        eye2=(Spinner)findViewById(R.id.eye2);
        lips=(Spinner)findViewById(R.id.lips);
        others=(EditText)findViewById(R.id.editText12);
        others2=(EditText)findViewById(R.id.editText14);
        others1=(EditText)findViewById(R.id.editText13);
        next=(Button)findViewById(R.id.button5);
        back=(Button)findViewById(R.id.button15);

        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        back.setVisibility(View.INVISIBLE);

        adapter= ArrayAdapter.createFromResource(Health2.this,R.array.eye1,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eye1.setAdapter(adapter);

        eye1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eye11=parent.getItemAtPosition(position).toString();

                if(eye11=="others")
                {
                    others.setVisibility(View.VISIBLE);
                    other1=others.getText().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter1= ArrayAdapter.createFromResource(Health2.this,R.array.eye2,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eye2.setAdapter(adapter1);

        eye2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eye21=parent.getItemAtPosition(position).toString();

                if(eye21=="others")
                {
                    others1.setVisibility(View.VISIBLE);
                    other2=others1.getText().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter2= ArrayAdapter.createFromResource(Health2.this,R.array.Lips,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lips.setAdapter(adapter2);

        lips.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lips1=parent.getItemAtPosition(position).toString();

                if(lips1=="others")
                {
                    others2.setVisibility(View.VISIBLE);
                    other3=others2.getText().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other1=others.getText().toString();
                other2=others1.getText().toString();
                other3=others2.getText().toString();
                UpdateData();

                Intent i=new Intent(Health2.this,Health3.class);
                startActivity(i);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Health2.this,Health1.class);
                startActivity(i);
            }
        });


    }
    private void UpdateData() {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Health").document("Choices");
        contact.update("Eyes conjuctiva:", eye11);
        contact.update("Eyes cornea:", eye21);
        contact.update("Lips:", lips1)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        if(other1!=null && eye11=="others")
        {
            contact.update("Eyes conjuctiva:",other1);
        }
        if(other2!=null && eye21=="others")
        {
            contact.update("Eyes cornea:", other2);
        }
        if(other3!=null && lips1=="others")
        {
            contact.update("Lips:", other3);

        }

    }
//    @Override
//    public void onBackPressed()
//    {
//        Toast.makeText(getApplicationContext(),"Invalid action",Toast.LENGTH_SHORT).show();
//
//    }
}
