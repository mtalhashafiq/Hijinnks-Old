package com.example.mtalh.hijinnks;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class addevent_tab_2_fragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView_navigation;
    private Recycleradapter_AddEvent_Location adapter_navigation;
    private RecyclerView.LayoutManager layoutManager_navigation;
    String[] name = {"Feed", "RSVP'd Events", "Favourites", "Message", "Followers", "Following"};

    public addevent_tab_2_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addevent_tab_2, container, false);

        recyclerView_navigation = (RecyclerView) view.findViewById(R.id.recyclerview_addevent_location_intrest);
        adapter_navigation = new Recycleradapter_AddEvent_Location(name, getActivity());
        layoutManager_navigation = new LinearLayoutManager(getContext());
        recyclerView_navigation.setLayoutManager(layoutManager_navigation);
        recyclerView_navigation.setHasFixedSize(true);
        recyclerView_navigation.setAdapter(adapter_navigation);
        adapter_navigation.SsetClickListener_NavigationDrawer(new Recycleradapter_AddEvent_Location.ItemClickListener() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(getContext(), "" + Pos, Toast.LENGTH_SHORT).show();
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
    }
*/

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
