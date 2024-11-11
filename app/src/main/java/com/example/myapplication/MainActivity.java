package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {
    ViewPager2 vp;
    ViewPager2_Adapter csa;
    DataVM myVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a ViewModel the first time the system calls an activity's
        // onCreate() method.  Re-created activities receive the same
        // MyViewModel instance created by the first activity.
        myVM = new ViewModelProvider(this).get(DataVM.class);

        //get a ref to the viewpager
        vp=findViewById(R.id.view_pager);
        //create an instance of the swipe adapter
        csa = new ViewPager2_Adapter(this,myVM);

        //set this viewpager to the adapter
        vp.setAdapter(csa);

        // Create the observer which updates the UI.
        final Observer<Boolean> bmpObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean bNewData) {
                // Update the UI, in this case, a TextView.
                csa.notifyDataSetChanged();
            }
        };
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        myVM.getNewData().observe(this,bmpObserver);
    }
}