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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Health1 extends AppCompatActivity {

    EditText others,others1;
    Button next,back;

    Spinner hair;
    ArrayAdapter<CharSequence> adapter;

    Spinner face;
    ArrayAdapter<CharSequence> adapter1;

    RadioGroup r;

    int id1;

    String yes,hair1,face1;
    String other1,other2;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health1);

        r=(RadioGroup)findViewById(R.id.radioyes);
        hair=(Spinner)findViewById(R.id.hair);
        face=(Spinner)findViewById(R.id.face);
        next=(Button)findViewById(R.id.button4);
        back=(Button)findViewById(R.id.button14);
        others=(EditText)findViewById(R.id.editText10);
        others1=(EditText)findViewById(R.id.editText11);
        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();


        back.setVisibility(View.INVISIBLE);


        adapter= ArrayAdapter.createFromResource(Health1.this,R.array.hair,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hair.setAdapter(adapter);

        hair.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hair1=parent.getItemAtPosition(position).toString();

//                if(hair1=="others")
//                {
//                    others.setVisibility(View.VISIBLE);
//                    other1=others.getText().toString();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter1= ArrayAdapter.createFromResource(Health1.this,R.array.face,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        face.setAdapter(adapter1);

        face.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                face1=parent.getItemAtPosition(position).toString();

//                if(face1=="others")
//                {
//                    others1.setVisibility(View.VISIBLE);
//                    other2=others1.getText().toString();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });








        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id1=r.getCheckedRadioButtonId();

                switch (id1)
                {
                    case R.id.yes:yes="yes";break;
                    case R.id.no:yes="no";break;
                }

                other1=others.getText().toString();
                other2=others1.getText().toString();
                UpdateData(yes,other1,other2);

                Intent i=new Intent(Health1.this,Health2.class);
                startActivity(i);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Health1.this,Health.class);
                startActivity(i);
            }
        });
    }

    private void UpdateData(String yes, String other1, String y) {

        String x = pref.getString("name", "Default");
        String dbname = x;



        DocumentReference contact = db.collection("Student").document(dbname).collection("Health").document("Choices");
        contact.update("Pallor:", yes);
        contact.update("Hair:", hair1);
        contact.update("Face:", face1)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        if(hair1=="others")
            contact.update("Hair:", other1);

        if(face1=="others")
        contact.update("Face:", y);


    }
//    @Override
//    public void onBackPressed()
//    {
//        Toast.makeText(getApplicationContext(),"Invalid action",Toast.LENGTH_SHORT).show();
//
//    }
}
