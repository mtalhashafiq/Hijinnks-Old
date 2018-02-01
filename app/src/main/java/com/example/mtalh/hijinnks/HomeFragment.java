package com.example.mtalh.hijinnks;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("ValidFragment")

public class HomeFragment extends Fragment {
    HomeFragmentInterface homeFragmentInterface;
    String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    String videourl = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    String[] profile_name = {"Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard",
            "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard", "Battey", "Bonut", "DashBoard"};
 String []video={videourl,TEST_URL,videourl,TEST_URL,videourl,TEST_URL,videourl,TEST_URL,videourl,TEST_URL,videourl,TEST_URL,videourl,TEST_URL,
         videourl,TEST_URL,videourl,TEST_URL,videourl,TEST_URL,videourl};
    DrawerLayout fragment_drawer_get;
    ImageView comment_button;
 /*   int[] video = {R.raw.a, R.raw.b, R.raw.c, R.raw.a, R.raw.b, R.raw.c, R.raw.a, R.raw.b, R.raw.c, R.raw.a, R.raw.b, R.raw.c, R.raw.a, R.raw.b, R.raw.c,
            R.raw.a, R.raw.b, R.raw.c, R.raw.a, R.raw.b, R.raw.c};*/
    /*  private RecyclerView recyclerView_navigation;
      private Recycleradapter_Home_Home_NavigationDrawer adapter_navigation;
      private RecyclerView.LayoutManager layoutManager_navigation;
      String[] name = {"Feed", "RSVP'd Events", "Favourites", "Message", "Followers", "Following"};
      int[] image = {R.drawable.menu_feed_icon, R.drawable.menu_rsvpd_event_icon, R.drawable.menu_favourties_icon, R.drawable.message_icon,
              R.drawable.menu_followers_icon, R.drawable.menu_following_icon};
      String[] count_message = {"38", "", "", "75", "", ""};

  */
    DrawerLayout drawerLayout_homeFragment;
    private RecyclerView recyclerView;
    private RecyclerAdapter_Home_HomeFragment adapter;
    private RecyclerView.LayoutManager layoutManager;
    private OnFragmentInteractionListener mListener;

   /* public HomeFragment() {

    }*/

    public HomeFragment(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        comment_button = (ImageView) view.findViewById(R.id.comment_button);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_home_fragment);

        adapter = new RecyclerAdapter_Home_HomeFragment(profile_name, video, getActivity());

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        fragment_drawer_get = (DrawerLayout) getActivity().findViewById(R.id.home_navigation_drawer);
        ImageView drawer_icon = (ImageView) view.findViewById(R.id.drawer_image);


        adapter.SsetClickListener(new RecyclerAdapter_Home_HomeFragment.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), "Hi " + Pos, Toast.LENGTH_SHORT).show();
            }
        });

        drawer_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment_drawer_get.openDrawer(Gravity.LEFT);
                Toast.makeText(getActivity(), "Drawer Open", Toast.LENGTH_SHORT).show();
            }
        });
        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



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
        void onFragmentInteraction(Uri uri);
    }
}
