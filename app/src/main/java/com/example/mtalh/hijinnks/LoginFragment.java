package com.example.mtalh.hijinnks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.pixplicity.easyprefs.library.Prefs;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;


public class LoginFragment extends Fragment {

    LoginButton loginButton;
    TwitterLoginButton twitterLoginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    final String[] id = new String[1];
    Button login_Button;
    ImageView facebook_button, twitter_button;
    String imageURL;
    String Compelete_information;
    EditText username;
    TextView connectwith;
    private boolean firstLaunch = false;


    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Twitter.initialize(getContext());
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        login_Button = view.findViewById(R.id.login_Button);
        username = (EditText) view.findViewById(R.id.username);
        connectwith = (TextView) view.findViewById(R.id.connectwith);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        facebook_button = (ImageView) view.findViewById(R.id.facebook_button);
        twitterLoginButton = (TwitterLoginButton) view.findViewById(R.id.login_button_twitter);
        twitter_button = (ImageView) view.findViewById(R.id.twitter_button);


       /* if (Prefs.getString("pref", "") != "") {
            Log.d("PREFRENCES", Prefs.getString("pref", ""));
            startActivity(new Intent(getActivity(), Home.class));
        } else {
          // startActivity(new Intent(getActivity(), Login_SignUp.class));
        }*/

        FacebookSdk.sdkInitialize(getContext());
        callbackManager = CallbackManager.Factory.create();


        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "public_profile");
        loginButton.setReadPermissions(permissionNeeds);


        facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });
        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(getActivity(), Home.class);
                startActivity(homeActivity);

            }
        });

        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        System.out.println("onSuccess");

                        String accessToken = loginResult.getAccessToken().getToken();
                        Log.i("accessToken", accessToken);

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.i("LoginActivity", response.toString());
                                        try {
                                            id[0] = object.getString("id");
                                            try {
                                                URL profile_pic = new URL("http://graph.facebook.com/" + id[0] + "/picture?type=large");
//                                                Log.i("profile_pic",
//                                                        profile_pic + "");textView.setText(profile.getName() + "  " + profile.getId());
//                                                String url = profile_pic;//profile.getProfilePictureUri(150, 150).toString();
                                                //   Glide.with(getContext()).load("http://graph.facebook.com/" + id[0] + "/picture?type=large").into((imageView));

                                            } catch (MalformedURLException e) {
                                                e.printStackTrace();
                                            }

                                            final String name = object.getString("name");

                                            // Compelete_information =  name;

                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Compelete_information = name;
                                                }
                                            });
                                            Prefs.putString("pref", Compelete_information);
                                            Prefs.putString("pref_image", "http://graph.facebook.com/" + id[0] + "/picture?type=large");
                                            getActivity().finish();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(new Intent(getActivity(), Home.class));
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields",
                                "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        System.out.println("onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("onError");
                    }

                });


        twitter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twitterLoginButton.performClick();
            }
        });


        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                final String username = result.data.getUserName();
                Call<User> user = TwitterCore.getInstance().getApiClient().getAccountService().verifyCredentials(true, false, true);
                user.enqueue(new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        User userInfo = result.data;
                        String email = userInfo.email;
                        String description = userInfo.description;
                        String location = userInfo.location;
                        String timezone = userInfo.timeZone;
                        String status = String.valueOf(userInfo.status);
                        String id = String.valueOf(userInfo.id);
                        imageURL = userInfo.profileImageUrl.replace("_normal", "");
                        int freindsCount = userInfo.friendsCount;
                        int favouritCount = userInfo.favouritesCount;
                        int followersCount = userInfo.followersCount;

                        //  Picasso.with(getContext()).load(imageURL).into(imageview);

                        StringBuilder sb = new StringBuilder();
                        sb.append(username);
                      /*  sb.append("\n");
                        sb.append("email:- " + email);
                        sb.append("\n");
                        sb.append("timezone:- " + timezone);
                        sb.append("\n");
                        sb.append("status:- " + status);
                        sb.append("\n");
                        sb.append("description:- " + description);
                        sb.append("\n");
                        sb.append("id:- " + id);
                        sb.append("\n");
                        sb.append("location:- " + location);
                        sb.append("\n");
                        sb.append("freindsCount:- " + freindsCount);
                        sb.append("\n");
                        sb.append("favouritCount:- " + favouritCount);
                        sb.append("\n");
                        sb.append("followersCount:- " + followersCount);
                        sb.append("\n");*/


                        Prefs.putString("pref", sb.toString());
                        Prefs.putString("pref_image", imageURL);
                        startActivity(new Intent(getActivity(), Home.class));
                        Toast.makeText(getContext(), sb.toString(), Toast.LENGTH_LONG).show();
                        getActivity().finish();
                    }

                    @Override
                    public void failure(TwitterException exception) {

                    }
                });


            }

            @Override
            public void failure(TwitterException exception) {
                Log.e("ERRORIN", exception.getMessage());
            }
        });

//getActivity().finish();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }





/*@Override
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
