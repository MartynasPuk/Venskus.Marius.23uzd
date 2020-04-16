package com.example.venskusmarius23uzd;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TwoColumnListAdapter extends ArrayAdapter<Notes> {

    private LayoutInflater mInflater;
    private ArrayList<Notes> notes;
    private int mViewResourceId;


    public TwoColumnListAdapter(Context context, int textViewResourceId, ArrayList<Notes> notes) {
        super(context, textViewResourceId, notes);
        this.notes = notes;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Notes note = notes.get(position);

        if(note != null) {
            TextView noteName = convertView.findViewById(R.id.textNoteName);
            TextView noteCat = convertView.findViewById(R.id.textNoteCat);
            TextView noteText = convertView.findViewById(R.id.textNoteText);

            if(noteName != null) {
                noteName.setText(note.getNoteName());
            }
            if(noteCat != null) {
                noteCat.setText(note.getNoteCat());
            }
            if(noteText != null) {
                noteText.setText(note.getNoteText());
            }
        }

        return convertView;
    }
}
