package com.example.covihelp;

// import statements
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LoginSignUp extends AppCompatActivity {

    // register and login field objects
    private CardView register;
    private CardView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);

        // linking the objects with there views in UI/xml
        register = findViewById(R.id.registerCard);
        login = findViewById(R.id.loginCard);

        // setting up action that happens when user clicks on the register card
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginSignUp.this, Register.class);
                startActivity(intent);

            }
        });

        // setting up action that happens when user clicks on the login card
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginSignUp.this,Login.class);
                startActivity(intent);

            }
        });

    }

}