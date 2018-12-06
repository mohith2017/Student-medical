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

public class Health3 extends AppCompatActivity {
    EditText others,others1,others2;
    Button next,back;

    Spinner tongue;
    ArrayAdapter<CharSequence> adapter;

    Spinner teeth;
    ArrayAdapter<CharSequence> adapter1;

    Spinner gums;
    ArrayAdapter<CharSequence> adapter2;

    String tongue1,teeth1,gums1;
    String other,other1,other2;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health3);

        tongue=(Spinner)findViewById(R.id.Tongue);
        teeth=(Spinner)findViewById(R.id.Teeth);
        gums=(Spinner)findViewById(R.id.Gums);
        others=(EditText)findViewById(R.id.editText15);
        others1=(EditText)findViewById(R.id.editText16);
        others2=(EditText)findViewById(R.id.editText17);
        next=(Button)findViewById(R.id.button6);
        back=(Button)findViewById(R.id.button16);

        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        back.setVisibility(View.INVISIBLE);


        adapter= ArrayAdapter.createFromResource(Health3.this,R.array.Tongue,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tongue.setAdapter(adapter);

        tongue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tongue1=parent.getItemAtPosition(position).toString();

                if(tongue1=="others")
                {
                    others.setVisibility(View.VISIBLE);
                    other=others.getText().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter1= ArrayAdapter.createFromResource(Health3.this,R.array.Teeth,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teeth.setAdapter(adapter1);

        teeth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teeth1=parent.getItemAtPosition(position).toString();

                if(teeth1=="others")
                {
                    others1.setVisibility(View.VISIBLE);
                    other1=others1.getText().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter2= ArrayAdapter.createFromResource(Health3.this,R.array.Gums,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gums.setAdapter(adapter2);

        gums.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gums1=parent.getItemAtPosition(position).toString();

                if(gums1=="others")
                {
                    others2.setVisibility(View.VISIBLE);
                    other2=others2.getText().toString();
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
                other2=others2.getText().toString();
                UpdateData();
                Intent i=new Intent(Health3.this,Health4.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Health3.this,Health2.class);
                startActivity(i);
            }
        });


    }
    private void UpdateData() {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Health").document("Choices");
        contact.update("Tongue:", tongue1);
        contact.update("Teeth:", teeth1);
        contact.update("Gums:", gums1)


                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        if(other!=null && tongue1=="others")
        {
            contact.update("Tongue:", other);
        }
        if(other1!=null && teeth1=="others")
        {
            contact.update("Teeth:", other1);
        }
        if(other2!=null && gums1=="others")
        {
            contact.update("Gums:", other2);
        }

    }

//    @Override
//    public void onBackPressed()
//    {
//        Toast.makeText(getApplicationContext(),"Invalid action",Toast.LENGTH_SHORT).show();
//
//    }
}
