package com.example.mtalh.hijinnks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.example.mtalh.hijinnks.Comments.list_comments;

/**
 * Created by mtalh on 17-Oct-17.
 */

public class RecyclerAdapter_comment extends RecyclerView.Adapter<RecyclerAdapter_comment.RecyclerViewholder> {

    /*String[] country_names;
    String[] user_name;
    String[] description;
    com.hedgehog.ratingbar.RatingBar[] rating;*/
    Context context;

    static RecyclerAdapter_comment.ItemClickInterface itemClickInterface;


    public RecyclerAdapter_comment(List<Model_Comments> list, Context c) {
        this.context = c;
    }

    @Override
    public RecyclerAdapter_comment.RecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_comments, parent, false);
        RecyclerAdapter_comment.RecyclerViewholder recyclerviewholder = new RecyclerAdapter_comment.RecyclerViewholder(view);
        return recyclerviewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter_comment.RecyclerViewholder holder, int position) {
        //  holder.Tx_logos.setImageResource(logos[position]);
        holder.Tx_profile_person_name.setText(list_comments.get(position).name);
        holder.Tx_comment_date.setText(list_comments.get(position).Date);
        holder.Tx_comment_text.setText(list_comments.get(position).Comment);


    }


    @Override
    public int getItemCount() {
        return list_comments.size();
    }

    public static class RecyclerViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Tx_profile_person_name;
        TextView Tx_comment_date;
        TextView Tx_comment_text;


        public RecyclerViewholder(View view) {
            super(view);
            Tx_profile_person_name = (TextView) view.findViewById(R.id.commemt_person_name);
            Tx_comment_date = (TextView) view.findViewById(R.id.comment_date);
            Tx_comment_text = (TextView) view.findViewById(R.id.comment_text);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            itemClickInterface.itemclickmethod(getAdapterPosition());
        }
    }

    public void SetItemClcik(RecyclerAdapter_comment.ItemClickInterface listner) {
        this.itemClickInterface = listner;
    }

    public interface ItemClickInterface {
        public void itemclickmethod(int position);
    }

    public void addnewdata(String profile_persone_name, String comment_date, String comment_text) {
        list_comments.add(new Model_Comments(profile_persone_name, comment_date, comment_text));
        notifyDataSetChanged();
    }
}
