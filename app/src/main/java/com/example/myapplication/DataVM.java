package com.example.myapplication;

import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataVM extends ViewModel {
    public int[] image_resources = { R.drawable.p0,R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5 };

    // Set the max cache size to length of image_resources
//    public SynchronizedCache scache = new SynchronizedCache(image_resources.length);
    public SynchronizedCache scache = new SynchronizedCache(2);

    public Integer getimage(int position){
        Integer mydrawable=scache.get(position);
        if(mydrawable==null){
            //launch a thread to get it
            GetImage myTask = new GetImage(position);
            myTask.start();
        }
        return mydrawable;
    }
    private class GetImage extends Thread{
        private final Integer position;
        public GetImage(Integer position) {
            //which do we want?
            this.position = position;
        }

        @Override
        public void run() {
            super.run();
            //just sleep for a bit
            try {
                Thread.sleep(1000); //sleep for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //now add it to the cache if its not there
            scache.put(position,image_resources[position]);

            //notify all watchers that new data is there
            bNewData.postValue(true);
        }
    }
    //lets add some livedata, kinda cheesy, just forces the
    //mainactivity to redraw
    //did something get added to the cache?
    private MutableLiveData<Boolean> bNewData ;
    public MutableLiveData<Boolean> getNewData() {
        if(bNewData==null)
            bNewData=new MutableLiveData<Boolean>();
        return bNewData;
    }
}
