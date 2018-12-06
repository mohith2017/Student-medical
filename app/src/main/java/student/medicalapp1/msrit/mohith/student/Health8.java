package student.medicalapp1.msrit.mohith.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Health8 extends AppCompatActivity {
    EditText e1,e2,e3;
    Button next,back;
    String a,b,c;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health8);

        e1=(EditText)findViewById(R.id.editText34);
        e2=(EditText)findViewById(R.id.editText35);
        e3=(EditText)findViewById(R.id.editText36);

        next=(Button)findViewById(R.id.button11);
        back=(Button)findViewById(R.id.button21);

        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        back.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=e1.getText().toString();
                b=e2.getText().toString();
                c=e3.getText().toString();
                UpdateData(a,b,c);
                Intent i=new Intent(Health8.this,Health9.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Health8.this,Health7.class);
                startActivity(i);
            }
        });
    }

    private void UpdateData(String a, String b, String c) {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Health").document("Choices");
        contact.update("Dental Complaint:", a);
        contact.update("Dental Examination:", b);
        contact.update("Dental Diagnosis:", c)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

//    @Override
//    public void onBackPressed()
//    {
//        Toast.makeText(getApplicationContext(),"Invalid action",Toast.LENGTH_SHORT).show();
//
//    }
}
