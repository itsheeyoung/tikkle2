package heeyoung.tikkle2;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import static android.R.attr.value;
import static heeyoung.tikkle2.R.id.text;

public class MainActivity extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener{
    public int REQUEST_CODE_SIGNIN = 101;
    public static final int REQUEST_CODE_LOGIN = 102;

    Button Select_supporter;
    Button Select_bene;
    Button mLogout;

    private static final int RC_SIGN_IN=10;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    static String sharedText;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase2;
    String uid;

    FirebaseDatabase percentDatabase;
//    DatabaseReference myRef;
    DatabaseReference myRef2;
    FirebaseDatabase database;
    DatabaseReference percentpath;
    static double accum;
    boolean check = true;

    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        mLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                FirebaseAuth.getInstance().signOut();
//            }
//        });
//

        final Intent intent = getIntent();
        sharedText = intent.getStringExtra("my_text");


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
        }

//        mDatabase = FirebaseDatabase.getInstance().getReference("Supporters").child(uid).child("won");
//        mDatabase.setValue(sharedText);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Button Googlesign = (Button) findViewById(R.id.button15);
        Googlesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);

                if (REQUEST_CODE_SIGNIN == 101) {
                    Toast.makeText(MainActivity.this, "가입되었습니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), supporter2_home_activity.class);
                    startActivityForResult(intent, REQUEST_CODE_SIGNIN);
                    mDatabase = FirebaseDatabase.getInstance().getReference("Supporters").child(uid).child("percent");
                    mDatabase.setValue(0);
                    mDatabase = FirebaseDatabase.getInstance().getReference("Supporters").child(uid).child("point");
                    mDatabase.setValue(0);
                }
                else {
                    Toast.makeText(MainActivity.this, "구글 로그인 되었습니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), bene_signup_activity.class);
                    startActivityForResult(intent, REQUEST_CODE_SIGNIN);
                }



            }
        });

        Button Googlesign2 = (Button) findViewById(R.id.button14);
        Googlesign2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });


        Select_supporter = (Button) findViewById(R.id.button2);
        Select_bene = (Button) findViewById(R.id.button);


        if (intent.hasExtra("my_text")){
            check = true;
//            myRef2 = FirebaseDatabase.getInstance().getReference();
            percentDatabase = FirebaseDatabase.getInstance();
            percentpath = percentDatabase.getReference("Supporters").child(uid);



                percentpath.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        Double percent = dataSnapshot.child("percent").getValue(Double.class);
                        accum = dataSnapshot.child("point").getValue(Double.class);

                        //데이터를 화면에 출력해 준다.

                        if(check) {

                        accum = percent * Double.parseDouble(sharedText.toString()) + accum;

//                        myRef2 = FirebaseDatabase.getInstance().getReference().child("Supporters").child(uid).child("point");;
                        myRef2 = database.getInstance().getReference("Supporters").child(uid).child("point");


                            check = false;

                            myRef2.setValue(accum);
                            //Toast.makeText(MainActivity.this,intent.toString(),Toast.LENGTH_SHORT).show();
                            Intent sendIntent = getPackageManager().getLaunchIntentForPackage("heeyoung.capston3");
                            sendIntent.putExtra("my_text2", String.valueOf(percent * Double.parseDouble(sharedText.toString())));
                            startActivity(sendIntent);

                        }
                        else {

                        }

                        Log.d(TAG, "Value is: " + value);


                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            check = true;
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (REQUEST_CODE_SIGNIN == 101) {
                                Toast.makeText(MainActivity.this, "구글 로그인 되었습니다", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), supporter2_home_activity.class);
                                startActivityForResult(intent, REQUEST_CODE_SIGNIN);
                            }
                            else {
                                Toast.makeText(MainActivity.this, "구글 로그인 되었습니다", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), bene_signup_activity.class);
                                startActivityForResult(intent, REQUEST_CODE_SIGNIN);
                            }

                        }
else {
                            Toast.makeText(MainActivity.this, "구글 로그인 실패하셨습니다", Toast.LENGTH_SHORT).show();

                        }
                        // ...
                    }
                });
    }



//    private void signOut() {
//        // Firebase sign out
//        mAuth.signOut();
//
//        // Google sign out
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(@NonNull Status status) {
//                    }
//                });
 //   }




    public void button_supporter_clicked(View v) {
        Select_supporter.setBackgroundResource(R.drawable.supporterselected);
        Select_bene.setBackgroundResource(R.drawable.beneficiaryoff);
        REQUEST_CODE_SIGNIN = 101;
    }


    public void button_bene_clicked(View v) {
        Select_bene.setBackgroundResource(R.drawable.beneficiaryselected);
        Select_supporter.setBackgroundResource(R.drawable.supporteroff);
        REQUEST_CODE_SIGNIN = 105;
    }

//
//    public void buttonsignupclicked(View v) {
//        if (REQUEST_CODE_SIGNIN == 101) {
//            Toast.makeText(MainActivity.this,"구글 로그인 되었습니다",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), signupactivity.class);
//            startActivityForResult(intent, REQUEST_CODE_SIGNIN);
//        } else {
//            Toast.makeText(MainActivity.this,"구글 로그인 되었습니다",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(), bene_signup_activity.class);
//            startActivityForResult(intent, REQUEST_CODE_SIGNIN);
//        }
//    }
//
//
//    public void buttonloginclicked(View v) {
//        Intent intent = new Intent(getApplicationContext(), loginactivity.class);
//        startActivityForResult(intent, REQUEST_CODE_LOGIN);
//    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }


}
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
