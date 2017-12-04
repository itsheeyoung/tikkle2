package heeyoung.tikkle2;

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

public class bene_signup_activity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button bene_register;
    private EditText mSignup_name_bene;
    private EditText mSignup_phonenum_bene;
    private EditText mSignup_password_bene;
    private EditText mSignup_passcheck_bene;
    private EditText mSignup_num_bene;
    private EditText mSignup_address_bene;
    private EditText mSignup_size_bene;
    private EditText mSignup_intro_bene;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bene_signup_activity);


        bene_register = (Button) findViewById(R.id.button_bene);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Beneficiaries");

        mSignup_name_bene = (EditText) findViewById(R.id.bene_name);
        mSignup_phonenum_bene = (EditText) findViewById(R.id.bene_phonenum);
        mSignup_password_bene = (EditText) findViewById(R.id.bene_password);
        mSignup_passcheck_bene = (EditText) findViewById(R.id.bene_passcheck);
        mSignup_num_bene = (EditText) findViewById(R.id.bene_num);
        mSignup_address_bene = (EditText) findViewById(R.id.bene_address);
        mSignup_size_bene = (EditText) findViewById(R.id.bene_size);
        mSignup_intro_bene = (EditText) findViewById(R.id.bene_intro);

        bene_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1 - Create child in root object
                //2 - Assign some value to the child object


                String name_bene = mSignup_name_bene.getText().toString().trim();
                String phonenum_bene = mSignup_phonenum_bene.getText().toString().trim();
                String password_bene = mSignup_password_bene.getText().toString().trim();
                String passcheck_bene = mSignup_passcheck_bene.getText().toString().trim();
                String num_bene = mSignup_num_bene.getText().toString().trim();
                String address_bene = mSignup_address_bene.getText().toString().trim();
                String size_bene = mSignup_size_bene.getText().toString().trim();
                String intro_bene = mSignup_intro_bene.getText().toString().trim();

                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Bene Name", name_bene);
                dataMap.put("Bene Phone number", phonenum_bene);
                dataMap.put("Bene Password", password_bene);
                dataMap.put("Bene Password Check", passcheck_bene);
                dataMap.put("Bene Number", num_bene);
                dataMap.put("Bene Address", address_bene);
                dataMap.put("Bene Size", size_bene);
                dataMap.put("Bene Intro", intro_bene);


                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(bene_signup_activity.this, "Stored..", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(bene_signup_activity.this, "Stored..", Toast.LENGTH_LONG).show();
                        }

                    }

                });
            }
        });
    }
}
