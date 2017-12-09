package heeyoung.tikkle2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static java.lang.System.load;

public class board_reading_activity extends AppCompatActivity {

    private RecyclerView mBoardList;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_reading_activity);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Board");

        mBoardList = (RecyclerView)findViewById(R.id.board_list);
        mBoardList.setHasFixedSize(true);
        mBoardList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<board,BoardViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<board,BoardViewHolder>(
                board.class,
                R.layout.board_list,
                BoardViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BoardViewHolder viewHolder, board model, int position){

                viewHolder.setTitle(model.getTitle());
                viewHolder.setContent(model.getContent());
                viewHolder.setImage(getApplicationContext(), model.getImage());
            }
        };

        mBoardList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BoardViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public BoardViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setTitle(String title) {

            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);

        }

        public void setContent(String content){

        TextView post_content = (TextView) mView.findViewById(R.id.post_content);
            post_content.setText(content);
        }

        public void setImage(Context ctx, String image){

            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);

        }


    }


}
