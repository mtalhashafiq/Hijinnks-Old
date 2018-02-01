package com.example.mtalh.hijinnks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mtalh on 26-Oct-17.
 */

public class Recycleradapter_Search_Followers_Fragment extends RecyclerView.Adapter<Recycleradapter_Search_Followers_Fragment.RecyclerViewHolder> {
    static Recycleradapter_Search_Followers_Fragment.ItemClickListener itemClickListener;
    Context context;
    String[] profile_name;
    int[] profile_images;
    String[] number_of_invites;
    String[] text_follow;
    int[] follow_background;

    ArrayList<Model_Search_ALL> arrayList_followers = new ArrayList<>();

    public Recycleradapter_Search_Followers_Fragment(ArrayList<Model_Search_ALL> arrayList_followers) {
        this.arrayList_followers = arrayList_followers;
    }


    /*  public Recycleradapter_Search_All_Fragment(String[] profile_name, int[] profile_images, String[] number_of_invites, String[] text_follow, int[] follow_background, Context context) {
        this.profile_name = profile_name;
        this.profile_images = profile_images;
        this.number_of_invites = number_of_invites;
        this.text_follow = text_follow;
        this.follow_background = follow_background;
        this.context = context;
    }*/


    @Override
    public Recycleradapter_Search_Followers_Fragment.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_search_all_fragment, parent, false);
        Recycleradapter_Search_Followers_Fragment.RecyclerViewHolder recyclerviewholder = new Recycleradapter_Search_Followers_Fragment.RecyclerViewHolder(view);
        return recyclerviewholder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


        holder.Search_profile_image.setImageResource(arrayList_followers.get(position).getProfile_image());
        holder.Search_profile_name.setText(arrayList_followers.get(position).getProfile_name());
        holder.Search_number_of_invites.setText(arrayList_followers.get(position).getNumber_of_invites());
        holder.Search_text_follow.setText(arrayList_followers.get(position).getText_follow());
        holder.Search_follow_background.setBackgroundResource(arrayList_followers.get(position).getFollow_background());

      /*  holder.Search_profile_image.setImageResource(profile_images[position]);
        holder.Search_profile_name.setText(profile_name[position]);
        holder.Search_number_of_invites.setText(number_of_invites[position]);
        holder.Search_text_follow.setText(text_follow[position]);
        holder.Search_follow_background.setBackgroundDrawable(context.getResources().getDrawable(follow_background[position]));*/
    }


    @Override
    public int getItemCount() {
        return arrayList_followers.size();
    }

    public void filter(String s) {

    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        de.hdodenhof.circleimageview.CircleImageView Search_profile_image;
        TextView Search_profile_name, Search_number_of_invites, Search_text_follow;
        LinearLayout Search_follow_background;


        public RecyclerViewHolder(View view) {
            super(view);

            Search_profile_image = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.search_profile_image);
            Search_profile_name = (TextView) view.findViewById(R.id.search_profile_name);
            Search_number_of_invites = (TextView) view.findViewById(R.id.search_numberof_invites);
            Search_text_follow = (TextView) view.findViewById(R.id.search_follow_text);
            Search_follow_background = (LinearLayout) view.findViewById(R.id.search_backgroung_follow);


            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.OnItemClick(getAdapterPosition());
        }
    }

    public void SsetClickListener_NavigationDrawer(Recycleradapter_Search_Followers_Fragment.ItemClickListener listener) {
        this.itemClickListener = listener;
    }


    public interface ItemClickListener {
        public void OnItemClick(int Pos);
    }

    public void setFilter_followers(ArrayList newList_followers) {
        arrayList_followers = new ArrayList<>();
        arrayList_followers.addAll(newList_followers);
        notifyDataSetChanged();
    }
}