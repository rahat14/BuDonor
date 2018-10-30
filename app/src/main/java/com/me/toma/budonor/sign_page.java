package com.me.toma.budonor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class sign_page extends AppCompatActivity {

   Button sign_in ;
   TextView SignUp ;
   EditText emial_sign_in , Pass_sign_in ;
   FirebaseAuth mAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_page);
        mAuth = FirebaseAuth.getInstance() ;


        // getting action bar
        ActionBar actionBar = getSupportActionBar() ;
        actionBar.setTitle("Sign In Page");

        // linking up
         sign_in = (Button)findViewById(R.id.sign_in_button);
         SignUp= (TextView)findViewById(R.id.signUP_Backlink);
         emial_sign_in = (EditText)findViewById(R.id.Sign_edit_email);
         Pass_sign_in = (EditText)findViewById(R.id.edit_Sign_password);

         //adding button

         SignUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(getApplicationContext() , register_page.class);
                 startActivity(i);
             }
         });


         sign_in.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String email = emial_sign_in.getText().toString();
                 String pass= Pass_sign_in.getText().toString();

                 if(!TextUtils.isEmpty(email)&& !TextUtils.isEmpty(pass)){


                     mAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {


                             if(task.isSuccessful()){

                                 Intent i  = new Intent(getApplicationContext(), MainActivity.class);
                                 startActivity(i);
                                 finish();


                             }
                             else {
                                 String e  = task.getException().getMessage() ;
                                 Toast.makeText(getApplicationContext() , "Error "+ e  , Toast.LENGTH_LONG).show();


                             }



                         }
                     });







                 }
                 else {

                     Toast.makeText(getApplicationContext() , "Please Enter The Email & Password" , Toast.LENGTH_LONG);
                 }



             }
         });













    }

    @Override
    protected void onStart() {
        super.onStart();
        // if user is already singend in

        FirebaseUser CurrentUser = mAuth.getCurrentUser() ;
        if(CurrentUser != null){
            Intent i = new Intent(getApplicationContext()  , MainActivity.class) ;
            startActivity(i);
            finish();


        }





    }
}
