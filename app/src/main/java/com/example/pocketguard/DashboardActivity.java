package com.example.pocketguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseArray;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    SliderView sliderView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView bookRecycler,bookRecycler2,bookRecycler3,bookRecycler4;
    FirebaseRecyclerAdapter<Category2,CategoryViewHolder2> adapter1;
    FirebaseRecyclerAdapter<Category,CategoryViewHolder> adapter;
    FirebaseRecyclerAdapter<Category2,CategoryViewHolder2> adapter2;
    FirebaseRecyclerAdapter<Category,CategoryViewHolder> adapter3;
    FirebaseRecyclerAdapter<Category2,CategoryViewHolder2> adapter4;
    FirebaseRecyclerAdapter<Category,CategoryViewHolder> adapter5;
    FirebaseRecyclerAdapter<Category2,CategoryViewHolder2> adapter6;
    FirebaseRecyclerAdapter<Category,CategoryViewHolder> adapter7;
    RecyclerView.LayoutManager manager,manager2,manager3,manager4;
    TextView search;
    FirebaseDatabase database ;
    DatabaseReference user;
    private ArrayList<Helper> list;
       int[] images = {R.drawable.down12, R.drawable.dash56, R.drawable.dash2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        database = FirebaseDatabase.getInstance();
        user = database.getReference("category");
        sliderView = findViewById(R.id.imageslider);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav);
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);
        list = new ArrayList<>();
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
        manager = new LinearLayoutManager(this);
        manager2 = new LinearLayoutManager(this);
        manager3 = new LinearLayoutManager(this);
        manager4 = new LinearLayoutManager(this);
        bookRecycler = findViewById(R.id.recyclerView);
        bookRecycler2 = findViewById(R.id.recyclerView2);
        bookRecycler.setLayoutManager(manager);
        bookRecycler2.setLayoutManager(manager2);
        bookRecycler3 = findViewById(R.id.recyclerView3);
        bookRecycler4 = findViewById(R.id.recyclerView4);
        bookRecycler3.setLayoutManager(manager3);
        bookRecycler4.setLayoutManager(manager4);

               FirebaseRecyclerOptions<Category> options= new FirebaseRecyclerOptions.Builder<Category>().setQuery(user.child("Science Fiction"),Category.class).build();
               adapter=new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
                   @Override
                   protected void onBindViewHolder(@NonNull  CategoryViewHolder holder, int position,  Category model) {
                       FirebaseRecyclerOptions<Category2> options2 = new FirebaseRecyclerOptions.Builder<Category2>().setQuery(user.child("Science Fiction").child("data"), Category2.class).build();
                       adapter1 = new FirebaseRecyclerAdapter<Category2, CategoryViewHolder2>(options2) {
                           @Override
                           protected void onBindViewHolder(@NonNull CategoryViewHolder2 holder1, int position, @NonNull Category2 model) {
                               holder1.bookname.setText(model.getBookName());
                               holder1.bookprice.setText("BDT " +model.getBookPrice());
                               String category = model.getCategoryId();
                               holder.CategoryName.setText(category);
                               Picasso.get().load(model.getBookImage()).into(holder1.bookimage);
                               holder1.SubCategory(new SubCategory() {
                                   @Override
                                   public void onClick(View view, boolean isLongPressed) {
                                       Intent intent = new Intent(DashboardActivity.this, detail2Activity3.class);
                                       intent.putExtra("BookName", model.getBookName());
                                       intent.putExtra("BookImage", model.getBookImage());
                                       intent.putExtra("BookPrice", model.getBookPrice());
                                       intent.putExtra("description",model.getDescription());

                                       startActivity(intent);
                                   }
                               });

                           }

                           @NonNull

                           @Override
                           public CategoryViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                               View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_recycle, parent, false);
                               return new CategoryViewHolder2(v2);
                           }
                       };
                       adapter1.startListening();
                       adapter1.notifyDataSetChanged();
                       holder.categoryrecycleview.setAdapter(adapter1);
                   }

                   @NonNull

                   @Override
                   public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                       View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
                       return new CategoryViewHolder(v1);
                   }


                };
                adapter.startListening();
                adapter.notifyDataSetChanged();
                bookRecycler.setAdapter(adapter);

        FirebaseRecyclerOptions<Category> options3= new FirebaseRecyclerOptions.Builder<Category>().setQuery(user.child("Novels"),Category.class).build();
        adapter3=new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options3) {
            @Override
            protected void onBindViewHolder(@NonNull  CategoryViewHolder holder, int position,  Category model) {
                FirebaseRecyclerOptions<Category2> options4 = new FirebaseRecyclerOptions.Builder<Category2>().setQuery(user.child("Novels").child("data"), Category2.class).build();
                adapter2 = new FirebaseRecyclerAdapter<Category2, CategoryViewHolder2>(options4) {
                    @Override
                    protected void onBindViewHolder(@NonNull CategoryViewHolder2 holder1, int position, @NonNull Category2 model) {
                        holder1.bookname.setText(model.getBookName());
                        holder1.bookprice.setText("BDT " +model.getBookPrice());
                        String category = model.getCategoryId();
                        holder.CategoryName.setText(category);

                        Picasso.get().load(model.getBookImage()).into(holder1.bookimage);
                        holder1.SubCategory(new SubCategory() {
                            @Override
                            public void onClick(View view, boolean isLongPressed) {
                                Intent intent = new Intent(DashboardActivity.this,  detail2Activity3.class);
                                intent.putExtra("BookName", model.getBookName());
                                intent.putExtra("BookImage", model.getBookImage());
                                intent.putExtra("BookPrice", model.getBookPrice());
                                intent.putExtra("description",model.getDescription());

                                startActivity(intent);
                            }
                        });

                    }

                    @NonNull

                    @Override
                    public CategoryViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_recycle, parent, false);
                        return new CategoryViewHolder2(v2);
                    }
                };
                adapter2.startListening();
                adapter2.notifyDataSetChanged();
                holder.categoryrecycleview.setAdapter(adapter2);
            }

            @NonNull

            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
                return new CategoryViewHolder(v1);
            }


        };
        adapter3.startListening();
        adapter3.notifyDataSetChanged();
        bookRecycler2.setAdapter(adapter3);

        //category3
        FirebaseRecyclerOptions<Category> options5= new FirebaseRecyclerOptions.Builder<Category>().setQuery(user.child("Comics"),Category.class).build();
        adapter5=new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options5) {
            @Override
            protected void onBindViewHolder(@NonNull  CategoryViewHolder holder, int position,  Category model) {
                FirebaseRecyclerOptions<Category2> options6 = new FirebaseRecyclerOptions.Builder<Category2>().setQuery(user.child("Comics").child("data"), Category2.class).build();
                adapter4 = new FirebaseRecyclerAdapter<Category2, CategoryViewHolder2>(options6) {
                    @Override
                    protected void onBindViewHolder(@NonNull CategoryViewHolder2 holder1, int position, @NonNull Category2 model) {
                        holder1.bookname.setText(model.getBookName());
                        holder1.bookprice.setText("BDT " +model.getBookPrice());
                        String category = model.getCategoryId();
                        holder.CategoryName.setText(category);
                        Picasso.get().load(model.getBookImage()).into(holder1.bookimage);
                        holder1.SubCategory(new SubCategory() {
                            @Override
                            public void onClick(View view, boolean isLongPressed) {
                                Intent intent = new Intent(DashboardActivity.this,  detail2Activity3.class);
                                intent.putExtra("BookName", model.getBookName());
                                intent.putExtra("BookImage", model.getBookImage());
                                intent.putExtra("BookPrice", model.getBookPrice());
                                intent.putExtra("description",model.getDescription());

                                startActivity(intent);
                            }
                        });

                    }

                    @NonNull

                    @Override
                    public CategoryViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_recycle, parent, false);
                        return new CategoryViewHolder2(v2);
                    }
                };
                adapter4.startListening();
                adapter4.notifyDataSetChanged();
                holder.categoryrecycleview.setAdapter(adapter4);
            }

            @NonNull

            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
                return new CategoryViewHolder(v1);
            }


        };
        adapter5.startListening();
        adapter5.notifyDataSetChanged();
        bookRecycler3.setAdapter(adapter5);

        //category 4
        FirebaseRecyclerOptions<Category> options7= new FirebaseRecyclerOptions.Builder<Category>().setQuery(user.child("History"),Category.class).build();
        adapter7=new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options7) {
            @Override
            protected void onBindViewHolder(@NonNull  CategoryViewHolder holder, int position,  Category model) {
                FirebaseRecyclerOptions<Category2> options8 = new FirebaseRecyclerOptions.Builder<Category2>().setQuery(user.child("History").child("data"), Category2.class).build();
                adapter6= new FirebaseRecyclerAdapter<Category2, CategoryViewHolder2>(options8) {
                    @Override
                    protected void onBindViewHolder(@NonNull CategoryViewHolder2 holder1, int position, @NonNull Category2 model) {
                        holder1.bookname.setText(model.getBookName());
                        holder1.bookprice.setText("BDT " +model.getBookPrice());
                        String category = model.getCategoryId();
                        holder.CategoryName.setText(category);
                        Picasso.get().load(model.getBookImage()).into(holder1.bookimage);
                        holder1.SubCategory(new SubCategory() {
                            @Override
                            public void onClick(View view, boolean isLongPressed) {
                                Intent intent = new Intent(DashboardActivity.this,  detail2Activity3.class);
                                intent.putExtra("BookName", model.getBookName());
                                intent.putExtra("BookImage", model.getBookImage());
                                intent.putExtra("BookPrice",  model.getBookPrice());
                                intent.putExtra("description",model.getDescription());

                                startActivity(intent);
                            }
                        });

                    }

                    @NonNull

                    @Override
                    public CategoryViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_recycle, parent, false);
                        return new CategoryViewHolder2(v2);
                    }
                };
                adapter6.startListening();
                adapter6.notifyDataSetChanged();
                holder.categoryrecycleview.setAdapter(adapter6);
            }

            @NonNull

            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
                return new CategoryViewHolder(v1);
            }


        };
        adapter7.startListening();
        adapter7.notifyDataSetChanged();
        bookRecycler4.setAdapter(adapter7);





    }




    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        /*
        .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
      *

         */
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.search:
                Intent in=new Intent(DashboardActivity.this,SearchActivity.class);
                startActivity(in);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void processSearch(String s) {




    }




    @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        break;
                    case R.id.cart:
                        Intent intent = new Intent(DashboardActivity.this, CartActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.orders:
                        Intent intent1 = new Intent(DashboardActivity.this, OrderActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.Myprofile:
                        Intent intent2 = new Intent(DashboardActivity.this, UserActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), Log_In.class));
                        finish();
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        }
