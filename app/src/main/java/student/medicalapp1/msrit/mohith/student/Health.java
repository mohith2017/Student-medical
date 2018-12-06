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

public class Health extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;

    Button next;
    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    String a,b,c,d,e,f,g,h,i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        next=(Button)findViewById(R.id.button3);
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText3);
        e3=(EditText)findViewById(R.id.editText4);
        e4=(EditText)findViewById(R.id.editText5);
        e5=(EditText)findViewById(R.id.editText6);
        e6=(EditText)findViewById(R.id.editText7);
        e7=(EditText)findViewById(R.id.editText8);
        e8=(EditText)findViewById(R.id.editText9);
        e9=(EditText)findViewById(R.id.editText44);
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
                i=e9.getText().toString();
                UpdateData(a,b,c,d,e,f,g,h,i);

                Intent i=new Intent(Health.this,Health1.class);
                startActivity(i);

            }
        });
    }



    private void UpdateData(String a, String b, String c, String d, String e, String f, String g, String h,String i) {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Health").document("Details");
        contact.update("Date of health checkup:", a);
        contact.update("Class:", b);
        contact.update("Section:", c);
        contact.update("Age:", d);
        contact.update("Weight observed:", e);
        contact.update("Weight expected:", f);
        contact.update("Height observed:", g);
        contact.update("Height expected:", h);
        contact.update("Name", i)



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
