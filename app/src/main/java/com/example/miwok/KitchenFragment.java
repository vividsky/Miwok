package com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class KitchenFragment extends Fragment {

    MediaPlayer mediaPlayer;
    AudioManager mAudioManager;
    private MediaPlayer.OnCompletionListener mCompletionListener = mp -> releaseMediaPlayer();

    /* Method to release media player to free up the memory
    It checks if media player is already instantiated, then it empty that for
    future use by calling release method
     */
    public void releaseMediaPlayer() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(audioFocusChangeListener);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                mediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Change 1 : adding getActivity method as ragment does not have access to system services, whereas the Activity does
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        ArrayList<Word> numbers = new ArrayList<>();
        numbers.add(new Word("One", "ek", R.drawable.number_one, R.raw.number_one));
        numbers.add(new Word("Two", "Do", R.drawable.number_two, R.raw.number_two));
        numbers.add(new Word("Three", "Teen", R.drawable.number_three, R.raw.number_three));
        numbers.add(new Word("Four", "Chaar", R.drawable.number_four, R.raw.number_four));
        numbers.add(new Word("Five", "Paanch", R.drawable.number_five, R.raw.number_five));
        numbers.add(new Word("Six", "Che", R.drawable.number_six, R.raw.number_six));
        numbers.add(new Word("Seven", "Saat", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new Word("Eight", "Aath", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new Word("Nine", "Nau", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new Word("Ten", "Das", R.drawable.number_ten, R.raw.number_ten));


        /*
        Change 2 :  Change this with getActivity() in parameters as
         Fragment is not a valid Context.
         However, the code used to work when “this” referred the
         NumbersActivity because an Activity is a valid Context.
         */
        WordAdapter adapter = new WordAdapter(getActivity(), numbers, R.color.numbers);


        /* CHANGE 3 : Fragment does not have a findViewById method,
        Fix the error by calling findViewById(int) on the rootView object,
        which should contain children views such as the ListView.
         */
        ListView listView = rootView.findViewById(R.id.list);


        // listView.setBackgroundColor(getResources().getColor(R.color.numbers));
        listView.setAdapter(adapter);

        //Play the media file on click
        listView.setOnItemClickListener((parent, view, position, id) -> {

            releaseMediaPlayer();
            Word word = numbers.get(position);
            int result = mAudioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                /* CHANGE 4: NumbersActivity.this -> getActivity(),
                same reason as change 2
                 */
                mediaPlayer = MediaPlayer.create(getActivity(), word.getMediaResourceId());

                mediaPlayer.start();

                //release the media player on completion
                mediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }
}