package student.medicalapp1.msrit.mohith.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Environment4 extends AppCompatActivity {
    EditText t;

    Spinner water;
    ArrayAdapter<CharSequence> adapter;

    Spinner eating;
    ArrayAdapter<CharSequence> adapter1;

    Spinner lavatory;
    ArrayAdapter<CharSequence> adapter2;

    Button proceed;

    RadioGroup r;

    int id1;


    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    String yes,water1,eating1,lavatory1,t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment4);

        r=(RadioGroup)findViewById(R.id.radio1);
        t=(EditText)findViewById(R.id.editText);
        water=(Spinner)findViewById(R.id.WaterSupply);
        eating=(Spinner)findViewById(R.id.EatingFacility);
        lavatory=(Spinner)findViewById(R.id.LavatorySanitary);
        proceed=(Button)findViewById(R.id.button2);
        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        adapter= ArrayAdapter.createFromResource(Environment4.this,R.array.water,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        water.setAdapter(adapter);

        water.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                water1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter1= ArrayAdapter.createFromResource(Environment4.this,R.array.eating,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eating.setAdapter(adapter1);

        eating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eating1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter2= ArrayAdapter.createFromResource(Environment4.this,R.array.lavatory,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lavatory.setAdapter(adapter2);

        lavatory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lavatory1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id1=r.getCheckedRadioButtonId();

                switch (id1)
                {
                    case R.id.radioButton3:yes="yes";break;
                    case R.id.radioButton4:yes="no";break;
                }

                t1=t.getText().toString();
                UpdateData(t1,yes);
                Intent i=new Intent(Environment4.this,Health.class);
                startActivity(i);
            }
        });
    }
    private void UpdateData(String t1,String y) {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Environment").document("Choices");
        contact.update("Sufficient natural light?:", y);
        contact.update("Water supply:", water1);
        contact.update("Eating facility:", eating1);
        contact.update("Lavatory Sanitary:", lavatory1);
        contact.update("Contents of first aid kit:", t1)


                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public void onBackPressed()
    {
        Toast.makeText(getApplicationContext(),"Invalid action",Toast.LENGTH_SHORT).show();

    }
}
