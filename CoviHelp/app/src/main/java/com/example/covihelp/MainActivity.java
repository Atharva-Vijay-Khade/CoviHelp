package com.example.covihelp;

// import statements
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    // button, and image field objects

    private Button addHelpInfo,helpTheNeedy;
    private  ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // this is the first function which runs when activity is started
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding the objects with there views so that we can preform tasks on them
        addHelpInfo = findViewById(R.id.AddHelpButton);
        helpTheNeedy = findViewById(R.id.HelpAnyOneButton);
        logout = findViewById(R.id.logout);

        // setting up logout action, when user hits on the logout button
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // logging out the user and redirecting to login activity
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();

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

    }

}