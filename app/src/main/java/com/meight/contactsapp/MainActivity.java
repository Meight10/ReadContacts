package com.meight.contactsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meight.contactsapp.adapters.ViewPagerAdapter;
import com.meight.contactsapp.fragments.FragmentCalls;
import com.meight.contactsapp.fragments.FragmentContacts;
import com.meight.contactsapp.fragments.FragmentFav;
import com.meight.contactsapp.models.ModelContact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static ArrayList<ModelContact> contacts;

    private final int[] ICONS = {R.drawable.ic_call, R.drawable.ic_person, R.drawable.ic_stars};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            contacts = new ArrayList<>();
            getContacts();
        }

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        FragmentContacts fragment1 = FragmentContacts.newInstance("1",contacts);

        adapter.addFragment(new FragmentCalls(), "Calls");
        adapter.addFragment(fragment1, "Contacts");
        adapter.addFragment(new FragmentFav(), "Favorites");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        for(int i = 0; i<tabLayout.getTabCount(); i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void askPermissions(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) !=
                PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_CONTACTS}, 1);

            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_CALL_LOG}, 1);

        }
    }

    private void getContacts(){

        Cursor cursor = this.getContentResolver().query(ContactsContract.
                        CommonDataKinds.Phone.CONTENT_URI, null,
                null, null, ContactsContract.Contacts.DISPLAY_NAME
                        + " ASC");


        cursor.moveToFirst();
        while(cursor.moveToNext()){

            contacts.add(new ModelContact(cursor.getString(cursor.getColumnIndex(ContactsContract.
                    CommonDataKinds.Phone.DISPLAY_NAME)), cursor.getString(cursor.getColumnIndex(ContactsContract.
                    CommonDataKinds.Phone.NUMBER))));

        }

    }
}
