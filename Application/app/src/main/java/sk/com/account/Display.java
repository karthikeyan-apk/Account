package sk.com.account;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Beam.Users;
import Database.DataBaseHandler;


public class Display extends Fragment {


    DataBaseHandler db ;
    List<Users> userList;
    TextView list;
    List<String> name = new ArrayList<>();
    int i= 0 ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        list = (TextView) rootView.findViewById(R.id.listdispla);
        final AutoCompleteTextView dele_name = (AutoCompleteTextView) rootView.findViewById(R.id.del_name);
        Button dele = (Button) rootView.findViewById(R.id.delete);
        db = new DataBaseHandler(getContext());
        userList= db.getAllUserDetails();
        String display ="";
        for (Users cn : userList) {
            String log = "Name: "+cn.getName()+" Total Amount: " + cn.getTotalAmount() + " Balance Amount: " + cn.getBalanceAmount();
            display= display+"\n"+log;
            name.add(cn.getName());
            i++;

            // Writing Contacts to log
            Log.e("Name: ", log);
        }
        list.setText(display);

        //No of rows
        int no = db.numberOfRows();
        Log.e("No of Rows", String.valueOf(no));


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,name);
        dele_name.setAdapter(adapter);
        dele_name.setThreshold(0);

        dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dele_name.length() != 0) {
                    db.delete(dele_name.getText().toString());


                    db = new DataBaseHandler(getContext());
                    userList = db.getAllUserDetails();
                    String display = "";
                    for (Users cn : userList) {
                        String log = "Name: " + cn.getName() + " Total Amount: " + cn.getTotalAmount() + " Balance Amount: " + cn.getBalanceAmount();
                        display = display + "\n" + log;
                        // Writing Contacts to log
                       // Log.e("Name: ", log);
                    }


                    list.setText(display);
                    dele_name.setText("");
                }
                else {
                    Snackbar.make(rootView.findViewById(R.id.dele_lay), "Enter the Name to Delete", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
        return rootView;
    }

}
