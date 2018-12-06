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

public class Health6 extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button next,back;
    SharedPreferences.Editor editor;
    SharedPreferences pref;

    String a,b,c,d;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health6);


        e1=(EditText)findViewById(R.id.editText26);
        e2=(EditText)findViewById(R.id.editText27);
        e3=(EditText)findViewById(R.id.editText28);
        e4=(EditText)findViewById(R.id.editText29);
        next=(Button)findViewById(R.id.button9);
        back=(Button)findViewById(R.id.button19);

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
                d=e4.getText().toString();

                UpdateData(a,b,c,d);
                Intent i=new Intent(Health6.this,Health7.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Health6.this,Health5.class);
                startActivity(i);
            }
        });
    }
    private void UpdateData(String a, String b, String c, String d) {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Health").document("Choices");
        contact.update("Ear:", a);
        contact.update("Nose:", b);
        contact.update("Throat:", c);
        contact.update("Skin Infection or Injuries:", d)

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
