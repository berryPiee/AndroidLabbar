package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements ListFragment.OnInteractionListener {

    private DetailFragment detailFragment;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailFragment = new DetailFragment();
        listFragment = new ListFragment();

        if(findViewById(R.id.container_port) != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_port, listFragment).commit();
        }
        else{
            listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
            detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        }

    }

    @Override
    public void getGroupMembers(String groupName) {
        if (findViewById(R.id.container_port) != null) {
            detailFragment = DetailFragment.newInstance(groupName);
            getSupportFragmentManager().beginTransaction().replace(R.id.container_port, detailFragment).
                    addToBackStack(null).commit();
        }
    }
}
