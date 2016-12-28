package com.techpalle.b33_databaseeg1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ADDING FRAGMENT DYNAMICALLY TO ACTIVITY
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MyFragment myFragment = new MyFragment();
        fragmentTransaction.add(R.id.container, myFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
