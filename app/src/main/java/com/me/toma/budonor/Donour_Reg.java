package com.me.toma.budonor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Donour_Reg extends AppCompatActivity {

Button finalee ;
    String databasearent_name = "donor" ;
    String mDatabasePath = databasearent_name ;

    Uri mFilePathUri ;

    DatabaseReference mDatabaseReference ;
    ProgressDialog mprogressDialog ;


    EditText namee , ph , iD , Bg ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donour__reg);


        namee = (EditText)findViewById(R.id.name);

        iD = (EditText)findViewById(R.id.ID);

        ph = (EditText)findViewById(R.id.Number);


        Bg= (EditText)findViewById(R.id.blood_group);


        finalee = (Button)findViewById(R.id.final_reg);


        finalee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadDataToFirebase();


                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();









            }
        });




        mDatabaseReference = FirebaseDatabase.getInstance().getReference(mDatabasePath);


        //pregress dialog
        mprogressDialog = new ProgressDialog(Donour_Reg.this  );







    }


    private  void uploadDataToFirebase() {

        String Rnamee ;
        String Rph ;
        String RBg ;
        String RId ;

Rnamee =namee.getText().toString() ;
Rph=ph.getText().toString();
RBg =Bg.getText().toString();
RId = iD.getText().toString();
       donorUploadInfo imageUploadInfo = new donorUploadInfo(Rnamee , Rph,RBg,RId );
        String imageUploadid = mDatabaseReference.push().getKey() ;

        mDatabaseReference.child(imageUploadid).setValue(imageUploadInfo);











    }


    public  void showAlertDialogButton(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Submitted");
        builder.setMessage("Your Request Has Been Uploaded");
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {





            }
        });






    }




}
