package com.example.attendancemanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirstwarningActivity extends AppCompatActivity {

    public static final String ARTIST_NAME = "net.simplifiedcoding.firebasedatabaseexample.artistname";
    public static final String ARTIST_ID = "net.simplifiedcoding.firebasedatabaseexample.artistid";

    ListView listViewArtists;
    List<FirstStage> artists;
    EditText editTextName,editTextHrs;
    Spinner spinnerGenre;
    DatabaseReference databaseArtists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstwarning);


        //getting the reference of artists node
        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");

        //getting views
        editTextName =  findViewById(R.id.editTextName);
        editTextHrs =  findViewById(R.id.editTextHrs);
        spinnerGenre =  findViewById(R.id.spinnerGenres);
        listViewArtists =  findViewById(R.id.listViewArtists);
        //list to store artists
        artists = new ArrayList<>();
        /////////////////////////////////////////////////////////

        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                FirstStage firstStage = artists.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

                // putting artist name and id to intent
                intent.putExtra(ARTIST_ID, firstStage.getArtistId());
                intent.putExtra(ARTIST_NAME, firstStage.getArtistName());
                startActivity(intent);
            }
        });
        listViewArtists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                FirstStage firstStage = artists.get(i);
                showUpdateDeleteDialog(firstStage.getArtistId(), firstStage.getArtistName());
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                artists.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    FirstStage firstStage = postSnapshot.getValue(FirstStage.class);
                    //adding artist to the list
                    artists.add(firstStage);
                }

                //creating adapter
                ArtistList artistAdapter = new ArtistList(FirstwarningActivity.this, artists);
                //attaching adapter to the listview
                listViewArtists.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDeleteDialog(final String artistId, String artistName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName =  dialogView.findViewById(R.id.editTextName);
        final Spinner spinnerGenre =  dialogView.findViewById(R.id.spinnerGenres);
        final EditText editTextHrs = dialogView.findViewById(R.id.editTextHrs);
        final Button buttonUpdate =  dialogView.findViewById(R.id.buttonUpdateArtist);
        final Button buttonDelete =  dialogView.findViewById(R.id.buttonDeleteArtist);

        dialogBuilder.setTitle(artistName);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String genre = spinnerGenre.getSelectedItem().toString();
                String hrs  = editTextHrs.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateArtist(artistId, name,genre,hrs);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteArtist(artistId);
                b.dismiss();
            }
        });
    }


    private boolean updateArtist(String id, String name, String genre, String hrs) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("artists").child(id);

        //updating artist
        FirstStage firstStage = new FirstStage(id,name,genre,hrs);
        dR.setValue(firstStage);
        Toast.makeText(getApplicationContext(), "Student Updated", Toast.LENGTH_LONG).show();
        return true;
    }


    private boolean deleteArtist(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("artists").child(id);

        //removing artist
        dR.removeValue();

        //getting the tracks reference for the specified artist
        DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        //removing all tracks
        drTracks.removeValue();
        Toast.makeText(getApplicationContext(), "Student Deleted", Toast.LENGTH_LONG).show();

        return true;
    }
}

