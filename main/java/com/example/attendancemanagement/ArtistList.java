package com.example.attendancemanagement;


import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Belal on 2/26/2017.
 */

public class ArtistList extends ArrayAdapter<FirstStage> {
    private Activity context;
    List<FirstStage> artists;

    public ArtistList(Activity context, List<FirstStage> artists) {
        super(context, R.layout.layout_artist_list, artists);
        this.context = context;
        this.artists = artists;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_artist_list, null, true);

        TextView textViewName =  listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre =  listViewItem.findViewById(R.id.textViewGenre);
        TextView textViewHrs  = listViewItem.findViewById(R.id.textViewHrs);

        FirstStage firstStage = artists.get(position);
        textViewName.setText(firstStage.getArtistName());
        textViewGenre.setText(firstStage.getArtistGenre());
        textViewHrs.setText(firstStage.getArtistHrs());

        return listViewItem;
    }
}