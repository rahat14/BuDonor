package com.me.toma.budonor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PostDetailActivity extends AppCompatActivity {
    TextView mTitleTv, mphTv, mPriceTv , id_tv;



    EditText quan ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        mTitleTv = findViewById(R.id.titleTv);
        mPriceTv = findViewById(R.id.PriceTv);
        id_tv = findViewById(R.id.descriptionTv) ;
        mphTv = findViewById(R.id.descriptionPH) ;


        String identity_num = getIntent().getStringExtra("image");
        final String name = getIntent().getStringExtra("title");
        String ph = getIntent().getStringExtra("description");
        final String bg = getIntent().getStringExtra("price");



        //set data to views
        mTitleTv.setText(name);
        mphTv.setText(ph);
        mPriceTv.setText(bg);
        id_tv.setText(identity_num);



    }
}
