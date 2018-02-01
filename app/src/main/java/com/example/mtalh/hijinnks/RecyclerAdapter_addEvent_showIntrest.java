package com.example.mtalh.hijinnks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.example.mtalh.hijinnks.AddEvent_Fragment.list_addEvent_addIntrest;


/**
 * Created by mtalh on 17-Oct-17.
 */

public class RecyclerAdapter_addEvent_showIntrest extends RecyclerView.Adapter<RecyclerAdapter_addEvent_showIntrest.RecyclerViewholder> {

    static RecyclerAdapter_addEvent_showIntrest.ItemClickInterface itemClickInterface;
    /*String[] country_names;
    String[] user_name;
    String[] description;
    com.hedgehog.ratingbar.RatingBar[] rating;*/
    Context context;


    public RecyclerAdapter_addEvent_showIntrest(List<Model_addEvent_addIntrest> list, Context c) {
        this.context = c;
    }

    @Override
    public RecyclerAdapter_addEvent_showIntrest.RecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_show_intrests, parent, false);
        RecyclerAdapter_addEvent_showIntrest.RecyclerViewholder recyclerviewholder = new RecyclerAdapter_addEvent_showIntrest.RecyclerViewholder(view);
        return recyclerviewholder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter_addEvent_showIntrest.RecyclerViewholder holder, final int position) {
        holder.name_event.setText(list_addEvent_addIntrest.get(position).event_name);



    }


    @Override
    public int getItemCount() {
        return list_addEvent_addIntrest.size();
    }

    public void SetItemClcik(RecyclerAdapter_addEvent_showIntrest.ItemClickInterface listner) {
        this.itemClickInterface = listner;
    }

    public interface ItemClickInterface {
        public void itemclickmethod(int position);
    }

    public static class RecyclerViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name_event;



        public RecyclerViewholder(View view) {
            super(view);
            name_event = (TextView) view.findViewById(R.id.nameof_event_final);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            itemClickInterface.itemclickmethod(getAdapterPosition());
        }
    }

  /*  public void addnewdata(String eventName) {
        list_addEvent_addIntrest.add(new Model_addEvent_addIntrest(eventName));
        notifyDataSetChanged();
    }*/
}
