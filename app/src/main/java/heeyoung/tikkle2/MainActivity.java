package heeyoung.tikkle2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
public static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToggleButton tb2 =
                (ToggleButton) this.findViewById(R.id.usertoggleButton);
        final ImageView Image1 =
                (ImageView) this.findViewById(R.id.imageView);

        tb2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(tb2.isChecked()){
                            Image1.setImageDrawable(
                                    getResources().
                                            getDrawable(R.drawable.acorn)
                    );
                }else{
                            Image1.setImageDrawable(
                                    getResources().
                                            getDrawable(R.drawable.squirrel)
                    );
                } // end if
            } // end onClick()

            public void buttonsignupclicked(View v) {
                Intent intent = new Intent(getApplicationContext(), signupactivity.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });


    }




    }

//Image1.setBackgroundDrawable(
//        getResources().
//        getDrawable(R.drawable.acorn)

