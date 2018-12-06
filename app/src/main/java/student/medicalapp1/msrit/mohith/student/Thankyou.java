package student.medicalapp1.msrit.mohith.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Thankyou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        Toast.makeText(Thankyou.this,"You can safely close the app now!",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed()
    {
        Toast.makeText(getApplicationContext(),"Invalid action! You have completed the survey",Toast.LENGTH_SHORT).show();

    }

}
