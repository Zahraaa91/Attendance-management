package com.example.attendancemanagement;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Belal on 2/26/2017.
 */
@IgnoreExtraProperties
public class Third {
    private String artistId;
    private String artistName;
    private String artistGenre;
    private String artistSub;
    private String artistHrs;

    public Third() {
        //this constructor is required
    }

    public Third(String artistId, String artistName, String artistGenre, String artistHrs,String artistSub) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
        this.artistSub = artistSub;
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
    public String getArtistSub() {
        return artistSub;
    }
    public String getArtistHrs() {
        return artistHrs;
    }
}