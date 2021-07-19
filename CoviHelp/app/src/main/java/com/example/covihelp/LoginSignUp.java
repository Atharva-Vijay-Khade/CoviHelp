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
    // Similar to JVM to run the java code, in android we have DVM -> Dalvik VM, which takes in the .class
    // file generated from java compiler and then the Dex compiler {has platform specific tools to compile}
    // in DVM generates the .dex file which is packaged by
    // Android Assets packaging tool { aapt } into an .apk file that is platform specific and machine neutrality is achieved

    // The savedInstanceState is a reference to a Bundle object that is passed into
    // the onCreate method of every Android Activity.

    protected void onCreate(Bundle savedInstanceState) {

        // this super.onCreate line tells the DVM to run the onCreate of parent class with the onCreate of
        // this class, because the parent class onCreate contains many more features which are required to run
        // the android application, and this creates a layer of abstraction
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
        // setOnClickListener is triggered when the user clicks on the corresponding view
        // this methods expects an object of anonymous class in java, here the anonymous class
        // is given by name OnClickListener which is the interface in View class, and for the class'
        // object we implement by Overriding the onClick present the interface, and internally the current
        // clicked View is passed to this onClick object,  and this object of anonymous class is
        // passed to setOnClickListener for further processing to implement the onClick functionality
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // this is intent object which is useful when we want to navigate from one activity to other activity
                // the object takes in the current context and the .class file where we want to navigate
                Intent intent = new Intent(LoginSignUp.this, Register.class);
                // we navigate when we say startActivity
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