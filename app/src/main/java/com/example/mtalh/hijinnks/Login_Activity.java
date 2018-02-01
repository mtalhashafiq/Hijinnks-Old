package com.example.mtalh.hijinnks;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

public class Login_Activity extends AppCompatActivity {

    final String[] id = new String[1];
    LoginButton loginButton;
    TwitterLoginButton twitterLoginButton;
    ImageView facebook_button, twitter_button;
    String imageURL;
    String Compelete_information;
    EditText username;
    TextView connectwith;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(getApplicationContext());
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_login_);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        username = (EditText) findViewById(R.id.username);
        connectwith = (TextView)findViewById(R.id.connectwith);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        facebook_button = (ImageView) findViewById(R.id.facebook_button);
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.login_button_twitter);
        twitter_button = (ImageView) findViewById(R.id.twitter_button);


       /* if (Prefs.getString("pref", "") != "") {
            Log.d("PREFRENCES", Prefs.getString("pref", ""));
            startActivity(new Intent(getActivity(), Home.class));
        } else {
          // startActivity(new Intent(getActivity(), Login_SignUp.class));
        }*/
        if (!Prefs.getString("pref", "").equals("")) {
            Log.d("PREFRENCES", Prefs.getString("pref", ""));
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        } else {
            //  startActivity(new Intent(getApplicationContext(), Login_SignUp.class));
        }
        FacebookSdk.sdkInitialize(Login_Activity.this);
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

                                            Login_Activity.this.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Compelete_information = name;
                                                }
                                            });
                                            Prefs.putString("pref", Compelete_information);
                                            Prefs.putString("pref_image", "http://graph.facebook.com/" + id[0] + "/picture?type=large");
                                            Login_Activity.this.finish();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(new Intent(Login_Activity.this, Home.class));
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
                        startActivity(new Intent(Login_Activity.this, Home.class));
                        Toast.makeText(Login_Activity.this, sb.toString(), Toast.LENGTH_LONG).show();
                        Login_Activity.this.finish();
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
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }
}
