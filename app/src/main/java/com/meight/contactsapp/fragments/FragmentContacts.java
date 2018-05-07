package com.meight.contactsapp.fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meight.contactsapp.R;
import com.meight.contactsapp.adapters.CallsRvAdapter;
import com.meight.contactsapp.adapters.ContactsRvAdapter;
import com.meight.contactsapp.models.ModelCalls;
import com.meight.contactsapp.models.ModelContact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentContacts extends Fragment {

    private View view;
    private ViewPager vp;
    private RecyclerView recyclerView;

    private ArrayList<ModelContact> contacts;

    private String type;

    public FragmentContacts() {
    }

    public static FragmentContacts newInstance(String type, ArrayList<ModelContact> contacts) {
        FragmentContacts fragment = new FragmentContacts();
        Bundle args = new Bundle();
        args.putString("type", type);
        args.putParcelableArrayList(type, contacts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if (getArguments() == null) {
            throw new RuntimeException("You must to send a dummyModels ");
        }
        contacts = getArguments().getParcelableArrayList("1");
        type = (String) getArguments().getString("type");


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.from(container.getContext()).inflate(R.layout.fragment_contacts, container, false);
        Bundle args = new Bundle();
        Context ctx = rootView.getContext();
        RecyclerView recyclerView = (RecyclerView)rootView;
        recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
        vp = (ViewPager)getActivity().findViewById(R.id.viewpager);
        ContactsRvAdapter adapter = new ContactsRvAdapter(vp, contacts);
        recyclerView.setAdapter(adapter);

        return rootView;
    }



}
