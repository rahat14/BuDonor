package com.me.toma.budonor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
Button searchBlood , post_req , show_req  ;

TextView usere ;
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        searchBlood = (Button)findViewById(R.id.search_blood) ;
        post_req   =(Button)findViewById(R.id.post_request);
        show_req =(Button)findViewById(R.id.donate_dlood) ;
        usere = findViewById(R.id.user_id);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();

           usere.setText(email);




               searchBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , Donor_List.class) ;
                startActivity(i);



            }
        });


       post_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , Create_Blood_request.class) ;
                startActivity(i);



            }
        });
       show_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() , show_req_list.class) ;
                startActivity(i);



            }
        });





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.log_out ,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_sign_out:
                logout() ;
                return  true ;




            default:

                return  false ;






        }





    }
    public  void logout(){

        mAuth.signOut();
        sendtoLOgin();


    }

    public  void sendtoLOgin(){

        Intent i  = new Intent(getApplicationContext() , sign_page.class);
        startActivity(i);
        finish();




    }
}
