package heeyoung.tikkle2;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static heeyoung.tikkle2.R.id.button14;

public class signupactivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button signupbutton;
    private EditText mSignup_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        signupbutton = (Button) findViewById(R.id.button14);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mSignup_name = (EditText) findViewById(R.id.signup_name);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1 - Create child in root object
                //2 - Assign some value to the child object

                String name = mSignup_name.getText().toString().trim();

                mDatabase.child("Name").push().setValue(name);
            }
        });
    }

    public static final int REQUEST_CODE_SETTING = 104;

    public void buttonsignupclicked(View v) {
        Intent intent = new Intent(getApplicationContext(), setting_activity.class);
        startActivityForResult(intent, REQUEST_CODE_SETTING);
    }
}



/*
  public class MenuActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signupactivity);

            Button button = (Button) findViewById(R.id.button13);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });
        }
    }
*/