package com.example.android.somow;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.android.somow.MainActivity;
import com.example.android.somow.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DASHBOARD extends AppCompatActivity {
    ListView listView;
    private DatabaseReference mDatabaseRef;
    private ChildEventListener mchild;
    private StorageReference mStorageRef;


    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String DATABASE_PATH = "images";
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);
        listView = (ListView) findViewById(R.id.listview);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        final ArrayList<ImageUpload> imgs=new ArrayList<>();



        mDatabaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.v("dashboard","here  also  :P");
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                Log.v("dashboard","here 78  :P");
                for(DataSnapshot child :children){
                    Iterable<DataSnapshot> ch = child.getChildren();

                    Log.v("dashboard","here  90 :P");
                    for(DataSnapshot q :ch) {

                        Log.v("dashboard","here  :P");
                        // String mUuid = q.getKey();
                        // if (mUuid.equals(uuid)) {             static uuid nhi h
                        //  String mBidName = q.child("name").getValue(String.class);
                        String mBidDes = q.child("description").getValue(String.class);
                        String timelimit = q.child("time").getValue(String.class);
                        String mCurrentPrice =q.child("price").getValue(String.class);
                        // String mStatus = q.child("status").getValue(String.class);
                        Log.v("dashboard",mBidDes+"   "+mCurrentPrice+"   "+timelimit+"  :P");
                        ImageUpload temp = new ImageUpload(mBidDes,null,timelimit,mCurrentPrice);//uri or picture
                        imgs.add(temp);

                        //myBidAdapter.notifyItemInserted(mBidsList.size()-1);
                        // }
                    }
                }
                ImageAdapter adapter = new ImageAdapter(DASHBOARD.this,imgs);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        drawer = findViewById(R.id.drawer);

        NavigationView navigationView = findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        //  menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawer.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        int id = menuItem.getItemId();
                        if (id == R.id.action_new) {
                            Intent t=new Intent(DASHBOARD.this,newauctions.class);
                            startActivity(t);
                        }
                        if (id == R.id.action_mine) {
                            Intent t=new Intent(DASHBOARD.this,myauctions.class);
                            startActivity(t);
                        }
                        if (id == R.id.history) {
                            Intent t=new Intent(DASHBOARD.this,history.class);
                            startActivity(t);
                        }

                        return true;
                    }
                });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_tab,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if(id==R.id.sign_out) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(DASHBOARD.this,MainActivity.class));

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}