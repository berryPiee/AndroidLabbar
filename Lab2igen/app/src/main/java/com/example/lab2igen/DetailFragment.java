package com.example.lab2igen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }

    private String todo;
    private TextView inputText;

    public static DetailFragment newInstance(String todoText) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("todoText", todoText);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView staticText = view.findViewById(R.id.staticToDoText);
        inputText = view.findViewById(R.id.inputToDoText);


        if (getArguments() != null) {
            todo = getArguments().getString("todoText");
        }
        staticText.setText(R.string.static_message);
        inputText.setText(todo);


        return view;
    }

    public void updateTodo(String todo) {
        inputText.setText(todo);
    }


}
