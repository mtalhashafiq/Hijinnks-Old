package com.example.mtalh.hijinnks;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class Search_Followers_Fragment extends Fragment /*implements android.support.v7.widget.SearchView.OnQueryTextListener*/ {
    private RecyclerView recyclerView_navigation;
    private Recycleradapter_Search_All_Fragment adapter_navigation_follower;
    private RecyclerView.LayoutManager layoutManager_navigation;
    // EditText edit_search_bar;
    String[] profile_name = {"Feed_FOLLOWERS Start", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "Followers all END"};
    int[] profile_image = {R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5};
    String[] number_invite = {"38453", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232"};
    String[] text_follow = {"Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers"};
    int[] follow_background = {R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue,
            R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue,
            R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue};

    ArrayList<Model_Search_ALL> arrayList_followers = new ArrayList<>();

//    private Search_Followers_Fragment.OnFragmentInteractionListener;

    private OnFragmentInteractionListener mListener;

    public Search_Followers_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search__followers_, container, false);

        recyclerView_navigation = (RecyclerView) view.findViewById(R.id.recyclerview_search_followers_fragment);
        //   edit_search_bar = (EditText) view.findViewById(R.id.search_bar);
        // adapter_navigation_follower = new Recycleradapter_Search_All_Fragment(arrayList_followers);
        layoutManager_navigation = new LinearLayoutManager(getContext());
        recyclerView_navigation.setLayoutManager(layoutManager_navigation);
        recyclerView_navigation.setHasFixedSize(true);
        recyclerView_navigation.setAdapter(adapter_navigation_follower);


     /*   int count = 0;
        for (String Name : profile_name) {
            arrayList_followers.add(new Model_Search_ALL(Name, number_invite[count], text_follow[count], profile_image[count], follow_background[count]));
            count++;
        }*/
        if (arrayList_followers.size() == 0) {
            for (int i = 0; i < profile_name.length; i++) {
                arrayList_followers.add(new Model_Search_ALL(profile_name[i], number_invite[i], text_follow[i], profile_image[i], follow_background[i]));
            }

        }
        adapter_navigation_follower = new Recycleradapter_Search_All_Fragment(arrayList_followers,getContext());
        recyclerView_navigation.setAdapter(adapter_navigation_follower);


        adapter_navigation_follower.SsetClickListener_NavigationDrawer(new Recycleradapter_Search_All_Fragment.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), "Followers " + Pos, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

     /*   getMenuInflater().inflate(R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener((android.support.v7.widget.SearchView.OnQueryTextListener) this);

        return true;*/

    }


/*    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void filter_followers(String s) {
        s = s.toLowerCase();
        ArrayList<Model_Search_ALL> newList_follower = new ArrayList<>();
        for (Model_Search_ALL temp : arrayList_followers) {
            String name = temp.getProfile_name().toLowerCase();
            if (name.contains(s)) {
                newList_follower.add(temp);
            }
        }
        if (adapter_navigation_follower != null)
            adapter_navigation_follower.setFilter(newList_follower);

//        filter(s);
    }*/
/*

    @Override
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
    }
*/


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
