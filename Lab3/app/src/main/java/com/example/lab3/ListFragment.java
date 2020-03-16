package com.example.lab3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private RequestQueue mQueue;
    private Context mainAct;
    private OnInteractionListener listener;
    private GroupList groupList = new GroupList();
    private ArrayAdapter<String> adapter;

    public ListFragment() {
        // Required empty public constructor
    }

    public interface OnInteractionListener {
        void getGroupMembers(String groupName);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQueue = Volley.newRequestQueue(mainAct);
        jsonParse();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        final ListView listView = view.findViewById(R.id.list_view_groups);

        //Skapa en adapter som gör om gruppnamnen till objekt som man kan trycka på
        adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, groupList.getGrupper());

        //Tillämpa adaptern på listan
        listView.setAdapter(adapter);

        //Gör en mappning mellan tryck på listan och action
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.getGroupMembers(groupList.getGrupper().get(position));
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity) {
            mainAct = (MainActivity)context;
            listener = (OnInteractionListener) context;

        }
        else{
            throw new RuntimeException(context.toString() + " listener in ListFragment cant communicate " +
                    "with MainActivity");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainAct = null;
    }

    private void jsonParse(){
        final Gson gson = new Gson();
        String url = "https://tddd80server.herokuapp.com/grupper";
        System.out.println("INNE I FUNKTION");
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("grupper");
                            for (int i = 0; i <jsonArray.length() ; i++) {
                                System.out.println(jsonArray.getString(i)+"############");
                                groupList.addGroup(jsonArray.getString(i));
                                System.out.println(groupList.getGrupper().get(i)+ "*****************");
                            }
                            adapter.notifyDataSetChanged();
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
        System.out.println(groupList.getGrupper() + "UTANFÖÖÖÖÖR");
    }
}
