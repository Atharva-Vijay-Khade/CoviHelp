package com.example.covihelp;

// import statements
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
// buttons and imageViews are UI component classes which are descendants of the View class
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
// in this activity FirebaseAuth used for signOut
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


//_____________________________________________________________________________________________________________________________________________________________________


public class MainActivity extends AppCompatActivity {


    // button, and image field objects
    private Button addHelpInfo,helpTheNeedy;
    private  ImageView logout;
    private  Button yourPostsButton;

    // setting up logout action, when user hits on the backbutton
    @Override
    public void onBackPressed(){
        // logging out the user and redirecting to login activity
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.ic_menu_delete)
                .setTitle("Logout")
                .setMessage("Are you sure you want to Logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this,Login.class));
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // this is the first function which runs when activity is started
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding the objects with there views so that we can preform tasks on them
        addHelpInfo = findViewById(R.id.AddHelpButton);
        helpTheNeedy = findViewById(R.id.HelpAnyOneButton);
        logout = findViewById(R.id.logout);
        yourPostsButton = findViewById(R.id.yourPostButton);



        // setting up logout action, when user hits on the logout button
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // logging out the user and redirecting to login activity
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_menu_delete)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to Logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(MainActivity.this,Login.class));
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }

        });

        // taking control to activity AddHelpInfo
        addHelpInfo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,AddHelpInfo.class);
                startActivity(intent);

            }

        });

        // taking control to activity HelpTheNeedy
        helpTheNeedy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,HelpTheNeedy.class);
                startActivity(intent);

            }

        });

        yourPostsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,YourPosts.class));
            }
        });

    }

}