/*package com.example.android.somow;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.android.somow.R;

public class DASHBOARD extends AppCompatActivity {

    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Shop");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    toolbar.setTitle("Shop");
                    return true;
                case R.id.navigation_gifts:
                    toolbar.setTitle("My Gifts");
                    return true;
                case R.id.navigation_cart:
                    toolbar.setTitle("Cart");
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    return true;
            }
            return false;
        }
    };
}
    }
}
*/
package com.example.android.somow;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.somow.MainActivity;
import com.example.android.somow.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DASHBOARD extends AppCompatActivity {
ListView listView;
    private DatabaseReference mDatabaseRef;
    private ChildEventListener mchild;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    /*   String DATABASE_PATH = "images";

        Log.v("DASHBOARD","ALL FINE :p");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);
        listView = (ListView) findViewById(R.id.listview);
        mStorageRef = FirebaseStorage.getInstance().getReference();


        final ArrayList<ImageUpload> imgs=new ArrayList<>();
        Log.v("DASHBOARD","ALL FINE TILL HERE :p");

       /* mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

               // Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                    Log.v("DASHBOARD","DATA RETRIEVED :p");
                    //ethu bilkul houni
            ImageUpload post = postSnapshot.getValue(ImageUpload.class);

                    Log.v("DASHBOARD","DATA SHOWN :p");
                    imgs.add(post);


                }
                ImageAdapter adapter= new ImageAdapter(getApplicationContext(),imgs);

                // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
                // {@link ListView} will  display list items for each {@link Word} in the list.
                listView.setAdapter(adapter);

                Log.v("DASHBOARD","DATA dISPLAYED :p");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG!!!", "Failed to read value.", databaseError.toException());

            }

        });
*/
        //Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
      /*  StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

        File localFile = null;
        try {
            localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        riversRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        Toast.makeText(getApplicationContext(), "passed ?in downloading", Toast.LENGTH_SHORT).show();

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                Toast.makeText(getApplicationContext(), "failed in downloading", Toast.LENGTH_SHORT).show();
                // ...
            }
        });

       if(mchild==null) {
           Log.v("DASHBOARD","in here :p");

           mchild = new ChildEventListener() {
                //  Log.v("DASHBOARD","over here :p");

               @Override
                public void onChildAdded( DataSnapshot dataSnapshot, String s) {
                   Log.v("DASHBOARD","here bro!! :p");

                   ImageUpload friendlyMessage = dataSnapshot.getValue(ImageUpload.class);
                   Log.v("DASHBOARD","DATA taken :p");

                   imgs.add(friendlyMessage);
                   Log.v("DASHBOARD","DATA dISPLAYED :p");

                   ImageAdapter adapter= new ImageAdapter(getApplicationContext(),imgs);
                   Log.v("DASHBOARD","DATA shown :p");


                   // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
                    // {@link ListView} will display list items for each {@link Word} in the list.
                    listView.setAdapter(adapter);


                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            mDatabaseRef.addChildEventListener(mchild);
        }
*/


        TextView buttons5 = (TextView) findViewById(R.id.button5);
        buttons5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(DASHBOARD.this,myauctions.class);
                startActivity(k);
            }
        });
        TextView buttons6 = (TextView) findViewById(R.id.button6);
        buttons6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(DASHBOARD.this,newauctions.class);
                startActivity(k);
            }
        });



    }
}