package com.example.mtalh.hijinnks;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Search_Fragment extends Fragment/* implements android.support.v7.widget.SearchView.OnQueryTextListener */ {
    LinearLayout search_all_tablayout, search_followers_tablayout, search_following_tablayout;
    Button search_all_button, search_followers_button, search_following_button;
    View search_all_view, search_followers_view, search_following_view;

    Search_All_fragments search_all_fragments = new Search_All_fragments();
    Search_Followers_Fragment search_followers_fragment = new Search_Followers_Fragment();
    Search_following_fragments search_following_fragments = new Search_following_fragments();

    Custome_EditText_Varela_Regular edittext_searchbar;
    TextView clear_edittext;
    SearchView searchView;
    boolean search_all_indicator = true;
    boolean search_followers_indicator = false;
    boolean search_follwoing_indicator = false;
    String[] profile_name_all = {"Feed all starts ", "RSVP'd Events", "Favourites", "Message", "jdkonly", "Mian", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "All all END"};
    int[] profile_image_all = {R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5};
    String[] number_invite_all = {"38453", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232"};
    String[] text_follow_all = {"Following", "Followed", "Follow", "Follow", "Follow", "Followed", "Following", "Following", "Following", "Followed", "Followed", "Following", "Following", "Following", "Followed", "Following", "Followed", "Follow", "Follow", "Follow"};
    int[] follow_background_all = {R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow, R.drawable.background_follow,
            R.drawable.background_follow_blue, R.drawable.background_follow_red, R.drawable.background_follow_red, R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow_blue, R.drawable.background_follow_red, R.drawable.background_follow_red, R.drawable.background_follow_red, R.drawable.background_follow_blue,
            R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow, R.drawable.background_follow};

    String[] profile_name_followers = {"Feed_FOLLOWERS Start", "RSVP'd Events", "only", "Message", "Followers", "Talha", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "Followers followers END"};
    int[] profile_image_followers = {R.drawable.person5, R.drawable.person4, R.drawable.person3, R.drawable.person2, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5};
    String[] number_invite_followers = {"38453", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232"};
    String[] text_follow_followers = {"Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers", "Followers"};
    int[] follow_background_followers = {R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue,
            R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue,
            R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue};

    String[] profile_name_following = {"Feed FOLLOWING starts ", "RSVP'd Events", "just", "Message", "Followers", "Shafiq", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "Followers", "Feed", "RSVP'd Events", "Favourites", "Message", "Following following END"};
    int[] profile_image_following = {R.drawable.person3, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5, R.drawable.person1, R.drawable.person2, R.drawable.person3, R.drawable.person4, R.drawable.person5};
    String[] number_invite_following = {"38453", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232", "38", "300", "2322", "7335", "2133232"};
    String[] text_follow_following = {"Followed", "Following", "Following", "Following", "Followed", "Followed", "Following", "Following", "Following", "Followed", "Followed", "Following", "Following", "Following", "Followed", "Followed", "Following", "Following", "Following", "Followed"};
    int[] follow_background_following = {R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue,
            R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue,
            R.drawable.background_follow_red, R.drawable.background_follow_blue, R.drawable.background_follow, R.drawable.background_follow_red, R.drawable.background_follow_blue};
    ArrayList<Model_Search_ALL> arrayList_search_all = new ArrayList<>();
    ArrayList<Model_Search_ALL> arrayList_search_followers = new ArrayList<>();
    ArrayList<Model_Search_ALL> arrayList_search_following = new ArrayList<>();
    HomeFragmentInterface homeFragmentInterface;
    private RecyclerView recyclerView_navigation;
    private Recycleradapter_Search_All_Fragment adapter_navigation;
    private RecyclerView.LayoutManager layoutManager_navigation;
    private OnFragmentInteractionListener mListener;

    public Search_Fragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public Search_Fragment(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_, container, false);


        clear_edittext = (TextView) view.findViewById(R.id.clear_edittext);
        edittext_searchbar = (Custome_EditText_Varela_Regular) view.findViewById(R.id.search_bar_edittext);
        //  searchView = (SearchView) view.findViewById(R.id.search_bar_search);
//        getActivity().getWindow().setStatusBarColor(Color.GRAY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color. parseColor("#1f4ba4"));
        }
        clear_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Main fragment search bar clear", Toast.LENGTH_SHORT).show();
                edittext_searchbar.setText("");
                //  searchView.setQuery("", false);
                // searchView.clearFocus();

            }
        });


        edittext_searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                String text_from_searchbar = edittext_searchbar.getText().toString().toLowerCase(Locale.getDefault());
//                adapter_navigation.filter(text_from_searchbar);


                if (search_all_indicator == true && search_followers_indicator == false && search_follwoing_indicator == false) {
                    filter(editable.toString());
                    /*filter_followers(editable.toString());
                    filter_following(editable.toString());*/
                } else if (search_all_indicator == false && search_followers_indicator == true && search_follwoing_indicator == false) {
//                    filter(editable.toString());
                    filter_followers(editable.toString());
//                    filter_following(editable.toString());
                } else if (search_all_indicator == false && search_followers_indicator == false && search_follwoing_indicator == true) {
//                    filter(editable.toString());
//                    filter_followers(editable.toString());
                    filter_following(editable.toString());
                    //  filter_followers(editable.toString());
                }
            }
        });

        // searchView.setOnQueryTextListener(this);










       /* edite_searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // filter(editable.toString());

//                android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(edite_searchbar);
                //  searchView.setOnQueryTextListener((android.support.v7.widget.SearchView.OnQueryTextListener) this);

                *//*
                arrayList_search_all.filter(editable.toString());
                search_followers_fragment.filter_followers(editable.toString());
                search_following_fragments.filter_following(editable.toString());
*//*
            }
        });*/
        search_all_tablayout = (LinearLayout) view.findViewById(R.id.search_all_layout);
        search_followers_tablayout = (LinearLayout) view.findViewById(R.id.search_followers_layout);
        search_following_tablayout = (LinearLayout) view.findViewById(R.id.search_following_layout);


        search_all_button = (Button) view.findViewById(R.id.search_all_Button);
        search_followers_button = (Button) view.findViewById(R.id.search_followers_Button);
        search_following_button = (Button) view.findViewById(R.id.search_following_Button);


        search_all_view = (View) view.findViewById(R.id.search_all_View);
        search_followers_view = (View) view.findViewById(R.id.search_followers_View);
        search_following_view = (View) view.findViewById(R.id.search_following_View);
      /*  if (savedInstanceState == null) {
            if (!search_all_fragments.isAdded()) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.search_fragment_framelayout, search_all_fragments);
                fragmentTransaction.commitAllowingStateLoss();
            }
        }*/


        recyclerView_navigation = (RecyclerView) view.findViewById(R.id.recyclerview_search);
        adapter_navigation = new Recycleradapter_Search_All_Fragment(arrayList_search_all, getActivity());
        layoutManager_navigation = new LinearLayoutManager(getContext());
        recyclerView_navigation.setLayoutManager(layoutManager_navigation);
        recyclerView_navigation.setHasFixedSize(true);
        recyclerView_navigation.setAdapter(adapter_navigation);

        if (arrayList_search_all.size() == 0) {
            for (int i = 0; i < profile_name_all.length; i++) {
                arrayList_search_all.add(new Model_Search_ALL(profile_name_all[i], number_invite_all[i], text_follow_all[i], profile_image_all[i], follow_background_all[i]));
            }
        }
        adapter_navigation.SsetClickListener_NavigationDrawer(new Recycleradapter_Search_All_Fragment.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), " ALL " + Pos, Toast.LENGTH_SHORT).show();
            }
        });
        search_all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                edite_searchbar.setText("");

            /*    FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.search_fragment_framelayout, search_all_fragments);
                fragmentTransaction.commit();*/
                search_all_indicator = true;
                search_followers_indicator = false;
                search_follwoing_indicator = false;
                edittext_searchbar.setText("");
                adapter_navigation = new Recycleradapter_Search_All_Fragment(arrayList_search_all, getActivity());
                layoutManager_navigation = new LinearLayoutManager(getContext());
                recyclerView_navigation.setLayoutManager(layoutManager_navigation);
                recyclerView_navigation.setHasFixedSize(true);
                recyclerView_navigation.setAdapter(adapter_navigation);
                if (arrayList_search_all.size() == 0) {
                    for (int i = 0; i < profile_name_all.length; i++) {
                        arrayList_search_all.add(new Model_Search_ALL(profile_name_all[i], number_invite_all[i], text_follow_all[i], profile_image_all[i], follow_background_all[i]));
                    }
                }
                adapter_navigation.SsetClickListener_NavigationDrawer(new Recycleradapter_Search_All_Fragment.ItemClickListener() {
                    @Override
                    public void OnItemClick(int Pos) {
                        Toast.makeText(getContext(), " ALL " + Pos, Toast.LENGTH_SHORT).show();
                    }
                });

                search_all_button.setTextColor(getResources().getColor(R.color.login_button_blue));
                search_followers_button.setTextColor(getResources().getColor(R.color.light_grey));
                search_following_button.setTextColor(getResources().getColor(R.color.light_grey));

                search_all_view.setVisibility(View.VISIBLE);
                search_followers_view.setVisibility(View.INVISIBLE);
                search_following_view.setVisibility(View.INVISIBLE);
            }
        });
        search_followers_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_all_indicator = false;
                search_followers_indicator = true;
                search_follwoing_indicator = false;
                edittext_searchbar.setText("");
               /* FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.search_fragment_framelayout, search_followers_fragment);
                fragmentTransaction.commit();*/
                adapter_navigation = new Recycleradapter_Search_All_Fragment(arrayList_search_followers, getActivity());
                layoutManager_navigation = new LinearLayoutManager(getContext());
                recyclerView_navigation.setLayoutManager(layoutManager_navigation);
                recyclerView_navigation.setHasFixedSize(true);
                recyclerView_navigation.setAdapter(adapter_navigation);
                if (arrayList_search_followers.size() == 0) {
                    for (int i = 0; i < profile_name_followers.length; i++) {
                        arrayList_search_followers.add(new Model_Search_ALL(profile_name_followers[i], number_invite_followers[i], text_follow_followers[i], profile_image_followers[i], follow_background_followers[i]));
                    }
                }
                adapter_navigation.SsetClickListener_NavigationDrawer(new Recycleradapter_Search_All_Fragment.ItemClickListener() {
                    @Override
                    public void OnItemClick(int Pos) {
                        Toast.makeText(getContext(), " Followers " + Pos, Toast.LENGTH_SHORT).show();
                    }
                });
                search_all_button.setTextColor(getResources().getColor(R.color.light_grey));
                search_followers_button.setTextColor(getResources().getColor(R.color.login_button_blue));
                search_following_button.setTextColor(getResources().getColor(R.color.light_grey));

                // edite_searchbar.setText("");

                Log.d("followers", "followers LAyout");
                search_all_view.setVisibility(View.INVISIBLE);
                search_followers_view.setVisibility(View.VISIBLE);
                search_following_view.setVisibility(View.INVISIBLE);
            }
        });
        search_following_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_all_indicator = false;
                search_followers_indicator = false;
                search_follwoing_indicator = true;
                edittext_searchbar.setText("");
                /*FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.search_fragment_framelayout, search_following_fragments);
                fragmentTransaction.commit();*/
                // edite_searchbar.setText("");
                adapter_navigation = new Recycleradapter_Search_All_Fragment(arrayList_search_following, getActivity());
                layoutManager_navigation = new LinearLayoutManager(getContext());
                recyclerView_navigation.setLayoutManager(layoutManager_navigation);
                recyclerView_navigation.setHasFixedSize(true);
                recyclerView_navigation.setAdapter(adapter_navigation);
                if (arrayList_search_following.size() == 0) {
                    for (int i = 0; i < profile_name_following.length; i++) {
                        arrayList_search_following.add(new Model_Search_ALL(profile_name_following[i], number_invite_following[i], text_follow_following[i], profile_image_following[i], follow_background_following[i]));
                    }
                }
                adapter_navigation.SsetClickListener_NavigationDrawer(new Recycleradapter_Search_All_Fragment.ItemClickListener() {
                    @Override
                    public void OnItemClick(int Pos) {
                        Toast.makeText(getContext(), " Following " + Pos, Toast.LENGTH_SHORT).show();
                    }
                });
                search_all_button.setTextColor(getResources().getColor(R.color.light_grey));
                search_followers_button.setTextColor(getResources().getColor(R.color.light_grey));
                search_following_button.setTextColor(getResources().getColor(R.color.login_button_blue));


                Log.d("Following", "folllowing LAyout");
                search_all_view.setVisibility(View.INVISIBLE);
                search_followers_view.setVisibility(View.INVISIBLE);
                search_following_view.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

  /*  @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        s = s.toLowerCase();
        ArrayList<Model_Search_ALL> newList = new ArrayList<>();
        for (Model_Search_ALL temp : arrayList_search_all) {
            String name = temp.getProfile_name().toLowerCase();
            if (name.contains(s)) {
                newList.add(temp);
            }

        }
        adapter_navigation.setFilter(newList);


        return true;
    }
*/




   /* @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        getMenuInflater().inflate(R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener((android.support.v7.widget.SearchView.OnQueryTextListener) this);

        return true;

    }*/


    public void filter(String s) {
        s = s.toLowerCase();
        ArrayList<Model_Search_ALL> newList = new ArrayList<>();
        for (Model_Search_ALL temp : arrayList_search_all) {
            String name = temp.getProfile_name().toLowerCase();
            if (name.contains(s)) {
                newList.add(temp);
            }
        }
        if (adapter_navigation != null) {
            adapter_navigation.setFilter(newList);
        } else {
            Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
        }
        //  filter(s);
    }

    public void filter_followers(String s) {
        s = s.toLowerCase();
        ArrayList<Model_Search_ALL> newList = new ArrayList<>();
        for (Model_Search_ALL temp : arrayList_search_followers) {
            String name = temp.getProfile_name().toLowerCase();
            if (name.contains(s)) {
                newList.add(temp);
            }
        }
        if (adapter_navigation != null) {
            adapter_navigation.setFilter(newList);
        } else {
            Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
        }
        //  filter(s);
    }

    public void filter_following(String s) {
        s = s.toLowerCase();
        ArrayList<Model_Search_ALL> newList = new ArrayList<>();
        for (Model_Search_ALL temp : arrayList_search_following) {
            String name = temp.getProfile_name().toLowerCase();
            if (name.contains(s)) {
                newList.add(temp);
            }
        }
        if (adapter_navigation != null) {
            adapter_navigation.setFilter(newList);
        } else {
            Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
            Log.d("norecord", "norecord");
        }
        //  filter(s);
    }




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
    }*/

    /*public void SsetClickListener_NavigationDrawer(OnFragmentInteractionListener listener) {
        this.mListener = listener;
    }*/

    public interface OnFragmentInteractionListener {

    }
}
