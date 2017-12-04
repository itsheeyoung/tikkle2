package heeyoung.tikkle2;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static heeyoung.tikkle2.R.id.button14;

public class signupactivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button signupbutton;
    private EditText mSignup_name;
    private EditText mSignup_phonenum;
    private EditText mSignup_password;
    private EditText mSignup_passcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);


        signupbutton = (Button) findViewById(R.id.button14);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Supporters");

        mSignup_name = (EditText) findViewById(R.id.signup_name);
        mSignup_phonenum=(EditText) findViewById(R.id.signup_phonenum);
        mSignup_password=(EditText) findViewById(R.id.signup_password);
        mSignup_passcheck=(EditText) findViewById(R.id.signup_passcheck);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1 - Create child in root object
                //2 - Assign some value to the child object


                String name = mSignup_name.getText().toString().trim();
                String phonenum = mSignup_phonenum.getText().toString().trim();
                String password = mSignup_password.getText().toString().trim();
                String passcheck = mSignup_passcheck.getText().toString().trim();

                HashMap<String,String> dataMap = new HashMap<String, String>();
                dataMap.put("Name",name);
                dataMap.put("Phone number", phonenum);
                dataMap.put("Password",password);
                dataMap.put("Password Check",passcheck);



                 mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {

                             if(task.isSuccessful()){

                                 Toast.makeText(signupactivity.this, "Stored..", Toast.LENGTH_LONG).show();
                             }
                             else {
                                 Toast.makeText(signupactivity.this, "Stored..", Toast.LENGTH_LONG).show();
                             }

                         }

                    });

                Intent intent = new Intent(getApplicationContext(), setting_activity.class);
                startActivityForResult(intent, REQUEST_CODE_SETTING);

                };

        });
    }

    public static final int REQUEST_CODE_SETTING = 104;

    public void buttonsignupclicked(View v) {

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