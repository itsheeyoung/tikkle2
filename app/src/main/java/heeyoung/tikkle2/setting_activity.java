package heeyoung.tikkle2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setting_activity extends AppCompatActivity {

public static final int REQUEST_CODE_SUPPORTERHOME = 110;

    FirebaseDatabase database;
    DatabaseReference myRef;
    double percent = 0;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_setting_activity);

        final ToggleButton toggleButton1 = (ToggleButton)findViewById(R.id.toggleButton1);
        final ToggleButton toggleButton3 = (ToggleButton)findViewById(R.id.toggleButton3);
        final ToggleButton toggleButton5 = (ToggleButton)findViewById(R.id.toggleButton5);
        final ToggleButton toggleButton10 = (ToggleButton)findViewById(R.id.toggleButton10);
        final Button mRegister = (Button)findViewById(R.id.button17);

toggleButton1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(toggleButton1.isChecked()){
            toggleButton1.setBackgroundColor(Color.GRAY);
            toggleButton3.setChecked(false);
            toggleButton5.setChecked(false);
            toggleButton10.setChecked(false);

            percent = 0.01;

            toggleButton3.setBackgroundColor(Color.WHITE);
            toggleButton5.setBackgroundColor(Color.WHITE);
            toggleButton10.setBackgroundColor(Color.WHITE);
        }
        else{
            toggleButton1.setBackgroundColor(Color.WHITE);

            percent = 0;

        }
    }
});

        toggleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleButton3.isChecked()){
                    toggleButton3.setBackgroundColor(Color.GRAY);
                    toggleButton1.setChecked(false);
                    toggleButton5.setChecked(false);
                    toggleButton10.setChecked(false);

                    percent = 0.03;

                    toggleButton1.setBackgroundColor(Color.WHITE);
                    toggleButton5.setBackgroundColor(Color.WHITE);
                    toggleButton10.setBackgroundColor(Color.WHITE);
                }
                else{
                    toggleButton3.setBackgroundColor(Color.WHITE);

                    percent = 0;

                }
            }
        });

        toggleButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleButton5.isChecked()){
                    toggleButton5.setBackgroundColor(Color.GRAY);
                    toggleButton3.setChecked(false);
                    toggleButton1.setChecked(false);
                    toggleButton10.setChecked(false);

                    percent = 0.05;

                    toggleButton3.setBackgroundColor(Color.WHITE);
                    toggleButton1.setBackgroundColor(Color.WHITE);
                    toggleButton10.setBackgroundColor(Color.WHITE);
                }
                else{
                    toggleButton5.setBackgroundColor(Color.WHITE);

                    percent = 0;
                }
            }
        });

        toggleButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleButton10.isChecked()){
                    toggleButton10.setBackgroundColor(Color.GRAY);
                    toggleButton3.setChecked(false);
                    toggleButton5.setChecked(false);
                    toggleButton1.setChecked(false);

                    percent = 0.1;

                    toggleButton3.setBackgroundColor(Color.WHITE);
                    toggleButton5.setBackgroundColor(Color.WHITE);
                    toggleButton1.setBackgroundColor(Color.WHITE);
                }
                else{
                    toggleButton10.setBackgroundColor(Color.WHITE);

                    percent = 0;
                }
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(percent == 0)
                    Toast.makeText(setting_activity.this,"퍼센트를 선택하여 주세요",Toast.LENGTH_SHORT).show();
                else {
                    database = FirebaseDatabase.getInstance();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        // Name, email address, and profile photo Url
                        uid = user.getUid();
                    }

                    myRef = database.getReference("Supporters").child(uid).child("percent");
                    myRef.setValue(percent);

                    Toast.makeText(setting_activity.this,"퍼센트가 " + (Math.round(percent*100)) + "%로 설정되었습니다",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), supporter_home_activity.class);
                    startActivityForResult(intent, REQUEST_CODE_SUPPORTERHOME);
                }
                }
        });

    }



}
