package sk.com.account;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import Beam.Users;
import Database.DataBaseHandler;


public class AddViewFragment extends Fragment {
    EditText userName , amt ;
    Users obj;
    DataBaseHandler db;
    FragmentManager mfm ;
    FragmentTransaction mft;

    public AddViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View mvieew = inflater.inflate(R.layout.activity_main, container, false);
        db=new DataBaseHandler(getContext());
        userName = (EditText) mvieew.findViewById(R.id.name);
        amt = (EditText) mvieew.findViewById(R.id.amt);
        Button add = (Button) mvieew.findViewById(R.id.add_bt);
        Button viewss = (Button) mvieew.findViewById(R.id.view_bt_main);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = userName.getText().toString().trim();
                if (name.length() !=0 && amt.length() != 0) {

                    int amount = Integer.parseInt(amt.getText().toString());
                    int balanceAmount = amount;
                    obj = new Users(name, amount, balanceAmount);
                    db.adduser(obj);
                    userName.setText("");
                    amt.setText("");
                    Snackbar.make(mvieew.findViewById(R.id.add_layout), name.toUpperCase() + " Successfully added", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    Snackbar.make(mvieew.findViewById(R.id.add_layout),"Enter both fields", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        viewss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfm = getFragmentManager();
                mft = mfm.beginTransaction();
                mft.replace(R.id.main_frame, new PaymentView());
                mft.commit();
               /* Intent intt = new Intent(MainActivity.this, PaymentView.class);
                startActivity(intt);*/
            }
        });
        // Inflate the layout for this fragment
        return mvieew;
    }

}
