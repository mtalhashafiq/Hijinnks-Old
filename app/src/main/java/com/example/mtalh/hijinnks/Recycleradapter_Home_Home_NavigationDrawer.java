package com.example.mtalh.hijinnks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mtalh on 26-Oct-17.
 */

public class Recycleradapter_Home_Home_NavigationDrawer extends RecyclerView.Adapter<Recycleradapter_Home_Home_NavigationDrawer.RecyclerViewHolder> {

    static Recycleradapter_Home_Home_NavigationDrawer.ItemClickListener itemClickListener;
    String[] name;
    int[] images;
    String[] count;
    int[] border;
    int[]count_border;
    Context context;


    public Recycleradapter_Home_Home_NavigationDrawer(String[] name, int[] images, String[] count, int[] border,int[]count_border ,Context context) {
        this.name = name;
        this.images = images;
        this.count = count;
        this.border = border;
        this.count_border=count_border;
        this.context = context;

    }


    @Override
    public Recycleradapter_Home_Home_NavigationDrawer.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_home_navigationdrawer, parent, false);
      //  Recycleradapter_Home_Home_NavigationDrawer.RecyclerViewHolder recyclerviewholder = new Recycleradapter_Home_Home_NavigationDrawer.RecyclerViewHolder(view);

        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.height = parent.getMeasuredHeight()/6;
        view.setLayoutParams(lp);

        return new Recycleradapter_Home_Home_NavigationDrawer.RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.Name.setText(name[position]);
        holder.NumberCount.setText(count[position]);
        holder.Image.setImageResource(images[position]);
        holder.border.setBackgroundResource(border[position]);
        holder.count_border.setBackgroundResource(count_border[position]);
    }


    @Override
    public int getItemCount() {
        return name.length;
    }

    public void SsetClickListener_NavigationDrawer(Recycleradapter_Home_Home_NavigationDrawer.ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface ItemClickListener {
        public void OnItemClick(int Pos);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Name, NumberCount;
        ImageView Image;
        LinearLayout border,count_border;


        public RecyclerViewHolder(View view) {
            super(view);

            Name = (TextView) view.findViewById(R.id.nameof_product);
            NumberCount = (TextView) view.findViewById(R.id.number_of_message);
            Image = (ImageView) view.findViewById(R.id.image);
            border = (LinearLayout) view.findViewById(R.id.border_background);
            count_border = (LinearLayout) view.findViewById(R.id.number_of_messages_background);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.OnItemClick(getAdapterPosition());
        }
    }
}