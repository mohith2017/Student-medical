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

public class Environment2 extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8;

    Button next;

    String a,b,c,d,e,f,g,h;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment2);

        next=(Button)findViewById(R.id.buttone1);
        e1=(EditText)findViewById(R.id.editTexte1);
        e2=(EditText)findViewById(R.id.editText1e1);
        e3=(EditText)findViewById(R.id.editText2e1);
        e4=(EditText)findViewById(R.id.editText3e1);
        e5=(EditText)findViewById(R.id.editText4e1);
        e6=(EditText)findViewById(R.id.editText5e1);
        e7=(EditText)findViewById(R.id.editText6e1);
        e8=(EditText)findViewById(R.id.editText7e1);
        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=e1.getText().toString();
                b=e2.getText().toString();
                c=e3.getText().toString();
                d=e4.getText().toString();
                e=e5.getText().toString();
                f=e6.getText().toString();
                g=e7.getText().toString();
                h=e8.getText().toString();

                UpdateData(a,b,c,d,e,f,g,h);
                Intent i=new Intent(Environment2.this,Environment3.class);
                startActivity(i);
            }
        });

    }

    private void UpdateData(String a, String b, String c, String d, String e, String f, String g, String h) {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Environment").document("Details");
        contact.update("School Inaugrated on:", a);
        contact.update("Number of teaching staff:", b);
        contact.update("Number of non-teaching staff:", c);
        contact.update("Number of male students:", d);
        contact.update("Headmaster contact:", d);
        contact.update("Number of female students:", e);
        contact.update("Total Number of students:", f);
        contact.update("Classes from:", g);
        contact.update("Classes to:", h)

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
