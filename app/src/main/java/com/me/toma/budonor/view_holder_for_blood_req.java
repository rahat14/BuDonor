package com.me.toma.budonor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class view_holder_for_blood_req extends RecyclerView.ViewHolder {

    View mView ;
    public view_holder_for_blood_req(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }

    //set details to recycler view row
    public void setdetails(Context ctx, String name, String phn, String bg ){
        //Views
        TextView mname = mView.findViewById(R.id.name_req);
        TextView mphn = mView.findViewById(R.id.ph_req);
        TextView mIbg = mView.findViewById(R.id.blood_group_req);

        //set data to views
       mname.setText(name);
        mphn.setText(phn);
        mIbg.setText(bg);



    }

    private view_holder_for_blood_req.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(view_holder_for_blood_req.ClickListener clickListener){
        mClickListener = clickListener;
    }

}


