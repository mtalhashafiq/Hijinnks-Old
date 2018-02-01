package com.example.mtalh.hijinnks;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

public class Login_SignUp extends AppCompatActivity {
    private static long sayBackPress;
    LinearLayout login_Layout, signUp_layout;
    Button login_Button, signup_Button;
    View login_View, signup_View;
    Fragment fragment;
    ImageView backbutton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login__sign_up);


        if (!Prefs.getString("pref", "").equals("")) {
            Log.d("PREFRENCES", Prefs.getString("pref", ""));
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        } else {
            //  startActivity(new Intent(getApplicationContext(), Login_SignUp.class));
        }











      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        }*/

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        login_Layout = (LinearLayout) findViewById(R.id.login_layout);
        login_Button = (Button) findViewById(R.id.login_Button);
        login_View = (View) findViewById(R.id.login_Button_View);
        signUp_layout = (LinearLayout) findViewById(R.id.signup_layout);
        signup_Button = (Button) findViewById(R.id.signup_Button);
        signup_View = (View) findViewById(R.id.signup_Button_View);


        if (savedInstanceState == null) {
            fragment = new LoginFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.login_signup_framelayout, fragment);
            fragmentTransaction.commit();

        }

      /*  backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comment = new Intent(Login_SignUp.this, Comments.class);
                startActivity(comment);
            }
        });*/
        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.login_signup_framelayout, new LoginFragment());
                fragmentTransaction.commit();
                Toast.makeText(Login_SignUp.this, "Login Layout", Toast.LENGTH_SHORT).show();
                login_View.setVisibility(View.VISIBLE);
                signup_View.setVisibility(View.INVISIBLE);
                login_Button.setTextColor(getResources().getColor(R.color.white));
                signup_Button.setTextColor(getResources().getColor(R.color.login_signup_unselect));

            }
        });

        signup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.login_signup_framelayout, new SignUpFragment());
                fragmentTransaction.commit();
                Toast.makeText(Login_SignUp.this, "Signup Layout", Toast.LENGTH_SHORT).show();
                login_View.setVisibility(View.INVISIBLE);
                signup_View.setVisibility(View.VISIBLE);
                login_Button.setTextColor(getResources().getColor(R.color.login_signup_unselect));
                signup_Button.setTextColor(getResources().getColor(R.color.white));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                fragment.onActivityResult(requestCode, resultCode, data);
                Log.d("Activity", "ON RESULT CALLED");
            }
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }
    }

    @Override
    public void onBackPressed() {
        if (sayBackPress + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);

        } else {
            Toast.makeText(getApplicationContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
            sayBackPress = System.currentTimeMillis();
        }
    }


}
