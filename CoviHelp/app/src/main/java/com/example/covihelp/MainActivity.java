package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button addHelpInfo,helpTheNeedy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addHelpInfo = findViewById(R.id.AddHelpButton);
        helpTheNeedy = findViewById(R.id.HelpAnyOneButton);

        addHelpInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddHelpInfo.class);
                startActivity(intent);
            }
        });

        helpTheNeedy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginSignUp.class);
                startActivity(intent);
            }
        });

    }
}