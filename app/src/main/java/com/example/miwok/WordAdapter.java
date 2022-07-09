package com.example.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mcolor;


    // invoke the suitable constructor of the ArrayAdapter class
    public WordAdapter(@NonNull Context context, ArrayList<Word> typeOfWords, int color) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, typeOfWords);
        mcolor = color;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // of the recyclable view is null then inflate the custom layout for the same
//        here parent is ListView in which we will inflate our listItems containing two textviews
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            int color = ContextCompat.getColor(getContext(), mcolor);

            convertView.setBackgroundColor(color);

        }

        // get the position of the view from the ArrayAdapter
        Word currentNumberPosition = getItem(position);


        // then according to the position of the view assign the desired TextView 1 for the same
        TextView hindi = convertView.findViewById(R.id.hindi_text_view);
        hindi.setText(currentNumberPosition.getHindiTranslation());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView english = convertView.findViewById(R.id.default_text_view);
        english.setText(currentNumberPosition.getDefaultTranslation());

        // then according to the position of the view assign the desired image for the same
        ImageView wordImage = convertView.findViewById(R.id.image_view);
        if (currentNumberPosition.hasImage()) {
            wordImage.setImageResource(currentNumberPosition.getImageResourceId());
            wordImage.setVisibility(View.VISIBLE);
        } else {
            wordImage.setVisibility(View.GONE);
        }


        // then return the recyclable view
        return convertView;
    }
}
