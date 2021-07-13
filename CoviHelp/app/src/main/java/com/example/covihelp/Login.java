package com.example.covihelp;          // com.example.app_name also serves as an APP ID for play store reference for future version updates

// import statements
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//_________________________________________________________________________________________________________________________________________________________



// Purpose of the class => The class will authenticate the user for login and check the email and password are valid or not
// if valid then it will take the user into the coviHelp system

public class Login extends AppCompatActivity {

    // fields used for login
    private EditText email;
    private EditText password;
    private ProgressBar progress_login;
    private Button login_button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // whenever this activity/screen will start then
        // this onCreate(); function is started
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // here we are getting the current instance of the firebase data base
        // so that we can perform tasks on the database
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        progress_login = findViewById(R.id.progress_login);
        login_button = findViewById(R.id.login_button);

        // setting the progress bar as invisible on the UI
        progress_login.setVisibility(View.GONE);


        // setting up login button action
        login_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // string fields for edit texts
                String email_string = email.getText().toString().trim();
                String password_string = password.getText().toString().trim();

                // checking if any field is empty
                if(email_string.isEmpty()) {

                    email.setError("Email is required");
                    email.requestFocus();
                    return;

                }

                if(password_string.isEmpty()) {

                    password.setError("Password is required");
                    password.requestFocus();
                    return;

                }

                // in firebase the length of password should be not less than 6
                if(password_string.length()<6) {

                    password.setError("Password length should be at least 6 characters");
                    return;

                }

                // now verifying the user

                // now since we are in authentication process we will set the progress bar as visible
                progress_login.setVisibility(View.VISIBLE);

                // authenticating the user
                mAuth.signInWithEmailAndPassword(email_string,password_string).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // successful login of user
                        if(task.isSuccessful()) {

                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,MainActivity.class));

                        }
                        else {

                            Toast.makeText(Login.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }

                        progress_login.setVisibility(View.GONE);

                    }

                });

            }

        });

    }

}