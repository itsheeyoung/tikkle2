package heeyoung.tikkle2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link bene_account.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link bene_account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bene_account extends Fragment {


    TextView accu_money;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    static final int REQUEST_CODE_BOARD = 112;

    FirebaseDatabase database;
    DatabaseReference mdatabase;
    DatabaseReference mmyRef;
    FirebaseDatabase myRef;
    String uid;

    private OnFragmentInteractionListener mListener;
    public bene_account() {



        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment supporter_account.
     */
    // TODO: Rename and change types and number of parameters
    public static bene_account newInstance(String param1, String param2) {
        bene_account fragment = new bene_account();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

//        database = FirebaseDatabase.getInstance().getReference();
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            uid = user.getUid();
//        }

//        final TextView accu_money = (TextView) getView().findViewById(R.id.accu_money);
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("Supporters").child(uid).child("point");


//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                Double point = dataSnapshot.getValue(Double.class);
//                //데이터를 화면에 출력해 준다.
//
//                accu_money.setText("누적 적립금 : " + point + "원");
//
//                Log.d(TAG, "Value is: " + point);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_supporter_account, container, false);
        View view = inflater.inflate(R.layout.fragment_supporter_account, container, false);

        accu_money = (TextView)view.findViewById(R.id.accu_money);



        mdatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            uid = user.getUid();
        }


        database = FirebaseDatabase.getInstance();
        mmyRef = database.getReference("Supporters").child(uid).child("point");

        final TextView mStar = (TextView) view.findViewById(R.id.star);

        mmyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Double point = dataSnapshot.getValue(Double.class);



                accu_money.setText("누적 적립금 : " + String.valueOf(Math.round(point)).toString() + "원");

                if(Math.round(point)<30000)
                    mStar.setText("★");
                else if (Math.round(point)<50000)
                    mStar.setText("★★");
                else if (Math.round(point)<100000)
                    mStar.setText("★★★");
                else if (Math.round(point)<200000)
                    mStar.setText("★★★★");
                else
                    mStar.setText("★★★★★");

//                Toast.makeText(supporter_account.this,"sss", Toast.LENGTH_SHORT).show();
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





        // Inflate the layout for this fragment
        return view;
//inflater.inflate(R.layout.fragment_supporter_account, container, false);
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
          //  Toast.makeText(context, "계좌 fragment attached", Toast.LENGTH_SHORT).show();
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