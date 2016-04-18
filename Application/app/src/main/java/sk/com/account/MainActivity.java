package sk.com.account;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import Beam.Users;
import Database.DataBaseHandler;

public class MainActivity extends AppCompatActivity {
   // private TextInputLayout
   EditText userName , amt ;
   // TextView bal_txt,topay_txt;
    Users obj;
    DataBaseHandler db;
    NavigationView mnvaigation;
    DrawerLayout mdrawerLayout;
     Toolbar mtoolbar;
    FragmentManager mfm;
    FragmentTransaction mft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        mfm = getSupportFragmentManager();
        mft = mfm.beginTransaction();
        mft.add(R.id.main_frame, new AddViewFragment());
        mft.commit();
        mnvaigation = (NavigationView) findViewById(R.id.navigation_view);
        mnvaigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);


                mdrawerLayout.closeDrawers();
                String tittle = (String) item.getTitle();
                switch (tittle.toLowerCase()) {
                    case "add":
                        mfm = getSupportFragmentManager();
                        mft = mfm.beginTransaction();
                        mft.replace(R.id.main_frame, new AddViewFragment());
                        mft.commit();
                        break;
                    case "search by name":
                        mfm = getSupportFragmentManager();
                        mft = mfm.beginTransaction();
                        mft.replace(R.id.main_frame, new PaymentView());
                        mft.commit();
                        break;
                    case "display":
                        mfm = getSupportFragmentManager();
                        mft = mfm.beginTransaction();
                        mft.replace(R.id.main_frame, new Display());
                        mft.commit();
                        break;
                }
                return false;
            }
        });
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionbardrawertoggle = new
                ActionBarDrawerToggle(this, mdrawerLayout, mtoolbar, R.string.openDrawer, R.string.closeDrawer) {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        //Log.e("sample", "sa ");

                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                    }
                };
        mdrawerLayout.addDrawerListener(actionbardrawertoggle);
        actionbardrawertoggle.syncState();
       /* db=new DataBaseHandler(MainActivity.this);
        userName = (EditText) findViewById(R.id.name);
        amt = (EditText) findViewById(R.id.amt);
       /* pay_now = (EditText) findViewById(R.id.amtpaynow);
        bal_txt = (TextView) findViewById(R.id.bal);
        topay_txt = (TextView) findViewById(R.id.topay);
        Button add = (Button) findViewById(R.id.add_bt);
        Button viewss = (Button) findViewById(R.id.view_bt_main);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = userName.getText().toString().trim();
                int amount = Integer.parseInt(amt.getText().toString());
                obj = new Users(name, amount);
                db.adduser(obj);
            }
        });
        viewss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager mfm = getSupportFragmentManager();
                FragmentTransaction mft = mfm.beginTransaction();
                mft.replace(R.id.container, new PaymentView());
                mft.commit();
               /* Intent intt = new Intent(MainActivity.this, PaymentView.class);
                startActivity(intt);
            }
        });*/


    }
}
