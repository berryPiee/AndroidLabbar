package com.example.tddd80_projekt;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private int maxUsernameLength;
    private int maxPasswordLength;

    private EditText usernameOrEmail;
    private EditText password;

    private RequestQueue mQueue;
    private MainActivity mainActivity;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQueue = Volley.newRequestQueue(mainActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        final String usernameOrEmailText;
        final String passwordText;
        Button loginBtn = view.findViewById(R.id.login_btn);

        usernameOrEmailText = view.findViewById(R.id.edit_text_username_or_email).toString();
        passwordText = view.findViewById(R.id.edit_text_password).toString();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(container, usernameOrEmailText + passwordText, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            mainActivity = (MainActivity) context;
        }
    }
}
