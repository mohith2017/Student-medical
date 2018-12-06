package student.medicalapp1.msrit.mohith.student;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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

public class Health9 extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    String a,b,c,d,e;
    Button finish,back;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health9);

        e1=(EditText)findViewById(R.id.editText37);
        e2=(EditText)findViewById(R.id.editText38);
        e3=(EditText)findViewById(R.id.editText39);
        e4=(EditText)findViewById(R.id.editText40);
        e5=(EditText)findViewById(R.id.editText41);

        finish=(Button)findViewById(R.id.button12);
        back=(Button)findViewById(R.id.button22);
        back.setVisibility(View.INVISIBLE);

        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=e1.getText().toString();
                b=e2.getText().toString();
                c=e3.getText().toString();
                d=e4.getText().toString();
                e=e5.getText().toString();
                UpdateData(a,b,c,d,e);

                showAlert();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Health9.this,Health8.class);
                startActivity(i);
            }
        });
    }

    private void UpdateData(String a, String b, String c, String d, String e) {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Health").document("Choices");
        contact.update("Final Diagnosis 1:", a);
        contact.update("Final Diagnosis 2:", b);
        contact.update("Final Diagnosis 3:", c);
        contact.update("Final Advice:", d);
        contact.update("Final Referred to dept:", e)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void showAlert()
    {
        builder = new AlertDialog.Builder(Health9.this);
       builder.setTitle("Confirm!").setMessage("Submit responses?")
               .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Intent i=new Intent(Health9.this,Thankyou.class);
                       startActivity(i);
                   }
               });
       builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

           }
       });
       builder.show();
    }
//    @Override
//    public void onBackPressed()
//    {
//        Toast.makeText(getApplicationContext(),"Invalid action",Toast.LENGTH_SHORT).show();
//
//    }
}
