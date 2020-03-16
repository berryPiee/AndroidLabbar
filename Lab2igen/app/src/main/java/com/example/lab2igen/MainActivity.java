package com.example.lab2igen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener {
    //Implementera de två fragmenten och dess metoder som skickar data upp hit
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListFragment listFragment = new ListFragment();
        detailFragment = new DetailFragment();

        //Om telefonen är i portrait mode
        if(findViewById(R.id.container_port) != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container_port, listFragment).commit();
        }
        else {
            listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.weekdayList);
            detailFragment  = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailView);
        }
    }

    @Override
    public void sendToDo(String todo) {
        if(findViewById(R.id.container_port) != null) {
            DetailFragment detailFragment = DetailFragment.newInstance(todo);
            getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.container_port, detailFragment).addToBackStack(null).commit();
        }
        else{
            //När man är i landscapemode är båda fragments öppna samtidigt och man behöver ej replaca någon
            detailFragment.updateTodo(todo);
        }
    }
}
