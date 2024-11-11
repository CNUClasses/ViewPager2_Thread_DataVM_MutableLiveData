package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewPager2_Adapter extends RecyclerView.Adapter{
    private final LayoutInflater li;
    private final Context ctx;
    private final DataVM vm;
    private static final String TAG="ViewPager2_Adapter";

    class PagerViewHolder extends RecyclerView.ViewHolder {
        private static final int UNINITIALIZED = -1;
        ImageView iv;
        TextView tv;
        int position=UNINITIALIZED;   //start off uninitialized, set it when we are populating

        //with a view in onBindViewHolder
        public PagerViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.imageView);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }
    public ViewPager2_Adapter(Context ctx, DataVM vm){
        this.ctx=ctx;
        this.vm=vm;
        li=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //call this when we need to create a brand new PagerViewHolder
        View view = li.inflate(R.layout.swipe_layout, parent, false);
        return new PagerViewHolder(view);   //the new one
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //passing in an existing instance, reuse the internal resources (iv and tv) to set
        //the imageview and textview to widgets corresponding to position
        //passing in an existing instance, reuse the internal resources
        //pass our data to our ViewHolder.
        PagerViewHolder viewHolder = (PagerViewHolder) holder;

        //set to some default image

        viewHolder.tv.setText("Image : " + position);
        viewHolder.position=position;       //remember which image this view is bound to

        //ask VM for image
        Integer bmp_ID=vm.getimage(position);
        Log.d(TAG, "onBindViewHolder: position="+Integer.toString(position));
        if (bmp_ID ==null){
            Log.d(TAG, "ERROR IMG, position="+Integer.toString(position));
            viewHolder.iv.setImageResource(R.drawable.error);}
        else{
            Log.d(TAG, "    BINDING: position="+Integer.toString(position));
            viewHolder.iv.setImageResource(bmp_ID);
        }
    }

    @Override
    public int getItemCount() {
        //the size of the collection that contains the items we want to display
        return vm.image_resources.length;
    }
}
