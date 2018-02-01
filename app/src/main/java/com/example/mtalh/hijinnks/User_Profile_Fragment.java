package com.example.mtalh.hijinnks;


import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class User_Profile_Fragment extends Fragment {

    ImageView share_icon;
    HomeFragmentInterface homeFragmentInterface;
    ArrayList<Model_User_Profile_Invites_rsvpd> list_invites = new ArrayList<>();
    ArrayList<Model_User_Profile_Invites_rsvpd> list_rsvpd = new ArrayList<>();
    LinearLayout invites_layout, rsvpd_layout;
    TextView invite_text, invite_count;
    TextView rsvpd_text, rsvpd_count;
    View invite_View, rsvpd_View;
    ImageView cover_photo;
    ImageView back_image;
    Profile_Invites_Fragment profile_invites_fragment = new Profile_Invites_Fragment();
    Rsvpd_invite_Fragment rsvpd_invite_fragment = new Rsvpd_invite_Fragment();
    FrameLayout frameLayout;
    String[] profile_name = {"Welcome to ziro family 2018", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard"};
    String[] profile_type_invites = {"Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites", "Invites"};
    String[] profile_name_rsvpd = {"Welcome to lahore city Model Town 2018", "Battey", "Bonut", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard"};
    String[] profile_type_rsvpd = {"RSVP", "RSVP", "RSVP", "RSVP'd", "RSVP", "RSVP", "RSVP", "RSVP", "RSVP", "RSVP", "RSVP", "RSVP", "RSVP", "RSVP", "RSVP"};
    HomeFragment homeFragment = new HomeFragment(homeFragmentInterface);
    private RecyclerView recyclerView;
    private RecyclerAdapter_Home_User_Profile_Invite_Rsvpd adapter;
    private RecyclerView.LayoutManager layoutManager;

    public User_Profile_Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user__profile_, container, false);
        invites_layout = (LinearLayout) view.findViewById(R.id.invite_layout_user);
        rsvpd_layout = (LinearLayout) view.findViewById(R.id.rsvpd_layout_user);
        invite_text = (TextView) view.findViewById(R.id.invite_text_user);
        invite_count = (TextView) view.findViewById(R.id.invite_number_user);
        rsvpd_text = (TextView) view.findViewById(R.id.rsvpd_text_user);
        rsvpd_count = (TextView) view.findViewById(R.id.rsvpd_number_user);
        invite_View = (View) view.findViewById(R.id.invite_View_user);
        rsvpd_View = (View) view.findViewById(R.id.rsvpd_View_user);
        share_icon = (ImageView) view.findViewById(R.id.share_icon_user);
        cover_photo = (ImageView) view.findViewById(R.id.cover_photo_user);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        back_image = (ImageView) view.findViewById(R.id.back_image_user);
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        share_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationInfo api = getContext().getApplicationInfo();
                String apkPath = api.sourceDir;

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/vnd.android.package-archive");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
                startActivity(Intent.createChooser(intent, "SHARE APP USING"));

            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_home_profile_invite_fragment_user);
        adapter = new RecyclerAdapter_Home_User_Profile_Invite_Rsvpd(list_invites, getActivity());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        if (list_invites.size() == 0) {
            for (int i = 0; i < profile_name.length; i++) {
                list_invites.add(new Model_User_Profile_Invites_rsvpd(profile_name[i], profile_type_rsvpd[i]));
            }
        }
        adapter.SsetClickListener(new RecyclerAdapter_Home_User_Profile_Invite_Rsvpd.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), "I am Invite Fragment at position : " + Pos, Toast.LENGTH_SHORT).show();
            }
        });

        invites_layout.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                adapter = new RecyclerAdapter_Home_User_Profile_Invite_Rsvpd(list_invites, getActivity());
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);

                for (int i = 0; i < profile_name.length; i++) {
                    list_invites.add(new Model_User_Profile_Invites_rsvpd(profile_name[i], profile_type_rsvpd[i]));
                }


                adapter.SsetClickListener(new RecyclerAdapter_Home_User_Profile_Invite_Rsvpd.ItemClickListener() {
                    @Override
                    public void OnItemClick(int Pos) {
                        Toast.makeText(getContext(), "I am Invite Fragment at position : " + Pos, Toast.LENGTH_SHORT).show();
                    }
                });

                invite_text.setTextColor(getResources().getColor(R.color.login_button_blue));
                invite_count.setTextColor(getResources().getColor(R.color.login_button_blue));
                invite_View.setBackgroundColor(getResources().getColor(R.color.login_button_blue));

                rsvpd_text.setTextColor(getResources().getColor(R.color.light_grey));
                rsvpd_count.setTextColor(getResources().getColor(R.color.light_grey));
                rsvpd_View.setBackgroundColor(getResources().getColor(R.color.light_grey));
            }
        });

        rsvpd_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                adapter = new RecyclerAdapter_Home_User_Profile_Invite_Rsvpd((ArrayList<Model_User_Profile_Invites_rsvpd>) list_rsvpd, getActivity());
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
                if (list_rsvpd.size() == 0) {
                    for (int j = 0; j < profile_name_rsvpd.length; j++) {
                        list_rsvpd.add(new Model_User_Profile_Invites_rsvpd(profile_name_rsvpd[j], profile_type_rsvpd[j]));
                    }
                }
                adapter.SsetClickListener(new RecyclerAdapter_Home_User_Profile_Invite_Rsvpd.ItemClickListener() {
                    @Override
                    public void OnItemClick(int Pos) {
                        Toast.makeText(getContext(), "I am RSVP'D Fragment at position : " + Pos, Toast.LENGTH_SHORT).show();
                    }
                });

                invite_text.setTextColor(getResources().getColor(R.color.light_grey));
                invite_count.setTextColor(getResources().getColor(R.color.light_grey));
                invite_View.setBackgroundColor(getResources().getColor(R.color.light_grey));


                rsvpd_text.setTextColor(getResources().getColor(R.color.login_button_blue));
                rsvpd_count.setTextColor(getResources().getColor(R.color.login_button_blue));
                rsvpd_View.setBackgroundColor(getResources().getColor(R.color.login_button_blue));
            }
        });

        return view;
    }

}
