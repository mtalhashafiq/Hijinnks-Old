package com.example.mtalh.hijinnks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.mtalh.hijinnks.AddEvent_Fragment.list_addEvent_addIntrest;


/**
 * Created by mtalh on 17-Oct-17.
 */

public class RecyclerAdapter_addEvent_addIntrest extends RecyclerView.Adapter<RecyclerAdapter_addEvent_addIntrest.RecyclerViewholder> {

    /*String[] country_names;
    String[] user_name;
    String[] description;
    com.hedgehog.ratingbar.RatingBar[] rating;*/
    Context context;

    static RecyclerAdapter_addEvent_addIntrest.ItemClickInterface itemClickInterface;


    public RecyclerAdapter_addEvent_addIntrest(List<Model_addEvent_addIntrest> list, Context c) {
        this.context = c;
    }

    @Override
    public RecyclerAdapter_addEvent_addIntrest.RecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_addevent_location, parent, false);
        RecyclerAdapter_addEvent_addIntrest.RecyclerViewholder recyclerviewholder = new RecyclerAdapter_addEvent_addIntrest.RecyclerViewholder(view);
        return recyclerviewholder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter_addEvent_addIntrest.RecyclerViewholder holder, final int position) {
        //  holder.Tx_logos.setImageResource(logos[position]);
        holder.name_event.setText(list_addEvent_addIntrest.get(position).event_name);

        holder.textview_for_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.editText_edit_field.setVisibility(View.VISIBLE);
                holder.editText_edit_field.setText(list_addEvent_addIntrest.get(position).event_name);
                holder.editing_done.setVisibility(View.VISIBLE);
                holder.textview_for_edit.setVisibility(View.INVISIBLE);
                holder.name_event.setVisibility(View.INVISIBLE);
           holder.editText_edit_field.requestFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

            }
        });
        holder.editing_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.editText_edit_field.setVisibility(View.INVISIBLE);
                holder.editing_done.setVisibility(View.INVISIBLE);
                holder.textview_for_edit.setVisibility(View.VISIBLE);
                holder.name_event.setVisibility(View.VISIBLE);
                Toast.makeText(context, "" + holder.editText_edit_field.getText().toString(), Toast.LENGTH_SHORT).show();

                list_addEvent_addIntrest.get(position).event_name = holder.editText_edit_field.getText().toString();
//                holder.name_event.setText(list_addEvent_addIntrest.get(position).event_name);
//                holder.editText_edit_field.setText("");
                // list_addEvent_addIntrest.add(new Model_addEvent_addIntrest(holder.editText_edit_field.getText().toString()));
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                notifyItemChanged(position);
                notifyDataSetChanged();

            }
        });

    }


    @Override
    public int getItemCount() {
        return list_addEvent_addIntrest.size();
    }

    public static class RecyclerViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name_event, editing_done, textview_for_edit;
        EditText editText_edit_field;


        public RecyclerViewholder(View view) {
            super(view);
            name_event = (TextView) view.findViewById(R.id.nameof_event);
            editing_done = (TextView) view.findViewById(R.id.textview_done);
            textview_for_edit = (TextView) view.findViewById(R.id.textview_for_edit);
            editText_edit_field = (EditText) view.findViewById(R.id.edittext_for_edit_field);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            itemClickInterface.itemclickmethod(getAdapterPosition());
        }
    }

    public void SetItemClcik(RecyclerAdapter_addEvent_addIntrest.ItemClickInterface listner) {
        this.itemClickInterface = listner;
    }

    public interface ItemClickInterface {
        public void itemclickmethod(int position);
    }

    public void addnewdata(String eventName) {
        list_addEvent_addIntrest.add(new Model_addEvent_addIntrest(eventName));
        notifyDataSetChanged();
    }
}
