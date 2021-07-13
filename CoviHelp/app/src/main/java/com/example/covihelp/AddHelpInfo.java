package com.example.covihelp;         // this is important to give unique id for the app on playstore

// import statements
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
// AdaptersViews are used to fill data in drop down lists as given by spinners UI components
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
// FirebaseDatabase is the used to access the realtime database in firebase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
// HashMap is the dataStructure in java used to store key:value pair, each key is unique
// this HashMap object is directly passed in database to store the user information
import java.util.HashMap;

//_________________________________________________________________________________________________________________________________________________

// Purpose of the class => take the help data from the user push it into firebase realtime database

public class AddHelpInfo extends AppCompatActivity {

    // objects to manage spinners
    private Spinner state_spinner;
    private Spinner district_spinner;
    private ArrayAdapter state_adapter;
    private ArrayAdapter district_adapter;

    // objects to manage edit texts and get the input information
    private EditText help_description,address,email,contact;
    private String help_description_string,address_string,email_string,contact_string,state_string,district_string,Adder;
    private Button submitButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // this is the method that starts at first when the screen is loaded
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_help_info);

        // binding the field objects with their UI components
        state_spinner = findViewById(R.id.add_help_stateSpinner);
        district_spinner = findViewById(R.id.add_help_districtSpinner);
        help_description = findViewById(R.id.helpInfoText);
        address = findViewById(R.id.addressText);
        email = findViewById(R.id.emailIDText);
        contact = findViewById(R.id.contactNumberText);
        submitButton = findViewById(R.id.submitButton);
        progressBar = findViewById(R.id.add_info_progress);

        // setting up the state adapter
        // filling the state adapter with the resources
        state_adapter = state_adapter.createFromResource(this,R.array.array_indian_states,R.layout.add_help_spinner);
        // setting the dropdown layout
        state_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // now setting up the spinner with the adapter
        state_spinner.setAdapter(state_adapter);

        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                // now binding the district spinner with it's UI component
                district_spinner = findViewById(R.id.add_help_districtSpinner);

                // now selecting district from the selected state
                String selected_state = state_spinner.getSelectedItem().toString();

                switch (selected_state) {

                    case "Select Your State":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_default_districts, R.layout.add_help_spinner);
                        break;
                    case "Andhra Pradesh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_andhra_pradesh_districts, R.layout.add_help_spinner);
                        break;
                    case "Arunachal Pradesh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_arunachal_pradesh_districts, R.layout.add_help_spinner);
                        break;
                    case "Assam":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_assam_districts, R.layout.add_help_spinner);
                        break;
                    case "Bihar":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_bihar_districts, R.layout.add_help_spinner);
                        break;
                    case "Chhattisgarh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_chhattisgarh_districts, R.layout.add_help_spinner);
                        break;
                    case "Goa":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_goa_districts, R.layout.add_help_spinner);
                        break;
                    case "Gujarat":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_gujarat_districts, R.layout.add_help_spinner);
                        break;
                    case "Haryana":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_haryana_districts, R.layout.add_help_spinner);
                        break;
                    case "Himachal Pradesh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_himachal_pradesh_districts, R.layout.add_help_spinner);
                        break;
                    case "Jammu and Kashmir":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_jammu_kashmir_districts, R.layout.add_help_spinner);
                        break;
                    case "Jharkhand":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_jharkhand_districts, R.layout.add_help_spinner);
                        break;
                    case "Karnataka":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_karnataka_districts, R.layout.add_help_spinner);
                        break;
                    case "Kerala":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_kerala_districts, R.layout.add_help_spinner);
                        break;
                    case "Madhya Pradesh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_madhya_pradesh_districts, R.layout.add_help_spinner);
                        break;
                    case "Maharashtra":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_maharashtra_districts, R.layout.add_help_spinner);
                        break;
                    case "Manipur":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_manipur_districts, R.layout.add_help_spinner);
                        break;
                    case "Meghalaya":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_meghalaya_districts, R.layout.add_help_spinner);
                        break;
                    case "Mizoram":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_mizoram_districts, R.layout.add_help_spinner);
                        break;
                    case "Nagaland":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_nagaland_districts, R.layout.add_help_spinner);
                        break;
                    case "Orissa":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_odisha_districts, R.layout.add_help_spinner);
                        break;
                    case "Punjab":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_punjab_districts, R.layout.add_help_spinner);
                        break;
                    case "Rajasthan":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_rajasthan_districts, R.layout.add_help_spinner);
                        break;
                    case "Sikkim":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_sikkim_districts, R.layout.add_help_spinner);
                        break;
                    case "Tamil Nadu":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_tamil_nadu_districts, R.layout.add_help_spinner);
                        break;
                    case "Telangana":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_telangana_districts, R.layout.add_help_spinner);
                        break;
                    case "Tripura":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_tripura_districts, R.layout.add_help_spinner);
                        break;
                    case "Uttar Pradesh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_uttar_pradesh_districts, R.layout.add_help_spinner);
                        break;
                    case "Uttarakhand":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_uttarakhand_districts, R.layout.add_help_spinner);
                        break;
                    case "West Bengal":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_west_bengal_districts, R.layout.state_spinner_layout);
                        break;
                    case "Andaman and Nicobar Islands":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_andaman_nicobar_districts, R.layout.add_help_spinner);
                        break;
                    case "Chandigarh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_chandigarh_districts, R.layout.add_help_spinner);
                        break;
                    case "Dadra and Nagar Haveli":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_dadra_nagar_haveli_districts, R.layout.add_help_spinner);
                        break;
                    case "Daman and Diu":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_daman_diu_districts, R.layout.add_help_spinner);
                        break;
                    case "Delhi":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_delhi_districts, R.layout.add_help_spinner);
                        break;
                    case "Lakshadweep":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_lakshadweep_districts, R.layout.add_help_spinner);
                        break;
                    case "Ladakh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_ladakh_districts, R.layout.add_help_spinner);
                        break;
                    case "Puducherry":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_puducherry_districts, R.layout.add_help_spinner);
                        break;
                    default:
                        break;

                }

                // setting up the dropdown layout for the district spinner
                district_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // filling the spinner with the adapter
                district_spinner.setAdapter(district_adapter);


                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // checking for empty string inputs
                        // getting the data from the input fields
                        help_description_string = help_description.getText().toString().trim();
                        address_string = address.getText().toString().trim();
                        email_string = email.getText().toString().trim();
                        contact_string = contact.getText().toString().trim();
                        state_string = state_spinner.getSelectedItem().toString();
                        district_string = district_spinner.getSelectedItem().toString();

                        if(help_description_string.isEmpty()) {

                            help_description.setError("help description is required");
                            help_description.requestFocus();

                        }
                        else if(address_string.isEmpty()) {

                            address.setError("address required");
                            address.requestFocus();

                        }
                        else if(email_string.isEmpty()) {

                            email.setError("email is required");
                            email.requestFocus();

                        }
                        else if(contact_string.isEmpty()) {

                            contact.setError("contact is required");
                            contact.requestFocus();

                        }
                        else if(state_string.equals("Select Your State")) {

                            Toast.makeText(AddHelpInfo.this, "Please Select State", Toast.LENGTH_SHORT).show();
                            state_spinner.requestFocus();

                        }
                        else if(district_string.equals("Select Your District")) {

                            Toast.makeText(AddHelpInfo.this, "Please Select District", Toast.LENGTH_SHORT).show();
                            district_spinner.requestFocus();

                        }
                        else if(!Patterns.EMAIL_ADDRESS.matcher(email_string).matches()) {

                            email.setError("Please Enter Valid Email");
                            email.requestFocus();

                        }
                        else if(contact_string.length()!=10) {

                            contact.setError("Please Enter Valid Mobile Number");
                            contact.requestFocus();

                        }
                        else {

                            // Now adding data to DataBase FireBase

                            progressBar.setVisibility(View.VISIBLE);

                            // Using hashmap to insert data into the data base
                            HashMap<String,Object> helpData = new HashMap<>();
                            helpData.put("HelpDescription",help_description_string);
                            helpData.put("Address",address_string);
                            helpData.put("Email",email_string);
                            helpData.put("Contact",contact_string);
                            helpData.put("Adder", FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());

                            // now adding the hashmap into the data base

                            FirebaseDatabase.getInstance().getReference().child("CoviHelp").child(state_string).child(district_string).push().updateChildren(helpData);

                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(AddHelpInfo.this, "Data Added Successful, Thanks : )", Toast.LENGTH_SHORT).show();

                            help_description.setText("");
                            address.setText("");
                            email.setText("");
                            contact.setText("");

                        }

                    }

                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }

        });



    }


}