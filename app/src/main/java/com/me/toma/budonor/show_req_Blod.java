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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class show_req_Blod extends AppCompatActivity {

    TextView nameE , bllod , phonenum ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_req__blod);

        nameE = (TextView)findViewById(R.id.name_as_req) ;
        bllod =(TextView)findViewById(R.id.blood_group_as_req) ;
        phonenum= (TextView)findViewById(R.id.phn_as_req);



        final String name = getIntent().getStringExtra("Name");
        String ph = getIntent().getStringExtra("phone");
        final String bg = getIntent().getStringExtra("BlodGroup");



        //set data to views
        nameE.setText(name);
        phonenum.setText(ph);
       bllod.setText(bg);



    }}