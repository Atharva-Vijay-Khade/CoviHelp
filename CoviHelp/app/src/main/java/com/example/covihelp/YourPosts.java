package com.example.covihelp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YourPosts extends AppCompatActivity {

    private ListView yourPostList;
    private String currentUserEmail;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_posts);

        yourPostList = findViewById(R.id.yourPostList);
        currentUserEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        progressBar = findViewById(R.id.yourPost_progress);
        ArrayList<CoviHelpData> coviHelpData = new ArrayList<>();

        progressBar.setVisibility(View.VISIBLE);

        class MyNoDataAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {

                view = getLayoutInflater().inflate(R.layout.your_post_no_data,viewGroup,false);
                return view;

            }
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
                view = getLayoutInflater().inflate(R.layout.your_post_list_item,viewGroup,false);

                TextView helpDescription = view.findViewById(R.id.help_description);
                TextView address = view.findViewById(R.id.address_);
                TextView email = view.findViewById(R.id.email_);
                TextView mobileNumber = view.findViewById(R.id.mobileNumber);

                helpDescription.setText(coviHelpData.get(position).getHelpDescription().toString());
                address.setText(coviHelpData.get(position).getAddress().toString());
                email.setText(coviHelpData.get(position).getEmail().toString());
                mobileNumber.setText(coviHelpData.get(position).getContact().toString());

                view.findViewById(R.id.deletePost).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        new AlertDialog.Builder(YourPosts.this)
                                .setIcon(android.R.drawable.ic_menu_delete)
                                .setTitle("Delete Alert")
                                .setMessage("Are you sure you want to delete this post?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        // Now deleting the data from dataBase
                                        String helpD = coviHelpData.get(position).getHelpDescription();
                                        String addrs = coviHelpData.get(position).getAddress();
                                        String em = coviHelpData.get(position).getEmail();
                                        String mobileNum = coviHelpData.get(position).getContact();
                                        String adder = coviHelpData.get(position).getAdder();
                                        // now we are getting the data fom the data base
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CoviHelp");
                                        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                boolean isDeleted = false;
                                                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                                                    for(DataSnapshot snapshot2 : snapshot1.getChildren()){
                                                        for(DataSnapshot snapshot3 : snapshot2.getChildren()) {
                                                            CoviHelpData data = new CoviHelpData();
                                                            data = snapshot3.getValue(CoviHelpData.class);
                                                            if((data!=null)&&
                                                                    (data.getAddress().equals(addrs))&&
                                                                    (data.getAdder().equals(adder))&&
                                                                    (data.getContact().equals(mobileNum))&&
                                                                    (data.getEmail().equals(em))&&
                                                                    (data.getHelpDescription().equals(helpD)))
                                                            {
                                                                DatabaseReference post = snapshot3.getRef();
                                                                post.removeValue();
                                                                progressBar.setVisibility(View.INVISIBLE);
                                                                Toast.makeText(YourPosts.this, "Post deleted successfully", Toast.LENGTH_SHORT).show();
                                                                isDeleted = true;
                                                                break;
                                                            }
                                                        }
                                                        if(isDeleted)
                                                            break;
                                                    }
                                                    if(isDeleted)
                                                        break;
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(YourPosts.this, "Error while deleting :(", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.INVISIBLE);
                                            }
                                        });
                                        coviHelpData.remove(position);
                                        MyAdapter adapterTemp = new MyAdapter();
                                        yourPostList.setAdapter(adapterTemp);
                                        if(yourPostList.getAdapter().getCount()==0) {

                                            MyNoDataAdapter myNoDataAdapter = new MyNoDataAdapter();
                                            yourPostList.setAdapter(myNoDataAdapter);

                                        }
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();
                    }
                });

                return view;

            }

        }


        MyAdapter adapter = new MyAdapter();
        yourPostList.setAdapter(adapter);

        if(yourPostList.getAdapter().getCount()==0) {

            MyNoDataAdapter myNoDataAdapter = new MyNoDataAdapter();
            yourPostList.setAdapter(myNoDataAdapter);

        }

        // now we are getting the data fom the data base
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CoviHelp");

        // we use addValueEventListener to read data and listen for changes
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // snapshot represent the current CoviHelpData copy in the dataBase for the selected state and city
                coviHelpData.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    for(DataSnapshot snapshot2 : snapshot1.getChildren()){
                        for(DataSnapshot snapshot3 : snapshot2.getChildren()) {
                            CoviHelpData data = new CoviHelpData();
                            data = snapshot3.getValue(CoviHelpData.class);
                            if((data.getAdder()!=null) && data!=null && (data.getAdder().equals(currentUserEmail)))
                                coviHelpData.add(data);
                        }
                    }
                }

                MyAdapter adapter = new MyAdapter();
                yourPostList.setAdapter(adapter);

                if(yourPostList.getAdapter().getCount()==0) {

                    MyNoDataAdapter myNoDataAdapter = new MyNoDataAdapter();
                    yourPostList.setAdapter(myNoDataAdapter);

                }
//                adapter.notifyDataSetChanged();
//                 setting the progress bar as invisible after as data fetch is completed
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(YourPosts.this, "No Data Available : (", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }

        });

    }

}