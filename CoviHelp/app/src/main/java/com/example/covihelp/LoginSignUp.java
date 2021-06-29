package com.example.covihelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginSignUp extends AppCompatActivity {

    private CardView register;
    private CardView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);

        register = findViewById(R.id.registerCard);
        login = findViewById(R.id.loginCard);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginSignUp.this, Register.class);
                startActivity(intent);

            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginSignUp.this,Login.class);
                startActivity(intent);

            }
        });

    }


}