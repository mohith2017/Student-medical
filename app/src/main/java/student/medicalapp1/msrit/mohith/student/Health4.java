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

public class Health4 extends AppCompatActivity {

    EditText others,others1,others2;
    Button next,back;

    Spinner thyroid;
    ArrayAdapter<CharSequence> adapter;

    Spinner skin;
    ArrayAdapter<CharSequence> adapter1;

    Spinner nails;
    ArrayAdapter<CharSequence> adapter2;

    String thyroid1,skin1,nails1;
    String other,other1,other2;
    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health4);

        thyroid=(Spinner)findViewById(R.id.Thyroidgland);
        nails=(Spinner)findViewById(R.id.Nails);
        skin=(Spinner)findViewById(R.id.Skin);
        others=(EditText)findViewById(R.id.editText18);
        others1=(EditText)findViewById(R.id.editText19);
        others2=(EditText)findViewById(R.id.editText20);
        next=(Button)findViewById(R.id.button7);
        back=(Button)findViewById(R.id.button17);

        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        back.setVisibility(View.INVISIBLE);

        adapter= ArrayAdapter.createFromResource(Health4.this,R.array.Thyroidgland,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thyroid.setAdapter(adapter);

        thyroid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thyroid1=parent.getItemAtPosition(position).toString();

                if(thyroid1=="others")
                {
                    others.setVisibility(View.VISIBLE);
                    other=others.getText().toString();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter1= ArrayAdapter.createFromResource(Health4.this,R.array.Skin,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skin.setAdapter(adapter1);

        skin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                skin1=parent.getItemAtPosition(position).toString();

                if(skin1=="others")
                {
                    others1.setVisibility(View.VISIBLE);
                    other1=others1.getText().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter2= ArrayAdapter.createFromResource(Health4.this,R.array.Nails,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nails.setAdapter(adapter2);

        nails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nails1=parent.getItemAtPosition(position).toString();

                if(nails1=="others")
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

                Intent i=new Intent(Health4.this,Health5.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Health4.this,Health3.class);
                startActivity(i);
            }
        });



    }
    private void UpdateData() {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Health").document("Choices");
        contact.update("Thyroid gland:", thyroid1);
        contact.update("Skin:", skin1);
        contact.update("Nails:", nails1)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Updated Successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        if(other!=null && thyroid1=="others")
        {
            contact.update("Thyroid gland:", other);
        }
        if(other1!=null && skin1=="others")
        {
            contact.update("Skin:", other1);
        }
        if(other2!=null && nails1=="others")
        {
            contact.update("Nails:", other2);
        }
    }
//    @Override
//    public void onBackPressed()
//    {
//        Toast.makeText(getApplicationContext(),"Invalid action",Toast.LENGTH_SHORT).show();
//
//    }
}
