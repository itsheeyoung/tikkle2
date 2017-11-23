package heeyoung.tikkle2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signupactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);}

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