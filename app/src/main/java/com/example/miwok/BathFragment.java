package com.example.miwok;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class BathFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        ArrayList<Word> phrases = new ArrayList<>();
        phrases.add(new Word("Come here", "Idhar aao", R.raw.phrase_come_here));
        phrases.add(new Word("How are you feeling?", "Tum kaisa mehsoos kar rhe ho?", R.raw.phrase_how_are_you_feeling));


        WordAdapter adapter = new WordAdapter(getActivity(), phrases, R.color.phrases);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }
}
