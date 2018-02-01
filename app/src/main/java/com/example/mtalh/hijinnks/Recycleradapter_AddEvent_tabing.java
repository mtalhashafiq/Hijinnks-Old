package com.example.mtalh.hijinnks;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by mtalh on 26-Oct-17.
 */

public class Recycleradapter_AddEvent_tabing extends RecyclerView.Adapter<Recycleradapter_AddEvent_tabing.RecyclerViewHolder> {

  /*  String[] name;*/


    List<Fragment> fragmentList = new ArrayList<>();
    private final Context context;
    add_event_tab_1_fragment add_event_tab_1= new add_event_tab_1_fragment();
    addevent_tab_2_fragment add_event_tab_2= new addevent_tab_2_fragment();
    addevent_tab_3_fragment add_event_tab_3= new addevent_tab_3_fragment();

    private FragmentManager fragmentManager;


    static Recycleradapter_AddEvent_tabing.ItemClickListener itemClickListener;

    public Recycleradapter_AddEvent_tabing(List<Fragment> fragmentList, Context context, FragmentManager fragmentManager) {
        this.fragmentList = fragmentList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    /*  public Recycleradapter_AddEvent_tabing(String[] name, Context context) {
        this.name = name;
        this.context = context;

    }*/

    @Override
    public Recycleradapter_AddEvent_tabing.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list, parent, false);
        Recycleradapter_AddEvent_tabing.RecyclerViewHolder recyclerviewholder = new Recycleradapter_AddEvent_tabing.RecyclerViewHolder(view);

        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.height = parent.getMeasuredHeight() ;
        view.setLayoutParams(lp);
        return recyclerviewholder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        if (position == 0) {
            // Fragment fragment = fragmentList.get(position);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(holder.tab_1.getId(),add_event_tab_1, TAG + "pos" + position);
            transaction.commitAllowingStateLoss();
        }
        if (position == 1) {
            //  Fragment fragment = fragmentList.get(position);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(holder.tab_2.getId(),add_event_tab_2, TAG + "pos" + position);
            transaction.commitAllowingStateLoss();
        }
        if (position == 2) {
            // Fragment fragment = fragmentList.get(position);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(holder.tab_3.getId(), add_event_tab_3, TAG + "pos" + position);
            transaction.commitAllowingStateLoss();
        }


      /*  if (position == 3) {
            Fragment fragment = fragmentList.get(position);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(holder.tab_4.getId(), fragment, TAG + "pos" + position);
            transaction.commit();
        }*/
    }


    @Override
    public int getItemCount() {
        return 3;
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
     FrameLayout tab_1,tab_2,tab_3,tab_4;


        public RecyclerViewHolder(View view) {
            super(view);

            tab_1 = (FrameLayout) view.findViewById(R.id.tab_1);
            tab_2 = (FrameLayout) view.findViewById(R.id.tab_2);
            tab_3 = (FrameLayout) view.findViewById(R.id.tab_3);
          //  tab_4 = (FrameLayout) view.findViewById(R.id.tab_4);


            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            itemClickListener.OnItemClick(getAdapterPosition());
        }
    }

    public void SsetClickListener_NavigationDrawer(Recycleradapter_AddEvent_tabing.ItemClickListener listener) {
        this.itemClickListener = listener;
    }


    public interface ItemClickListener {
        public void OnItemClick(int Pos);
    }
}