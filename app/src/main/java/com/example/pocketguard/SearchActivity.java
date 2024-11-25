package com.example.pocketguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class SearchActivity extends AppCompatActivity {
    private SearchView mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;
    myadapter adapter;
    myadapter adapter2;
    private DatabaseReference mUserDatabase;
    SearchView searchview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Admincategory");

        searchview = findViewById(R.id.search_field);
        //mSearchField = findViewById(R.id.search_field);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Category2> options2 =
                new FirebaseRecyclerOptions.Builder<Category2>()
                        .setQuery(mUserDatabase, Category2.class)
                        .build();


       adapter=new myadapter(options2);
       adapter.startListening();
        mResultList.setAdapter(adapter);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processSearch(query.toUpperCase());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processSearch(newText.toUpperCase());
                return false;
            }
        });
       /* searchview.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getQuery().toString().toUpperCase().trim();

               // firebaseUserSearch(searchText);
              //  Toast.makeText(SearchActivity.this, "Started Search", Toast.LENGTH_LONG).show();

                Query firebaseSearchQuery = mUserDatabase.orderByChild("bookName").startAt(searchText).endAt(searchText + "\uf8ff");
                FirebaseRecyclerOptions<Category2> options =
                        new FirebaseRecyclerOptions.Builder<Category2>()
                                .setQuery(firebaseSearchQuery, Category2.class)
                                .build();


                       adapter2=new myadapter(options);
                        adapter2.startListening();
              //  firebaseRecyclerAdapter.notifyDataSetChanged();
                mResultList.setAdapter(adapter2);

            }
        });
*/
    }

    private void processSearch(String searchText) {
        Query firebaseSearchQuery = mUserDatabase.orderByChild("bookName").startAt(searchText).endAt(searchText + "\uf8ff");
        FirebaseRecyclerOptions<Category2> options =
                new FirebaseRecyclerOptions.Builder<Category2>()
                        .setQuery(firebaseSearchQuery, Category2.class)
                        .build();


        adapter2=new myadapter(options);
        adapter2.startListening();
        //  firebaseRecyclerAdapter.notifyDataSetChanged();
        mResultList.setAdapter(adapter2);

    }


}



