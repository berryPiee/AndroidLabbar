package com.example.lab2igen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    public ListFragment() {
        // Required empty public constructor
    }

    public interface OnFragmentInteractionListener{
        void sendToDo(String todo);
    }

    private OnFragmentInteractionListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Skapa en view med hjälp av XML filern (layout)
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //Hämta alla element från veckodagslistan
        String[] weekdayArray = getResources().getStringArray(R.array.Weekdays);

        //Hämta rätt view där alla veckodagar ska ritas ut
        final ListView listView = (ListView) view.findViewById(R.id.weekdayList);

        //Skapa en ArrayAdapter som gör om listan av veckodagar till faktiska rutor som man kan klicka på
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                                                        android.R.layout.simple_list_item_1,
                                                        weekdayArray);
        //Säg åt listView att den ska använda sig av den skapade adaptern
        listView.setAdapter(adapter);

        //Gör en mappning mellan menytryck och funktionalitet
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String [] toDo = getResources().getStringArray(R.array.Todo);
                listener.sendToDo(toDo[position]);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        }
        else{
            throw new RuntimeException(context.toString() + " must implement ListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
