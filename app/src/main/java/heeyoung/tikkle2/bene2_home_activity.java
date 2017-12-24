package heeyoung.tikkle2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class bene2_home_activity extends AppCompatActivity {


    private DatabaseReference mDatabase;

    private DatabaseReference database;
    FirebaseDatabase mdatabase;
    DatabaseReference myRef;
    String uid;

    private static final String TAG = "bene2_home_activity";


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_계좌:
                    transaction.replace(R.id.content, new bene_account()).commit();
                    return true;
                case R.id.navigation_기관:

                    return true;
                case R.id.navigation_설정:
                    transaction.replace(R.id.content, new supporter_setting()).commit();
                    return true;
            }
            return true;
//            여기 왜 return false?
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporter2_home_activity);

//        final TextView accu_money2 = (TextView)findViewById(R.id.accu_money);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new supporter_account()).commit();

        database = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            uid = user.getUid();
        }


        mdatabase = FirebaseDatabase.getInstance();
        myRef = mdatabase.getReference("Supporters").child(uid).child("point");;

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Double point = dataSnapshot.getValue(Double.class);

//                Toast.makeText(supporter2_home_activity.this,String.valueOf(point).toString(), Toast.LENGTH_SHORT).show();
//                //데이터를 화면에 출력해 준다.
//                Toast.makeText(supporter2_home_activity.this,String.valueOf(accu_money2).toString(), Toast.LENGTH_SHORT).show();
  //              accu_money2.setText("누적 적립금 : ");

//                Log.d(TAG, "Value is: " + point);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

}