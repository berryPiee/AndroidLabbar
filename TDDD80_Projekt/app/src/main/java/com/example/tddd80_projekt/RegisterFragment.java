package com.example.tddd80_projekt;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private int maxEmailLength;
    private int maxUsernameLength;
    private int maxPasswordLength;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * adds restrictions to name, email and password of a user
     * @param maxEmailLength max length of email address
     * @param maxUsernameLength max length of username
     * @param maxPasswordLength max length of password
     * @return new RegisterFragment
     */
    public static RegisterFragment newInstance(int maxEmailLength, int maxUsernameLength,
                                               int maxPasswordLength) {
        Bundle bundle = new Bundle();
        RegisterFragment fragment = new RegisterFragment();

        bundle.putInt("maxEmailLength", maxEmailLength);
        bundle.putInt("maxUsernameLength", maxUsernameLength);
        bundle.putInt("maxPasswordLength", maxPasswordLength);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}
