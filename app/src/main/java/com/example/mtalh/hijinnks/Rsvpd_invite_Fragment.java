package com.example.mtalh.hijinnks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Rsvpd_invite_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerAdapter_Home_Profile_Invites adapter;
    private RecyclerView.LayoutManager layoutManager;
    String[] profile_name = {"Battey", "Bonut", "DashBoard","Battey", "Bonut", "DashBoard","Battey", "Bonut", "DashBoard","Battey", "Bonut", "DashBoard","Battey", "Bonut", "DashBoard"};

    private OnFragmentInteractionListener mListener;

    public Rsvpd_invite_Fragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.fragment_rsvpd_invite_, container, false);
    /*    recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_home_profile_invite_fragment);
        adapter = new RecyclerAdapter_Home_Profile_Invites(profile_name, getActivity());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.SsetClickListener(new RecyclerAdapter_Home_Profile_Invites.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), "I am Invite Fragment at position : "+Pos, Toast.LENGTH_SHORT).show();
            }
        });*/
      return view;
    }



   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
