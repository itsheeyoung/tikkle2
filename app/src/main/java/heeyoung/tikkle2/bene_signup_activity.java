package heeyoung.tikkle2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    String uid;
    static final int REQUEST_CODE_BENEHOME = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bene_signup_activity);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            uid = user.getUid();

        }


        bene_register = (Button) findViewById(R.id.button_bene);
        mDatabase = FirebaseDatabase.getInstance().getReference("Beneficiaries").child(uid);


        mSignup_name_bene = (EditText) findViewById(R.id.bene_name);
        mSignup_phonenum_bene = (EditText) findViewById(R.id.bene_phonenum);
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
                String address_bene = mSignup_address_bene.getText().toString().trim();
                String size_bene = mSignup_size_bene.getText().toString().trim();
                String intro_bene = mSignup_intro_bene.getText().toString().trim();

                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Bene Name", name_bene);
                dataMap.put("Bene Phone number", phonenum_bene);
                dataMap.put("Bene Address", address_bene);
                dataMap.put("Bene Size", size_bene);
                dataMap.put("Bene Intro", intro_bene);


                mDatabase.setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(bene_signup_activity.this, "정보를 저장중입니다.", Toast.LENGTH_LONG).show();
                            Toast.makeText(bene_signup_activity.this, "저장이 완료되었습니다.", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), supporter_home_activity.class);
                            startActivityForResult(intent, REQUEST_CODE_BENEHOME);

                        } else {
                            Toast.makeText(bene_signup_activity.this, "다시 시도해주세요", Toast.LENGTH_LONG).show();
                        }

                    }

                });
            }
        });
    }
}
