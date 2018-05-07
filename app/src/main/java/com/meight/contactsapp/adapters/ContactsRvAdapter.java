package com.meight.contactsapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meight.contactsapp.R;
import com.meight.contactsapp.models.ModelCalls;
import com.meight.contactsapp.models.ModelContact;

import java.util.ArrayList;
import java.util.List;

public class ContactsRvAdapter extends RecyclerView.Adapter<ContactsRvAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;

    private ViewPager viewPager;

    private ArrayList<ModelContact> listContacts;

    public ContactsRvAdapter(ViewPager viewPager, ArrayList<ModelContact> listContacts){
        this.context = context;
        this.listContacts= listContacts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_contact, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       holder.name.setText(listContacts.get(position).getName());
       holder.number.setText(listContacts.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return listContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView name, number;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contact_name);
            number = itemView.findViewById(R.id.contact_number);
        }
    }
}
