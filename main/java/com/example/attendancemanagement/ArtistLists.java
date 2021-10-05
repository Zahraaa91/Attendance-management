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

public class ArtistLists extends ArrayAdapter<Second> {
    private Activity context;
    List<Second> artistss;

    public ArtistLists(Activity context, List<Second> artistss) {
        super(context, R.layout.layout_artist_list2, artistss);
        this.context = context;
        this.artistss = artistss;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem2 = inflater.inflate(R.layout.layout_artist_list2, null, true);
        TextView textViewName =  listViewItem2.findViewById(R.id.textViewName);
        TextView textViewGenre =  listViewItem2.findViewById(R.id.textViewGenre);
        TextView textViewSub =  listViewItem2.findViewById(R.id.textViewSub);
        TextView textViewHrs  = listViewItem2.findViewById(R.id.textViewHrs);

        Second second = artistss.get(position);
        textViewName.setText(second.getArtistName());
        textViewGenre.setText(second.getArtistGenre());
        textViewSub.setText(second.getArtistSub());
        textViewHrs.setText(second.getArtistHrs());

        return listViewItem2;
    }
}