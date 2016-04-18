package sk.com.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by karthikeyan on 08-Apr-16.
 */
public class PaymentView extends Fragment {
    EditText paynow;
    AutoCompleteTextView name;
    TextView  totlAmount,balanceAmount,listdispla;
    Button view,update,but_display;
    Users userObj;
    DataBaseHandler db ;
    int amtPayNow , tBalanceAmount;

    List<Users> userList;
    List<String> userNameList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.pay_view, container, false);
        name = (AutoCompleteTextView) rootView.findViewById(R.id.pay_name);
        totlAmount = (TextView) rootView.findViewById(R.id.pay_bal);
        balanceAmount = (TextView) rootView.findViewById(R.id.topay);
        paynow = (EditText) rootView.findViewById(R.id.amtpaynow);
        view = (Button) rootView.findViewById(R.id.view_bt);
        update = (Button) rootView.findViewById(R.id.update);


        db = new DataBaseHandler(getContext());
        userList= db.getAllUserDetails();
        for (Users cn : userList) {
            userNameList.add(cn.getName());

        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,userNameList);
        name.setAdapter(adapter);
        name.setThreshold(0);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uName = name.getText().toString().trim();
                if (uName.length() != 0) {
                    db = new DataBaseHandler(getContext());
                    userObj = db.getValues(uName);
                    totlAmount.setText(String.valueOf(userObj.getTotalAmount()));
                    balanceAmount.setText(String.valueOf(userObj.getBalanceAmount()));
                    tBalanceAmount = userObj.getBalanceAmount();

                }
                else {
                    Snackbar.make(rootView.findViewById(R.id.view_layout),"Enter the Name field", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = name.getText().toString().trim();
                if (uName.length() != 0 && paynow.length() != 0) {
                    amtPayNow = Integer.parseInt(paynow.getText().toString());
                   // balanceAmount.setText(String.valueOf(userObj.getBalanceAmount()));
                    if (tBalanceAmount >= amtPayNow) {
                        tBalanceAmount = tBalanceAmount - amtPayNow;
                        userObj = db.updateValues(uName, tBalanceAmount);
                        name.setText(userObj.getName());
                        totlAmount.setText(String.valueOf(userObj.getTotalAmount()));
                        balanceAmount.setText(String.valueOf(userObj.getBalanceAmount()));
                        Snackbar.make(rootView.findViewById(R.id.view_layout), uName.toUpperCase() + " Successfully update", Snackbar.LENGTH_SHORT).show();
                    }
                    else {
                        Snackbar.make(rootView.findViewById(R.id.view_layout),"Enter Amount less than Balance Amount", Snackbar.LENGTH_SHORT).show();
                    }
                }
                else {
                    Snackbar.make(rootView.findViewById(R.id.view_layout),"Enter the Both fields", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
        return rootView;

    }
    public void view(View v){

    }
    public void update(View v){

    }
}
