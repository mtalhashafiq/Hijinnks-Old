package com.example.mtalh.hijinnks;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;


@SuppressLint("ValidFragment")
public class Setting_Fragment extends Fragment {
    ImageView back_image;
    HomeFragmentInterface homeFragmentInterface ;
    LinearLayout account_tv,settings_tv,connect_account_tv;
    LinearLayout  change_password_tv, liked_events_tv, intrests_tv;
    LinearLayout  private_account_tv,push_notification_tv,connect_facebook_linear, connect_twitter_linear;
    LinearLayout setting_fragment_linearlayout;
    float divide_value, divide_value1;
    ScrollView settings_scrolls;
    private OnFragmentInteractionListener mListener;

    public Setting_Fragment(HomeFragmentInterface homeFragmentInterface) {
        // Required empty public constructor
        this.homeFragmentInterface = homeFragmentInterface;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.parseColor("#1f4ba4"));
        }
        setting_fragment_linearlayout=(LinearLayout)view.findViewById(R.id.setting_fragment_linearlayout);
        settings_scrolls=(ScrollView)view.findViewById(R.id.settings_scrollview);
        back_image = (ImageView) view.findViewById(R.id.back_image);
        account_tv = (LinearLayout) view.findViewById(R.id.account_heading_textview);
        change_password_tv = (LinearLayout) view.findViewById(R.id.change_password_textview);
        private_account_tv = (LinearLayout) view.findViewById(R.id.private_account_textview);
        settings_tv = (LinearLayout) view.findViewById(R.id.settings_heading_textview);
        liked_events_tv = (LinearLayout) view.findViewById(R.id.liked_events_textview);
        push_notification_tv = (LinearLayout) view.findViewById(R.id.push_notification_textview);
        intrests_tv = (LinearLayout) view.findViewById(R.id.intrests_textview);
        connect_account_tv = (LinearLayout) view.findViewById(R.id.connect_account_textview);
        connect_facebook_linear = (LinearLayout) view.findViewById(R.id.connect_facebook_linearlayout);
        connect_twitter_linear = (LinearLayout) view.findViewById(R.id.connect_twitter_linearlayout);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                divide_value = setting_fragment_linearlayout.getMeasuredHeight();
                divide_value1 = divide_value / 11;
                float xyz =divide_value1;

                divide_function(account_tv, xyz);
                divide_function(change_password_tv, xyz);
                divide_function(private_account_tv, xyz);
                divide_function(settings_tv, xyz);
                divide_function(liked_events_tv, xyz);
                divide_function(push_notification_tv, xyz);
                divide_function(intrests_tv, xyz);
                divide_function(connect_account_tv, xyz);
                divide_function(connect_facebook_linear, xyz);
                divide_function(connect_twitter_linear, xyz);

              /*  progress.setVisibility(View.GONE);
                scroll.setVisibility(View.VISIBLE);*/
                settings_scrolls.setSmoothScrollingEnabled(true);

            }
        }, 100);


        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.home_framelayout, new Edit_ProfileFragment());
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    private void divide_function(View v, float value) {

        if (v instanceof LinearLayout) {
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) v.getLayoutParams();
            param.height = (int) value;
            v.setLayoutParams(param);
        } else if (v instanceof RelativeLayout) {
            LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) v.getLayoutParams();
            param.height = (int) value;
            v.setLayoutParams(param);
        }
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
