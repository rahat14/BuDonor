package com.me.toma.budonor;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class show_req_list extends AppCompatActivity {
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    Toolbar toolbar ;
    ProgressBar bar ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_req_list);
        bar = (ProgressBar)findViewById(R.id.progressbar_list);


        bar.setVisibility(View.VISIBLE);


        mSharedPref = getSharedPreferences("SortSettings", MODE_PRIVATE);
        String mSorting = mSharedPref.getString("Sort", "newest"); //where if no settingsis selected newest will be default

        if (mSorting.equals("newest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means newest first
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        } else if (mSorting.equals("oldest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means oldest first
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView_list);
        mRecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(mLayoutManager);

        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("BloodReq");
        mRef.keepSynced(true);




    }

    //search data
    private void firebaseSearch(String searchText) {

        //convert string entered in SearchView to lowercase
        String query = searchText.toUpperCase();

        Query firebaseSearchQuery = mRef.orderByChild("blodgroup").startAt(query).endAt(query + "\uf8ff");


        FirebaseRecyclerAdapter<model_blood_req, view_holder_for_blood_req> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model_blood_req, view_holder_for_blood_req>(
                        model_blood_req.class,
                        R.layout.row_for_blood_req,
                        view_holder_for_blood_req.class,
                        firebaseSearchQuery
                ) {

                    @Override
                    protected void populateViewHolder(view_holder_for_blood_req viewHolder, model_blood_req model, int position) {

                        viewHolder.setdetails(getApplicationContext(), model.getRname(), model.getPhone(), model.getBlodgroup() );
                    }

                    @Override
                    public view_holder_for_blood_req onCreateViewHolder(ViewGroup parent, int viewType) {

                        final view_holder_for_blood_req viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new view_holder_for_blood_req.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views



                                //get data from views
                                String mname = getItem(position).getRname()   ;                     //mTitleTv.getText().toString();
                                String mphone = getItem(position).getPhone();                                  //mDescTv.getText().toString();
                                String   mpbg = getItem(position).getBlodgroup();


                                Intent intent = new Intent(view.getContext(), show_req_Blod.class);

                                //put bitmap image as array of bytes
                                intent.putExtra("Name",mname); // put title
                                intent.putExtra("phone", mphone); //put description

                                //put image
                                intent.putExtra("BlodGroup",mpbg);//put price



                                startActivity(intent); //start activity


                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                //TODO do your own implementaion on long item click
                            }
                        });

                        return viewHolder;
                    }


                };

        //set adapter to recyclerview
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    //load data into recycler view onStart
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<model_blood_req, view_holder_for_blood_req> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model_blood_req, view_holder_for_blood_req>(
                        model_blood_req.class,
                        R.layout.row_for_blood_req,
                        view_holder_for_blood_req.class,
                        mRef
                ) {


                    @Override
                    protected void populateViewHolder(view_holder_for_blood_req viewHolder, model_blood_req model, int position) {


                        viewHolder.setdetails(getApplicationContext(), model.getRname(), model.getPhone(), model.getBlodgroup() );

                        Handler handler  = new Handler() ;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            bar.setVisibility(View.INVISIBLE);

                            }
                        },2000);








                    }






                    @Override
                    public view_holder_for_blood_req onCreateViewHolder(ViewGroup parent, int viewType) {

                        view_holder_for_blood_req viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new view_holder_for_blood_req.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                //Views
                                //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                                //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                                //     ImageView mImageView = view.findViewById(R.id.rImageView);

                                //get data from views
                                String mname = getItem(position).getRname()   ;                     //mTitleTv.getText().toString();
                                String mphone = getItem(position).getPhone();                                  //mDescTv.getText().toString();
                                String   mpbg = getItem(position).getBlodgroup();


                                Intent intent = new Intent(view.getContext(), show_req_Blod.class);

                                //put bitmap image as array of bytes
                                intent.putExtra("Name",mname); // put title
                                intent.putExtra("phone", mphone); //put description

                                //put image
                                intent.putExtra("BlodGroup",mpbg);//put price


                                startActivity(intent); //start activity




                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                //TODO do your own implementaion on long item click
                            }
                        });

                        return viewHolder;
                    }

                };

        //set adapter to recyclerview
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it present
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter as you type
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //handle other action bar item clicks here
        if (id == R.id.action_sort) {
            //display alert dialog to choose sorting
            showSortDialog();
            return true;
        }

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        //options to display in dialog
        String[] sortOptions = {" Newest", " Oldest"};
        //create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by") //set title
                //.setIcon(R.drawable.ic_action_sort) //set icon
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position of the selected item
                        // 0 means "Newest" and 1 means "oldest"
                        if (which == 0) {
                            //sort by newest
                            //Edit our shared preferences
                            SharedPreferences.Editor editor = mSharedPref.edit();
                            editor.putString("Sort", "newest"); //where 'Sort' is key & 'newest' is value
                            editor.apply(); // apply/save the value in our shared preferences
                            recreate(); //restart activity to take effect
                        } else if (which == 1) {
                            {
                                //sort by oldest
                                //Edit our shared preferences
                                SharedPreferences.Editor editor = mSharedPref.edit();
                                editor.putString("Sort", "oldest"); //where 'Sort' is key & 'oldest' is value
                                editor.apply(); // apply/save the value in our shared preferences
                                recreate(); //restart activity to take effect
                            }
                        }
                    }
                });
        builder.show();
    }



}
