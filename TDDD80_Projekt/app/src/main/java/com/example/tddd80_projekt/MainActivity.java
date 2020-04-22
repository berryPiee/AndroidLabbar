package com.example.tddd80_projekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        createLoginScreen(ft);


    }

    /**
     * Login screen which is the first fragment that user sees
     * @param ft FragmentTransaction
     */
    private void createLoginScreen( FragmentTransaction ft){
        //Request to server (username, password lenght)
        LoginFragment loginFragment = new LoginFragment();
        ft.replace(R.id.container, loginFragment).addToBackStack(null).commit();




    }
}
