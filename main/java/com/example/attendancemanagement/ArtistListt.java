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

public class ArtistListt extends ArrayAdapter<Third> {
    private Activity context;
    List<Third> artistst;

    public ArtistListt(Activity context, List<Third> artistst) {
        super(context, R.layout.layout_artist_list3, artistst);
        this.context = context;
        this.artistst = artistst;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem3 = inflater.inflate(R.layout.layout_artist_list3, null, true);
        TextView textViewName =  listViewItem3.findViewById(R.id.textViewName);
        TextView textViewGenre =  listViewItem3.findViewById(R.id.textViewGenre);
        TextView textViewSub =  listViewItem3.findViewById(R.id.textViewSub);
        TextView textViewHrs  = listViewItem3.findViewById(R.id.textViewHrs);

        Third third = artistst.get(position);
        textViewName.setText(third.getArtistName());
        textViewGenre.setText(third.getArtistGenre());
        textViewSub.setText(third.getArtistSub());
        textViewHrs.setText(third.getArtistHrs());

        return listViewItem3;
    }
}