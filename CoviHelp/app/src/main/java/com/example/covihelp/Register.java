package com.example.covihelp;                                     // com.example.app_name is actually the package name and it also serves as an APP ID for play store reference for future version updates


// import statements


// NonNull is used to implement Null safety, NonNull() will tell that a
// function can't return a null value which is a null safety measure, else there will
// be runtime errors in applications
import androidx.annotation.NonNull;
// AppCompatActivity => it's Base class for activities that wish to use some of the newer platform features on older Android devices -> features
// like multiple themes and navigation related features etc
import androidx.appcompat.app.AppCompatActivity;
// Intent in android => An intent is to perform an action on the screen. It is mostly used to start activity, send message between two activities.
import android.content.Intent;
// Android Bundle => Android Bundle is used to pass data between activities.
import android.os.Bundle;
// Patterns class in java is android is used to to check common patterns and validate them, such as email patterns etc
import android.util.Patterns;
// View in android => Every UI component in android is a descendant of the View class, it's important to make UI components on screen of the application
import android.view.View;
// Buttons EditTexts Progress Bars are all UI components which are descendants of the View class used to implement UI on the screen
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
// Toast class can be considered as a widget which is used when we want to show a popup message on the screen which vanishes after short or long time
import android.widget.Toast;
// Tasks are used to implement ways by which we can deal with the tasks/operations on firebase that are of unknown durations
// OnCompleteListeners are mainly used to depict the work to be performed after certain task is completed
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
// AuthResult is used to get information on currently logged in user
import com.google.firebase.auth.AuthResult;
// FirebaseAuth provides multiple options/operations to implement user authentication such as login/signUp etc
import com.google.firebase.auth.FirebaseAuth;


//_______________________________________________________________________________________________________________________________________________________________________


// Purpose of this class => Register the user in the CoviHelp system if not already registered


public class Register extends AppCompatActivity {

    // private variables for auth user,email text,password text,register button
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button register;
    private ProgressBar register_progress_bar;


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

        setContentView(R.layout.activity_register);

        // here we are getting the current instance of the firebase data base
        // so that we can perform tasks on the database
        mAuth = FirebaseAuth.getInstance();

        // connect variables with there views
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register_button);
        register_progress_bar = findViewById(R.id.register_progress_bar);

        // not showing progress to user now
        register_progress_bar.setVisibility(View.GONE);

        // on register click action
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // here we validate the user has inputted all the data
                // we can fetch the data entered by user in the edit text as follows
                String email_string = email.getText().toString().trim();       // trim eliminates any leading or trailing spaces
                String password_string = password.getText().toString().trim();

                // checking for empty email or password
                if(email_string.isEmpty()==true) {

                    // .setError is the method to tell user that
                    // this input went wrong and it's needs correction
                    // .requestFocus will take the focus to that UI element
                    email.setError("Email is required");
                    email.requestFocus();
                    return;

                }

                if(password_string.isEmpty()==true) {

                    password.setError("Password is required");
                    password.requestFocus();
                    return;

                }

                // checking for incorrect email pattern using Patterns class
                if(!Patterns.EMAIL_ADDRESS.matcher(email_string).matches()) {

                    email.setError("Please enter valid email address");
                    email.requestFocus();
                    return;

                }

                // checking length of password as firebase does'nt accept passwords of length lower than 6
                if(password_string.length()<6) {

                    password.setError("Password length should be at least 6 characters");
                    password.requestFocus();
                    return;

                }

                // now since we will authenticate the user we set the progress bar to visible here
                register_progress_bar.setVisibility(View.VISIBLE);

                // checking if user already has an account on firebase
                // if user already exist then mAuth.getCurrentUser() won't be NULL
                if(mAuth.getCurrentUser()!=null) {

                    // directly send to main activity
                    Toast.makeText(Register.this, "It looks like your account already exist," +
                            "if not there might be a network issue, " +
                            "please checkout logging in," +
                            " if your account already exist", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Register.this,Login.class));
                    // setting the progress bar as invisible as authentication is completed
                    register_progress_bar.setVisibility(View.GONE);
                    finish();   // this finish quits any ongoing work that was the activity was doing
                    return;

                }


                // registering the user in firebase with email and password
                mAuth.createUserWithEmailAndPassword(email_string,password_string).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    // NonNull gives a null safety feature, task can't be null, else it gives runtime errors
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // if the user is created successfully, represented by task.isSuccessful() then
                        // we direct to the main activity
                        if(task.isSuccessful()) {
                            //successfully created a user
                            Toast.makeText(Register.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,MainActivity.class));
                        }
                        else {
                            Toast.makeText(Register.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                        // setting the progress bar as invisible on UI here
                        register_progress_bar.setVisibility(View.GONE);

                    }
                });

            }

        });

    }

}