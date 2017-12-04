package heeyoung.tikkle2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class supporter_home_activity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private TextView mUserNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter_home_activity);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Supporters");
        mUserNameView = (TextView) findViewById(R.id.user_name_view);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.getValue().toString();

                mUserNameView.setText("User : " + name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }




}


/* tabhost관련
전자책 421
*/