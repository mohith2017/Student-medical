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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    Button proceed;

    Spinner discipline;
    ArrayAdapter<CharSequence> adapter;

    String discipline1,d1;
    String a,b,c,d,e,f;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1=(EditText)findViewById(R.id.editTexte);
        e2=(EditText)findViewById(R.id.editText1e);
        e3=(EditText)findViewById(R.id.editText2e);
        e4=(EditText)findViewById(R.id.editText3e);
        e5=(EditText)findViewById(R.id.editText4e);
        e6=(EditText)findViewById(R.id.editText5e);
        discipline=(Spinner)findViewById(R.id.spinnere);
        proceed=(Button)findViewById(R.id.buttone);
        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        adapter= ArrayAdapter.createFromResource(MainActivity.this,R.array.Urban,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        discipline.setAdapter(adapter);

        discipline.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                discipline1=parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=e1.getText().toString();
                b=e2.getText().toString();
                c=e3.getText().toString();
                d=e4.getText().toString();
                e=e5.getText().toString();
                f=e6.getText().toString();

                UpdateData(a,b,c,d,e,f);

                Intent i=new Intent(MainActivity.this,Environment2.class);
                startActivity(i);

            }
        });

    }

    private void UpdateData(String a, String b, String c, String d, String e, String f) {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Environment").document("Details");
        contact.update("School name:", a);
        contact.update("Address:", b);
       contact.update("Urban or rural", discipline1);
        contact.update("Headmaster name:", c);
        contact.update("Headmaster contact:", d);
        contact.update("Landline:", e);
        contact.update("School Email:", f)

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
