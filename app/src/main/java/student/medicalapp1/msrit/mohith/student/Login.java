package student.medicalapp1.msrit.mohith.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import student.medicalapp1.msrit.mohith.student.util.InternetConnection;

public class Login extends AppCompatActivity {
    Button login;
    EditText email,name;

    Spinner village;
    ArrayAdapter<CharSequence> adapter;

    String village1,email1,name1;

    SharedPreferences.Editor editor;
    SharedPreferences pref;

    FirebaseFirestore db;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        village=(Spinner)findViewById(R.id.village);
        login=(Button)findViewById(R.id.button13);
        email=(EditText)findViewById(R.id.editText42);
        name=(EditText)findViewById(R.id.editText43);
        db = FirebaseFirestore.getInstance();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        adapter=ArrayAdapter.createFromResource(this,R.array.village,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        village.setAdapter(adapter);

        village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              village1=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name1=name.getText().toString();
                email1=email.getText().toString();
//
//                if ()
//                {
//
//                }


                if(name1.length()==0 || email1.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "All fields should be filled!",
                            Toast.LENGTH_SHORT).show();
                }

                else if(!InternetConnection.checkConnection(Login.this))
                {
                    Toast.makeText(getApplicationContext(), "Oops!No internet connection!",
                            Toast.LENGTH_SHORT).show();
                }

                else {
                    editor.putString("name", email1);
                    editor.commit();

                    addLogindata(email1, name1, village1);
                    //addEnvironmentdata();
                    //addEnvironment1data();
                    addhealthdata();
                    addhealth1data();

                    Intent i = new Intent(Login.this, Health.class);
                    startActivity(i);
                }
            }
        });

    }

    private void addhealth1data() {
        String x = pref.getString("name", "Default");
        String dbname = x;

        Map<String, Object> newDetails = new HashMap<>();
        newDetails.put("Pallor:", "yes");
        newDetails.put("Hair:", "yes");
        newDetails.put("Face:", "yes");
        newDetails.put("Eyes conjuctiva:", "yes");
        newDetails.put("Eyes cornea:", "yes");
        newDetails.put("Lips:", "yes");
        newDetails.put("Tongue:", "yes");
        newDetails.put("Teeth:", "yes");
        newDetails.put("Gums:", "yes");
        newDetails.put("Thyroid gland:", "yes");
        newDetails.put("Skin:", "yes");
        newDetails.put("Nails:", "yes");
        newDetails.put("Edema:", "yes");
        newDetails.put("Rachitic changes:", "yes");
        newDetails.put("CVS:", "yes");
        newDetails.put("RS:", "yes");
        newDetails.put("Abd:", "yes");
        newDetails.put("Ear:", "yes");
        newDetails.put("Nose:", "yes");
        newDetails.put("Throat:", "yes");
        newDetails.put("Skin Infection or Injuries:", "yes");
        newDetails.put("Right eye:", "yes");
        newDetails.put("Left eye:", "yes");
        newDetails.put("Any other problems:", "yes");
        newDetails.put("Diagnosis:", "yes");
        newDetails.put("Dental Complaint:", "yes");
        newDetails.put("Dental Examination:", "yes");
        newDetails.put("Dental Diagnosis:", "yes");
        newDetails.put("Final Diagnosis 1:", "yes");
        newDetails.put("Final Diagnosis 2:", "yes");
        newDetails.put("Final Diagnosis 3:", "yes");
        newDetails.put("Final Advice:", "yes");
        newDetails.put("Final Referred to dept:", "yes");



        db.collection("Student").document(dbname).collection("Health").document("Choices").set(newDetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Health1 added",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }

    private void addEnvironment1data() {

        String x = pref.getString("name", "Default");
        String dbname = x;

        Map<String, Object> newDetails = new HashMap<>();
        newDetails.put("Location:", "a");
        newDetails.put("Site:", "a");
        newDetails.put("Structure:", "a");
        newDetails.put("Classrooms:", "a");
        newDetails.put("Doors and windows:", "a");
        newDetails.put("Colour:", "a");
        newDetails.put("Sufficient natural light?:", "a");
        newDetails.put("Water supply:", "a");
        newDetails.put("Eating facility:", "a");
        newDetails.put("Lavatory Sanitary:", "a");
        newDetails.put("Contents of first aid kit:", "a");

        db.collection("Student").document(dbname).collection("Environment").document("Choices").set(newDetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Envi2 added",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

    }

    private void addEnvironmentdata() {
        String x = pref.getString("name", "Default");
        String dbname = x;

        Map<String, Object> newOptions = new HashMap<>();
        newOptions.put("School name:", "a");
        newOptions.put("Address:", "b");
        newOptions.put("Urban or rural", "discipline1");
        newOptions.put("Headmaster name:", "c");
        newOptions.put("Headmaster contact:", "d");
        newOptions.put("Landline:", "e");
        newOptions.put("School Email:", "f");
        newOptions.put("School Inaugrated on:", "f");
        newOptions.put("Number of teaching staff:", "f");
        newOptions.put("Number of non-teaching staff:", "f");
        newOptions.put("Number of male students:", "f");
        newOptions.put("Number of female students:", "f");
        newOptions.put("Total Number of students:", "f");
        newOptions.put("Classes from:", "f");
        newOptions.put("Classes to:", "f");

        db.collection("Student").document(dbname).collection("Environment").document("Details").set(newOptions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Login.this, "Envi1 added",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

    }

    private void addLogindata(String email1, String name1,String v) {



        String x = pref.getString("name", "Default");
        String dbname = x;

        Map<String, Object> newDetails = new HashMap<>();
        newDetails.put("Email:", email1);
        newDetails.put("Name:", name1);
        newDetails.put("Village", v);

        db.collection("Student").document(dbname).set(newDetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Details added",
                        Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }

    private void addhealthdata() {

        String x = pref.getString("name", "Default");
        String dbname = x;

        Map<String, Object> newDetails = new HashMap<>();
        newDetails.put("Date of health checkup:", "b");
        newDetails.put("Class:", "b");
        newDetails.put("Section:", "b");
        newDetails.put("Age:", "b");
        newDetails.put("Weight observed:", "b");
        newDetails.put("Weight expected:", "b");
        newDetails.put("Height observed:", "b");
        newDetails.put("Height expected:", "b");
        newDetails.put("Name", "b");

        db.collection("Student").document(dbname).collection("Health").document("Details").set(newDetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Health added",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }
}
