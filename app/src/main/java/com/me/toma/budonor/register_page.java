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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register_page extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button nextstep ;
    EditText email_in , pass_in ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        // getting action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register Page");
        // linking data
        email_in = (EditText)findViewById(R.id.email_input_);
        pass_in = (EditText)findViewById(R.id.pass_input_);
        nextstep=(Button)findViewById(R.id.nextStep);
            mAuth = FirebaseAuth.getInstance();


        nextstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email_register = email_in.getText().toString();
                String pass_reg = pass_in.getText().toString();

                if (!TextUtils.isEmpty(email_register) && !TextUtils.isEmpty(pass_reg)) {

                    mAuth.createUserWithEmailAndPassword(email_register , pass_reg).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){


                                Intent i = new Intent(getApplicationContext()  , Donour_Reg.class) ;
                                startActivity(i);
                                finish();


                            }
                            else  {
                                String e  = task.getException().getMessage() ;
                                Toast.makeText(getApplicationContext() , "Error "+ e  , Toast.LENGTH_LONG).show();
                            }




                        }
                    });


                }
                else  {

                    Toast.makeText(getApplicationContext() , "Plzz ENter The Data" , Toast.LENGTH_LONG).show();



                }



            }
        });









    }

    @Override
    protected void onStart() {
        super.onStart();
        // if user is already singend in




    }
}
