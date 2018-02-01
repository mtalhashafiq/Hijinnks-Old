package com.example.mtalh.hijinnks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mtalh on 26-Oct-17.
 */

public class Recycleradapter_AddEvent_Location extends RecyclerView.Adapter<Recycleradapter_AddEvent_Location.RecyclerViewHolder> {

    String[] name;
    Context context;
    static Recycleradapter_AddEvent_Location.ItemClickListener itemClickListener;


    public Recycleradapter_AddEvent_Location(String[] name, Context context) {
        this.name = name;
        this.context = context;

    }

    @Override
    public Recycleradapter_AddEvent_Location.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_addevent_location, parent, false);
        Recycleradapter_AddEvent_Location.RecyclerViewHolder recyclerviewholder = new Recycleradapter_AddEvent_Location.RecyclerViewHolder(view);
        return recyclerviewholder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.Name.setText(name[position]);

    }


    @Override
    public int getItemCount() {
        return name.length;
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Name;


        public RecyclerViewHolder(View view) {
            super(view);

            Name = (TextView) view.findViewById(R.id.nameof_event);


            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.OnItemClick(getAdapterPosition());
        }
    }

    public void SsetClickListener_NavigationDrawer(Recycleradapter_AddEvent_Location.ItemClickListener listener) {
        this.itemClickListener = listener;
    }


    public interface ItemClickListener {
        public void OnItemClick(int Pos);
    }
}