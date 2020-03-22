package com.example.lab3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private Context mainAct;
    private RequestQueue mQueue;
    private Group group = null;
    private TextView textView;

    public DetailFragment() {
        // Required empty public constructor
    }

    static DetailFragment newInstance(String groupName) {

        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();

        args.putString("groupName", groupName);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQueue = Volley.newRequestQueue(mainAct);

        if (getArguments() != null){
            group = new Group(getArguments().getString("groupName"), new ArrayList<Member>());
        }
        else{
            group = new Group("         Press group to show memebers", new ArrayList<Member>());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        textView = view.findViewById(R.id.text_view_members);

        if (getArguments() != null) {
            jsonParse(group.getGroupName());
        }

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity){
            mainAct = (MainActivity) context;
        }
        else{
            throw new RuntimeException(context.toString() + " error in DetailFragment!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainAct = null;
    }

    private void jsonParse(String groupName) {
        final Gson gson = new Gson();
        String url = "https://tddd80server.herokuapp.com/medlemmar/" + groupName;
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("medlemmar");
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                Member member = gson.fromJson(jsonArray.get(i).toString(), Member.class);
                                group.addMember(member);
                            }
                            textView.setText(group.getGroupName());
                            for (Member m:group.getMembers()) {
                                textView.append("\n\n"+ "Namn: "+m.getName()+"\nEpost: " + m.getEmail()
                                        + "\nSvarade: " + m.getAnswered());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    public void updateGroup(String groupName) {
        textView.setText("");
        group.setGroupName(groupName);
        group.resetMemebers();
        jsonParse(groupName);
    }
}
