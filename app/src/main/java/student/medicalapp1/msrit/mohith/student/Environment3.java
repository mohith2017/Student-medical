package student.medicalapp1.msrit.mohith.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Environment3 extends AppCompatActivity {
    Spinner location;
    ArrayAdapter<CharSequence> adapter;

    Spinner site;
    ArrayAdapter<CharSequence> adapter1;

    Spinner structure;
    ArrayAdapter<CharSequence> adapter2;

    Spinner classrooms;
    ArrayAdapter<CharSequence> adapter3;

    Spinner doors;
    ArrayAdapter<CharSequence> adapter4;

    Spinner colour;
    ArrayAdapter<CharSequence> adapter5;


    String location1,site1,structure1,classrooms1,doors1,colour1;

    Button next;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment3);

        location=(Spinner)findViewById(R.id.location);
        colour=(Spinner)findViewById(R.id.colour);
        doors=(Spinner)findViewById(R.id.Doorsandwindows);
        structure=(Spinner)findViewById(R.id.Structure);
        classrooms=(Spinner)findViewById(R.id.ClassRooms);
        site=(Spinner)findViewById(R.id.Site);
        next=(Button)findViewById(R.id.next);
        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        adapter= ArrayAdapter.createFromResource(Environment3.this,R.array.location1,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(adapter);

       location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               location1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter1= ArrayAdapter.createFromResource(Environment3.this,R.array.Site,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        site.setAdapter(adapter1);

        site.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                site1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter2= ArrayAdapter.createFromResource(Environment3.this,R.array.Structure,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        structure.setAdapter(adapter2);

        structure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                structure1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter3= ArrayAdapter.createFromResource(Environment3.this,R.array.Classrooms,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classrooms.setAdapter(adapter3);

        classrooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classrooms1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter4= ArrayAdapter.createFromResource(Environment3.this,R.array.Doors,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doors.setAdapter(adapter4);

        doors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doors1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter5= ArrayAdapter.createFromResource(Environment3.this,R.array.colour,android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colour.setAdapter(adapter5);

        colour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               colour1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               UpdateData();

               Intent i=new Intent(Environment3.this,Environment4.class);
               startActivity(i);

           }
       });
    }

    private void UpdateData() {

        String x = pref.getString("name", "Default");
        String dbname = x;

        DocumentReference contact = db.collection("Student").document(dbname).collection("Environment").document("Choices");
        contact.update("Location:", location1);
        contact.update("Site:", site1);
        contact.update("Structure:", structure1);
        contact.update("Classrooms:", classrooms1);
        contact.update("Doors and windows:", doors1);
        contact.update("Colour:", colour1)


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
