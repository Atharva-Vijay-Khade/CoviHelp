package com.example.covihelp;   // this is the package name where this .java file is located

// import statements
// AppCompatActivity => it's Base class for activities that wish to use some of the newer platform features on older Android devices -> features
// like multiple themes and navigation related features etc
import androidx.appcompat.app.AppCompatActivity;
// CardView class is essential to implement cards UI in android application
import androidx.cardview.widget.CardView;
// Intent in android => An intent is to perform an action on the screen. It is mostly used to start activity, send message between two activities.
import android.content.Intent;
// Android Bundle => Android Bundle is used to pass data between activities.
import android.os.Bundle;
// View in android => Every UI component in android is a descendant of the View class, it's important to make UI components on screen of the application
import android.view.View;
// Activities: It provides a window in which the app draws its UI. This window fills the screen, generally a activity is a screen


//________________________________________________________________________________________________________________________________________________________

// Purpose of this class -> ask user to register or login

// This class implements the first page where user will have an option to register if not registered earlier
// or login if registered earlier

public class LoginSignUp extends AppCompatActivity {

    // register and login field objects
    private CardView register;
    private CardView login;

    @Override
    // this is the onCreate function that runs first whenever this activity starts
    //The savedInstanceState is a reference to a Bundle object that is passed into
    // the onCreate method of every Android Activity.
    // Activities have the ability, under special circumstances, to restore
    // themselves to a previous state using the data stored in this bundle
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // setContentView is the function that links the UI components with this .java class
        // UI components are represented in .xml file and decides the complete UI of the screen
        // this R.layout.activity_login_sign_up means link this .java class with activity_login_sign_up xml component
        // which is in Resources [R], in layout folder
        setContentView(R.layout.activity_login_sign_up);


        // linking the objects with their views in UI/xml
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

//___________________________________________________________________________________________________________________________________________________________________