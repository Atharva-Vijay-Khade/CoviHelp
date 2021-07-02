package com.example.covihelp;         // this is important to give unique id for the app on playstore

// import statements
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


// class for AddHelpInfo implementation
public class AddHelpInfo extends AppCompatActivity {

    private Spinner state_spinner;
    private Spinner district_spinner;
    private ArrayAdapter state_adapter;
    private ArrayAdapter district_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // this is the method that starts at first when the screen is loaded
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_help_info);

        // binding the field objects with their UI components
        state_spinner = findViewById(R.id.add_help_stateSpinner);

        // setting up the state adapter
        // filling the state adapter with the resources
        state_adapter = state_adapter.createFromResource(this,R.array.array_indian_states,R.layout.state_spinner_layout);
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
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_default_districts, R.layout.state_spinner_layout);
                        break;
                    case "Andhra Pradesh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_andhra_pradesh_districts, R.layout.state_spinner_layout);
                        break;
                    case "Arunachal Pradesh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_arunachal_pradesh_districts, R.layout.state_spinner_layout);
                        break;
                    case "Assam":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_assam_districts, R.layout.state_spinner_layout);
                        break;
                    case "Bihar":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_bihar_districts, R.layout.state_spinner_layout);
                        break;
                    case "Chhattisgarh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_chhattisgarh_districts, R.layout.state_spinner_layout);
                        break;
                    case "Goa":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_goa_districts, R.layout.state_spinner_layout);
                        break;
                    case "Gujarat":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_gujarat_districts, R.layout.state_spinner_layout);
                        break;
                    case "Haryana":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_haryana_districts, R.layout.state_spinner_layout);
                        break;
                    case "Himachal Pradesh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_himachal_pradesh_districts, R.layout.state_spinner_layout);
                        break;
                    case "Jammu and Kashmir":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_jammu_kashmir_districts, R.layout.state_spinner_layout);
                        break;
                    case "Jharkhand":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_jharkhand_districts, R.layout.state_spinner_layout);
                        break;
                    case "Karnataka":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_karnataka_districts, R.layout.state_spinner_layout);
                        break;
                    case "Kerala":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_kerala_districts, R.layout.state_spinner_layout);
                        break;
                    case "Madhya Pradesh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_madhya_pradesh_districts, R.layout.state_spinner_layout);
                        break;
                    case "Maharashtra":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_maharashtra_districts, R.layout.state_spinner_layout);
                        break;
                    case "Manipur":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_manipur_districts, R.layout.state_spinner_layout);
                        break;
                    case "Meghalaya":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_meghalaya_districts, R.layout.state_spinner_layout);
                        break;
                    case "Mizoram":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_mizoram_districts, R.layout.state_spinner_layout);
                        break;
                    case "Nagaland":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_nagaland_districts, R.layout.state_spinner_layout);
                        break;
                    case "Orissa":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_odisha_districts, R.layout.state_spinner_layout);
                        break;
                    case "Punjab":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_punjab_districts, R.layout.state_spinner_layout);
                        break;
                    case "Rajasthan":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_rajasthan_districts, R.layout.state_spinner_layout);
                        break;
                    case "Sikkim":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_sikkim_districts, R.layout.state_spinner_layout);
                        break;
                    case "Tamil Nadu":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_tamil_nadu_districts, R.layout.state_spinner_layout);
                        break;
                    case "Telangana":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_telangana_districts, R.layout.state_spinner_layout);
                        break;
                    case "Tripura":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_tripura_districts, R.layout.state_spinner_layout);
                        break;
                    case "Uttar Pradesh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_uttar_pradesh_districts, R.layout.state_spinner_layout);
                        break;
                    case "Uttarakhand":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_uttarakhand_districts, R.layout.state_spinner_layout);
                        break;
                    case "West Bengal":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_west_bengal_districts, R.layout.state_spinner_layout);
                        break;
                    case "Andaman and Nicobar Islands":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_andaman_nicobar_districts, R.layout.state_spinner_layout);
                        break;
                    case "Chandigarh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_chandigarh_districts, R.layout.state_spinner_layout);
                        break;
                    case "Dadra and Nagar Haveli":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_dadra_nagar_haveli_districts, R.layout.state_spinner_layout);
                        break;
                    case "Daman and Diu":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_daman_diu_districts, R.layout.state_spinner_layout);
                        break;
                    case "Delhi":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_delhi_districts, R.layout.state_spinner_layout);
                        break;
                    case "Lakshadweep":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_lakshadweep_districts, R.layout.state_spinner_layout);
                        break;
                    case "Ladakh":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_ladakh_districts, R.layout.state_spinner_layout);
                        break;
                    case "Puducherry":
                        district_adapter = district_adapter.createFromResource(view.getContext(), R.array.array_puducherry_districts, R.layout.state_spinner_layout);
                        break;
                    default:
                        break;

                }

                // setting up the dropdown layout for the district spinner
                district_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // filling the spinner with the adapter
                district_spinner.setAdapter(district_adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });



    }


}