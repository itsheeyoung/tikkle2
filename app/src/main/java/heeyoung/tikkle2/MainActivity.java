package heeyoung.tikkle2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_SIGNIN = 101;
    public static final int REQUEST_CODE_LOGIN = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonsignupclicked(View v) {
        Intent intent = new Intent(getApplicationContext(), signupactivity.class);
        startActivityForResult(intent, REQUEST_CODE_SIGNIN);
    }

    public void buttonloginclicked(View v) {
        Intent intent = new Intent(getApplicationContext(), loginactivity.class);
        startActivityForResult(intent, REQUEST_CODE_LOGIN);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}

//Image1.setBackgroundDrawable(
//        getResources().
//        getDrawable(R.drawable.acorn)

//    final ToggleButton tb2 =
//            (ToggleButton) this.findViewById(R.id.usertoggleButton);
//    final ImageView Image1 =
//            (ImageView) this.findViewById(R.id.imageView);
//
//        tb2.setOnClickListener(new View.OnClickListener() {
//
//public void onClick(View v) {
//        if (tb2.isChecked()) {
//        Image1.setImageDrawable(
//        getResources().
//        getDrawable(R.drawable.acorn)
//        );
//        } else {
//        Image1.setImageDrawable(
//        getResources().
//        getDrawable(R.drawable.squirrel)
//        );
//        } // end if
//        } // end onClick()
//        });
