//package heeyoung.tikkle2;
//
//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.text.TextUtils;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ProgressBar;
//
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//import static android.app.Activity.RESULT_OK;
//import static heeyoung.tikkle2.R.id.container;
//
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link supporter_bene.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link supporter_bene#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class supporter_bene extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public supporter_bene() {
//        // Required empty public constructor
//    }
//
//
//    //+++
//    private ImageButton mSelectImage;
//    private EditText mPostTitle;
//    private EditText mPostContent;
//
//    private Button mSubmitButton;
//
//    private Uri mImageUri = null ;
//
//    private static final int GALLERY_REQUEST = 1;
//
//    private StorageReference mStorage;
//    private DatabaseReference mDatabase;
//
//    private ProgressBar mProgress;
//
//
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment supporter_bene.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static supporter_bene newInstance(String param1, String param2) {
//        supporter_bene fragment = new supporter_bene();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View view = inflater.inflate(R.layout.fragment_supporter_bene, container, false);
//
//        mStorage = FirebaseStorage.getInstance().getReference();
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Board");
//
//        mSelectImage = (ImageButton) getView().findViewById(R.id.imageSelect);
//
//        mPostTitle = (EditText) getView().findViewById(R.id.board_title_field);
//        mPostContent = (EditText) getView().findViewById(R.id.board_content_field);
//
//        mSubmitButton = (Button) getView().findViewById(R.id.submit_button);
//
//        mProgress = new ProgressBar(this.getActivity());
//
//
//        mSelectImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                galleryIntent.setType("image/*");
//                startActivityForResult(galleryIntent, GALLERY_REQUEST);
//            }
//        });
//
//        mSubmitButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startPosting();
//            }
//        });
//
//
//
//    private void startPosting() {
//        final String title_val = mPostTitle.getText().toString().trim();
//        final String contents_val = mPostContent.getText().toString().trim();
//        //여기 final 추가는 유튜브하고 다름. 인터넷 검색해서 오류 떠서 넣음
//
//        if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(contents_val) && mImageUri != null) {
//
//            StorageReference filepath = mStorage.child("Board_Images").child(mImageUri.getLastPathSegment());
//
//            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                    //Uri앞부분도 유튭하고 다름. 오류떠서 인터넷 검색.
//
//                    DatabaseReference newPost = mDatabase.push();
//
//                    newPost.child("title").setValue(title_val);
//                    newPost.child("content").setValue(contents_val);
//                    newPost.child("image").setValue(downloadUrl.toString());
//                    //여기다 authentication써가지고 사람도 게시판 사람도 추가해야함
//
//                    startActivity(new Intent(supporter_bene.this.getActivity(), board_reading_activity.class));
//                }
//            });
//        }
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
//
//            mImageUri = data.getData();
//
//            mSelectImage.setImageURI(mImageUri);
//
//        }
//
//    }
//            return view;
//}
//
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            Toast.makeText(context, "기관 fragment attached", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//}
//
