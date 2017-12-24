package heeyoung.tikkle2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class board_writing_activity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private EditText mPostTitle;
    private EditText mPostContent;

    private Button mSubmitButton;

    static final int REQUEST_CODE_READBOARD = 115;

    private Uri mImageUri = null ;

    private static final int GALLERY_REQUEST = 1;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;

    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_writing_activity);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Board");

        mSelectImage = (ImageButton) findViewById(R.id.imageSelect);

        mPostTitle = (EditText) findViewById(R.id.board_title_field);
        mPostContent = (EditText) findViewById(R.id.board_content_field);

        mSubmitButton = (Button) findViewById(R.id.submit_button);

        mProgress = new ProgressBar(this);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });

    }

    private void startPosting() {
        final String title_val = mPostTitle.getText().toString().trim();
        final String contents_val = mPostContent.getText().toString().trim();
        //여기 final 추가는 유튜브하고 다름. 인터넷 검색해서 오류 떠서 넣음

        if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(contents_val) && mImageUri != null){

            StorageReference filepath = mStorage.child("Board_Images").child(mImageUri.getLastPathSegment());

            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>(){
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    //Uri앞부분도 유튭하고 다름. 오류떠서 인터넷 검색.

                    DatabaseReference newPost = mDatabase.push();

                    newPost.child("title").setValue(title_val);
                    newPost.child("content").setValue(contents_val);
                    newPost.child("image").setValue(downloadUrl.toString());
                    //여기다 authentication써가지고 사람도 게시판 사람도 추가해야함

                 //   startActivity(new Intent(board_writing_activity.this, board_reading_activity.class);


                    mSubmitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent(getApplicationContext(), board_writing_activity.class);
                            startActivityForResult(intent, REQUEST_CODE_READBOARD);
                        }
                    });



        }
    });
        }

    }


  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

          mImageUri = data.getData();

          mSelectImage.setImageURI(mImageUri);

      }
  }
}
