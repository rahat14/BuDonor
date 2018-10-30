package com.me.toma.budonor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create_Blood_request extends AppCompatActivity {

    EditText phn , Rname , bloodGroup ;
    Button sumbt ;
    String databasearent_name = "BloodReq" ;
    String mDatabasePath = databasearent_name ;



    DatabaseReference mDatabaseReference ;
    ProgressDialog mprogressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__blood_request);

        Rname = findViewById(R.id.R_name) ;
        phn= findViewById(R.id.contact);
        bloodGroup = findViewById(R.id.header);
        sumbt = findViewById(R.id.Submit);



        sumbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploadDataToFirebase();

                Handler handler  = new Handler() ;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getApplicationContext() , "Your Blood Request Submitted" , Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                },2000);






            }
        });



        mDatabaseReference = FirebaseDatabase.getInstance().getReference(mDatabasePath);







    }
    private  void uploadDataToFirebase() {

        String namee ;
        String ph ;
        String Bg ;


        namee =Rname.getText().toString() ;
        ph=phn.getText().toString();
        Bg =bloodGroup.getText().toString();
        reqUploadInfo reqUploadInfo = new reqUploadInfo(namee , ph,Bg );
        String imageUploadid = mDatabaseReference.push().getKey() ;

        mDatabaseReference.child(imageUploadid).setValue(reqUploadInfo);











    }
}
