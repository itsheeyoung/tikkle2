package heeyoung.tikkle2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link supporter_setting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link supporter_setting#newInstance} factory method to
 * create an instance of this fragment.
 */

public class supporter_setting extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseDatabase database;
    DatabaseReference myRef;
    double percent = 0;
    String uid;


    private OnFragmentInteractionListener mListener;

    public supporter_setting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment supporter_setting.
     */
    // TODO: Rename and change types and number of parameters
    public static supporter_setting newInstance(String param1, String param2) {
        supporter_setting fragment = new supporter_setting();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_supporter_setting, container, false);

        final ToggleButton toggleButton1 = (ToggleButton)view.findViewById(R.id.toggleButton1);
        final ToggleButton toggleButton3 = (ToggleButton)view.findViewById(R.id.toggleButton3);
        final ToggleButton toggleButton5 = (ToggleButton)view.findViewById(R.id.toggleButton5);
        final ToggleButton toggleButton10 = (ToggleButton)view.findViewById(R.id.toggleButton10);
        final Button mRegister = (Button)view.findViewById(R.id.button17);

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
                if(percent == 0) {
                    Toast.makeText(getContext(),"퍼센트를 선택하여 주세요",Toast.LENGTH_SHORT).show();
                }
                else {
                    database = FirebaseDatabase.getInstance();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        // Name, email address, and profile photo Url
                        uid = user.getUid();
                    }

                    myRef = database.getReference("Supporters").child(uid).child("percent");
                    myRef.setValue(percent);

                    Toast.makeText(getContext(),"퍼센트가 " + (Math.round(percent*100)) + "%로 설정되었습니다",Toast.LENGTH_SHORT).show();

                }
            }
        });

       return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            Toast.makeText(context, "설정 fragment attached", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


