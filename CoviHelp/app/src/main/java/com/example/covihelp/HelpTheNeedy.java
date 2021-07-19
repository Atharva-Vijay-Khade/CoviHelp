package com.example.covihelp;                          // com.example.app_name gives unique id to our application when it's on playstore

// import statements

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
// Base adapter is used to work with custom adapter which will support cardViews
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
// spinner is used to create dropdown lists in UI
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
// FireBase imports to support data fetch feature
// and error checking and iterating on the firebase data
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
// ArrayList is DataStructure in java
import java.util.ArrayList;


//_________________________________________________________________________________________________________________________________________________


// Purpose of the class => implement search based on state and city and then display the data to user

public class HelpTheNeedy extends AppCompatActivity {

    // setting up state and district spinner objects
    private Spinner state_spinner;
    private Spinner district_spinner;
    // now we have to use arrayAdapter to set contents in the spinner, it's acts as a bridge
    // between the data source and the UI component, and is used to populate the data from the
    // data source to the UI component
    private ArrayAdapter state_adapter,district_adapter;

    // search button and list view and progress bar fields
    private Button search;
    private ListView listView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // this function runs when this activity is loaded
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_the_needy);

        // binding the spinner object with the UI xml component
        state_spinner = findViewById(R.id.state_spinner);
        // binding the search button and the list view with their UI component
        search = findViewById(R.id.search);
        listView = findViewById(R.id.show_data_list);
        progressBar = findViewById(R.id.search_progress);

        // now initializing the arrayAdapter for the state
        state_adapter = state_adapter.createFromResource(this,R.array.array_indian_states,R.layout.state_spinner_layout);

        // now specifying the layout of the list when it appears
        state_adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);

        // now populating the data from the adapter to the spinner
        state_spinner.setAdapter(state_adapter);


        // now on the basis of the selected state we should select the district
        // and populate the data on the district spinner
        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                // first getting the selected state
                String selected_state = state_spinner.getSelectedItem().toString();
                district_spinner = findViewById(R.id.district_spinner);

                // now we will show the districts in the selected state
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

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // setting the progress bar as visible
                progressBar.setVisibility(View.VISIBLE);

                // arraylist to store the dataBase data of current state and district
                ArrayList<CoviHelpData> coviHelpData = new ArrayList<>();

                String state_string;
                String district_string;

                state_string = state_spinner.getSelectedItem().toString();
                district_string = district_spinner.getSelectedItem().toString();

                // check for error condition
                if(state_string.equals("Select Your State")) {
                    Toast.makeText(HelpTheNeedy.this, "Please select the state", Toast.LENGTH_SHORT).show();
                    state_spinner.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(district_string.equals("Select Your District")) {
                    Toast.makeText(HelpTheNeedy.this, "Please select the district", Toast.LENGTH_SHORT).show();
                    district_spinner.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                // implementing custom adapter { custom adapter is used to support CardViews }
                class MyAdapter extends BaseAdapter {

                    @Override
                    public int getCount() {
                        // getting count from information in data base
                        return coviHelpData.size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }

                    // ViewGroups are descendants of Views which provides a layout outside the View UI components
                    @Override
                    public View getView(int position, View view, ViewGroup viewGroup) {

                        // getLayoutInflater instantiates the xml UI component from the xml into it's view object
                        view = getLayoutInflater().inflate(R.layout.help_the_needy_data_list_item,viewGroup,false);

                        TextView helpDescription = view.findViewById(R.id.help_description);
                        TextView address = view.findViewById(R.id.address_);
                        TextView email = view.findViewById(R.id.email_);
                        TextView mobileNumber = view.findViewById(R.id.mobileNumber);

                        helpDescription.setText(coviHelpData.get(position).getHelpDescription().toString());
                        address.setText(coviHelpData.get(position).getAddress().toString());
                        email.setText(coviHelpData.get(position).getEmail().toString());
                        mobileNumber.setText(coviHelpData.get(position).getContact().toString());

                        return view;

                    }

                }

                MyAdapter adapter = new MyAdapter();
                listView.setAdapter(adapter);

                // now we are getting the data fom the data base
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CoviHelp").child(state_string).child(district_string);

                // we use addValueEventListener to read data and listen for changes
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        // snapshot represent the current CoviHelpData copy in the dataBase for the selected state and city
                        coviHelpData.clear();
                        for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                                CoviHelpData data = new CoviHelpData();
                                data = snapshot1.getValue(CoviHelpData.class);
                                coviHelpData.add(data);
                        }
                        if(coviHelpData.size()==0) {
                            Toast.makeText(HelpTheNeedy.this, "No Data Available for this location : (", Toast.LENGTH_SHORT).show();
                        }

                        adapter.notifyDataSetChanged();
                        // setting the progress bar as invisible after as data fetch is completed
                        progressBar.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(HelpTheNeedy.this, "No Data Available : (", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);

                    }

                });

            }

        });

    }

}