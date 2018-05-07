package com.meight.contactsapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meight.contactsapp.R;
import com.meight.contactsapp.models.ModelCalls;

import java.util.List;

public class CallsRvAdapter extends RecyclerView.Adapter<CallsRvAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;

    private List<ModelCalls> callsList;

    public CallsRvAdapter(Context context, List<ModelCalls> callsList){
        this.context = context;
        this.callsList = callsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_calls, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView name, duration, date;

        name = holder.name;
        duration = holder.duration;
        date = holder.date;

        name.setText(callsList.get(position).getName());
        duration.setText(callsList.get(position).getDuration());
        date.setText(callsList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return callsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, duration, date;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contact_name);
            duration = itemView.findViewById(R.id.call_duration);
            date = itemView.findViewById(R.id.call_Date);
        }
    }
}
