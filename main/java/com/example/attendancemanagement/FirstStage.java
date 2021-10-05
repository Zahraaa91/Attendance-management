package com.example.attendancemanagement;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/26/2017.
 */
@IgnoreExtraProperties
public class FirstStage {
    private String artistId;
    private String artistName;
    private String artistGenre;
    private String artistHrs;

    public FirstStage() {
        //this constructor is required
    }

    public FirstStage(String artistId, String artistName, String artistGenre, String artistHrs) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
        this.artistHrs = artistHrs;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }
    public String getArtistHrs() {
        return artistHrs;
    }
}