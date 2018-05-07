package com.meight.contactsapp.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.meight.contactsapp.R;
import com.meight.contactsapp.adapters.CallsRvAdapter;
import com.meight.contactsapp.models.ModelCalls;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentCalls  extends Fragment{

    private RecyclerView recyclerView;
    private View view;

    public FragmentCalls(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_calls, container, false);

        recyclerView = view.findViewById(R.id.rv_calls);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;

        recyclerView.setLayoutManager(layoutManager);

        CallsRvAdapter adapter = new CallsRvAdapter(getContext(), getCallLog());

        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<ModelCalls> getCallLog(){

        List<ModelCalls> listCalls = new ArrayList<>();

        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_CALL_LOG}, 1);
        }

        Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                null, null, CallLog.Calls.DATE + " ASC");

        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int dateInt = cursor.getColumnIndex(CallLog.Calls.DATE);

        cursor.moveToFirst();
        while(cursor.moveToNext()){

            Date date = new Date(Long.valueOf(cursor.getString(dateInt)));

            String month_day, week_day, month;

            month_day = (String) DateFormat.format("dd", date);
            week_day = (String) DateFormat.format("EEEE", date);
            month = (String) DateFormat.format("MMMM", date);


            listCalls.add(new ModelCalls(cursor.getString(number), cursor.getString(duration),
                    week_day + " " + month_day + " " + month));

        }
        return listCalls;
    }


}
