package com.example.android.somow;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class newauctions extends AppCompatActivity {
    Button camera,gallery,ok; //ok=upload,gallery==chose
     Uri url;
    ImageView imageView;
    private Uri uri;
    StorageReference riversRef;
    EditText timelimit,bidprice,description;

    public static String STORAGE_PATH = "images/";
    public static String DATABASE_PATH = "images";

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newauctions);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(DATABASE_PATH);

        camera=findViewById(R.id.camera);

        gallery=findViewById(R.id.gallery);
        ok=findViewById(R.id.ok);
        imageView=findViewById(R.id.imageview);
        timelimit=findViewById(R.id.timelimit);
        bidprice=findViewById(R.id.bidprice);
        description=(EditText)findViewById(R.id.descrption);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "SELECT IMAGE"), 1);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if there is a file to upload
                if (uri != null) {
                    Toast.makeText(getApplicationContext()," Image is selected",Toast.LENGTH_SHORT).show();

                     riversRef = mStorageRef.child(STORAGE_PATH+System.currentTimeMillis()+"." +getImg(uri));
                     Log.v("","here !!!");
                    riversRef.putFile(uri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    Log.v("newacution","here also !!!");
                                   riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                           // Uri downloadUrl = uri;
                                            Log.v("newauction"," eithi !!!");

                                            url=uri;
                                           // Toast.makeText(getBaseContext(), "Upload success! URL - " + downloadUrl.toString() , Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    //reference
                                    ImageUpload imageupload = new ImageUpload(description.getText().toString(),url,timelimit.getText().toString(),bidprice.getText().toString());
                                    Toast.makeText(getApplicationContext(), "111 ", Toast.LENGTH_LONG).show();
                                    String uploadId =mDatabaseRef.push().getKey();
                                    Toast.makeText(getApplicationContext(), "222 ", Toast.LENGTH_LONG).show();

                                    mDatabaseRef.child(uploadId).setValue(imageupload);
                                    //if the upload is successful

                                    //and displaying a success toast
                                    Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                                    finish();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    //if the upload is not successfull

                                    //and displaying error message
                                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
                //if there is not any file
                else {
                    //you can display an error toast
                    Toast.makeText(getApplicationContext(),"Select Image",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public String getImg(Uri uri)
    {
        ContentResolver contentResolver= getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();


            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}